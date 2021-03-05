<template>
<div>
  <div class="d_header_img justify-content-center d-none d-md-block"></div>
  <div class="d-flex justify-content-center">
    <v-container fluid class="col-md-11" style="font-size:1rem;">
      <v-snackbar
        v-model="rqFlag"
        bottom
        :timeout="2000"
        color="#356859"
        class="mr-3"
      >
        요청이 완료되었습니다.
        <template v-slot:action="{ attrs }">
          <v-btn text v-bind="attrs" @click="rqFlag = false">Close</v-btn>
        </template>
      </v-snackbar>
      <v-snackbar
        v-model="rejectFlag"
        bottom
        :timeout="2000"
        color="error"
        class="mr-3"
      >
        정보를 다시 입력하세요.
        <template v-slot:action="{ attrs }">
          <v-btn text v-bind="attrs" @click="rejectFlag = false">Close</v-btn>
        </template>
      </v-snackbar>
      <v-snackbar
        v-model="tempRejectFlag"
        bottom
        :timeout="2000"
        color="error"
        class="mr-3"
      >
        제목은 반드시 입력해야합니다.
        <template v-slot:action="{ attrs }">
          <v-btn text v-bind="attrs" @click="tempRejectFlag = false"
            >Close</v-btn
          >
        </template>
      </v-snackbar>
      <v-snackbar
        v-model="tempFlag"
        bottom
        :timeout="2000"
        color="#356859"
        class="mr-3"
      >
        임시저장이 완료되었습니다.
        <template v-slot:action="{ attrs }">
          <v-btn text v-bind="attrs" @click="tempFlag = false">Close</v-btn>
        </template>
      </v-snackbar>

      <v-form persistent ref="form">
        <v-row class="d-flex justify-content-center">
          <v-col cols="6">
            <!-- 새로 등록하는 이미지-->
            <v-carousel
              v-if="inputFiles.length > 0"
              style="width:100%;height:23rem; border:1px solid lightgray; border-radius:5px;"
            >
              <v-carousel-item v-for="(item, i) in inputFiles" :key="i">
                <img
                  :src="addCarousel(item)"
                  style="width:100%;height:23rem;"
                />
              </v-carousel-item>
            </v-carousel>

            <!-- 처음 들어왔을 때 carousel-->
            <v-carousel
              v-if="!imgFlag"
              style="width:100%;height:23rem; border:1px solid lightgray; border-radius:5px;"
            >
              <v-carousel-item
                v-for="(item, i) in images"
                :key="i"
                reverse-transition="fade-transition"
                transition="fade-transition"
              >
                <img :src="getImg(item)" style="height:23rem; width:100%;" />
              </v-carousel-item>
            </v-carousel>

            <div
              v-if="inputFiles.length == 0 && images.length == 0"
              style="width:100%;height:23rem;"
            >
              <img
                :src="require(`@/assets/main-smile.jpg`)"
                style="width:100%;height:23rem;border-radius:8px;"
              />
            </div>
          </v-col>
          <!-- <v-col cols="1"></v-col> -->
          <v-col cols="6" class="mt-1">
            이미지
            <v-file-input
              ref="file"
              accept="image/png, image/jpeg, image/bmp"
              v-model="inputFiles"
              color="#356859"
              counter
              multiple
              prepend-icon
              :rules="[value => !!value.length > 0 || '이미지를 선택하세요']"
              outlined
              placeholder="Click! (복수 선택 가능)"
            >
              <template v-slot:selection="{ text }">
                <v-chip color="#356859" dark label small>{{ text }}</v-chip>
              </template>
            </v-file-input>
            <!-- 제목 -->
            제목
            <v-text-field
              v-model="DonationCreate.title"
              outlined
              dense
              :rules="[v => !!v || '제목은 필수값입니다.']"
              required
              placeholder="제목을 입력하세요"
            ></v-text-field>
            <v-col class="d-flex justify-content-between p-0">
              <!-- 카테고리 -->
              <v-col class="p-0 mr-3">
                카테고리
                <v-select
                  v-model="DonationCreate.category"
                  :items="items"
                  :rules="[v => !!v || '카테고리는 필수값입니다.']"
                  dense
                  outlined
                  class="p-0"
                  placeholder="카테고리를 선택하세요"
                ></v-select>
              </v-col>
              <!-- 기부 대상 -->
              <v-col class="p-0">
                기부대상
                <v-text-field
                  v-model="DonationCreate.receiver"
                  outlined
                  dense
                  class="p-0"
                  :rules="[v => !!v || '기부대상은 필수값입니다.']"
                  required
                  placeholder="기부대상을 입력하세요"
                ></v-text-field>
              </v-col>
            </v-col>
            <v-col class="d-flex justify-content-between p-0">
              <!-- 마감일 -->
              <v-col class="p-0 mr-3">
                마감일
                <v-menu
                  v-model="menu2"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  transition="scale-transition"
                  offset-y
                  min-width="290px"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-text-field
                      class="p-0"
                      v-model="DonationCreate.edate"
                      outlined
                      dense
                      readonly
                      v-bind="attrs"
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-date-picker
                    v-model="DonationCreate.edate"
                    @input="menu2 = false"
                  ></v-date-picker>
                </v-menu>
              </v-col>
              <!--마감개수-->
              <v-col class="p-0">
                마감개수
                <v-col class="d-flex p-0">
                  <v-btn
                    icon
                    color="lightgray"
                    @click="DonationCreate.maxcnt = DonationCreate.maxcnt - 1"
                  >
                    <v-icon>mdi-arrow-down-drop-circle</v-icon>
                  </v-btn>
                  <v-text-field
                    v-model="DonationCreate.maxcnt"
                    class="p-0"
                    outlined
                    dense
                    align="center"
                    :rules="[
                      v => !!v || '마감개수는 필수값입니다.',
                      v => /^[0-9]+$/g.test(v) || '숫자만 입력 가능합니다.'
                    ]"
                    required
                    placeholder="마감 사진개수를 입력하세요"
                  ></v-text-field>
                  <v-btn
                    icon
                    color="lightgray"
                    @click="
                      DonationCreate.maxcnt = Number(DonationCreate.maxcnt) + 1
                    "
                  >
                    <v-icon>mdi-arrow-up-drop-circle</v-icon>
                  </v-btn>
                </v-col>
              </v-col>
            </v-col>
          </v-col>
        </v-row>
        <v-row class="d-flex justify-content-center p-0">
          <!-- 대상 주소 -->
          <v-col cols="12" class="pt-0 pb-0">
            주소 (선택)
            <v-col class="d-flex justify-content-between p-0 align-items-top">
              <v-text-field
                v-model="addr"
                outlined
                dense
                class="mr-2"
                readonly
                style="color: black"
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
        </v-row>
        <v-row class="d-flex justify-content-center p-0">
          <v-col cols="12" class="p-0">
            <hr />
          </v-col>
        </v-row>
        <!-- 상세내용 -->
        <v-row class="d-flex justify-content-center">
          <v-col cols="12" class="pt-0 pb-0">
            <span>상세내용</span>
            <v-textarea
              v-model="DonationCreate.content"
              auto-grow
              outlined
              rows="20"
              :rules="[v => !!v || '상세내용은 필수값입니다']"
              placeholder="기부 상세내용을 입력하세요"
            ></v-textarea>
          </v-col>
        </v-row>
      </v-form>
      <v-row cols="11" class="d-flex justify-content-end mr-1">
        <v-btn
          @click="saveDonation()"
          outlined
          class="mr-3"
          style="font-weight:bold;"
          >임시저장</v-btn
        >
        <v-btn @click="dialog = true" class="green-mbtn">등록</v-btn>
      </v-row>
      <v-row justify="center">
        <v-dialog v-model="dialog" persistent max-width="290">
          <v-card>
            <v-card-title>
              기부요청 하시겠습니까?
            </v-card-title>
            <v-card-text></v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                style="color:#356859; font-weight:bold;"
                text
                @click="registDonation()"
              >
                기부요청
              </v-btn>
              <v-btn text @click="dialog = false" style="font-weight:bold;">
                취소
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-row>
    </v-container>
  </div>
</div>
</template>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
import http from "@/util/http-common.js";
import http3 from "@/util/http-common3.js";
import { mapGetters, mapState } from "vuex";

export default {
  name: "DonationRequestCreateComp",

  data() {
    return {
      uid: "",
      inputFiles: [],
      items: ["아동,청소년", "어르신", "장애인", "가족,여성", "다문화", "기타"],
      menu2: false,
      addr: "",
      addrDetail: "",
      DonationCreate: {},
      dialog: false,
      rqFlag: false,
      rejectFlag: false,
      tempFlag: false,
      tempRejectFlag: false,
      donationid: "",
      images: [],
      imgFlag: false
    };
  },
  created() {
    this.donationid = this.$route.params.ID;
    http
      .get(`/donation/getTempDetail/${this.$route.params.ID}`)
      .then(res => {
        this.DonationCreate = res.data;
        this.images = this.DonationCreate.img
          .substring(0, this.DonationCreate.img.length - 1)
          .split("|");
      })
      .catch(err => {
        console.log(err);
      });
  },

  methods: {
    checkNum(val) {
      if (!/^[0-9]+$/g.test(val)) {
        return true;
      } else {
        return false;
      }
    },
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
    addCarousel(file) {
      return URL.createObjectURL(file);
    },
    registDonation() {
      this.DonationCreate.img = '';
      this.dialog = false;
      if (!this.$refs.form.validate()) {
        this.rejectFlag = true;
        return;
      } else {
        for (var i = 0; i < this.inputFiles.length; i++) {
          var formData = new FormData();
          const file = this.inputFiles[i];
          if (file != null) {
            if (i != this.inputFiles.length - 1) {
              formData.append("file", file);
              http3
                .post("/makeImageSrc", formData)
                .then(res => {
                  this.DonationCreate.img += res.data + "|";
                })
                .catch(err => {
                  console.log(err);
                });
            } else {
              formData.append("file", file);
              http3
                .post("/makeImageSrc", formData)
                .then(res => {
                  this.DonationCreate.img += res.data + "|";
                  this.DonationCreate.address = this.addr + this.addrDetail;
                  http
                    .post("/donation/regist", this.DonationCreate)
                    .then(res => {
                      this.rqFlag = true;
                      setTimeout(() => {
                        this.$router.push("/myPage").catch(err => {
                          console.log(err);
                        });
                      }, 1500);
                    })
                    .catch(err => {
                      console.log(err);
                    });
                })
                .catch(err => {
                  console.log(err);
                });
            }
          }
        }
        this.rqFlag = true;
      }
    },
    saveDonation() {
      this.DonationCreate.img = '';
      if (this.DonationCreate.title.length == 0) {
        this.tempRejectFlag = true;
        return;
      } else {
        if (this.inputFiles.length == 0) {
          http
            .put("/donation/update", this.DonationCreate)
            .then(res => {
              this.tempFlag = true;
              setTimeout(() => {
                this.$router.push("/myPage").catch(err => {
                  console.log(err);
                });
              }, 1500);
            })
            .catch(err => {
              console.log(err);
            });
          return;
        }
        for (var i = 0; i < this.inputFiles.length; i++) {
          var formData = new FormData();
          const file = this.inputFiles[i];
          if (file != null) {
            if (i != this.inputFiles.length - 1) {
              formData.append("file", file);
              http3
                .post("/makeImageSrc", formData)
                .then(res => {
                  this.DonationCreate.img += res.data + "|";
                })
                .catch(err => {
                  console.log(err);
                });
            } else {
              formData.append("file", file);
              http3
                .post("/makeImageSrc", formData)
                .then(res => {
                  this.DonationCreate.img += res.data + "|";
                  this.DonationCreate.address = this.addr + this.addrDetail;
                  http
                    .put("/donation/update", this.DonationCreate)
                    .then(res => {
                      this.tempFlag = true;
                      setTimeout(() => {
                        this.$router.push("/myPage").catch(err => {
                          console.log(err);
                        });
                      }, 1500);
                    })
                    .catch(err => {
                      console.log(err);
                    });
                })
                .catch(err => {
                  console.log(err);
                });
            }
          }
        }
        this.tempFlag = true;
      }
    },
    // saveDonation() {
    //   if (this.DonationCreate.title.length == 0) {
    //     this.tempRejectFlag = true;
    //     return;
    //   } else {
    //     this.DonationCreate.address = this.addr + this.addrDetail;
    //     http
    //       .put("/donation/update", this.DonationCreate)
    //       .then(res => {
    //         this.tempFlag = true;
    //         setTimeout(() => {
    //           this.$router.push("/myPage").catch(err => {
    //             console.log(err);
    //           });
    //         }, 1500);
    //         console.log(res.data);
    //       })
    //       .catch(err => {
    //         console.log(err);
    //       });
    //   }
    // },
    getImg(img) {
      return "../../../contents/" + img;
    }
  },
  watch: {
    inputFiles(v) {
      if (v.length > 0) {
        this.imgFlag = true;
      }
    }
  },
  computed: {
    ...mapGetters(["getProfile"]),
    ...mapState({
      uname: state => `${state.user.getProfile}`
    })
  }
};
</script>
<style>
.green-mbtn {
  background-color: #356859 !important;
  color: white !important;
  font-weight: bold !important;
}
</style>