<template>
  <div class="col-12 p-0">
    <div class="sc_header_img d-none d-md-block"></div>
    <div style="height:5rem;" class="d-block d-md-none"></div>
    <div class="d-flex justify-content-center">
      <div class="container col-11 " style="height:2300px;">
        <!-- stepper -->
        <v-row>
          <v-col cols="12">
            <v-stepper v-model="e1">
              <v-stepper-header>
                <v-stepper-step :complete="e1 > 1" step="1" color="#356859"
                  >step 1</v-stepper-step
                >
                <v-divider></v-divider>
                <v-stepper-step :complete="e1 > 2" step="2" color="#356859"
                  >step 2</v-stepper-step
                >
                <v-divider></v-divider>
                <v-stepper-step :complete="e1 > 3" step="3" color="#356859"
                  >step 3</v-stepper-step
                >
                <v-divider></v-divider>
                <v-stepper-step :complete="e1 > 4" step="4" color="#356859"
                  >step 4</v-stepper-step
                >
                <v-divider></v-divider>
                <v-stepper-step :complete="e1 > 5" step="5" color="#356859"
                  >step 5</v-stepper-step
                >
                <v-divider></v-divider>
                <v-stepper-step step="6" color="#356859">step 6</v-stepper-step>
              </v-stepper-header>

              <v-stepper-items>
                <v-stepper-content step="1">
                  <v-card class="mb-8" height="400px" style="overflow-x:hidden">
                    <v-row>
                      <v-col
                        class="col-xl-7 col-lg-7 col-md-7 col-sm-12 col-12"
                      >
                        <img
                          src="../../../public/images/step1.gif"
                          height="90%"
                          width="100%"
                          max-height="80%"
                        />
                      </v-col>
                      <v-col
                        class="col-xl-5 col-lg-5 col-md-5 col-sm-12 col-12 p-3"
                        style="letter-spacing:1px;word-spacing:3px;line-height:250%;"
                      >
                        <h3 style="font-weight:bold;margin-bottom:30px;">
                          <v-icon large color="#356859"
                            >mdi-account-question-outline</v-icon
                          >
                          스마힐 사용법에 대해 알아볼까요?
                        </h3>
                        <h4 style="font-weight:bold;margin-bottom:30px;">
                          웃음을 기부하는 방법은 크게 3가지가 있어요.
                        </h4>
                        <ol style="font-size:1.1rem;">
                          <li>재밌는 컨텐츠를 즐기며 웃는다.</li>
                          <li>셀카모드로 직접 웃는 사진을 캡쳐한다.</li>
                          <li>웃는 사진을 업로드하여 기부한다.</li>
                          <li>원하는대로 Start~!!</li>
                        </ol>
                      </v-col>
                    </v-row>
                  </v-card>
                  <v-btn color="#356859" dark @click="e1 = 2">Continue</v-btn>
                </v-stepper-content>

                <v-stepper-content step="2">
                  <v-card class="mb-8" height="400px" style="overflow-x:hidden">
                    <v-row>
                      <v-col
                        class="col-xl-7 col-lg-7 col-md-7 col-sm-12 col-12"
                      >
                        <img
                          src="../../../public/images/step2.gif"
                          height="90%"
                          width="100%"
                          max-height="80%"
                        />
                      </v-col>
                      <v-col
                        class="col-xl-5 col-lg-5 col-md-5 col-sm-12 col-12 p-3"
                        style="letter-spacing:1px;word-spacing:3px;line-height:220%;"
                      >
                        <h3 style="font-weight:bold;margin-bottom:30px;">
                          <v-icon large color="#356859" class="mr-3"
                            >mdi-gamepad-variant-outline</v-icon
                          >다양한 컨텐츠
                        </h3>
                        <h4 style="font-weight:bold;margin-bottom:30px;">
                          웃음을 주는 컨텐츠는 4가지가 있어요.
                        </h4>
                        <ol style="font-size:1.1rem;">
                          <li>재밌는 사진</li>
                          <li>연령에 맞게 랜덤으로 나오는 동영상</li>
                          <li>아이들을 위한 영상 컨텐츠</li>
                          <li>
                            목소리 변조기능
                            <ul>
                              <li>"녹음 시작"을 눌러서 목소리 녹음</li>
                              <li>"중지"하여 녹음 종료</li>
                              <li>다양하게 변조된 목소리를 들어보세요</li>
                            </ul>
                          </li>
                        </ol>
                      </v-col>
                    </v-row>
                  </v-card>
                  <v-btn color="#356859" dark @click="e1 = 3">Continue</v-btn>
                  <v-btn
                    color="#356859"
                    dark
                    class="font-weight-bold"
                    text
                    @click="e1 = 1"
                    >Back</v-btn
                  >
                </v-stepper-content>

                <v-stepper-content step="3">
                  <v-card class="mb-8" height="400px" style="overflow-x:hidden">
                    <v-row>
                      <v-col
                        class="col-xl-7 col-lg-7 col-md-7 col-sm-12 col-12"
                      >
                        <img
                          src="../../../public/images/step3.gif"
                          height="90%"
                          width="100%"
                          max-height="80%"
                        />
                      </v-col>
                      <v-col
                        class="col-xl-5 col-lg-5 col-md-5 col-sm-12 col-12 p-3"
                        style="letter-spacing:1px;word-spacing:3px;line-height:250%;"
                      >
                        <h3 style="font-weight:bold;margin-bottom:30px;">
                          <v-icon large color="#356859" class="mr-3"
                            >mdi-camera-wireless-outline</v-icon
                          >자동캡쳐
                        </h3>
                        <h4 style="font-weight:bold;margin-bottom:30px;">
                          컨텐츠 모드는 웃으면 자동캡쳐가 됩니다.
                        </h4>
                        <ol style="font-size:1.1rem;">
                          <li>캡쳐된 사진이 보여요.</li>
                          <li>다시 찍거나 사진을 확정해주세요.</li>
                          <li>
                            웃음왕 참가 여부를 체크해주세요.(사진 사용 허용)
                          </li>
                          <li>
                            한 줄의 희망메세지를 작성합니다.
                            <ul>
                              <li>비속어를 사용하면 기부가 제한됩니다.</li>
                            </ul>
                          </li>
                          <li>다 작성했으면 기부를 할 수 있어요.</li>
                        </ol>
                      </v-col>
                    </v-row>
                  </v-card>
                  <v-btn color="#356859" dark @click="e1 = 4">Continue</v-btn>
                  <v-btn
                    color="#356859"
                    dark
                    class="font-weight-bold"
                    text
                    @click="e1 = 1"
                    >Back</v-btn
                  >
                </v-stepper-content>

                <v-stepper-content step="4">
                  <v-card class="mb-8" height="400px" style="overflow-x:hidden">
                    <v-row>
                      <v-col
                        class="col-xl-7 col-lg-7 col-md-7 col-sm-12 col-12"
                      >
                        <img
                          src="../../../public/images/step4.gif"
                          height="90%"
                          width="100%"
                          max-height="80%"
                        />
                      </v-col>
                      <v-col
                        class="col-xl-5 col-lg-5 col-md-5 col-sm-12 col-12 p-3"
                        style="letter-spacing:1px;word-spacing:3px;line-height:220%;"
                      >
                        <h3 style="font-weight:bold;margin-bottom:30px;">
                          <v-icon large color="#356859" class="mr-3"
                            >mdi-webcam</v-icon
                          >셀카모드
                        </h3>
                        <h4 style="font-weight:bold;margin-bottom:30px;">
                          셀카 모드를 알아볼게요.
                        </h4>
                        <ol style="font-size:1.1rem;">
                          <li>"START"를 눌러 웹캠을 시작합니다.</li>
                          <li>"CAPTURE"를 눌러 사진을 캡쳐합니다.</li>
                          <li>"STOP"을 눌러 정지시킬 수 있어요.</li>
                          <li>"CHECK"를 눌러 사진을 인식시켜 주세요.</li>
                          <ul>
                            <li>얼굴이 선명하게 나와야합니다.</li>
                          </ul>
                          <li>
                            완료되면 다음은 컨텐츠 모드와 동일해요.
                            <ul>
                              <li>비속어를 사용하면 기부가 제한됩니다.</li>
                              <li>웃음지수가 낮으면 기부가 제한됩니다.</li>
                            </ul>
                          </li>
                        </ol>
                      </v-col>
                    </v-row>
                  </v-card>
                  <v-btn color="#356859" dark @click="e1 = 5">Continue</v-btn>
                  <v-btn
                    color="#356859"
                    dark
                    class="font-weight-bold"
                    text
                    @click="e1 = 3"
                    >Back</v-btn
                  >
                </v-stepper-content>

                <v-stepper-content step="5">
                  <v-card class="mb-8" height="400px" style="overflow-x:hidden">
                    <v-row>
                      <v-col
                        class="col-xl-7 col-lg-7 col-md-7 col-sm-12 col-12"
                      >
                        <img
                          src="../../../public/images/step5.gif"
                          height="90%"
                          width="100%"
                          max-height="80%"
                        />
                      </v-col>
                      <v-col
                        class="col-xl-5 col-lg-5 col-md-5 col-sm-12 col-12 p-3"
                        style="letter-spacing:1px;word-spacing:3px;line-height:220%;"
                      >
                        <h3 style="font-weight:bold;margin-bottom:30px;">
                          <v-icon large color="#356859" class="mr-3"
                            >mdi-cloud-upload-outline</v-icon
                          >파일업로드 모드
                        </h3>
                        <h4 style="font-weight:bold;margin-bottom:30px;">
                          파일업로드 모드를 알아볼게요.
                        </h4>
                        <ol style="font-size:1.1rem;">
                          <li>업로드 칸을 클릭하여 파일을 업로드합니다.</li>
                          <li>"CHECK"를 눌러 사진을 인식시켜주세요.</li>
                          <li>
                            완료되면 다음은 셀카 모드와 동일해요.
                            <ul>
                              <li>비속어를 사용하면 기부가 제한됩니다.</li>
                              <li>웃음지수가 낮으면 기부가 제한됩니다.</li>
                            </ul>
                          </li>
                        </ol>
                      </v-col>
                    </v-row>
                  </v-card>
                  <v-btn color="#356859" dark @click="e1 = 6">Continue</v-btn>
                  <v-btn
                    color="#356859"
                    class="font-weight-bold"
                    dark
                    text
                    @click="e1 = 4"
                    >Back</v-btn
                  >
                </v-stepper-content>

                <v-stepper-content step="6">
                  <v-card class="mb-8" height="400px" style="overflow-x:hidden">
                    <v-row>
                      <v-col
                        class="col-xl-7 col-lg-7 col-md-7 col-sm-12 col-12"
                      >
                        <img
                          src="../../../public/images/step6.gif"
                          height="90%"
                          width="100%"
                          max-height="80%"
                        />
                      </v-col>
                      <v-col
                        class="col-xl-5 col-lg-5 col-md-5 col-sm-12 col-12 p-3"
                        style="letter-spacing:1px;word-spacing:3px;line-height:220%;"
                      >
                        <h3 style="font-weight:bold;margin-bottom:30px;">
                          <v-icon large color="#356859" class="mr-3"
                            >mdi-close-circle-outline</v-icon
                          >주의사항
                        </h3>
                        <h4 style="font-weight:bold;margin-bottom:30px;">
                          주의사항을 지켜주세요.
                        </h4>
                        <ol style="font-size:1.1rem;">
                          <li>마스크를 착용하면 인식을 못해요.</li>
                          <li>얼굴의 일부가 가려지면 인식을 못해요.</li>
                          <li>
                            카메라와 적당한 거리를 유지해주세요.
                            <ul>
                              <li>화면 중앙에 얼굴을 위치시켜주세요.</li>
                            </ul>
                          </li>
                        </ol>
                      </v-col>
                    </v-row>
                  </v-card>
                  <v-btn color="#356859" dark @click="stepEnd">Complete</v-btn>
                  <v-btn
                    color="#356859"
                    dark
                    class="font-weight-bold"
                    text
                    @click="e1 = 5"
                    >Back</v-btn
                  >
                </v-stepper-content>
              </v-stepper-items>
            </v-stepper>
          </v-col>
        </v-row>
        <!-- 캡쳐 종류 선택 -->
        <v-row
          style="height:700px;margin-top:300px;"
          class="d-flex justify-content-center pt-15"
        >
          <v-col cols="10">
            <v-card>
              <v-tabs
                v-model="tab"
                background-color="#fffbe3"
                color="basil"
                class="basil--text"
                centered
                icons-and-text
              >
                <v-tabs-slider></v-tabs-slider>
                <v-tab
                  class="tab-text"
                  href="#tab-contents"
                  @click="someContents(), stopVideo()"
                >
                  Contents
                  <v-icon>mdi-animation</v-icon>
                </v-tab>

                <v-tab
                  class="tab-text"
                  href="#tab-self"
                  @click="selfieContents(), stopVideo()"
                >
                  Selfie
                  <v-icon>mdi-account-box</v-icon>
                </v-tab>

                <v-tab
                  class="tab-text"
                  href="#tab-file"
                  @click="selfieContents(), stopVideo()"
                >
                  Upload
                  <v-icon>mdi-file-upload</v-icon>
                </v-tab>
              </v-tabs>

              <v-tabs-items v-model="tab">
                <!-- 콘텐츠 -->
                <v-tab-item v-if="!contentsFlag" id="tab-contents">
                  <div class="container" style="height:400px;text-align:center">
                    <v-btn
                      color="#356859"
                      class="col-2"
                      style="top:170px;color:white;"
                      @click="(contentsFlag = true), loading(), startVideo()"
                      >start</v-btn
                    >
                  </div>
                </v-tab-item>

                <!-- 자동캡쳐 비디오 부분 -->
                <video
                  id="videoTag"
                  hidden
                  width="720"
                  height="560"
                  muted
                  @playing="addEventListener()"
                ></video>

                <v-tab-item
                  v-if="contentsFlag"
                  id="tab-contents"
                  style="margin-bottom:200px;"
                >
                  <v-row align="center" class="container m-0">
                    <v-item-group
                      v-model="window"
                      class="shrink"
                      mandatory
                      tag="v-flex"
                    >
                      <!-- 사진 -->
                      <v-item v-slot:default="{ active, toggle }" class="mb-5">
                        <div>
                          <v-btn :input-value="active" icon @click="toggle">
                            <v-icon>mdi-image-multiple</v-icon>
                          </v-btn>
                        </div>
                      </v-item>
                      <!-- 영상 -->
                      <v-item v-slot:default="{ active, toggle }" class="mb-5">
                        <div>
                          <v-btn :input-value="active" icon @click="toggle">
                            <v-icon>mdi-video</v-icon>
                          </v-btn>
                        </div>
                      </v-item>
                      <!-- 어린이 영상 -->
                      <v-item v-slot:default="{ active, toggle }" class="mb-5">
                        <div>
                          <v-btn :input-value="active" icon @click="toggle">
                            <v-icon>mdi-baby-carriage</v-icon>
                          </v-btn>
                        </div>
                      </v-item>
                      <!-- 음성 -->
                      <v-item v-slot:default="{ active, toggle }" class="mb-5">
                        <div>
                          <v-btn :input-value="active" icon @click="toggle">
                            <v-icon>mdi-microphone</v-icon>
                          </v-btn>
                        </div>
                      </v-item>
                    </v-item-group>

                    <!-- 컨텐츠 내용 -->
                    <v-col>
                      <v-window v-model="window" class="elevation-1" vertical>
                        <!-- 사진 -->
                        <v-window-item>
                          <v-card flat>
                            <v-card-text>
                              <div
                                class="container"
                                style="height:400px;text-align:center"
                              >
                                <v-carousel style="height:100%;">
                                  <v-carousel-item
                                    reverse-transition="fade-transition"
                                    transition="fade-transition"
                                  >
                                    <img
                                      :src="
                                        getImg(
                                          this.funImg[this.lotto[0]].funimg
                                        )
                                      "
                                      style="max-width:100%;width:400px;height:65%;"
                                      alt
                                    />
                                  </v-carousel-item>
                                  <v-carousel-item
                                    reverse-transition="fade-transition"
                                    transition="fade-transition"
                                  >
                                    <img
                                      :src="
                                        getImg(
                                          this.funImg[this.lotto[1]].funimg
                                        )
                                      "
                                      style="max-width:100%;width:400px;height:65%;"
                                      alt
                                    />
                                  </v-carousel-item>
                                  <v-carousel-item
                                    reverse-transition="fade-transition"
                                    transition="fade-transition"
                                  >
                                    <img
                                      :src="
                                        getImg(
                                          this.funImg[this.lotto[2]].funimg
                                        )
                                      "
                                      style="max-width:100%;width:400px;height:65%;"
                                      alt
                                    />
                                  </v-carousel-item>
                                </v-carousel>
                              </div>
                            </v-card-text>
                          </v-card>
                        </v-window-item>
                        <!-- 영상 -->
                        <v-window-item>
                          <v-card flat>
                            <v-card-text>
                              <LazyYoutubeVideo
                                :src="makeUrl(videos.videoId)"
                                style="width:100%;height:100%;"
                              />
                            </v-card-text>
                          </v-card>
                        </v-window-item>
                        <!-- 어린이 영상 -->
                        <v-window-item>
                          <v-card flat>
                            <v-card-text>
                              <LazyYoutubeVideo
                                :src="makeUrl(videosForChild.videoId)"
                                style="width:100%;height:100%;"
                              />
                            </v-card-text>
                          </v-card>
                        </v-window-item>
                        <!-- 음성 -->
                        <v-window-item>
                          <v-card flat>
                            <v-card-text>
                              <VoiceComp />
                            </v-card-text>
                          </v-card>
                        </v-window-item>
                      </v-window>
                    </v-col>
                  </v-row>
                </v-tab-item>

                <!-- 셀카 -->
                <v-tab-item v-if="!selfFlag" id="tab-self">
                  <div class="container" style="height:400px;text-align:center">
                    <v-btn
                      color="#356859"
                      class="col-2"
                      style="top:170px;color:white;"
                      @click="selfieInit()"
                      >start</v-btn
                    >
                  </div>
                </v-tab-item>
                <v-tab-item v-if="selfFlag" id="tab-self">
                  <audio
                    id="audio"
                    src="../../../public/laugh/laugh.mp3"
                    loop
                  ></audio>
                  <div class="container" style="height:400px;text-align:center">
                    <video
                      src
                      autoplay
                      class="feed"
                      width="100%"
                      height="100%"
                      id="webcam"
                      @click="init(), (camOn = true)"
                    ></video>
                  </div>
                  <v-row class="d-flex justify-content-center">
                    <v-btn
                      color="#356859"
                      class="m-2 col-md-2 col-sm-4 col-10"
                      style="color:white;"
                      v-if="startFlag"
                      @click="selfieStart()"
                      >start</v-btn
                    >
                    <v-btn
                      color="#356859"
                      class="m-2 col-md-2 col-sm-4 col-10"
                      style="color:white;"
                      v-if="endFlag"
                      @click="selfieStop(), (mloading = false), (nowPer = 0)"
                      >stop</v-btn
                    >
                    <v-btn
                      color="#356859"
                      class="m-2 col-md-2 col-sm-4 col-10"
                      style="color:white;"
                      v-if="endFlag"
                      @click="selfieCap()"
                      >capture</v-btn
                    >
                    <v-btn
                      color="#356859"
                      class="m-2 col-md-2 col-sm-4 col-10"
                      style="color:white;"
                      v-if="checkFlag"
                      @click="check(), (sloading = true)"
                      >check</v-btn
                    >
                  </v-row>

                  <div v-if="!startFlag">
                    <v-progress-linear
                      color="deep-purple"
                      :active="sloading"
                      :indeterminate="sloading"
                      rounded
                      height="3"
                    ></v-progress-linear>
                    <div
                      class="container pb-0"
                      style="height:250px;text-align:center"
                    >
                      <canvas height="200%" width="200%"></canvas>
                      <!-- <img id="myImage" /> -->
                      <!-- {{ selfieCapture }} -->
                    </div>
                    <div
                      v-if="nowPer > 0"
                      class="d-flex justify-content-center my-3"
                    >
                      웃음지수: {{ nowPer }} %
                    </div>
                    <div v-if="selCapFlag">
                      <!-- 사진 사용 여부 -->
                      <v-row>
                        <v-switch
                          style="margin:0 auto"
                          v-model="kingFlag"
                          inset
                          :label="`사진 허용 `"
                        ></v-switch>
                      </v-row>
                      <!-- 한줄 코멘트 -->
                      <v-row class="justify-content-center">
                        <v-col cols="6">
                          <p v-if="mloading">메세지 분석중...</p>
                          <v-progress-linear
                            color="deep-purple"
                            :active="mloading"
                            :indeterminate="mloading"
                            rounded
                            height="6"
                          ></v-progress-linear>
                          <v-text-field
                            v-model="comment"
                            :rules="commentRules"
                            :counter="20"
                            label="한 줄 메세지"
                          ></v-text-field>
                        </v-col>
                      </v-row>
                      <!-- 셀카 기부하기 -->
                      <v-row>
                        <v-btn
                          style="margin:0 auto 50px auto;color:white;"
                          class="col-3"
                          color="#356859"
                          @click="donationSelfie"
                          >기부하기</v-btn
                        >
                      </v-row>
                    </div>
                  </div>
                </v-tab-item>
                <!-- 파일 업로드 -->
                <v-tab-item v-if="!fileFlag" id="tab-file">
                  <div class="container" style="height:400px;text-align:center">
                    <v-btn
                      color="#356859"
                      class="col-2"
                      style="top:170px;color:white;"
                      @click="uploadInit()"
                      >start</v-btn
                    >
                  </div>
                </v-tab-item>
                <canvas
                  id="canvasImg"
                  height="200%"
                  width="200%"
                  hidden
                ></canvas>
                <v-tab-item
                  v-if="fileFlag"
                  id="tab-file"
                  style="margin:0 auto;text-align:center;"
                >
                  <v-row>
                    <div
                      class="col-lg-6 col-md-6 col-sm-8 col-10 pt-7"
                      style="margin:0 auto;text-align:center;"
                    >
                      파일 업로드
                      <v-file-input
                        ref="file"
                        accept="image/png, image/jpeg, image/bmp"
                        v-model="inputFile"
                        color="#356859"
                        counter
                        prepend-icon
                        outlined
                      >
                        <template v-slot:selection="{ text }">
                          <v-chip color="#356859" dark label small>{{
                            text
                          }}</v-chip>
                        </template>
                      </v-file-input>
                      <v-progress-linear
                        color="deep-purple"
                        :active="uloading"
                        :indeterminate="uloading"
                        rounded
                        height="6"
                      ></v-progress-linear>
                    </div>
                    <div class="col-12"></div>
                    <div
                      v-if="inputFile != ''"
                      style="margin:0 auto; margin-bottom:20px;"
                    >
                      <img
                        :src="preImg(inputFile)"
                        style="max-width:80%;width:400px;height:100%;"
                        id="uploadImg"
                      />
                    </div>
                  </v-row>

                  <!-- 업로드 파일 체크 -->
                  <v-row class="justify-content-center mb-5">
                    <v-btn
                      color="#356859"
                      class="m-2 col-md-2 col-sm-4 col-10"
                      style="color:white;"
                      v-if="checkFlag"
                      @click="checkUpload(), (uloading = true)"
                      >check</v-btn
                    >
                  </v-row>
                  <div
                    v-if="nowPer > 0"
                    class="d-flex justify-content-center my-3"
                  >
                    웃음지수: {{ nowPer }} %
                  </div>
                  <div v-if="upFlag">
                    <!-- 사진 사용 여부 -->
                    <v-row>
                      <v-switch
                        style="margin:0 auto"
                        v-model="kingFlag"
                        inset
                        :label="`사진 허용 `"
                      ></v-switch>
                    </v-row>
                    <!-- 한줄 코멘트 -->
                    <v-row class="justify-content-center">
                      <v-col cols="6">
                        <p v-if="mloading">메세지 분석중...</p>
                        <v-progress-linear
                          color="deep-purple"
                          :active="mloading"
                          :indeterminate="mloading"
                          rounded
                          height="6"
                        ></v-progress-linear>
                        <v-text-field
                          v-model="comment"
                          :rules="commentRules"
                          :counter="20"
                          label="한 줄 메세지"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                    <!-- 업로드 기부하기 -->
                    <v-row>
                      <v-btn
                        style="margin:0 auto 50px auto;color:white;"
                        class="col-3"
                        color="#356859"
                        @click="donationSelfie"
                        >기부하기</v-btn
                      >
                    </v-row>
                  </div>
                </v-tab-item>
              </v-tabs-items>
            </v-card>
          </v-col>
        </v-row>

        <div class="d-flex justify-content-center" style="margin-top: 200px;">
          <canvas
            v-if="contentsFlag"
            id="autoCanvas"
            height="200%"
            width="200%"
          ></canvas>
        </div>

        <div v-if="autoFlag">
          <!-- <v-row>
        <v-card style="">
          asd -->
          <!-- <img :src="getImg(autoCapture[0])" alt="캡쳐된 이미지" /> -->
          <!-- <v-img src="../../../public/images/1600678282188.png" alt="autoCapture"></v-img> -->
          <!-- </v-card>
      </v-row> -->
          <v-row class="justify-content-center">
            <v-btn
              class="col-lg-2 col-md-2 col-sm-5 col-5 mx-10 my-5"
              color="#356859"
              style="color:white;"
              @click="(contentsFlag = false), (autoFlag = false), stepEnd()"
              >다시찍기</v-btn
            >
            <v-btn
              class="col-lg-2 col-md-2 col-sm-5 col-5 mx-10 my-5"
              color="#356859"
              style="color:white;"
              @click="selectFlag = true"
              >사진확정</v-btn
            >
          </v-row>
        </div>

        <div v-if="autoFlag">
          <!-- 사진 사용 여부 -->
          <v-row class="justify-content-center" style="">
            <v-switch
              v-model="kingFlag"
              inset
              label="사진 허용"
              style=""
            ></v-switch>
          </v-row>
          <!-- 한줄 코멘트 -->
          <v-row class="justify-content-center">
            <v-col cols="6">
              <p v-if="mloading">메세지 분석중...</p>
              <v-progress-linear
                color="deep-purple"
                :active="mloading"
                :indeterminate="mloading"
                rounded
                height="6"
              ></v-progress-linear>
              <v-text-field
                v-model="comment"
                :rules="commentRules"
                :counter="20"
                label="한 줄 메세지"
              ></v-text-field>
            </v-col>
          </v-row>
          <!-- 컨텐츠 기부하기 -->
          <v-row
            class="col-sx-12 col-4 d-flex justift-content-center mb-10"
            style="margin:0 auto;text-align:center;"
          >
            <v-btn
              v-if="selectFlag"
              color="#356859"
              @click="donationContents"
              class="col-12"
              style="color:white;"
              >기부하기</v-btn
            >
          </v-row>
        </div>
        <div>
          <v-row class="text-left">
            <v-col>
              <v-btn class="mx-10" @click="goBack" icon fab large color="basil">
                <v-icon>mdi-undo</v-icon
                ><span style="font-weight:bold;">뒤로</span>
              </v-btn>
            </v-col>
          </v-row>
        </div>
        <!-- 로딩 오버레이 -->
        <v-overlay :value="overlay">
          <div
            class="container"
            style="margin:0 auto;text-align:center;position:relative;top:50%;"
          >
            <v-progress-circular
              v-if="(value > 0) & (value < 100)"
              :rotate="-90"
              :size="100"
              :width="15"
              :value="value"
              color="white"
              >{{ value }}</v-progress-circular
            >
            <p class="mt-5">카메라 인식중...</p>
          </div>
        </v-overlay>

        <!-- 캡쳐 알림 -->
        <v-snackbar
          v-model="captureFlag"
          top
          right
          :timeout="3000"
          color="#356859"
        >
          캡쳐되었습니다.
          <template v-slot:action="{ attrs }">
            <v-btn text v-bind="attrs" @click="captureFlag = false"
              >Close</v-btn
            >
          </template>
        </v-snackbar>

        <!-- 기부 알림 -->
        <v-snackbar
          v-model="donationFlag"
          top
          right
          :timeout="3000"
          color="#356859"
        >
          기부되었습니다.
          <template v-slot:action="{ attrs }">
            <v-btn text v-bind="attrs" @click="donationFlag = false"
              >Close</v-btn
            >
          </template>
        </v-snackbar>

        <!-- 로딩 오버레이 -->
        <v-overlay :value="donationFlag">
          <div
            class="container"
            style="margin:0 auto;text-align:center;position:relative;top:50%;"
          >
            <v-progress-circular
              :size="70"
              width="6"
              indeterminate
              color="amber"
            ></v-progress-circular>
          </div>
        </v-overlay>

        <!-- alert -->
        <v-snackbar v-model="noFace" top right color="error" :timeout="2000"
          ><p class="snackText">얼굴을 인식하지 못했습니다.</p></v-snackbar
        >
        <v-snackbar v-model="yesFace" top right color="success" :timeout="2000"
          ><p class="snackText">얼굴 인식을 성공했습니다.</p></v-snackbar
        >
        <v-snackbar v-model="noDev" top right color="error" :timeout="2000"
          ><p class="snackText">카메라를 찾을 수 없습니다.</p></v-snackbar
        >
        <v-snackbar v-model="reTry" top right color="error" :timeout="2000"
          ><p class="snackText">사진을 다시 찍어주세요.</p></v-snackbar
        >
        <v-snackbar v-model="rowPer" top right color="error" :timeout="2000"
          ><p class="snackText">웃음 지수가 너무 낮습니다.</p></v-snackbar
        >
        <v-snackbar v-model="noMsg" top right color="error" :timeout="2000"
          ><p class="snackText">응원 메세지를 적어주세요.</p></v-snackbar
        >
        <v-snackbar v-model="ckMsg" top right color="#356859" :timeout="2000"
          ><p class="snackText">{{ tempMsg }}</p></v-snackbar
        >
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
import { mapGetters, mapState } from "vuex";
import LazyYoutubeVideo from "vue-lazy-youtube-video";
import VoiceComp from "@/components/voice/VoiceComp.vue";
// npm install --save vue-lazy-youtube-video
import * as faceapi from "face-api.js";
// npm install --save face-api.js

export default {
  components: {
    LazyYoutubeVideo,
    VoiceComp
  },
  mounted() {
    this.videoTag = document.getElementById("videoTag");
    Promise.all([
      faceapi.nets.tinyFaceDetector.loadFromUri("/models"),
      faceapi.nets.faceLandmark68Net.loadFromUri("/models"),
      faceapi.nets.faceRecognitionNet.loadFromUri("/models"),
      faceapi.nets.faceExpressionNet.loadFromUri("/models")
    ]);
  },
  created() {
    scroll(0, 0);
    this.donationid = this.$route.params.ID;
    this.uid = this.getUserID;
    this.getAge();
    this.getFun();
  },
  data() {
    return {
      idname: "",
      donationid: 0,
      uid: "",
      uage: "",
      e1: 1,
      log: "",
      value: 0,
      comment: "",
      interval: {},
      length: 2,
      window: 0,
      tab: null,
      overlay: false,
      contentsFlag: false,
      selfFlag: false,
      captureFlag: false,
      autoCapFlag: false,
      kingFlag: false,
      camOn: false,
      startFlag: true,
      endFlag: false,
      checkFlag: false,
      selCapFlag: false,
      selectFlag: false,
      donationFlag: false,
      fileFlag: false,
      commentRules: [v => v.length <= 20 || "20자 이내로 써주세요."],
      autoCapture: {
        url: "",
        per: ""
      },
      selfieCapture: [],
      videos: {},
      videosForChild: {},
      inputFile: [],
      preUrl: "",
      videoTag: [],
      upFlag: false,
      autoFlag: false,
      snackbar: true,
      noFace: false,
      yesFace: false,
      noDev: false,
      reTry: false,
      rowPer: false,
      noMsg: false,
      ckMsg: false,
      tempMsg: "",
      uloading: false,
      sloading: false,
      mloading: false,
      nowPer: 0,
      funImg: [],
      lotto: []
    };
  },
  methods: {
    getAge() {
      this.uage = new Date().getFullYear() - this.getUserBirth.substr(0, 4);
      if (this.uage < 10) {
        this.uage = 1;
      } else {
        this.uage = this.uage - (this.uage % 10);
      }
      this.getVideos();
      this.getVideosForChild();
    },
    getVideos() {
      http
        .get(`/crawling/getVideosByAge/${this.uage}`)
        .then(res => {
          var jbRandom = Math.random();
          this.videos = res.data[Math.floor(jbRandom * res.data.length)];
        })
        .catch(err => {
          console.log(err);
        });
    },
    getVideosForChild() {
      http
        .get(`/crawling/getVideosByAge/1`)
        .then(res => {
          var jbRandom = Math.random();
          this.videosForChild =
            res.data[Math.floor(jbRandom * res.data.length)];
        })
        .catch(err => {
          console.log(err);
        });
    },
    playing() {
      // console.log("we are watching!!!");
    },
    check() {
      var myImage = document.querySelector("canvas").toDataURL();
      //btoa
      http
        .post(`/smile/smileCheck`, myImage)
        .then(res => {
          this.selfieCapture = res.data;
          if (this.selfieCapture == "findFail") {
            this.noFace = true;
            this.uloading = false;
            this.sloading = false;
          } else {
            this.selCapFlag = true;
            this.upFlag = true;
            this.yesFace = true;
            this.uloading = false;
            this.sloading = false;
            this.nowPer = this.selfieCapture[2];
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    capture() {
      const picture = document.querySelector("canvas");
      const ctx = picture.getContext("2d");
      ctx.imageSmoothingEnabled = true;
      ctx.imageSmoothingQuality = "high";
      ctx.drawImage(
        document.getElementById("webcam"),
        0,
        0,
        picture.width,
        picture.height
      );
    },
    stop() {
      if (this.camOn) {
        if (document.getElementById("webcam") != null) {
          const stream = document.getElementById("webcam").srcObject;
          const tracks = stream.getTracks();
          tracks.forEach(function(track) {
            track.stop();
          });

          document.getElementById("webcam").srcObject = null;
        }
        this.camOn = false;
      }
    },
    init() {
      if (
        "mediaDevices" in navigator &&
        "getUserMedia" in navigator.mediaDevices
      ) {
        navigator.mediaDevices.getUserMedia({ video: true }).then(stream => {
          const videoPlayer = document.getElementById("webcam");
          videoPlayer.srcObject = stream;
          videoPlayer.play();
        });
      } else {
        this.noDev = true;
      }
    },
    stepEnd() {
      scroll(0, 920);
    },
    getImg(img) {
      return "../../../images/funny/" + img;
    },
    makeUrl(url) {
      return "https://www.youtube.com/embed/" + url;
    },
    loading() {
      this.overlay = true;
      this.interval = setInterval(() => {
        if (this.value === 100) {
          this.overlay = false;
          clearInterval(this.interval);
          return;
        }
        this.value += 10;
      }, 200);
    },
    donationSelfie() {
      if (this.selfieCapture == "findFail") {
        this.reTry = true;
        this.mloading = false;
      } else {
        if (this.selfieCapture[2] < 30) {
          this.rowPer = true;
          this.mloading = false;
        } else {
          if (this.comment == "") {
            this.noMsg = true;
            this.mloading = false;
          } else {
            this.mloading = true;
            http
              .get(`/smile/textCheck/${this.comment}`)
              .then(res => {
                this.tempMsg = res.data;
                this.ckMsg = true;
                var word = res.data.split(" ");
                var smilePer = word[0].split(".")[0];
                if (word[2] == "긍정" && Number(smilePer) >= 60) {
                  //긍정 60프로 이상
                  http
                    .post("/smile/regist", {
                      userId: this.uid,
                      donationid: this.donationid,
                      photo: this.selfieCapture[0],
                      smileper: this.selfieCapture[2],
                      comment: this.comment,
                      agreement: this.kingFlag ? 1 : 0
                    })
                    .then(res => {
                      this.stop();
                      this.log = res.data;
                      this.donationFlag = true;
                      let x = this;
                      this.mloading = false;
                      setTimeout(function() {
                        x.$router.push("/donationlist");
                      }, 1500);
                    })
                    .catch(err => {
                      this.mloading = false;
                      console.log(err);
                    });
                } else {
                  this.mloading = false;
                }
              })
              .catch(err => {
                console.log(err);
              });
          }
        }
      }
    },
    doDonation() {
      this.mloading = true;
      if (this.comment != "") {
        http
          .get(`/smile/textCheck/${this.comment}`)
          .then(res => {
            this.tempMsg = res.data;
            this.ckMsg = true;
            var word = res.data.split(" ");
            var smilePer = word[0].split(".")[0];
            if (word[2] == "긍정" && Number(smilePer) >= 60) {
              //긍정 60프로 이상
              http
                .post("/smile/autoRegist", {
                  userId: this.uid,
                  donationid: this.donationid,
                  photo: this.autoCapture.url,
                  smileper: this.autoCapture.per,
                  comment: this.comment,
                  agreement: this.kingFlag ? 1 : 0
                })
                .then(res => {
                  this.log = res.data;
                  this.donationFlag = true;
                  let x = this;
                  this.mloading = false;
                  setTimeout(function() {
                    x.$router.push("/donationlist");
                  }, 1500);
                })
                .catch(err => {
                  this.mloading = false;
                  console.log(err);
                });
            } else {
              this.mloading = false;
            }
          })
          .catch(err => {
            console.log(err);
          });
      } else {
        this.noMsg = true;
      }
    },
    donationContents() {
      if (this.comment != "") {
        var myImage = document.querySelector("#autoCanvas").toDataURL();
        http
          .post(`/smile/autoCheck`, myImage)
          .then(res => {
            this.autoCapture.url = res.data;
            this.doDonation();
          })
          .catch(err => {
            console.log(err);
          });
      } else {
        this.noMsg = true;
      }
    },
    selfieInit() {
      this.selfFlag = true;
      this.checkFlag = false;
      this.selCapFlag = false;
      this.endFlag = false;
    },
    selfieStart() {
      let audio = document.getElementById("audio");
      audio.play();
      this.init();
      this.camOn = true;
      this.startFlag = false;
      this.endFlag = true;
    },
    selfieStop() {
      let audio = document.getElementById("audio");
      audio.pause();
      this.stop();
      this.startFlag = true;
      this.endFlag = false;
      this.checkFlag = false;
      this.selCapFlag = false;
      this.selfieCapture = [];
    },
    selfieCap() {
      let audio = document.getElementById("audio");
      audio.pause();
      this.capture();
      this.selfieCapture = [];
      this.checkFlag = true;
    },
    selfieContents() {
      this.autoCapFlag = false;
      this.startFlag = true;
      this.contentsFlag = false;
      this.checkFlag = false;
      this.selCapFlag = false;
      this.endFlag = false;
      this.selfFlag = false;
      this.value = 0;
      this.overlay = false;
      this.fileFlag = false;
      this.selfieCapture = [];
      this.inputFile = [];
      this.stop();
      this.canvasClear();
      this.camOff();
      this.upFlag = false;
      this.autoFlag = false;
      this.uloading = false;
      this.sloading = false;
      this.mloading = false;
      this.comment = "";
      this.nowPer = 0;
    },
    someContents() {
      this.selfFlag = false;
      this.value = 0;
      this.overlay = false;
      this.fileFlag = false;
      this.selfieCapture = [];
      this.inputFile = [];
      this.stop();
      this.canvasClear();
      this.upFlag = false;
      this.uloading = false;
      this.sloading = false;
      this.mloading = false;
      this.comment = "";
      this.nowPer = 0;
    },
    uploadInit() {
      this.selfFlag = false;
      this.contentsFlag = false;
      this.fileFlag = true;
      this.selfieCapture = [];
      this.stop();
      this.autoFlag = false;
      this.uloading = false;
      this.sloading = false;
      this.mloading = false;
      this.comment = "";
      this.nowPer = 0;
    },
    preImg(img) {
      if (img != null && img != "") {
        this.preUrl = URL.createObjectURL(img);
        return URL.createObjectURL(img);
      }
    },
    canvasClear() {
      const picture = document.querySelector("canvas");
      const ctx = picture.getContext("2d");
      // 픽셀 정리
      ctx.clearRect(0, 0, picture.width, picture.height);
      // 컨텍스트 리셋
      ctx.beginPath();
    },
    checkUpload() {
      const picture = document.querySelector("canvas");
      const ctx = picture.getContext("2d");
      ctx.imageSmoothingEnabled = true;
      ctx.imageSmoothingQuality = "high";
      ctx.drawImage(
        document.getElementById("uploadImg"),
        0,
        0,
        picture.width,
        picture.height
      );

      this.check();
    },
    startVideo() {
      navigator.mediaDevices.getUserMedia({ video: true }).then(stream => {
        const videoPlayer = document.getElementById("videoTag");
        videoPlayer.srcObject = stream;
        videoPlayer.play();
      });
    },
    addEventListener() {
      const canvas = faceapi.createCanvasFromMedia(this.videoTag);
      // document.body.append(canvas);
      const displaySize = {
        width: this.videoTag.width,
        height: this.videoTag.height
      };
      faceapi.matchDimensions(canvas, displaySize);
      setInterval(async () => {
        const detections = await faceapi
          .detectAllFaces(this.videoTag, new faceapi.TinyFaceDetectorOptions())
          .withFaceLandmarks()
          .withFaceExpressions();

        // // detections
        // const resizedDetections = faceapi.resizeResults(detections, displaySize);
        // canvas.getContext('2d').clearRect(0, 0, canvas.width, canvas.height);
        // faceapi.draw.drawDetections(canvas, resizedDetections);
        // faceapi.draw.drawFaceLandmarks(canvas, resizedDetections);
        // faceapi.draw.drawFaceExpressions(canvas, resizedDetections);
        if (
          detections.length > 0 &&
          detections[0].expressions.happy * 100 > 99.89
        ) {
          this.autoCapture.per = Math.round(
            detections[0].expressions.happy * detections[0].detection.score * 97
          );
          this.captureComplete();
          this.autoFlag = true;
          clearInterval();
          return;
        }
      }, 300);
    },
    captureComplete() {
      const picture = document.querySelector("#autoCanvas");
      const ctx = picture.getContext("2d");
      ctx.imageSmoothingEnabled = true;
      ctx.imageSmoothingQuality = "high";
      ctx.drawImage(
        document.getElementById("videoTag"),
        0,
        0,
        picture.width,
        picture.height
      );
      this.captureFlag = true;
      this.autoCapFlag = true;
      this.camOff();
      scroll(0, 1700);
    },
    camOff() {
      if (this.videoTag.srcObject != null && this.videoTag.srcObject != "") {
        const tracks = this.videoTag.srcObject.getTracks();
        tracks.forEach(function(track) {
          track.stop();
        });
        this.videoTag.srcObject = null;
      }
    },
    stopVideo() {
      if (
        document.querySelector("iframe") != "" &&
        document.querySelector("iframe") != null
      ) {
        document.querySelectorAll("iframe").src = "";
      }
    },
    getFun() {
      http
        .get("/crawling/funImage")
        .then(res => {
          this.funImg = res.data;
          for (var i = 0; i < 3; i++) {
            let n = Math.floor(Math.random() * 10);
            if (!this.sameNum(n)) {
              this.lotto.push(n);
            } else {
              i--;
            }
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    sameNum(n) {
      return this.lotto.find(e => e === n);
    },
    goBack() {
      window.history.back();
    }
  },
  computed: {
    ...mapGetters(["getUserID", "getUserBirth"]),
    ...mapState({
      userID: state => `${state.user.getUserID}`,
      userBirth: state => `${state.user.getUserBirth}`
    })
  },
  watch: {
    inputFile: function() {
      if (this.inputFile != "" && this.inputFile != null) {
        this.checkFlag = true;
      } else {
        this.checkFlag = false;
      }
    }
  },
  updated() {
    // console.log(this.videos)
    // var el = document.querySelectorAll("iframe");
    if (
      document.querySelector("iframe") != "" &&
      document.querySelector("iframe") != null
    ) {
      // console.log(document.querySelectorAll("iframe"))
      // console.log(document.querySelectorAll("iframe")[0].src.substring);
      if (document.querySelectorAll("iframe").length == 1) {
        // console.log(document.querySelectorAll("iframe")[0].src.split("/")[4].substring(0, document.querySelectorAll("iframe")[0].src.length - 11));
        var str = document.querySelectorAll("iframe")[0].src.split("/")[4];
        var len = str.length;
        var vname;
        if (len > 19) {
          vname = str.substring(0, len - 11);
        } else {
          vname = str;
        }
        if (vname == this.videos.videoId) {
          document.querySelectorAll("iframe")[0].src = this.makeUrl(
            this.videos.videoId
          );
        } else if (vname == this.videosForChild.videoId) {
          document.querySelectorAll("iframe")[0].src = this.makeUrl(
            this.videosForChild.videoId
          );
        }
        // if(str.substring(0, len - 11))
      } else {
        document.querySelectorAll("iframe")[0].src = this.makeUrl(
          this.videos.videoId
        );
        document.querySelectorAll("iframe")[1].src = this.makeUrl(
          this.videosForChild.videoId
        );
      }
    }
  }
};
</script>

<style scoped>
.camera {
  width: 100vw;
  height: 100vh;
  padding: 25px;
  box-sizing: border-box;
}
.feed {
  display: block;
  width: 100%;
  max-width: 1280px;

  margin: 0 auto;

  background-color: #171717;
  box-shadow: 6px 6px 12px 0px rgba(0, 0, 0, 0.35);
}
.basil--text {
  color: #356859 !important;
}
.tab-text {
  font-weight: bold;
  text-decoration: none;
}
.snackText {
  margin-bottom: 0;
  font-weight: bold;
  font-size: 1rem;
  word-spacing: 2px;
  letter-spacing: 2px;
}
.sc_header_img {
  background: url("../../assets/donation_hd2.jpg") no-repeat;
  width: 100%;
  height: 5rem;
  display: block;
  background-position: center;
  background-size: cover;
  filter: brightness(70%);
}
</style>
