<template>
  <v-container fluid class="modal fade mt-5" id="waitDetail" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <!-- 알림 -->
    <v-snackbar v-model="acceptFlag" bottom :timeout="2000" color="#356859" class="mr-3">
      승인이 완료되었습니다.
      <template v-slot:action="{ attrs }">
        <v-btn text v-bind="attrs" @click="acceptFlag = false">Close</v-btn>
      </template>
    </v-snackbar>
    <v-snackbar v-model="rejectFlag" bottom :timeout="2000" color="error" class="mr-3">
      거절되었습니다.
      <template v-slot:action="{ attrs }">
        <v-btn text v-bind="attrs" @click="rejectFlag = false">Close</v-btn>
      </template>
    </v-snackbar>

    <div class="modal-dialog col-12 d-flex justify-content-center">
      <div class="modal-content col-md-6">
        <div class="modal-header">
          <p class="modal-title my-auto ml-2" id="exampleModalLabel" style="font-weight:bold; font-size:1.3rem;text-align:center;">
            <span style="color:#356859">{{ wait.title }}</span>
          </p>
          <button type="button" class="close my-auto" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <!-- 사진 carousel -->
          <v-row justify="center">
            <v-col cols="11" class="p-0">
              <v-carousel hide-delimiters width="100%" height="300px">
                <v-carousel-item v-for="(item, i) in waitImages" :key="i" reverse-transition="fade-transition" transition="fade-transition">
                  <img :src="getImg(item)" style="height:100%; width:100%;" />
                </v-carousel-item>
              </v-carousel>

              <v-divider></v-divider>
              <v-row>
                <v-col cols="3" class="text-left my-auto py-0">
                  <v-icon style="margin-bottom:5px;margin-right:10px;color:#356859">mdi-label-outline</v-icon>
                  <span style="font-size:1.1rem; font-weight:bold;">카테고리</span>
                </v-col>
                <v-col cols="9" class="text-left pl-10 my-auto py-0">
                  <span style="font-size:1rem;">{{ wait.category }}</span>
                </v-col>
              </v-row>
              <v-divider></v-divider>
              <v-row>
                <v-col cols="3" class="text-left my-auto py-0">
                  <v-icon style="margin-bottom:5px;margin-right:10px;color:#356859">mdi-account-edit-outline</v-icon>
                  <span style="font-size:1.1rem; font-weight:bold;">작성자</span>
                </v-col>
                <v-col cols="9" class="text-left pl-10 my-auto py-0">
                  <span style="font-size:1rem;">{{ wait.writer }}</span>
                </v-col>
              </v-row>
              <v-divider></v-divider>
              <v-row>
                <v-col cols="3" class="text-left my-auto py-0">
                  <v-icon style="margin-bottom:5px;margin-right:10px;color:#356859">mdi-calendar</v-icon>
                  <span style="font-size:1.1rem; font-weight:bold;">마감일</span>
                </v-col>
                <v-col cols="9" class="text-left pl-10 my-auto py-0">
                  <span style="font-size:1rem;">{{ wait.edate }}</span>
                </v-col>
              </v-row>
              <v-divider></v-divider>
              <v-row>
                <v-col cols="3" class="text-left my-auto py-0">
                  <v-icon style="margin-bottom:5px;margin-right:10px;color:#356859">mdi-numeric-9-plus-box-multiple-outline</v-icon>
                  <span style="font-size:1.1rem; font-weight:bold;">마감개수</span>
                </v-col>
                <v-col cols="9" class="text-left pl-10 my-auto py-0">
                  <span style="font-size:1rem;">{{ wait.maxcnt }}장</span>
                </v-col>
              </v-row>
              <v-divider></v-divider>
              <v-icon style="margin-bottom:5px;margin-right:10px;color:#356859">mdi-comment-text-multiple-outline</v-icon>
              <span style="font-size:1.1rem; font-weight:bold;">상세설명</span>

              <p style="border:1px solid black;padding:10px;border-radius:10px;font-size:1rem;letter-spacing:1px;word-spacing:3px;line-height:160%;" class="mt-3">{{ wait.content }}</p>
            </v-col>
          </v-row>
        </div>
        <div class="modal-footer d-flex justify-content-end py-3 px-7">
          <!--Dialog-->
          <!-- <v-dialog v-model="dialog" persistent max-width="290">
            <template v-slot:activator="{ on }"> -->
          <v-btn color="light-blue darken-4" @click="accept()" style="color:white;font-weight:bold;">승인</v-btn>
          <v-btn color="error" @click="reject()" style="font-weight:bold;">거절</v-btn>
          <!-- </template>
            <v-card>
              <v-card-title class="headline"> 거절하시겠습니까? </v-card-title>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="green darken-1" text @click="dialog = false">
                  예
                </v-btn>
                <v-btn color="green darken-1" text @click="dialog = false">
                  아니오
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog> -->
        </div>
      </div>
    </div>
  </v-container>
</template>

<script>
import http from '@/util/http-common.js';

export default {
  data() {
    return {
      acceptFlag: false,
      rejectFlag: false,
      dialog: false,
    };
  },
  props: {
    wait: [Object, Array],
    waitImages: [Object, Array],
  },
  created() {},
  methods: {
    getImg(img) {
      return '../../../contents/' + img;
    },
    accept() {
      http
        .put('/donation/approve/' + this.wait.donationid)
        .then(() => {
          this.acceptFlag = true;
          setTimeout(() => {
            this.$router.go().catch((err) => {
              console.log(err);
            });
          }, 1000);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    reject() {
      http
        .delete('/donation/delete/' + this.wait.donationid)
        .then(() => {
          this.rejectFlag = true;
          setTimeout(() => {
            this.$router.go().catch((err) => {
              console.log(err);
            });
          }, 1000);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style></style>
