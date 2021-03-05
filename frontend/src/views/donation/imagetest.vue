<template>
  <div>
    <input ref="file" type="file" hidden @change="onChangeImages" />
    <img :src="tempimg" style="box-shadow:5px 5px 5px rgba(0,0,0,.15)" />
    <button
      type="button"
      class="btn btn-default btn-sm col-md-5 mt-2"
      style="border-radius:12px; font-size:13px; border:1.5px solid"
      @click="onClickImageUpload"
    >
      <i class="fas fa-image mr-2"></i>프로필사진 수정(1MB 이하)
    </button>
    <button @click="makeImgUrl()">경로보기</button>
    <img :src="getImg(curImage)">
  </div>
</template>
<script>
import http3 from "@/util/http-common3.js";
export default {
  data() {
    return {
      tempimg: "",
      curImage:"noimage202081602311919.png"
    };
  },
  methods: {
    getImg(img){
      return "../../../contents/" + img;
    },
    makeImgUrl() {
      var formData = new FormData();
      const file = this.$refs.file.files[0];
      if (file != null) {
        formData.append("file", file);
        http3
          .post("/makeImageSrc", formData)
          .then(res => {
            console.log(res.data);
          })
          .catch(err => {
            console.log(err);
          });
      }
    },
    onClickImageUpload() {
      this.$refs.file.click();
    },
    onChangeImages(e) {
      const file = e.target.files[0];
      if (file == null) {
        return;
      }
      if (file.size >= 1048576) {
        alert("파일 크기 초과");
        return;
      }
      this.tempimg = URL.createObjectURL(file);
    }
  }
};
</script>