<template>
  <div align="center">
    <h2
      class="font-weight-bold"
      style=" font-size:3rem;display:inline-flex;vertical-align:middel"
    >
      <v-icon style="font-size:3rem;color:#356859;" class="mr-2"
        >mdi-party-popper</v-icon
      >{{lastMonth}}월의 웃음왕<v-icon style="font-size:3rem;color:#356859" class="ml-2"
        >mdi-party-popper</v-icon
      >
    </h2>
    <h2 class="font-weight-bold mt-0 mb-5" style=" font-size:1.3rem;">
      액자와 상장이 배달됩니다. 마이페이지를 확인해보세요!
    </h2>
    <v-container>
      <v-row justify="center">
        <v-col cols="12" md="4" class="p-0 pt-4">
          <v-avatar size="300">
            <v-hover v-slot:default="{ hover }">
              <v-img :src="getImg(smileKing[0].photo)">
                <v-expand-transition>
                  <div
                    v-if="hover"
                    class="transition-fast-in-fast-out black v-card--reveal white--text"
                    style="height: 100%; font-size:2rem;"
                  >
                  <v-row class="d-flex justify-content-center" style="margin-top:90px;">
                  <v-col cols="6">
                    웃음지수
                  </v-col>
                  <v-col cols="4">
                    {{smileKing[0].smileper}}%
                  </v-col>
                  </v-row>
                  <v-row class="d-flex justify-content-center">
                  <v-col cols="6">
                    기부횟수
                  </v-col>
                  <v-col cols="4">
                    {{no1cnt}}회
                  </v-col>
                  </v-row>
                  </div>
                </v-expand-transition>
              </v-img>
            </v-hover>
          </v-avatar>
          <!-- <v-divider class="m-0 mt-4"></v-divider> -->
          <v-col class="d-flex justify-content-center">
            <img :src="getImg1()" style="width:80px;" />
            <span style="font-size:3rem;">{{ smileKing[0].userId }}</span>
          </v-col>
        </v-col>
        <!-- </v-row>
      <v-row justify="center"> -->
        <v-col cols="12" md="4" class="p-0 pt-4" style="border-left:none;">
          <v-avatar size="300">
            <v-hover v-slot:default="{ hover }">
            <v-img :src="getImg(smileKing[1].photo)">
            <v-expand-transition>
                  <div
                    v-if="hover"
                    class="transition-fast-in-fast-out black v-card--reveal white--text"
                    style="height: 100%; font-size:2rem;"
                  >
                  <v-row class="d-flex justify-content-center" style="margin-top:90px;">
                  <v-col cols="6">
                    웃음지수
                  </v-col>
                  <v-col cols="4">
                    {{smileKing[1].smileper}}%
                  </v-col>
                  </v-row>
                  <v-row class="d-flex justify-content-center">
                  <v-col cols="6">
                    기부횟수
                  </v-col>
                  <v-col cols="4">
                    {{no2cnt}}회
                  </v-col>
                  </v-row>
                  </div>
                </v-expand-transition>
            </v-img>
            </v-hover>
          </v-avatar>
          <!-- <v-divider class="m-0 mt-4"></v-divider> -->
          <v-col class="d-flex justify-content-center">
            <img width="80px" :src="getImg2()" />
            <span style="font-size:3rem;">{{ smileKing[1].userId }}</span>
          </v-col>
        </v-col>
        <v-col cols="12" md="4" class="p-0 pt-4 " style="border-left:none;">
          <v-avatar size="300">
            <v-hover v-slot:default="{ hover }">
            <v-img :src="getImg(smileKing[2].photo)">
            <v-expand-transition>
                  <div
                    v-if="hover"
                    class="transition-fast-in-fast-out black v-card--reveal white--text"
                    style="height: 100%; font-size:2rem;"
                  >
                  <v-row class="d-flex justify-content-center" style="margin-top:90px;">
                  <v-col cols="6">
                    웃음지수
                  </v-col>
                  <v-col cols="4">
                    {{smileKing[2].smileper}}%
                  </v-col>
                  </v-row>
                  <v-row class="d-flex justify-content-center">
                  <v-col cols="6">
                    기부횟수
                  </v-col>
                  <v-col cols="4">
                    {{no3cnt}}회
                  </v-col>
                  </v-row>
                  </div>
                </v-expand-transition>
            </v-img>
            </v-hover>
          </v-avatar>
          <!-- <v-divider class="m-0 mt-4"></v-divider> -->
          <v-col class="d-flex justify-content-center">
            <img width="80px" :src="getImg3()" />
            <span style="font-size:3rem;">{{ smileKing[2].userId }}</span>
          </v-col>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import http from "@/util/http-common.js";

export default {
  name: "SmileKingComp",
  data() {
    return {
      publicPath: process.env.BASE_URL,
      smileKing: [
        {
          userId: "",
          photo: ""
        },
        {
          userId: "",
          photo: ""
        },
        {
          userId: "",
          photo: ""
        }
      ],
      no1cnt:0,
      no2cnt:0,
      no3cnt:0,
      lastMonth: null,
    };
  },
  created() {
    this.lastMonth = new Date().getMonth();
    http
      .get(`/smile/smileKing`)
      .then(res => {
        this.smileKing = res.data;
        this.cntSmile();
      })
      .catch(err => {
        console.log(err);
      });
  },
  methods:{
    cntSmile(){
      http
      .get(`/smile/getMySmileCnt/${this.smileKing[0].userId}`)
      .then(res => {
        this.no1cnt=res.data;
      })
      .catch(err => {
        console.log(err);
      });
      http
      .get(`/smile/getMySmileCnt/${this.smileKing[1].userId}`)
      .then(res => {
        this.no2cnt=res.data;
      })
      .catch(err => {
        console.log(err);
      });
      http
      .get(`/smile/getMySmileCnt/${this.smileKing[2].userId}`)
      .then(res => {
        this.no3cnt=res.data;
      })
      .catch(err => {
        console.log(err);
      });
      
    },
    getImg(img) {
      return "../../../images/" + img;
    },
    getImg1() {
      return "../../../images/no1.png";
    },
    getImg2() {
      return "../../../images/no2.png";
    },
    getImg3() {
      return "../../../images/no3.png";
    },
  }
};
</script>

<style>
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: 0.5;
  position: absolute;
  width: 100%;
}
</style>