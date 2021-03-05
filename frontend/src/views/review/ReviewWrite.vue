<template>
  <div>
      <div class="rd_header_img justify-content-center d-none d-md-block"></div>
      <ReviewWriteComp/>
  </div>
</template>

<script>
import ReviewWriteComp from "@/components/review/ReviewWriteComp.vue";

export default {
    name:"ReviewWrite",
    components:{
        ReviewWriteComp
    }
}
</script>

<style>
.rd_header_img {
  background: url("../../assets/donation_hd.jpg") no-repeat;
  width: 100%;
  height: 5rem;
  display: block;
  background-position: center;
  background-size: cover;
}
</style>