<template>
  <div>
    <div class="rd_header_img justify-content-center d-none d-md-block"></div>
    <div class="container d-flex justify-content-center col-12">
      <div class="container mx-auto col-10 d-flex justify-content-center">
        <v-card class="mt-12 mb-3 col-10">
          <!-- 이미지 -->
          <img :src="image" width="100%" max-width="100%">
          <!-- 제목 -->
          <v-card-title style="font-weight:bold" class="mt-2">{{ item.title }}</v-card-title>
          <!-- 내용 -->
          <v-card-text>
            <v-row class="px-3">
              <div style="width:100%; text-align:right;">
                <h4 style="display:inline-block;cursor:pointer;font-weight:bold;">by {{ item.nickName }}</h4>
                <p class="text-right" style="color:gray;font-weight:bold;">{{ item.visit }} views · {{ getFormatDate(item.createdAt) }}</p>
              </div>
            </v-row>
            <!-- 좋아요 -->
            <div v-if="!likeFlag" class="mb-4 subtitle-1">
              <v-btn icon @click="upLike()"><v-icon>mdi-hand-heart-outline</v-icon></v-btn>
              {{ item.likeCnt }}
            </div>
            <div v-if="likeFlag" class="mb-4 subtitle-1">
              <v-btn icon @click="downLike()"><v-icon>mdi-hand-heart</v-icon></v-btn>
              {{ item.likeCnt }}
            </div>
            <v-textarea readonly auto-grow solo
            flat
            class="pa-3"
            :value="item.content"
            style="font-size:1rem;font-weight:bold;letter-spacing:1px;word-spacing:2px;line-height:200%;"
            >
            </v-textarea>
            <!-- <div style="font-size:1rem;font-weight:bold;letter-spacing:1px;word-spacing:2px;line-height:200%;">{{ item.content }}</div> -->
          </v-card-text>
          <!-- 버튼 -->
          <v-divider class="mx-4 mb-2"></v-divider>
          <div style="width:100%; text-align:center;">
            <v-btn class="mx-5" v-if="isWriter" @click="moveModify" icon fab large color="basil">
              <v-icon>mdi-pencil</v-icon>
            </v-btn>
            <v-btn class="mx-5" v-if="isWriter" @click="del = true" icon fab large color="basil">
              <v-icon>mdi-delete</v-icon>
            </v-btn>
            <v-btn class="mx-5" @click="goBack" icon fab large color="basil">
              <v-icon>mdi-undo</v-icon>
            </v-btn>
          </div>
        </v-card>

        <v-dialog v-model="del" max-width="400">
          <v-card>
            <v-card-title class="headline">정말 삭제하시겠습니까?</v-card-title>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn text @click="(del = false), deleteReview()" color="#DC143C">확인</v-btn>
              <v-btn text @click="del = false">취소</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </div>
      <v-snackbar v-model="delFlag" top right color="error" :timeout="2000"><p class="snackText2">삭제되었습니다.</p></v-snackbar>
    </div>
  </div>
</template>

<script>
import http from '@/util/http-common.js';
import { mapGetters } from 'vuex';
import moment from 'moment';

export default {
  name: 'ReviewDetail',
  data() {
    return {
      item: {},
      isWriter: false,
      publicPath: process.env.BASE_URL,
      del: false,
      likeFlag: false,
      log: '',
      delFlag: false,
      image: '',
    };
  },
  created() {
    http
      .get(`/review/get/${this.$route.params.num}`)
      .then(({ data }) => {
        this.item = data;
        this.image = this.getImg(this.item.img);
        if (this.getProfile == this.item.nickName) {
          this.isWriter = true;
        }
        this.getLike();
      })
      .catch((err) => {
        console.log(err);
      });
  },
  computed: {
    ...mapGetters(['getProfile']),
  },
  methods: {
    getFormatDate(regtime) {
      return moment(new Date(regtime)).format('YYYY년 MM월 DD일');
    },
    deleteReview() {
      http
        .delete(`/review/delete/${this.$route.params.num}`)
        .then(({ data }) => {
          if (data == 'success') {
            this.delFlag = true;
            setTimeout(() => {
              this.$router.push("/reviewList").catch(err => {
                console.log(err);
              });
            }, 1500);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    moveModify() {
      this.$router.push(`/reviewModify/${this.$route.params.num}`);
    },
    goBack() {
      window.history.back()
      // this.$router.push(`/reviewList`);
    },
    getLike() {
      http.get(`/like/getLike/${this.getProfile}/${this.item.num}`).then((res) => {
        // console.log(res.data);
        if (res.data != null && res.data != '') {
          this.likeFlag = true;
        } else {
          this.likeFlag = false;
        }
      });
    },
    downLike() {
      http
        .delete(`/like/deleteLike/${this.getProfile}/${this.item.num}`)
        .then((res) => {
          this.log = res.data;
          this.likeFlag = false;
          this.item.likeCnt -= 1;
          this.likeUpdate();
        })
        .catch((err) => {
          console.log(err);
        });
    },
    upLike() {
      http
        .post(`/like/pushLike/`, {
          nickname: this.getProfile,
          num: this.item.num,
        })
        .then((res) => {
          this.log = res.data;
          this.likeFlag = true;
          this.item.likeCnt += 1;
          this.likeUpdate();
        })
        .catch((err) => {
          console.log(err);
        });
    },
    likeUpdate() {
      http
        .put(`/review/update/${this.$route.params.num}`, {
          num: this.item.num,
          title: this.item.title,
          content: this.item.content,
          nickName: this.item.nickName,
          img: this.item.img,
          likeCnt: this.item.likeCnt,
          visit: this.item.visit,
          createdAt: this.item.createdAt,
          donationid: this.item.donationid,
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getImg(img) {
      return "../../../contents/" + img;
    },
  },
};
</script>

<style scoped>
.snackText2 {
  margin-bottom:0;
  font-weight:bold;
  font-size:1rem;
  word-spacing:2px;
  letter-spacing:2px;
  color: #dc143c;
}
.rd_header_img {
  background: url("../../assets/donation_hd.jpg") no-repeat;
  width: 100%;
  height: 5rem;
  display: block;
  background-position: center;
  background-size: cover;
}
</style>
