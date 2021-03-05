<template>
  <v-app>
    <v-app-bar app class="d-block d-md-none" color="white"
      ><v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      
      <v-btn
        @click="goHome()"
        class="headline"
        color="amber accent-4"
        text
        align-center
        ><img :src="require('@/assets/l1.png')" class="mr-1" width="40px" height="35px"><h3 class="mb-1 mt-1" style="color:black;font-family:'Gamja Flower',cursive;font-weight:bold; font-size:2.2rem;">스마힐</h3></v-btn
      >
    </v-app-bar>

    <v-navigation-drawer
      v-model="drawer"
      relative
      temporary
      app
      fixed
      style="overflow: auto;"
    >
      <div v-if="getProfile" class="text-center">
        <v-btn block large dark depressed class="green-mbtn" @click="logout">
          LOGOUT
        </v-btn>
        <v-list-item>
          <v-list-item-content>
            <v-list-item-title class="title">
              {{ getUserID }} 님
            </v-list-item-title>
            <v-list-item-subtitle>
              환영합니다!
            </v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </div>
      <div v-else class="text-center">
        <v-btn block large dark depressed class="green-mbtn" @click="goLogin">
          LOGIN
        </v-btn>
        <v-list-item>
          <v-list-item-content>
            <v-list-item-title >
              로그인을 해주세요
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </div>
      <v-list nav dense rounded>
        <v-list-item-group
          v-model="group"
          active-class="amber--text text--accent-4"
        >
          <v-list-item @click="goHome()">
            <v-list-item-icon>
              <v-icon>mdi-home</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="font-weight:bold;">메인 화면</v-list-item-title>
          </v-list-item>

          <v-list-item @click="goDonationList()">
            <v-list-item-icon>
              <v-icon>mdi-clipboard-text</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="font-weight:bold;">기부게시판</v-list-item-title>
          </v-list-item>

          <v-list-item @click="goReviewList()">
            <v-list-item-icon>
              <v-icon>mdi-message-draw</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="font-weight:bold;">감사게시판</v-list-item-title>
          </v-list-item>

          <v-list-item @click="goNotice()">
            <v-list-item-icon>
              <v-icon>mdi-clipboard-alert</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="font-weight:bold;">공지사항</v-list-item-title>
          </v-list-item>

          <v-list-item
            v-if="getProfile != '관리자' && getProfile"
            @click="goMyPage"
          >
            <v-list-item-icon>
              <v-icon>mdi-clipboard-account</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="font-weight:bold;">마이페이지</v-list-item-title>
          </v-list-item>

          <v-list-item v-if="getProfile == '관리자'" @click="goAdminPage">
            <v-list-item-icon>
              <v-icon>mdi-clipboard-account</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="font-weight:bold;">관리자페이지</v-list-item-title>
          </v-list-item>

          <!-- <v-list-item v-if="isSmileKing" @click="goAward()">
            <v-list-item-icon>
              <v-icon>mdi-seal</v-icon>
            </v-list-item-icon>
            <v-list-item-title>상장 보기</v-list-item-title>
          </v-list-item> -->
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar flat class="d-none d-md-block" style="position:absolute; z-index:5; background-color:transparent;">
      <v-btn @click="goHome()" class="headline" color="amber accent-4" text
        ><img :src="require('@/assets/l1.png')" class="mr-1 mt-3" width="60px" height="50px"><h3 class="mb-1" style="color:white;font-family:'Gamja Flower',cursive;font-weight:bold; font-size:2.2rem;">스마힐</h3></v-btn
      >
      <v-spacer></v-spacer>

      <!-- <v-btn @click="goDonationBoardDetail()" text>
        <span class="mr-2">ai_video test</span>
      </v-btn> -->

      <!-- <v-btn v-if="isSmileKing" @click="goAward()" text>
        <span class="mr-2 font-weight-bold">상장 보기</span>
      </v-btn> -->

      <v-btn @click="goDonationList()" text>
        <span class="mr-2 nav">기부게시판</span>
      </v-btn>

      <v-btn @click="goReviewList()" text>
        <span class="mr-2 nav">감사게시판</span>
      </v-btn>

      <v-btn @click="goNotice()" text>
        <span class="mr-2 nav">공지사항</span>
      </v-btn>

      <v-btn v-if="getProfile != '관리자' && getProfile" @click="goMyPage" text>
        <span class="mr-2 nav">마이페이지</span>
      </v-btn>

      <v-btn v-if="getProfile == '관리자'" @click="goAdminPage" text>
        <span class="mr-2 nav">관리페이지</span>
      </v-btn>

      <v-btn v-if="getProfile" @click="logout" text>
        <span class="mr-2 nav">LOGOUT</span>
      </v-btn>

      <v-btn v-else @click="goLogin" text>
        <span class="mr-2 nav">LOGIN</span>
      </v-btn>
    </v-app-bar>

    <!-- <v-main> -->
      <router-view />
    <!-- </v-main> -->

    <v-snackbar
      style="font-family: 'Nanum Gothic';"
      shaped
      color="error"
      elevation="24"
      v-model="loginCheck"
      timeout="3000"
    >
      로그인이 필요합니다.
      <template v-slot:action="{ attrs }">
        <v-btn color="white" text v-bind="attrs" @click="loginCheck = false"
          >Close</v-btn
        >
      </template>
    </v-snackbar>

    <v-snackbar
      style="font-family: 'Nanum Gothic';"
      shaped
      color="#356859"
      elevation="24"
      v-model="loginSuccess"
      timeout="3000"
    >
      <v-icon color="white" class="mr-1">mdi-emoticon-excited-outline</v-icon>
      {{ getProfile + "님 반갑습니다." }}
      <template v-slot:action="{ attrs }">
        <v-btn color="white" text v-bind="attrs" @click="loginSuccess = false"
          >Close</v-btn
        >
      </template>
    </v-snackbar>

    <v-snackbar
      style="font-family: 'Nanum Gothic';"
      shaped
      color="#356859"
      elevation="24"
      v-model="logoutSuccess"
      timeout="3000"
    >
      <v-icon color="white" class="mr-1">mdi-check-bold</v-icon>정상적으로
      로그아웃 되었습니다.
      <template v-slot:action="{ attrs }">
        <v-btn color="white" text v-bind="attrs" @click="logoutSuccess = false"
          >Close</v-btn
        >
      </template>
    </v-snackbar>

    <v-flex xs12>
      <v-btn
        v-scroll="onScroll"
        v-show="fab"
        fab
        dark
        fixed
        bottom
        right
        color="#356859"
        @click="toTop"
      >
        <v-icon color="#fffbe6">mdi-apple-keyboard-control</v-icon>
      </v-btn>
    </v-flex>

    <v-tooltip top>
      <template v-slot:activator="{ on, attrs }">
        <v-fab-transition>
          <v-btn
            fab
            large
            bottom
            dark
            left
            class="v-btn--example orange"
            fixed
            v-on="on"
            v-bind="attrs"
            data-toggle="modal"
            data-target="#searchTemplate"
          >
            <v-icon>mdi-table-search</v-icon>
          </v-btn>
        </v-fab-transition>
      </template>
      <span>기부 사진 템플릿 찾기</span>
    </v-tooltip>
    <div class=" d-flex justify-content center" v-show="show">
      <v-container
        class="modal fade mt-5"
        id="searchTemplate"
        tabindex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
        style="color:black"
      >
        <div class="modal-dialog col-12  d-flex justify-content-center">
          <div class="modal-content col-md-8">
            <div class="modal-header ">
              <p
                class="modal-title my-auto ml-2"
                id="exampleModalLabel"
                style="font-weight:bold; font-size:1.3rem;text-align:center;"
              >
                <span
                  >일련번호로 기부 사진 템플릿을 찾아보세요</span
                >
              </p>
              <button
                type="button"
                class="close my-auto"
                data-dismiss="modal"
                aria-label="Close"
                @click="keyword = ''"
              >
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <v-form action="/template/search" @submit="checkForm">
                  <v-text-field
                    placeholder="일련번호를 입력하세요."
                    hide-details
                    outlined
                    color="#356859"
                    clearable
                    name="template"
                    class="mr-2"
                    background-color="white"
                    v-model="keyword"
                    ref="search"
                  />
                  <div align="center" class="my-3" style="font-size:1rem;">
                  <span>당신을 위해 환한 웃음을 지은 사람들입니다.</span><br>
                  <span>부여받은 일련번호로 검색하여 확인하실 수 있습니다.</span>
                  </div>
                  <v-col align="center">
                  <v-btn @click="searchTemplate()" data-dismiss="modal" large class="green-mbtn" style="border-radius:25px;">
                    GO
                  </v-btn>
                  </v-col>
              </v-form>
            </div>
          </div>
        </div>
      </v-container>
    </div>
    <v-footer class="justify-center" color="#292929">
      <div
        class="font-weight-light grey--text text--lighten-1 text-center"
      >
        <div class="py-5"></div>
        &copy; {{ new Date().getFullYear() }} — SMAHEAL — Made with 💜 by
        미찾사<br />
        SMAHEAL 대전광역시 유성구 동서대로 98-39<br />
        대표 이재인 | 사업자번호 111-222-33333<br />
        GitLab
        <a href="https://lab.ssafy.com/s03-ai-sub3/s03p23b108"
          >https://lab.ssafy.com/s03-ai-sub3/s03p23b108</a
        ><br />
        제휴문의 ssafy@ssafy.com | 고객문의 ssafy@ssafy.com / 1111-2222
        (09:00~18:00)
        <div class="py-5"></div>
      </div>
    </v-footer>
  </v-app>
</template>

<script>
import { mapGetters, mapState } from "vuex";
import { AUTH_LOGOUT } from "./store/actions/auth";
import { USER_UPDATE } from "@/store/actions/user";
import http from "@/util/http-common.js";

export default {
  name: "App",
  components: {},
  data() {
    return {
      logoutSuccess: false,
      loginSuccess: false,
      isSmileKing: false,
      drawer: false,
      group: null,
      keyword: "",
      show: true,
      fab: false,
      loginCheck: false,
    };
  },
  created() {
    if(this.getProfile){
      this.$store.dispatch(USER_UPDATE, this.getUserID).then(() => {});
    }
    http
      .get(`/smile/smileKing`)
      .then(res => {
        const smileKing = res.data;
        for (const smile of smileKing) {
          if (smile.userId == this.getUserID) {
            this.isSmileKing = true;
          }
        }
      })
      .catch(err => {
        console.log(err);
      });
  },
  methods: {
    goDonationBoardDetail: function() {
      // 경로 중복 문제가 콘솔에 안 뜨게 하기 위해서 catch 이용
      this.$router.push("/donationDetail").catch(() => {});
    },
    goLogin() {
      this.$router.push("/login").catch(() => {});
    },
    goNotice() {
      this.$router.push("/notice").catch(() => {});
    },
    goHome() {
      this.$router.push("/").catch(() => {});
      scroll(0, 0);
    },
    goDonationList() {
      this.$router.push("/donationList").catch(() => {});
    },
    logout: function() {
      this.$store.dispatch(AUTH_LOGOUT).then(() => {
        this.drawer = false;
      });
      this.$router.push("/").catch(() => {});
    },
    goReviewList() {
      if (this.getProfile == '') {
        this.loginCheck = true;
      } else {
        this.$router.push("/reviewList").catch(() => {});
      }
    },
    goMyPage() {
      this.$router.push("/myPage").catch(() => {});
    },
    goAdminPage() {
      this.$router.push("/adminPage").catch(() => {});
    },
    goSearchTemplate() {
      // this.$router.push("/template").catch(() => {});
    },
    goAward() {
      this.$router.push("/award").catch(() => {});
    },
    searchTemplate: function() {
      if (this.$router.currentRoute.fullPath.substring(0, 16) == "/template/search" && this.keyword != "") {
        this.$router.replace(`/template/search?template=${this.keyword}`).catch(() => {});
        this.keyword = "";
        location.reload();
        } else if (this.$router.currentRoute.fullPath.substring(0, 16) != "/template/search" && this.keyword != "") {
        this.$router.push(`/template/search?template=${this.keyword}`).catch(() => {});
        this.keyword = "";
      }
    },
    checkForm: function(e) {
      if (this.keyword !== "") {
        return true;
      }
      e.preventDefault();
    },
    onScroll (e) {
      if (typeof window === 'undefined') return
      const top = window.pageYOffset ||   e.target.scrollTop || 0
      this.fab = top > 20
    },
    toTop () {
      this.$vuetify.goTo(0)
    }
  },
  computed: {
    ...mapGetters([
      "isAuthenticated",
      "isProfileLoaded",
      "getProfile",
      "getRealName",
      "getUserNum",
      "getUserID",
      "getUserBirth"
    ]),
    ...mapState({
      authLoading: state => state.auth.status === "loading",
      uname: state => `${state.user.getProfile}`,
      userNum: state => `${state.user.getUserNum}`,
      userName: state => `${state.user.getRealName}`,
      userID: state => `${state.user.getUserID}`,
      userBirth: state => `${state.user.getUserBirth}`
    })
  },
  watch: {
    getProfile: function() {
      if (this.getProfile == "") {
        this.logoutSuccess = true;
      } else {
        this.loginSuccess = true;
      }
    },
    group() {
      this.drawer = false;
    }
  }
};
</script>
<style>
#app {
  font-family: "Nanum Gothic", sans-serif;
  font-weight: bold;
}
html {
  scroll-behavior: smooth;
}
.nav{
  font-weight:bold;
  color:white;
  font-size:1rem;
  font-family: 'Nanum Gothic';
}
</style>