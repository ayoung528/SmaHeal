<template>
  <div>
    <DonationDetailComp/>
  </div>
</template>

<script>
// @ is an alias to /src
import DonationDetailComp from "@/components/donation/DonationDetailComp.vue";

export default {
  name: "DonationDetail",
  components: {
    DonationDetailComp
  }
};
</script>
