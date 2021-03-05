<template>
  <div style="background-color:#fffbe6;">
    <div class="container d-flex justify-content-center">
      <v-card class="m-5 col-xl-7 col-lg-8 col-md-10 col-sm-11 col-12">
        <v-form>
          <v-container fluid>
            <v-row justify="space-around">
              <v-col cols="12" md="9">
                <div class="text-center">
                <p style="font-size:2rem;"><v-icon class="mr-2" style="font-size:2rem;color:black;">mdi-account-heart-outline</v-icon>감사인사 남기기<v-icon class="ml-2" style="font-size:2rem;color:black;">mdi-account-heart-outline</v-icon></p>
                </div>
                <input ref="imageInput" type="file" hidden @change="onChangeImages" />
                <v-btn
                  class="mb-2 green-mbtn"
                  style="font-weight:bold; border-radius:15px;"
                  type="button"
                  @click="onClickImageUpload"
                  outlined
                  >이미지 업로드</v-btn
                >
                <div style="width:20rem;height:15rem;max-width:100%;border-radius:5px;border:1px solid #356859">
                  <img v-if="imageUrl" :src="imageUrl" style="width:20rem;height:15rem;border-radius:5px;" />
                </div>
                <v-text-field class="mt-7" v-model="title" label="제목" required></v-text-field>
              </v-col>
            </v-row>
            <v-row justify="space-around">
              <v-col cols="12" md="9">
                <v-textarea auto-grow v-model="content" label="내용" outlined required rows="10" row-height="30"></v-textarea>
              </v-col>
            </v-row>
          </v-container>
        </v-form>
        <div style="text-align:center;">
          <v-btn class="mx-5" v-if="isModify" @click="modi = true" icon fab large color="basil">
            <v-icon>mdi-pencil</v-icon>
          </v-btn>
          <v-btn v-else @click="writ = true" class="mx-5" icon fab large color="basil">
            <v-icon>mdi-upload</v-icon>
          </v-btn>
          <v-btn class="mx-5" @click="goBack" icon fab large color="basil">
            <v-icon>mdi-undo</v-icon>
          </v-btn>
        </div>
      </v-card>
    </div>
    <!-- 수정확인 -->
    <v-dialog v-model="modi" max-width="400">
      <v-card>
        <v-card-title class="headline">수정하시겠습니까?</v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn text @click="(modi = false), updateReview()" color="#DC143C">확인</v-btn>
          <v-btn text @click="modi = false">취소</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <!-- 등록 확인 -->
    <v-dialog v-model="writ" max-width="400">
      <v-card>
        <v-card-title class="headline">후기를 등록하시겠습니까?</v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn text @click="(writ = false), writeReview()" color="#DC143C">확인</v-btn>
          <v-btn text @click="writ = false">취소</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <!-- alert -->
    <v-snackbar v-model="badPath" top right color="error" :timeout="2000"><p class="snackText3">잘못된 경로입니다.</p></v-snackbar>
    <v-snackbar v-model="succW" top right color="#356859" :timeout="2000"><p class="snackText3">등록 되었습니다.</p></v-snackbar>
    <v-snackbar v-model="noImg" top right color="error" :timeout="2000"><p class="snackText3">이미지를 등록해주세요.</p></v-snackbar>
    <v-snackbar v-model="succM" top right color="#356859" :timeout="2000"><p class="snackText3">수정 되었습니다.</p></v-snackbar>
  </div>
</template>

<script>
import http from '@/util/http-common.js';
import http3 from '@/util/http-common3.js';
import { mapGetters } from 'vuex';

export default {
  name: 'ReviewWriteComp',
  data() {
    return {
      title: '',
      content: '',
      img: '',
      fileInfo: '',
      imageUrl: '',
      item: {},
      isModify: false,
      modi: false,
      writ: false,
      badPath: false,
      succW: false,
      noImg: false,
      succM: false,
    };
  },
  created() {
    if (this.$route.params.num) {
      this.isModify = true;
      http
        .get(`/review/get/${this.$route.params.num}`)
        .then(({ data }) => {
          this.item = data;
          this.title = data.title;
          this.content = data.content;
          this.img = data.img;
          if (this.getProfile != data.nickName) {
            this.badPath = true;
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
    }
  },
  computed: {
    ...mapGetters(['getProfile']),
  },
  methods: {
    postReview(data) {
      http
        .post(`/review/regist`, {
          title: this.title,
          content: this.content,
          nickName: this.getProfile,
          img: data,
          donationid: this.$route.params.donationid,
        })
        .then(({ data }) => {
          console.log(data);
          if (data == 'success') {
            this.succW = true;
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
    writeReview() {
      if (this.fileInfo != '') {
        var formData = new FormData();
        const file = this.fileInfo;
        formData.append("file", file);
        http3
          .post("/makeImageSrc", formData)
          .then(res => {
            this.postReview(res.data);
          })
          .catch((e) => {
            if (e.request.status === 404) {
              this.alertMsg = '등록 처리시 에러가 발생했습니다.';
              this.alert = true;
            } else {
              this.$router.push(`/apierror/${e.request.status}/`);
            }
            console.log(e.request.status);
          });
      } else {
        this.noImg = true;
      }
    },
    putReview(data) {
      this.img = data;
      http
        .put(`/review/update/${this.$route.params.num}`, {
          num: this.item.num,
          title: this.title,
          content: this.content,
          nickName: this.getProfile,
          img: this.img,
          likeCnt: this.item.likeCnt,
          visit: this.item.visit,
          createdAt: this.item.createdAt,
          donationid: this.item.donationid,
        })
        .then(({ data }) => {
          if (data == 'success') {
            this.succM = true;
            setTimeout(() => {
              this.$router.push(`/reviewDetail/${this.$route.params.num}`).catch(err => {
                console.log(err);
              });
            }, 1500);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    updateReview() {
      if (this.fileInfo != '') {
        var formData = new FormData();
        formData.append('img', this.fileInfo);
        http3
          .post(`/review/img`, formData)
          .then(({ data }) => {
            this.putReview(data);
          })
          .catch((e) => {
            if (e.request.status === 404) {
              this.alertMsg = '등록 처리시 에러가 발생했습니다.';
              this.alert = true;
            } else {
              this.$router.push(`/apierror/${e.request.status}/`);
            }
            console.log(e.request.status);
          });
      } else {
        this.putReview(this.img);
      }
    },
    goBack() {
      window.history.back();
    },
    onChangeImages(e) {
      const file = e.target.files[0];
      this.fileInfo = file;
      this.imageUrl = URL.createObjectURL(file);
    },
    onClickImageUpload() {
      this.$refs.imageInput.click();
    },
  },
};
</script>

<style scoped>
.snackText3 {
  margin-bottom:0;
  font-weight:bold;
  font-size:1rem;
  word-spacing:2px;
  letter-spacing:2px;
}
</style>
