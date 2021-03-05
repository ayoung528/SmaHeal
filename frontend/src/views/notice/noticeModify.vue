<template>
<div>
  <div class="nd_header_img justify-content-center d-none d-md-block"></div>
  <div class="d-flex justify-content-center">
    <noticeModifyComp
    :propItems="this.items"
    v-if="this.items.length !== 0"
    />
  </div>
</div>
</template>

<script>
import noticeModifyComp from "@/components/notice/noticeModifyComp.vue";
import http from "@/util/http-common";

export default {
  name: "noticeModify",
  components: {
    noticeModifyComp
  },
  data: function() {
    return {
        items: [],
    }
  },
  created() {
      http
      .get(`/notice/find/${this.$route.params.noticeid}`)
      .then(({data}) => {
        this.items = data;
      })
  }
};
</script>
