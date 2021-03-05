<template>
  <div>
    <div class="d_header_img justify-content-center d-none d-md-block"></div>
    <div class="d-flex justify-content-center">
      <DonationRequestDetailComp />
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import DonationRequestDetailComp from "@/components/donation/DonationRequestDetailComp.vue";

export default {
  name: "DonationRequestDetail",
  components: {
    DonationRequestDetailComp
  }
};
</script>

<style>
.d_header_img {
  background: url("../../assets/donation_hd2.jpg") no-repeat;
  width: 100%;
  height: 5rem;
  display: block;
  background-position: center;
  background-size: cover;
  filter:brightness(70%);
}
</style>