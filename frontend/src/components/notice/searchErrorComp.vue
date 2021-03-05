<template>
<div class="col-md-12 p-0">
    <div class="n_header_img d-flex justify-content-center">
      <div
        style="color:white; position:absolute; top:200px; font-size:3rem;"
      >
        공지사항
     </div>
    </div>
    <div
      class="d-flex justify-content-center"
      style="background-color:rgb(188,150,39)"
    >
  <v-container class="col-md-11">
    <v-card color="#fffbe6">
      <!-- <v-row style="color:#356859;font-size:1.3rem;" class="p-4">
        
        <span class="pl-5" style="display:inline-flex;vertical-align:middle"><v-icon class="mr-1" color="#356859"
          >mdi-clipboard-alert-outline</v-icon
        >공지사항</span>
      </v-row> -->
      <v-row>
        <v-col>
          <v-spacer />
        </v-col>
        <v-col class="mr-2">
          <v-form action="/notice/search" @submit="checkForm">
            <v-row class="mr-1">
              <v-text-field
              hide-details
                placeholder="제목으로 검색됩니다.. 검색어를 입력하세요."
                outlined
                color="#356859"
                clearable
                v-model="title"
                ref="search"
                name="title"
                prepend-inner-icon="mdi-text-search"
              />
            </v-row>
          </v-form>
        </v-col>
      </v-row>
    </v-card>
    <v-card>
    <v-row class="text-center" style="height:200px;">
      <v-col style="font-size:1rem;" class="mt-8">
        검색어 "{{this.keyword}}"에 해당하는 결과가 없습니다.
      </v-col>
    </v-row>
    </v-card>
    <v-row>
        <v-col>
            <v-btn @click="goBack()" color="#356859" dark>
                전체 목록보기
            </v-btn>
        </v-col>
      <v-col class="text-right">
        <v-btn @click="goNoticeRegist()" color="#356859" dark v-if="getUserID == 'admin'">
        글 작성하기
      </v-btn>
      </v-col>
    </v-row>
  </v-container>
    </div>
</div>
</template>

<script>
import { mapGetters, mapState } from "vuex";

export default {
  name: "searchErrorComp",
  props: {
    propKeyword: {type: String},
  },
  data(){
   return {
     keyword: this.propKeyword,
     title: "",
   };
  },
  methods: {
      goNoticeRegist: function() {
        this.$router.push('/notice/write')
      },
      goBack: function() {
          this.$router.push('/notice').catch(() => {})
      },
      checkForm: function(e) {
        if (this.title !== "") {
          return true;
        }
        e.preventDefault();
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
};
</script>
<style>
.n_header_img {
  background: url("../../assets/notice_hd.jpg") no-repeat;
  width: 100%;
  height: 20rem;
  display: block;
  background-position: center;
  background-size: cover;
}
</style>