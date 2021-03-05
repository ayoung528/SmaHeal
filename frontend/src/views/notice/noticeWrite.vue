<template>
  <div>
    <div class="nd_header_img justify-content-center d-none d-md-block"></div>
    <noticeWriteComp/>
  </div>
</template>

<script>
import noticeWriteComp from "@/components/notice/noticeWriteComp.vue";

export default {
  name: "noticeWrite",
  components: {
    noticeWriteComp
  },
  data: function() {
    return {

    }
  },
};
</script>
