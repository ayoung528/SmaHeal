<template>
  <div class="col-md-12 p-0">
    <div class="deault-header d-none d-md-block"></div>
    <div style="height:5rem;" class="d-block d-md-none"></div>
    <v-container fluid class="pa-0">
      <v-row class="pa-0" no-gutters>
        <v-col cols="6" class="pa-0 ma-0 d-none d-md-block">
          <v-img
            :src="require(`@/assets/login-smile.jpg`)"
            class="pa-0 ma-0 d-none d-md-block"
            :max-height="'calc(100vh - ' + $vuetify.application.top + 'px)'"
          />
        </v-col>
        <v-col class="my-auto mx-auto col-md-6 col-sm-12">
          <v-sheet
            class="text-center amber--text mb-7"
            style="font-size:3rem;font-family:Gamja Flower"
          >
            스마힐
          </v-sheet>
          <v-col cols="8">
            <v-form ref="form">
              <v-text-field
                class="mb-1"
                v-model="userId"
                hide-details
                outlined
                required
                placeholder="아이디를 입력하세요."
                @keypress.enter="loginRequest"
              />

              <v-text-field
                v-model="password"
                type="password"
                hide-details
                outlined
                placeholder="비밀번호를 입력하세요."
                required
                class="mb-7"
                @keypress.enter="loginRequest"
              />

              <v-btn class="pa-5 green-mbtn" width="100%" @click="loginRequest"
                >로그인</v-btn
              >
            </v-form>
            <!-- <v-divider></v-divider>
        <v-btn class="pa-5" width="100%" outlined color="#356859">카카오로 로그인</v-btn> -->
            <div style="text-align: right">
              <v-btn text class="mt-3" @click="goSignUp()">회원가입</v-btn>
            </div>
          </v-col>
        </v-col>
      </v-row>
      <v-snackbar v-model="match" top right flat color="error" :timeout="2000"
        ><p
          style="margin-bottom:0;font-weight:bold;font-size:1rem;word-spacing:2px;letter-spacing:2px;"
        >
          아이디와 비밀번호를 확인해주세요.
        </p></v-snackbar
      >
      <div style="height:230px" class="d-block d-md-none" >
      </div>
    </v-container>
  </div>
</template>

<script>
import { AUTH_REQUEST } from "@/store/actions/auth";

export default {
  name: "Login",
  data() {
    return {
      userId: "",
      password: "",
      match: false
    };
  },
  methods: {
    loginRequest() {
      //model에 바인딩된 데이터 모두 ==> this
      const { userId, password } = this;
      this.$store
        .dispatch(AUTH_REQUEST, { userId, password })
        .then(() => {
          this.userId = "";
          this.password = "";
          this.nowlogin = !this.nowlogin;
          this.$router.push("/");
        })
        // .catch(e => {
        //   if (e.request.status === 404) {
        //     this.alertMsg =
        //       "로그인에 실패하였습니다. 아이디와 비밀번호를 확인하세요.";
        //     this.alert = true;
        //   } else {
        //     this.$emit("closeLoginModal");
        //     this.$router.push(`/apierror/${e.request.status}/`);
        // }
        .catch(() => {
          this.match = true;
          this.userId = "";
          this.password = "";
          this.$router.push;
        });
    },
    goSignUp: function() {
      this.$router.push("/signup");
    }
  }
};
</script>

<style>
.deault-header {
  height: 4.7rem;
  width: 100%;
  background-image: url("../../assets/main-smile.jpg");
  background-size:cover;
  /* filter:brightness(70%); */
}
</style>
