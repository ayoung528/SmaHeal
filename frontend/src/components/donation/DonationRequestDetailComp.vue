<template>
  <v-container fluid class="col-md-11 px-0">
    <!-- 카테고리 -->
    <v-row justify="center">
      <v-col cols="12" class="pb-0">
        <v-chip color="#356859" text-color="white"
          ><v-icon left> mdi-label </v-icon>{{ donation.category }}</v-chip
        >
      </v-col>
    </v-row>
    <!-- 제목 -->
    <v-row justify="center">
      <v-col cols="12" class="pt-0">
        <h1 class="font-weight-bold">{{ donation.title }}</h1>
      </v-col>
    </v-row>
    <!-- 사진 carousel -->
    <v-col cols="12" class="p-0">
      <v-carousel width="100%" >
        <v-carousel-item
          v-for="(item, i) in images"
          :key="i"
          reverse-transition="fade-transition"
          transition="fade-transition"
        >
          <img :src="getImg(item)" style="height:100%; width:100%;" />
        </v-carousel-item>
      </v-carousel>
    </v-col>
    <!-- 내용 -->
    <v-row class="d-flex justify-content-center m-0">
      <v-tabs
        class="col-md-12 col-sm-12 basil--text px-0 d-block d-md-none"
        centered
        grow
        color="basil"
        style="position:sticky;top:43px;z-index:3;"
      >
        <v-tab @click="$vuetify.goTo('#section1')">
          <a class="nav-link" style="color:black;font-weight:bold;">상세내용</a>
        </v-tab>
        <v-tab @click="$vuetify.goTo('#section2')">
          <a class="nav-link" style="color:black;font-weight:bold;">위치</a>
        </v-tab>
        <v-tab @click="$vuetify.goTo('#section3')">
          <a class="nav-link" style="color:black;font-weight:bold;"
            >응원메세지</a
          >
        </v-tab>
      </v-tabs>
      <v-tabs
        class="col-md-12 col-sm-12 basil--text px-0 d-none d-md-block"
        centered
        grow
        color="basil"
        style="position:sticky;top:-13px;z-index:3;"
      >
        <v-tab @click="$vuetify.goTo('#section1')">
          <a class="nav-link" style="color:black;font-weight:bold;">상세내용</a>
        </v-tab>
        <v-tab @click="$vuetify.goTo('#section2')">
          <a class="nav-link" style="color:black;font-weight:bold;">위치</a>
        </v-tab>
        <v-tab @click="$vuetify.goTo('#section3')">
          <a class="nav-link" style="color:black;font-weight:bold;"
            >응원메세지</a
          >
        </v-tab>
      </v-tabs>
      <v-col>
        <div class="container-fluid">
          <h3
          id="section1"
            style="font-family: 'Nanum Gothic';font-weight:bold;"
            class="mb-5"
          >
            상세내용
          </h3>
          <v-textarea
            readonly
            auto-grow
            solo
            flat
            class="pa-3"
            :value="donation.content"
            style="font-size:1rem;font-weight:bold;font-family: 'Nanum Gothic';"
          >
          </v-textarea>
        </div>
          <v-divider></v-divider>
        <div class="container-fluid">
          <h3
          id="section2"
            style="font-family: 'Nanum Gothic';font-weight:bold;"
            class="mb-5"
          >
            위치
          </h3>
          <div v-if="donation.address">
            <div
              id="map"
              style="max-width: 100%; height:350px; z-index:0"
              class="mb-2"
            ></div>
            <span
              style="font-size:1rem;font-weight:bold;font-family: 'Nanum Gothic';"
              >{{ donation.address }}</span
            >
          </div>
          <div v-else>
            <span style="font-size:1rem;">등록된 주소가 없습니다.</span>
          </div>
        </div>
          <v-divider></v-divider>
        <div class="container-fluid">
          <h3 id="section3" style="font-family:'Nanum Gothic';font-weight:bold;" class="mb-5">
            응원메세지
          </h3>

          <v-list-item v-for="(msg, i) in msgs" :key="i">
            <v-list-item-content class="pb-0" style="display:block">
              <v-row
                class="d-flex justify-content-between"
                style="font-size:1rem;"
              >
                <v-col
                  class="py-0 ml-2"
                  style="color:#356859;font-weight:bold;font-family:'Nanum Gothic';"
                  >{{ msg.userId }}</v-col
                ><v-col
                  class="py-0 mr-2"
                  align="right"
                  style="font-family: 'Nanum Gothic';"
                  >{{ msg.createdate }}</v-col
                >
              </v-row>
              <v-row>
                <v-col cols="10" class="my-auto ml-2">
                  <h5
                    style="font-family: 'Nanum Gothic';"
                    v-text="msg.msg"
                    class="m-0"
                  ></h5>
                </v-col>
                <v-col class="d-flex justify-content-center p-0">
                  <v-btn
                    icon
                    color="gray"
                    v-if="uid == msg.userId"
                    @click="clickModify(msg)"
                  >
                    <v-icon>mdi-pencil</v-icon>
                  </v-btn>
                  <v-btn
                    icon
                    color="red darken-3"
                    v-if="uid == msg.userId"
                    @click="clickDelete(msg.cheerupid)"
                  >
                    <v-icon>mdi-delete-outline</v-icon>
                  </v-btn>
                </v-col>
              </v-row>
              <v-divider class="p-0 m-0"></v-divider>
            </v-list-item-content>
          </v-list-item>
          <div class="text-center ma-2" v-if="msgs.length != 0">
            <v-pagination v-model="limit" :length="totalPage" color="#356859" />
          </div>

          <!-- 메세지 삭제 dialog -->
          <v-dialog v-model="deleteFlag" max-width="300">
            <v-card>
              <v-card-title>
                <span style="font-weight:bold;">
                  메세지를 삭제하시겠습니까?
                </span>
              </v-card-title>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  color="red darken-1"
                  text
                  style="font-weight:bold;"
                  @click="deleteMsg(deleteIndex)"
                  >삭제</v-btn
                >
                <v-btn
                  text
                  style="font-weight:bold;"
                  @click="deleteFlag = false"
                  >취소</v-btn
                >
              </v-card-actions>
            </v-card>
          </v-dialog>

          <!-- 메세지 수정 dialog -->
          <v-dialog v-model="modifyFlag" max-width="450">
            <v-card>
              <v-card-title>
                <h5>
                  <span style="font-weight:bold;"
                    >수정할 메세지를 입력하세요.</span
                  >
                </h5>
              </v-card-title>
              <v-card-text style="font-weight:bold;">
                <v-text-field
                  v-model="tempMsg.msg"
                  counter
                  maxlength="50"
                ></v-text-field>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  text
                  style="font-weight:bold;"
                  @click="modifyMsg(tempMsg)"
                  >수정</v-btn
                >
                <v-btn
                  color="red darken-1"
                  text
                  style="font-weight:bold;"
                  @click="(modifyFlag = false), (tempMsg.msg = temp)"
                  >취소</v-btn
                >
              </v-card-actions>
            </v-card>
          </v-dialog>

          <!-- 메세지 입력 -->
          <v-row class="d-flex align-items-center my-auto p-3 ">
            <v-text-field
              counter
              maxlength="50"
              prepend-icon="mdi-comment"
              name="cheerup"
              placeholder="응원의 메세지를 입력하세요"
              v-model="cheerup"
              color="lightgray"
              style="font-family: 'Nanum Gothic';"
            ></v-text-field>
            <div>
              <v-btn
                small
                class="green-mbtn ml-2"
                style="font-family: 'Nanum Gothic';"
                @click="msgSubmit"
                >등록</v-btn
              >
            </div>
          </v-row>
        </div>
        <v-btn class="mx-5" @click="goBack" icon fab large color="basil">
          <v-icon>mdi-undo</v-icon><span style="font-weight:bold;">뒤로</span>
        </v-btn>
      </v-col>

      <!-- 플로팅 메뉴 -->
      <v-col cols="4" class="d-none d-md-block">
        <v-card
          class="mx-auto rounded-lg"
          max-width="344"
          height="auto"
          outlined
          elevation="2"
          style="position:sticky;top:100px;z-index:2;background-color:#fffbe6; border:1px solid #fffbe6 "
        >
          <v-list-item three-line>
            <v-list-item-content>
              <div>
                <p>To. {{ donation.receiver }}</p>
              </div>
              <v-divider class="m-0"></v-divider>

              <v-list-item-title class="mb-1" style="font-size:1.3rem;"
                >{{ slider }}%
                <v-chip
                  class="ma-2 font-weight-bold "
                  color="black"
                  small
                  style="color:white;"
                  >D - {{ this.Dday }}</v-chip
                >
              </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <br />
          <v-slider
            class="mt-2 mx-2"
            hide-details
            v-model="slider"
            :thumb-size="33"
            thumb-label="always"
            readonly
            color="#356859"
            track-color="lightgray"
          >
            <template v-slot:thumb-label="{ value }"
              ><span style="font-size:1.2rem;">{{
                satisfactionEmojis[Math.min(Math.floor(value / 8), 7)]
              }}</span></template
            >
          </v-slider>

          <div
            class="m-3 px-3"
            style="font-size:1rem; border:3px solid #356859; border-radius:15px; "
          >
            <!-- <v-col align="center" style="font-size:1.1rem;" class="pb-0"
              ><v-icon style="color:black">mdi-approximately-equal</v-icon> 기부
              현황
              <v-icon style="color:black"
                >mdi-approximately-equal</v-icon
              ></v-col
            > -->

            <v-row cols="12">
              <v-col cols="5" class="pr-0 pb-0"
                ><v-icon style="font-size:1rem;color:black;"
                  >mdi-check-box-outline</v-icon
                >현재 웃음사진</v-col
              ><v-col cols="7" class="pb-0"> {{ donation.nowcnt }}장</v-col>
            </v-row>
            <v-row cols="12">
              <v-col cols="5" class="pr-0 pb-0"
                ><v-icon style="font-size:1rem;color:black;"
                  >mdi-check-box-outline</v-icon
                >목표 웃음사진</v-col
              ><v-col cols="7" class="pb-0"> {{ donation.maxcnt }}장</v-col>
            </v-row>
            <v-row cols="12">
              <v-col cols="5" class="pr-0 pb-0"
                ><v-icon style="font-size:1rem;color:black;"
                  >mdi-check-box-outline</v-icon
                >마감일자</v-col
              ><v-col cols="7" class="pr-0">
                {{ makedate(donation.edate) }}</v-col
              >
            </v-row>
          </div>
          <div style="text-align:center" class="mb-3">
            <v-btn
              v-if="!this.btnflag"
              id="donation_btn"
              rounded
              class="green-mbtn"
              @click="goDonation"
              >웃음기부 GO</v-btn
            >
            <v-tooltip bottom v-else>
              <template v-slot:activator="{ on, attrs }">
                <v-fab-transition>
                  <v-btn
                    rounded
                    color="red"
                    dark
                    outlined
                    class="font-weight-bold"
                    v-on="on"
                    v-bind="attrs"
                  >
                    기부할 수 없습니다.
                  </v-btn>
                </v-fab-transition>
              </template>
              <span>본인이 쓴 게시물이거나 이미 기부한 게시물입니다.</span>
            </v-tooltip>
          </div>
        </v-card>
      </v-col>
    </v-row>
    <v-row class="d-block d-md-none">
      <v-col>
        <v-card
          class="mx-auto rounded-lg"
          max-width="344"
          height="auto"
          outlined
          elevation="2"
          style="position:sticky;top:200px;z-index:2;background-color:#fffbe6; border:1px solid #fffbe6 "
        >
          <v-list-item three-line>
            <v-list-item-content>
              <div>
                <p>To. {{ donation.receiver }}</p>
              </div>
              <v-divider class="m-0"></v-divider>

              <v-list-item-title class="mb-1" style="font-size:1.3rem;"
                >{{ slider }}%
                <v-chip
                  class="ma-2 font-weight-bold "
                  color="black"
                  small
                  style="color:white;"
                  >D - {{ this.Dday }}</v-chip
                >
              </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <br />
          <v-slider
            class="mt-2 mx-2"
            hide-details
            v-model="slider"
            :thumb-size="33"
            thumb-label="always"
            readonly
            color="#356859"
            track-color="lightgray"
          >
            <template v-slot:thumb-label="{ value }"
              ><span style="font-size:1.2rem;">{{
                satisfactionEmojis[Math.min(Math.floor(value / 8), 7)]
              }}</span></template
            >
          </v-slider>

          <div
            class="m-3 px-3"
            style="font-size:1rem; border:3px solid #356859; border-radius:15px; "
          >
            <!-- <v-col align="center" style="font-size:1.1rem;" class="pb-0"
              ><v-icon style="color:black">mdi-approximately-equal</v-icon> 기부
              현황
              <v-icon style="color:black"
                >mdi-approximately-equal</v-icon
              ></v-col
            > -->

            <v-row cols="12">
              <v-col cols="5" class="pr-0 pb-0"
                ><v-icon style="font-size:1rem;color:black;"
                  >mdi-check-box-outline</v-icon
                >현재 웃음사진</v-col
              ><v-col cols="7" class="pb-0"> {{ donation.nowcnt }}장</v-col>
            </v-row>
            <v-row cols="12">
              <v-col cols="5" class="pr-0 pb-0"
                ><v-icon style="font-size:1rem;color:black;"
                  >mdi-check-box-outline</v-icon
                >목표 웃음사진</v-col
              ><v-col cols="7" class="pb-0"> {{ donation.maxcnt }}장</v-col>
            </v-row>
            <v-row cols="12">
              <v-col cols="5" class="pr-0 pb-0"
                ><v-icon style="font-size:1rem;color:black;"
                  >mdi-check-box-outline</v-icon
                >마감일자</v-col
              ><v-col cols="7" class="pr-0">
                {{ makedate(donation.edate) }}</v-col
              >
            </v-row>
          </div>
          <div style="text-align:center" class="mb-3">
            <v-btn
              v-if="!btnflag"
              id="donation_btn2"
              rounded
              class="green-mbtn"
              @click="goDonation"
              >웃음기부 GO</v-btn
            >
            <v-tooltip bottom v-else>
              <template v-slot:activator="{ on, attrs }">
                <v-fab-transition>
                  <v-btn
                    rounded
                    color="red"
                    dark
                    outlined
                    class="font-weight-bold"
                    v-on="on"
                    v-bind="attrs"
                  >
                    기부할 수 없습니다.
                  </v-btn>
                </v-fab-transition>
              </template>
              <span>본인이 쓴 게시물이거나 이미 기부한 게시물입니다.</span>
            </v-tooltip>
          </div>
        </v-card>
      </v-col>
    </v-row>
    <div class="text-center ma-2">
      <v-snackbar
        v-model="alertFlag"
        bottom
        :timeout="3000"
        color="error"
        class="mr-3"
      >
        로그인이 필요합니다.
        <template v-slot:action="{ attrs }">
          <v-btn text v-bind="attrs" @click="goLogin">Login</v-btn>
        </template>
      </v-snackbar>
    </div>

    <div class="text-center ma-2">
      <v-snackbar
        v-model="msgFlag"
        bottom
        :timeout="3000"
        color="error"
        class="mr-3"
      >
        메세지를 입력해주세요.
        <template v-slot:action="{ attrs }">
          <v-btn text v-bind="attrs" @click="msgFlag = false">Close</v-btn>
        </template>
      </v-snackbar>
    </div>
  </v-container>
</template>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a0064e7e9af3feb23b87a09d13dbc1b1&libraries=services"></script>
<script>
import http from "@/util/http-common.js";
import { mapGetters, mapState } from "vuex";

export default {
  name: "DonationRequestDetailComp",

  data() {
    return {
      donation: [],
      donationid: 0,
      images: [],
      title: "",
      satisfactionEmojis: ["😐", "🙂", "😊", "😁", "😁", "😄", "😄", "😍"],
      slider: 0,
      Dday: 0,
      cheerup: "",
      msgs: [],
      log: "",
      alertFlag: false,
      uid: "",
      deleteFlag: false,
      deleteIndex: 0,
      modifyFlag: false,
      tempMsg: [],
      temp: "",
      msgFlag: false,
      totalPage: 2,
      limit: 1,
      btnflag: false
    };
  },

  created() {
    this.uid = this.getProfile;
    this.donationid = this.$route.params.ID;
    this.init();
    this.getMsg();
  },

  methods: {
    init() {
      http
        .get("/donation/detail/" + this.donationid)
        .then(res => {
          this.donation = res.data;
          this.slider = (this.donation.nowcnt * 100) / this.donation.maxcnt;
          this.images = this.donation.img
            .substring(0, this.donation.img.length - 1)
            .split("|");
          this.calDay();
          if (this.donation.address != "") {
            this.addScript();
          }
          // this.initMap();
          if (this.getUserID != '') {
            this.IsDonate();
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    getMsg() {
      http
        .get(`/cheerup/getList/${this.donationid}/${this.limit - 1}`)
        .then(res => {
          this.msgs = res.data;

          http.get(`/cheerup/getCount/${this.donationid}`).then(({ data }) => {
            if (data.length % 10 == 0) {
              this.totalPage = Math.floor(data.length / 10);
            } else {
              this.totalPage = Math.floor(data.length / 10) + 1;
            }
          });
        })
        .catch(err => {
          console.log(err);
        });
    },
    calDay() {
      var now = new Date();
      var endDate = this.donation.edate.split("-");
      var newEnd = new Date(endDate[0], Number(endDate[1]) - 1, endDate[2]);
      var gap = Math.ceil(
        (newEnd.getTime() - now.getTime()) / 1000 / 60 / 60 / 24
      );
      this.Dday = gap;
    },
    scrollSpy(evt) {
      evt.preventDefault();
      const href = evt.target.getAttribute("href");
      var location = document.querySelector(href).offsetTop;
      window.scrollTo({ top: location + 300, behavior: "smooth" });
    },
    msgSubmit() {
      if (this.uid != "") {
        if (this.cheerup != "") {
          http
            .post("/cheerup/writeMsg", {
              donationid: this.donationid,
              msg: this.cheerup,
              userId: this.getUserID
            })
            .then(res => {
              this.log = res;
              this.cheerup = "";
              this.getMsg();
            })
            .catch(err => {
              console.log(err);
            });
        } else {
          this.msgFlag = true;
        }
      } else {
        this.cheerup = "";
        this.alertFlag = true;
      }
    },
    clickDelete(v) {
      this.deleteIndex = v;
      this.deleteFlag = true;
    },
    clickModify(v) {
      this.tempMsg = v;
      this.temp = v.msg;
      this.modifyIndex = v.cheerupid;
      this.modifyFlag = true;
    },
    deleteMsg(v) {
      this.deleteFlag = false;
      http
        .delete("/cheerup/deleteMsg/" + v)
        .then(res => {
          this.log = res;
          this.getMsg();
        })
        .catch(err => {
          console.log(err);
        });
    },
    modifyMsg(v) {
      this.modifyFlag = false;
      if (v.msg != "") {
        http
          .put("/cheerup/modifyMsg/", {
            donationid: v.donationid,
            cheerupid: v.cheerupid,
            msg: v.msg,
            userId: v.userId
          })
          .then(res => {
            this.log = res;
            this.getMsg();
          })
          .catch(err => {
            console.log(err);
          });
      } else {
        this.msgFlag = true;
        this.getMsg();
      }
    },
    goDonation() {
      if (this.uid != "") {
        this.$router.push({
          name: "SmileCreate",
          params: { ID: this.donationid }
        });
      } else {
        this.alertFlag = true;
      }
    },
    goLogin() {
      this.alertFlag = false;
      this.$router.push("/login");
    },
    getImg(img) {
      return "../../../contents/" + img;
    },
    makedate(ndate) {
      if (ndate != null) {
        var arr = ndate.split("-");
        return arr[0] + "년 " + arr[1] + "월 " + arr[2] + "일";
      }
    },
    initMap() {
      var loc = this.donation.address;
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3
      };
      var map = new kakao.maps.Map(container, options); //마커추가하려면 객체를 아래와 같이 하나 만든다.
      var geocoder = new kakao.maps.services.Geocoder();
      geocoder.addressSearch(loc, function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
          var marker = new kakao.maps.Marker({
            map: map,
            position: coords
          });
          map.setCenter(coords);
        }
      });
    },
    addScript() {
      const script = document.createElement("script");
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=a0064e7e9af3feb23b87a09d13dbc1b1&libraries=services";
      document.head.appendChild(script);
    },
    goBack() {
      window.history.back();
    },
    IsDonate() {
      http
        .get(`/donation/isDonate/${this.donationid}/${this.getUserID}`)
        .then(({ data }) => {
          if (data == "OK") {
            var btn = document.getElementById("donation_btn");
            var btn2 = document.getElementById("donation_btn2");
            btn.disabled = true;
            btn2.disabled = true;
            this.btnflag = true;
          } else {
            http
              .get(
                `/donation/isDonateSelf/${this.donationid}/${this.getProfile}`
              )
              .then(({ data }) => {
                if (data == "OK") {
                  var btn = document.getElementById("donation_btn");
                  var btn2 = document.getElementById("donation_btn2");
                  btn.disabled = true;
                  btn2.disabled = true;
                  this.btnflag = true;
                }
              })
              .catch(e => {
                console.log(e);
              });
          }
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  watch: {
    limit() {
      this.getMsg();
    }
  },

  computed: {
    ...mapGetters(["getUserID", "getProfile"]),
    ...mapState({
      userID: state => `${state.user.getUserID}`,
      uname: state => `${state.user.getProfile}`
    })
  }
};
</script>
<style>
.basil {
  background-color: #fffbe6 !important;
}
.basil--text {
  color: #356859 !important;
}
</style>