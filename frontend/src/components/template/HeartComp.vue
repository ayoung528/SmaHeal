<template>
  <div cols="12" class="back" style="overflow:hidden">
    <div v-for="(img, index) in imgs" :key="index">
      <img
        :src="getImg(img)"
        class="col-md-1 col-sm-1 col-xs-1 p-0 "
        style="width:12vw; height:8vw"
      />
    </div>
    <v-overlay :absolute="true" :value="true" :opacity="0" class="col-12 p-0">
      <div cols="12" class="back">
        <img
          src="../../../public/images/heart2.png"
          style="width:100%; height:50vw;"
        />
      </div>
    </v-overlay>
  </div>
</template>

<script>
export default {
  data() {
    return {
      imgs: []
    };
  },
  props: {
    propsItem: { type: Array }
  },
  created() {
    const len = this.propsItem.length;
    if (len > 70) {
      for (var i = 0; i < 70; i++) {
        this.imgs.push(this.propsItem[i].photo);
      }
    } else {
      var idx = 0;
      var cnt = 0;
      while (cnt < 70) {
        cnt++;
        this.imgs.push(this.propsItem[idx].photo);
        idx++;
        if (idx == len) {
          idx = 0;
        }
      }
    }
  },
  methods: {
    getImg(img) {
      return "../../../images/" + img;
    }
  }
};
</script>
<style>
.back {
  width: 100%;
  height: 50vw;
}

.v-overlay__content {
  width: 100%;
  height: 50vw;
  position: absolute !important;
  top: 0px !important;
}
</style>