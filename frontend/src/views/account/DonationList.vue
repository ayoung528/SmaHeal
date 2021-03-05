<template>
  <div>
      <donationListComp
      :propNickname="getProfile"
      v-if="getProfile !== null"/>
  </div>
</template>

<script>
import DonationListComp from "@/components/account/DonationListComp.vue"
import { mapGetters, mapState } from "vuex";

export default {
    name:"DonationList",
    components:{
        DonationListComp
    },
    computed: {
        ...mapGetters([
        "isAuthenticated",
        "isProfileLoaded",
        "getProfile",
        "getRealName",
        "getUserNum",
        "getUserID",
        "getUserBirth",
        ]),
        ...mapState({
        authLoading: state => state.auth.status === "loading",
        uname: state => `${state.user.getProfile}`,
        userNum: state => `${state.user.getUserNum}`,
        userName: state => `${state.user.getRealName}`,
        userID: state => `${state.user.getUserID}`,
        userBirth: state => `${state.user.getUserBirth}`,
        }),
    },
}
</script>

<style>

</style>