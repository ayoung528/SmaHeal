<template>
  <div>
    <div
      class="col-12 col-sm-6 col-md-3"
      v-for="(smile, index) in smileList"
      :key="index"
    >
      <v-hover v-slot:default="{ hover }">
        <v-card :elevation="hover ? 12 : 2" :class="{ 'on-hover': hover }">
          <v-img :src="getImg(smile.photo)" height="200px" class="my-auto">
            <div style="height:70%"></div>
            <v-card-title class="title white--text" style="height:30%">
              <v-row class="fill-height" justify="space-between">
                <v-btn
                  :class="{ 'show-btns': hover }"
                  color="rgba(255, 255, 255, 0)"
                  style="width:30%;"
                  outlined
                  data-toggle="modal"
                  data-target="#mySmileDetail"
                  @click="goDetail(smile)"
                >
                  상세정보
                </v-btn>
              </v-row>
            </v-card-title>
          </v-img>
        </v-card>
      </v-hover>
    </div>
    <div v-if="this.smileList.length == 0">
      <v-row class="text-center ma-5">
        <v-col class="text-h2 font-weight-bold">
          💬 Not Yet..
        </v-col>
      </v-row>
      <v-row class="text-center">
        <v-col class="text-subtitle-1 black--text">
          아직 웃음기부를 하지 않으셨습니다.
        </v-col>
      </v-row>
      <v-row class="text-center">
        <v-col class="text-subtitle-1 black--text">
          서로가 행복해질 수 있도록 웃음을 기부해보세요 :)
        </v-col>
      </v-row>
      <v-row class="text-center">
        <v-col>
          <v-btn outlined color="#356859" class="font-weight-bold" @click="goDonationList()">
            웃음 기부하러 가기
          </v-btn>
        </v-col>
      </v-row>
    </div>
    <div class="text-center m-3" v-else>
      <v-pagination
        v-model="page"
        :length="length"
        color="#356859"
        prev-icon="mdi-menu-left"
        next-icon="mdi-menu-right"
      ></v-pagination>
    </div>
    <div
      class="modal fade mt-5 pl-15 pr-15"
      id="mySmileDetail"
      tabindex="-1"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
      style="color:black"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header ">
            <p
              class="modal-title my-auto ml-2"
              id="exampleModalLabel"
              style="font-weight:bold; font-size:1.3rem; "
            >
              <span style="color:#356859">{{ nickname }}</span>
              <span style="font-size:1rem;">님의 웃음기부 상세정보</span>
            </p>
            <button
              type="button"
              class="close my-auto"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <p class="detail-title">
              <v-icon color="black" class="mr-1">mdi-hand-heart-outline</v-icon
              >기부 게시물
            </p>
            <p style="font-size:1rem; color: gray">{{ detailSmile.title }}</p>
            <p class="detail-title">
              <v-icon color="black" class="mr-1">mdi-arrow-top-right</v-icon
              >웃음 지수
            </p>
            <p style="font-size:1rem; color: gray">
              {{ detailSmile.smileper }} %
            </p>
            <p class="detail-title">
              <v-icon color="black" class="mr-1"
                >mdi-message-processing-outline</v-icon
              >한줄 메세지
            </p>
            <p style="font-size:1rem; color: gray">
              {{ detailSmile.comment }}
            </p>
            <p class="detail-title">
              <v-icon color="black" class="mr-1">mdi-clock-outline</v-icon>기부
              일자
            </p>
            <p style="font-size:1rem; color: gray">
              {{ detailSmile.createdate }}
            </p>
          </div>
          <!-- <div class="modal-footer d-flex justify-content-end py-1">
             푸터
            </div> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
import { mapGetters, mapState } from "vuex";
export default {
  data() {
    return {
      smileList: [],
      detailSmile: [],
      nickname: "",
      page: 1,
      length: 0
    };
  },
  created() {
    this.nickname = this.getProfile;
    this.init();
    http
        .get(`/smile/getMySmileCnt/${this.getUserID}`)
        .then(res => {
          if (res.data % 8 == 0) {
            this.length = Math.floor(res.data / 8);
          } else {
            this.length = Math.floor(res.data / 8) + 1;
          }
        })
        .catch(err => {
          console.log(err);
        });
  },
  methods: {
    init() {
      http
        .get(`/smile/getMySmile/${this.getUserID}/${this.page - 1}`)
        .then(res => {
          this.smileList = res.data;
        })
        .catch(err => {
          console.log(err);
        });
      
    },
    getImg(img) {
      return "../../../images/" + img;
    },
    goDetail(smile) {
      this.detailSmile = smile;
    },
    goPage() {},
    goDonationList: function() {
      this.$router.push('/donationList');
    },
  },
  computed: {
    ...mapGetters(["getUserID", "getProfile"]),
    ...mapState({
      userID: state => `${state.user.getUserID}`,
      uname: state => `${state.user.getProfile}`
    })
  },
  watch: {
    page() {
      this.init();
    }
  }
};
</script>
<style scoped>
.smile-img {
  height: 200px !important;
  width: 100% !important;
  /* object-fit: cover; */
  transform: scale(1);
  transition: all 0.3s ease-in-out;
}
.v-card {
  transition: opacity 0.4s ease-in-out;
}

.v-card:not(.on-hover) {
  opacity: 0.6;
}

.show-btns {
  color: black !important;
  background-color: white !important;
  font-family: "Nanum Gothic", sans-serif;
  font-weight: bold;
  border: 1px solid white;
}
.detail-title {
  font-size: 1.2rem;
  margin: 0;
  vertical-align: middle;
  display: inline-flex;
}
</style>