<template>
<div>
  <!-- <div class="nd_header_img justify-content-center d-none d-md-block"></div> -->
  <div class="d-flex justify-content-center">
    <noticeSearchComp
    :propItems="items"
    :propCount="totalCnt"
    :propKeyword="keyword"
    v-if="items.length !== 0 && totalCnt !== 0"
    />
    <searchErrorComp
    :propKeyword="keyword"
    v-else/>
  </div>
</div>
</template>

<script>
import noticeSearchComp from "@/components/notice/noticeSearchComp.vue";
import searchErrorComp from "@/components/notice/searchErrorComp.vue";
import http from "@/util/http-common.js";

export default {
  name: "noticeSearch",
  components: {
    noticeSearchComp,
    searchErrorComp
  },
  data: function() {
    return {
        keyword: "",
        items: [],
        totalCnt: 0,
    }
  },
  created() {
    this.keyword = this.$route.query.title;

    http
    .get(`/notice/search/${this.keyword}/0`)
    .then(({data}) => {
      this.items = data;

      http.get(`/notice/search/getCount/${this.keyword}`)
      .then(({data}) => {
        this.totalCnt = data;
      })
    })
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
