<template>
  <div class="p-3" align="center">
    <v-simple-table style="text-align:center; width:90%" v-if="items.length != 0">
      <thead>
        <tr style="background-color:#fffbe6;">
          <td>No</td>
          <td>제목</td>
          <td>작성 날짜</td>
          <td>일련번호</td>
          <td>승인여부</td>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, index) in items" :key="index">
          <td style="width:10%">{{ (page-1)*8+index + 1 }}</td>
          <td
            style="width:40%; text-overflow:ellipsis; overflow: hidden; white-space: nowrap;"
          >
            {{ item.title }}
          </td>
          <td style="width:20%">{{ getFormatDate(item.createdate) }}</td>
          <td style="width:20%;">
            <v-btn small class="green-mbtn" @click="setDonation(item)"
              >조회</v-btn
            >
          </td>
          <td style="width:30%;" v-if="item.approval == 1 || item.approval == 2" class="green--text">
            승인
          </td>
          <td style="width:30%;" v-else class="red--text">
            대기
          </td>
        </tr>
      </tbody>
    </v-simple-table>
    <div class="text-center m-3" v-if="items.length != 0">
      <v-pagination
        v-model="page"
        :length="length"
        color="#356859"
        prev-icon="mdi-menu-left"
        next-icon="mdi-menu-right"
      ></v-pagination>
    </div>
    <div v-else>
      <v-row class="text-center ma-5">
        <v-col class="text-h2 font-weight-bold">
          💬 Not Yet..
        </v-col>
      </v-row>
      <v-row class="text-center">
        <v-col class="text-subtitle-1 black--text">
          기부를 요청한 게시물이 없습니다.
        </v-col>
      </v-row>
      <v-row class="text-center">
        <v-col class="text-subtitle-1 black--text">
          웃음이 필요하다면 기부요청을 해보세요! 기부된 사진들을 일련번호 조회를 통해 받아보실 수 있습니다 :)
        </v-col>
      </v-row>
      <v-row class="text-center">
        <v-col>
          <v-btn outlined color="#356859" class="font-weight-bold" @click="goDonationList()">
            기부 요청하러 가기
          </v-btn>
        </v-col>
      </v-row>
    </div>
    <v-row justify="center">
      <v-dialog v-model="dialog" persistent max-width="500">
        <v-card>
          <v-card-title style="font-size:1.5rem; font-weight:bold;">
            게시물 일련번호
          </v-card-title>
          <v-card-text v-if="detailItem.template" class="py-3">
           <p style="font-size:1.2rem; font-weight:bold; color:black;" class="m-0 mb-2">{{ detailItem.template }}</p>
           <span style="font-weight:bold;">위 일련번호를 통해 기부된 웃음 사진으로 제작된 템플릿을 볼 수 있습니다.
           아래 <v-btn icon class="v-btn--example orange" dark ><v-icon>mdi-table-search</v-icon></v-btn> 버튼을 눌러 검색하세요!</span>
          </v-card-text>
          <v-card-text v-if="!detailItem.template">
            게시물이 마감되면 일련번호가 부여됩니다.
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn @click="dialog = false" text style="font-weight:bold;">
              확인
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
import moment from "moment";
import http from "@/util/http-common.js";

export default {
  name: "DonationList",
  props: {
    propNickname: { type: String }
  },
  data() {
    return {
      items: [],
      dialog: false,
      detailItem: [],
      page: 1,
      length: 0
    };
  },
  created() {
    this.init();
    http
      .get(`/donation/mypage/getListCnt/${this.propNickname}`)
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
    init(){
      http
      .get(`/donation/mypage/getList/${this.propNickname}/${this.page - 1}`)
      .then(({ data }) => {
        this.items = data;
      });
    },
    getFormatDate: function(regtime) {
      return moment(new Date(regtime)).format("YYYY년 MM월 DD일");
    },
    setDonation(donation) {
      this.dialog = true;
      this.detailItem = donation;
    },
    goDonationList: function() {
      this.$router.push('/donationList');
    },
  },
  watch:{
    page(){
      this.init();
    }
  }
};
</script>