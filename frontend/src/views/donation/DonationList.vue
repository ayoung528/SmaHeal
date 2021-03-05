<template>
  <div class="d-flex justify-content-center">
    <DonationListComp/>
  </div>
</template>

<script>
// @ is an alias to /src
import DonationListComp from "@/components/donation/DonationListComp.vue";

export default {
  name: "DonationList",
  components: {
    DonationListComp
  }
};
</script>
