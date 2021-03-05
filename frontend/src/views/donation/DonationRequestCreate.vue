<template>
<div>
  <div class="d_header_img justify-content-center d-none d-md-block"></div>
  <div class=" d-flex justify-content-center">
    <DonationRequestCreateComp/>
  </div>
</div>
</template>

<script>
// @ is an alias to /src
import DonationRequestCreateComp from "@/components/donation/DonationRequestCreateComp.vue";

export default {
  name: "DonationRequestCreate",
  components: {
    DonationRequestCreateComp
  }
};
</script>
