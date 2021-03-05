<template>
  <div align="center">
    <div style="font-size:1rem; color:black;" class="m-5">
      <div class="mb-3">
        <span style="font-size:2rem;">
          축하합니다! 이달의 웃음왕에 선정되셨습니다.<br />
        </span>
      </div>
      <span style="font-size:1.2rem;">
        스마힐 웃음 기부에 참여해주셔서 감사합니다. <br />주소를 입력하시면
        회원님의 웃음사진이 담긴 액자와 스마힐 상장이 배송됩니다.
      </span>
      <v-col cols="9" class="pt-0 pb-0 mt-5">
        <v-col class="d-flex justify-content-between p-0 align-items-top">
          <v-text-field
            v-model="addr"
            outlined
            dense
            class="mr-2"
            readonly
            placeholder="우편번호 찾기를 클릭하세요"
          ></v-text-field>
          <v-text-field
            v-model="addrDetail"
            label="상세주소"
            outlined
            dense
            class="mr-2"
          ></v-text-field>
          <v-btn
            @click="searchAddr()"
            outlined
            style="border:2px solid lightgray; font-weight:bold; margin-top:2px; border-radius:15px;"
            >우편번호 찾기</v-btn
          >
        </v-col>
      </v-col>
      <v-row class="d-flex justify-content-center">
        <v-btn class="green-mbtn mr-2" @click="submitAddr()">주소 제출</v-btn>
        <v-btn class="green-mbtn" data-toggle="modal" data-target="#goAward"
          ><v-icon>mdi-medal</v-icon>상장 미리보기</v-btn
        >
      </v-row>
    </div>

    <div
      class="modal fade mt-5"
      id="goAward"
      tabindex="-1"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
      style="color:black"
    >
      <div class="modal-dialog container">
        <div class="modal-content">
          <div class="modal-header p-0 pr-5">
            <p
              class="modal-title my-auto ml-2"
              id="exampleModalLabel"
              style="font-weight:bold; font-size:1.3rem; "
            ></p>
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
            <v-card width="800px">
              <v-img width="100%" :src="getImg()"
                ><v-container
                  style="width: 100%; height: 100%; top:10%; font-size: 2em; font-family: 궁서; font-weight: bold; position: absolute;"
                >
                  <v-row>
                    <v-col align="left" style="left:15%">
                      제 2020-10호
                    </v-col>
                  </v-row>
                  <br />
                  <v-row>
                    <v-col align="center" style="font-size: 2.2em;">
                      웃 음 왕
                    </v-col>
                  </v-row>
                  <br /><br />
                  <v-row>
                    <v-col align="left" style="left:15%;">
                      {{ thisAward }}
                    </v-col>
                    <v-col align="right" style="right:15%">
                      {{ smileKing.userId }}
                    </v-col>
                  </v-row>
                  <br /><br />
                  <v-row>
                    <v-col align="center" style="left:17%;" cols="8">
                      위 회원은 SMAHEAL 2020년 10월 웃음 기부에서 뛰어난 웃음
                      지수로 위와 같이 수상하였기에 이 상을 수여합니다.
                    </v-col>
                  </v-row>
                  <br /><br />
                  <v-row>
                    <v-col align="center">
                      2020년 10월 1일
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col align="center">
                      SMAHEAL 운영진
                    </v-col>
                  </v-row>
                </v-container>
              </v-img>
            </v-card>
          </div>
        </div>
      </div>
    </div>
    <v-snackbar v-model="addrFlag" bottom :timeout="2000" color="#356859">
      주소가 제출되었습니다.
      <template v-slot:action="{ attrs }">
        <v-btn text v-bind="attrs" @click="addrFlag = false">Close</v-btn>
      </template>
    </v-snackbar>
    <v-snackbar v-model="noFlag" bottom :timeout="2000" color="error">
      주소를 입력하세요.
      <template v-slot:action="{ attrs }">
        <v-btn text v-bind="attrs" @click="noFlag = false">Close</v-btn>
      </template>
    </v-snackbar>
  </div>
</template>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
import http from "@/util/http-common.js";
import { mapGetters } from "vuex";

export default {
  name: "AwardComp",
  data() {
    return {
      publicPath: process.env.BASE_URL,
      smileKing: {},
      award: ["금상", "은상", "동상"],
      thisAward: "",
      addr: "",
      addrDetail: "",
      dialog: false,
      addrFlag: false,
      noFlag: false
    };
  },
  created() {
    http
      .get(`/smile/smileKing`)
      .then(res => {
        const smileKing = res.data;
        var cnt = 0;
        for (const smile of smileKing) {
          if (smile.userId == this.getUserID) {
            this.smileKing = smile;
            this.thisAward = this.award[cnt];
          }
          cnt++;
        }
      })
      .catch(err => {
        console.log(err);
      });
  },
  computed: {
    ...mapGetters(["getUserID"])
  },
  methods: {
    searchAddr() {
      let x = this;
      new daum.Postcode({
        oncomplete: function(data) {
          //   x.addr1 = data.zonecode;
          x.addr = data.address;
          x.addrDetail = data.buildingName;
        }
      }).open();
    },
    submitAddr() {
      if (this.addr.length == 0 || this.addrDetail.length == 0) {
        this.noFlag = true;
      } else {
        this.addrFlag = true;
        setTimeout(() => {
            this.$router.go().catch((err) => {
              console.log(err);
            });
          }, 1000);

      }
    },
    getImg() {
      return "../../../images/award.jpg"
    },
  }
};
</script>

<style>
</style>