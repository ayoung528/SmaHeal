<template>
  <div align="center">
      <v-container>
        <v-row>
          <v-col class="d-flex justify-content-center">
            <audio class="col-xl-6 col-lg-6 col-md-8 col-12" id="audio"></audio>
            <audio class="col-xl-6 col-lg-6 col-md-8 col-12" id="convert" controls></audio>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <button v-if="!currentlyRecording" @click="recordFromMicrophone">녹음 시작</button>
            <button v-else ref="stop" @click="stop">중지</button>
          </v-col>
        </v-row>
        <v-row v-if="bufferIs">
          <v-col>
            <button class="col-xl-4 col-lg-4 col-md-4 col-6" @click="loadTransform(0)">비브라토</button>
            <button class="col-xl-4 col-lg-4 col-md-4 col-6" @click="loadTransform(1)">메가폰</button>
            <button class="col-xl-4 col-lg-4 col-md-4 col-6" @click="loadTransform(2)">느리게</button>
            <button class="col-xl-4 col-lg-4 col-md-4 col-6" @click="loadTransform(3)">로꾸꺼</button>
            <button class="col-xl-4 col-lg-4 col-md-4 col-6" @click="loadTransform(4)">외계생명체</button>
            <button class="col-xl-4 col-lg-4 col-md-4 col-6" @click="loadTransform(5)">autowah</button>
            <br />
          </v-col>
        </v-row>
      </v-container>
  </div>
</template>

<script>
import bufferToWav from "audiobuffer-to-wav";

export default {
  name: "VoiceComp",
  data() {
    return {
      currentlyRecording: false,
      globalAudioBuffer: null,
      bufferIs: false
    };
  },
  created() {},
  methods: {
    async recordFromMicrophone() {
      if (this.currentlyRecording) {
        return;
      }
      this.currentlyRecording = true;

      let chunks = [];
      let stream = await navigator.mediaDevices.getUserMedia({
        audio: true,
        video: false
      });
      let mediaRecorder = new MediaRecorder(stream, { mimeType: "video/webm" });

      mediaRecorder.start();

      mediaRecorder.ondataavailable = function(e) {
        chunks.push(e.data);
      };

      let stopFn = function() {
        if (mediaRecorder.state != "inactive") {
          mediaRecorder.stop();
        }
      };

      this.$refs.stop.addEventListener("click", stopFn);

      mediaRecorder.onstop = async function() {
        let blob = new Blob(chunks, { type: mediaRecorder.mimeType });
        let audioURL = window.URL.createObjectURL(blob);

        let audio = document.getElementById("audio");
        audio.src = audioURL;
      };
    },
    stop() {
      this.currentlyRecording = false;
      this.bufferIs = true;
    },

    async loadTransform(num) {
      let audio = document.getElementById("audio");
      let arrayBuffer = await (await fetch(audio.src)).arrayBuffer();

      this.globalAudioBuffer = await new AudioContext().decodeAudioData(
        arrayBuffer
      );

      let outputAudioBuffer = null;

      switch (num) {
        case 0:
          outputAudioBuffer = this.vibratoTransform(this.globalAudioBuffer);
          break;

        case 1:
          outputAudioBuffer = this.megaphoneTransform(this.globalAudioBuffer);
          break;

        case 2:
          outputAudioBuffer = this.slowWobbleTransform(this.globalAudioBuffer);
          break;

        case 3:
          outputAudioBuffer = this.reverseTimeTransform(this.globalAudioBuffer);
          break;

        case 4:
          outputAudioBuffer = this.alienRobot1Transform(this.globalAudioBuffer);
          break;

        case 5:
          outputAudioBuffer = this.autowahTransform(this.globalAudioBuffer);
          break;

        default:
          break;
      }

      if (outputAudioBuffer != null) {
        outputAudioBuffer.then(function(result) {
          var anchor = document.getElementById("convert");

          var wav = bufferToWav(result);
          var blob = new window.Blob([new DataView(wav)], {
            type: "audio/wav"
          });

          var url = window.URL.createObjectURL(blob);
          anchor.src = url;
          anchor.play();
        });
      }
    },

    async megaphoneTransform(audioBuffer) {
      let ctx = new OfflineAudioContext(
        audioBuffer.numberOfChannels,
        audioBuffer.length,
        audioBuffer.sampleRate
      );
      let source = ctx.createBufferSource();
      source.buffer = audioBuffer;

      // Wave shaper
      let waveShaper = ctx.createWaveShaper();
      waveShaper.curve = makeDistortionCurve(30);
      function makeDistortionCurve(amount) {
        var k = typeof amount === "number" ? amount : 50;
        var n_samples = 44100;
        var curve = new Float32Array(n_samples);
        var deg = Math.PI / 180;
        var x;
        for (let i = 0; i < n_samples; ++i) {
          x = (i * 2) / n_samples - 1;
          curve[i] = ((3 + k) * x * 20 * deg) / (Math.PI + k * Math.abs(x));
        }
        return curve;
      }

      let lpf1 = ctx.createBiquadFilter();
      lpf1.type = "lowpass";
      lpf1.frequency.value = 2000.0;
      let lpf2 = ctx.createBiquadFilter();
      lpf2.type = "lowpass";
      lpf2.frequency.value = 2000.0;
      let hpf1 = ctx.createBiquadFilter();
      hpf1.type = "highpass";
      hpf1.frequency.value = 500.0;
      let hpf2 = ctx.createBiquadFilter();
      hpf2.type = "highpass";
      hpf2.frequency.value = 500.0;
      let compressor = ctx.createDynamicsCompressor();
      lpf1.connect(lpf2);
      lpf2.connect(hpf1);
      hpf1.connect(hpf2);
      hpf2.connect(waveShaper);
      waveShaper.connect(compressor);
      compressor.connect(ctx.destination);

      source.connect(lpf1);

      source.start(0);
      return await ctx.startRendering();
    },

    async vibratoTransform(audioBuffer) {
      let ctx = new OfflineAudioContext(
        audioBuffer.numberOfChannels,
        audioBuffer.length,
        audioBuffer.sampleRate
      );

      // Input
      let inputNode = ctx.createBufferSource();
      inputNode.buffer = audioBuffer;

      // Delay
      let delayNode = ctx.createDelay();
      delayNode.delayTime.value = 0.03;

      let osc = ctx.createOscillator();
      let gain = ctx.createGain();
      let wetGain = ctx.createGain();

      gain.gain.value = 0.002; // depth of change to the delay:

      osc.type = "sine";
      osc.frequency.value = 4.5;

      osc.connect(gain);
      gain.connect(delayNode.delayTime);
      inputNode.connect(delayNode);
      delayNode.connect(wetGain);
      wetGain.connect(ctx.destination);

      osc.start(0);
      inputNode.start(0);
      let outputAudioBuffer = await ctx.startRendering();
      return outputAudioBuffer;
    },

    async slowWobbleTransform(audioBuffer) {
      let ctx = new OfflineAudioContext(
        audioBuffer.numberOfChannels,
        audioBuffer.length,
        audioBuffer.sampleRate
      );

      let source = ctx.createBufferSource();
      source.buffer = audioBuffer;

      let oscillator = ctx.createOscillator();
      oscillator.frequency.value = 1;
      oscillator.type = "sine";

      let oscillatorGain = ctx.createGain();
      oscillatorGain.gain.value = 0.05;

      let delay = ctx.createDelay();
      delay.delayTime.value = 0.05;

      // source --> delay --> ctx.destination
      // oscillator --> oscillatorGain --> delay.delayTime --> ctx.destination

      source.connect(delay);
      delay.connect(ctx.destination);

      oscillator.connect(oscillatorGain);
      oscillatorGain.connect(delay.delayTime);

      oscillator.start();
      source.start();

      let outputAudioBuffer = await ctx.startRendering();
      return outputAudioBuffer;
    },

    async reverseTimeTransform(audioBuffer) {
      let ctx = new AudioContext();

      // copy audiobuffer
      let outputAudioBuffer = ctx.createBuffer(
        audioBuffer.numberOfChannels,
        audioBuffer.length,
        audioBuffer.sampleRate
      );
      for (let i = 0; i < audioBuffer.numberOfChannels; i++) {
        outputAudioBuffer.copyToChannel(audioBuffer.getChannelData(i), i);
      }

      // reverse new audiobuffer
      for (let i = 0; i < outputAudioBuffer.numberOfChannels; i++) {
        outputAudioBuffer.getChannelData(i).reverse();
      }

      return outputAudioBuffer;
    },

    async alienRobot1Transform(audioBuffer) {
      let ctx = new OfflineAudioContext(
        audioBuffer.numberOfChannels,
        audioBuffer.length,
        audioBuffer.sampleRate
      );

      let source = ctx.createBufferSource();
      source.buffer = audioBuffer;

      let oscillator = ctx.createOscillator();
      oscillator.frequency.value = 40;
      oscillator.type = "sine";

      let oscillatorGain = ctx.createGain();
      oscillatorGain.gain.value = 0.015;

      let delay = ctx.createDelay();
      delay.delayTime.value = 0.05;

      // source --> delay --> ctx.destination
      // oscillator --> oscillatorGain --> delay.delayTime --> ctx.destination

      source.connect(delay);
      delay.connect(ctx.destination);

      oscillator.connect(oscillatorGain);
      oscillatorGain.connect(delay.delayTime);

      oscillator.start();
      source.start();

      let outputAudioBuffer = await ctx.startRendering();
      return outputAudioBuffer;
    },
    async autowahTransform(audioBuffer) {
      let ctx = new OfflineAudioContext(
        audioBuffer.numberOfChannels,
        audioBuffer.length,
        audioBuffer.sampleRate
      );

      let inputNode = ctx.createBufferSource();
      inputNode.buffer = audioBuffer;

      let waveshaper = ctx.createWaveShaper();
      let awFollower = ctx.createBiquadFilter();
      awFollower.type = "lowpass";
      awFollower.frequency.value = 10.0;

      let curve = new Float32Array(65536);
      for (let i = -32768; i < 32768; i++) {
        curve[i + 32768] = (i > 0 ? i : -i) / 32768;
      }
      waveshaper.curve = curve;
      waveshaper.connect(awFollower);

      let wetGain = ctx.createGain();
      wetGain.gain.value = 1;

      let compressor = ctx.createDynamicsCompressor();
      compressor.threshold.value = -20;
      compressor.ratio.value = 16;

      let awDepth = ctx.createGain();
      awDepth.gain.value = 11585;
      awFollower.connect(awDepth);

      let awFilter = ctx.createBiquadFilter();
      awFilter.type = "lowpass";
      awFilter.Q.value = 15;
      awFilter.frequency.value = 50;
      awDepth.connect(awFilter.frequency);
      awFilter.connect(wetGain);

      inputNode.connect(waveshaper);
      inputNode.connect(awFilter);

      waveshaper.connect(compressor);
      wetGain.connect(compressor);
      compressor.connect(ctx.destination);

      inputNode.start(0);
      let outputAudioBuffer = await ctx.startRendering();
      return outputAudioBuffer;
    }
  }
};
</script>

<style>
</style>