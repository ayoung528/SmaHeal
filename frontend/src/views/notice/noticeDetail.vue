<template>
  <div>
    <div class="nd_header_img justify-content-center d-none d-md-block"></div>
    <div class="d-flex justify-content-center">
      <noticeDetailComp :propItem="item" v-if="item.length !== 0" />
    </div>
  </div>
</template>

<script>
import noticeDetailComp from "@/components/notice/noticeDetailComp.vue";
import http from "@/util/http-common.js";

export default {
  name: "noticeDetail",
  components: {
    noticeDetailComp
  },
  data: function() {
    return {
      item: []
    };
  },
  created() {
    http
      .get(`notice/find/${this.$route.params.noticeid}`)
      .then(({ data }) => {
        this.item = data;
      })
      .catch();
  }
};
</script>

<style>
.nd_header_img {
  background: url("../../assets/notice_hd.jpg") no-repeat;
  width: 100%;
  height: 5rem;
  display: block;
  background-position: center;
  background-size: cover;
}
</style>