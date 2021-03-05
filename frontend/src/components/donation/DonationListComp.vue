<template>
  <div class="col-12 p-0">
    <div class="header_img d-flex justify-content-center">
      <div style="color:white; position:absolute; top:200px; font-size:3rem;">
        기부게시판
      </div>
    </div>
    <div
      class="d-flex justify-content-center"
      style="background: linear-gradient(to right,  rgb(183, 166, 141),  rgb(168, 147, 116));"
    >
      <v-container fluid class="col-md-11 pt-0">
        <!-- 카테고리 -->
        <v-card color="basil">
          <v-card-title class="text-center justify-center p-0">
            <v-col cols="12" class="d-flex justify-content-end">
              <v-btn
                cols="3"
                @click="goCreate()"
                class="mr-2"
                style="background-color:#356859; color:white; font-weight:bold;"
                v-if="isAuthenticated"
                >등록 요청</v-btn
              >
            </v-col>
            <v-col
              cols="12"
              class="d-flex justify-content-center align-items-center p-0"
            >
              <v-col cols="6" class="pt-0">
                <v-text-field
                  v-model="searchTxt"
                  flat
                  hide-details
                  label="Search"
                  prepend-inner-icon="mdi-magnify"
                  solo-inverted
                  clearable
                ></v-text-field>
              </v-col>
            </v-col>
            <!-- <h1 class="font-weight-bold display-3 basil--text">BASiL</h1> -->
          </v-card-title>
          <v-tabs
            v-model="tab"
            background-color="transparent"
            color="basil"
            class="basil--text"
            grow
          >
            <!-- <v-tabs-slider></v-tabs-slider> -->
            <v-tab href="#tab-1" @click="infiniteId += 1" class="tab-text"
              >all</v-tab
            >
            <v-tab href="#tab-2" @click="infiniteId += 1" class="tab-text"
              >아동/청소년</v-tab
            >
            <v-tab href="#tab-3" @click="infiniteId += 1" class="tab-text"
              >어르신</v-tab
            >
            <v-tab href="#tab-4" @click="infiniteId += 1" class="tab-text"
              >장애인</v-tab
            >
            <v-tab href="#tab-5" @click="infiniteId += 1" class="tab-text"
              >가족/여성</v-tab
            >
            <v-tab href="#tab-6" @click="infiniteId += 1" class="tab-text"
              >다문화</v-tab
            >
            <v-tab href="#tab-7" @click="infiniteId += 1" class="tab-text"
              >기타</v-tab
            >
          </v-tabs>
        </v-card>
        <v-card>
          <v-tabs-items v-model="tab">
            <v-tab-item class="row justify-content-left p-3" value="tab-1">
              <div
                class="col-12 col-sm-6 col-md-3"
                v-for="(donation, index) in all"
                :key="index"
              >
                <v-card max-width="400" style="overflow:hidden;">
                  <div style="height:200px;width:100%;">
                    <img
                      class="donation-img white--text"
                      :src="getImg(donation.img)"
                      @click="getDetail(donation.donationid)"
                    />
                  </div>
                  <v-card-title
                    class="pb-0 mb-1 donation-title"
                    @click="getDetail(donation.donationid)"
                    >{{ donation.title }}</v-card-title
                  >
                  <v-card-text class="text--primary">
                    <div class="mb-3">{{ donation.receiver }}</div>
                    <v-progress-linear
                      rounded
                      :value="toPercent(donation.nowcnt, donation.maxcnt)"
                      height="10"
                      color="yellow darken-2"
                    ></v-progress-linear>
                    <div class="d-flex justify-content-between mt-2">
                      <div
                        class="d-flex"
                        style="font-size:large; color:#F9A825"
                      >
                        {{ toPercent(donation.nowcnt, donation.maxcnt) }}%
                      </div>
                      <div class="d-flex" style="font-size:large;">
                        {{ donation.nowcnt }}장
                      </div>
                    </div>
                  </v-card-text>
                </v-card>
              </div>
            </v-tab-item>
            <v-tab-item class="row justify-content-left p-3" value="tab-2">
              <div
                class="col-12 col-sm-6 col-md-3"
                v-for="(donation, index) in child"
                :key="index"
              >
                <v-card max-width="400" style="overflow:hidden;">
                  <div style="height:200px;width:100%;">
                    <img
                      class="donation-img white--text"
                      :src="getImg(donation.img)"
                      @click="getDetail(donation.donationid)"
                    />
                  </div>
                  <v-card-title
                    class="pb-0 mb-1 donation-title"
                    @click="getDetail(donation.donationid)"
                    >{{ donation.title }}</v-card-title
                  >
                  <v-card-text class="text--primary">
                    <div class="mb-3">{{ donation.receiver }}</div>
                    <v-progress-linear
                      rounded
                      :value="toPercent(donation.nowcnt, donation.maxcnt)"
                      height="10"
                      color="yellow darken-2"
                    ></v-progress-linear>
                    <div class="d-flex justify-content-between mt-2">
                      <div
                        class="d-flex"
                        style="font-size:large; color:#F9A825"
                      >
                        {{ toPercent(donation.nowcnt, donation.maxcnt) }}%
                      </div>
                      <div class="d-flex" style="font-size:large;">
                        {{ donation.nowcnt }}장
                      </div>
                    </div>
                  </v-card-text>
                </v-card>
              </div>
            </v-tab-item>
            <v-tab-item class="row justify-content-left p-3" value="tab-3">
              <div
                class="col-12 col-sm-6 col-md-3"
                v-for="(donation, index) in old"
                :key="index"
              >
                <v-card max-width="400" style="overflow:hidden;">
                  <div style="height:200px;width:100%;">
                    <img
                      class="donation-img white--text"
                      :src="getImg(donation.img)"
                      @click="getDetail(donation.donationid)"
                    />
                  </div>
                  <v-card-title
                    class="pb-0 mb-1 donation-title"
                    @click="getDetail(donation.donationid)"
                    >{{ donation.title }}</v-card-title
                  >
                  <v-card-text class="text--primary">
                    <div class="mb-3">{{ donation.receiver }}</div>
                    <v-progress-linear
                      rounded
                      :value="toPercent(donation.nowcnt, donation.maxcnt)"
                      height="10"
                      color="yellow darken-2"
                    ></v-progress-linear>
                    <div class="d-flex justify-content-between mt-2">
                      <div
                        class="d-flex"
                        style="font-size:large; color:#F9A825"
                      >
                        {{ toPercent(donation.nowcnt, donation.maxcnt) }}%
                      </div>
                      <div class="d-flex" style="font-size:large;">
                        {{ donation.nowcnt }}장
                      </div>
                    </div>
                  </v-card-text>
                </v-card>
              </div>
            </v-tab-item>
            <v-tab-item class="row justify-content-left p-3" value="tab-4">
              <div
                class="col-12 col-sm-6 col-md-3"
                v-for="(donation, index) in disabled"
                :key="index"
              >
                <v-card max-width="400" style="overflow:hidden;">
                  <div style="height:200px;width:100%;">
                    <img
                      class="donation-img white--text"
                      :src="getImg(donation.img)"
                      @click="getDetail(donation.donationid)"
                    />
                  </div>
                  <v-card-title
                    class="pb-0 mb-1 donation-title"
                    @click="getDetail(donation.donationid)"
                    >{{ donation.title }}</v-card-title
                  >
                  <v-card-text class="text--primary">
                    <div class="mb-3">{{ donation.receiver }}</div>
                    <v-progress-linear
                      rounded
                      :value="toPercent(donation.nowcnt, donation.maxcnt)"
                      height="10"
                      color="yellow darken-2"
                    ></v-progress-linear>
                    <div class="d-flex justify-content-between mt-2">
                      <div
                        class="d-flex"
                        style="font-size:large; color:#F9A825"
                      >
                        {{ toPercent(donation.nowcnt, donation.maxcnt) }}%
                      </div>
                      <div class="d-flex" style="font-size:large;">
                        {{ donation.nowcnt }}장
                      </div>
                    </div>
                  </v-card-text>
                </v-card>
              </div>
            </v-tab-item>
            <v-tab-item class="row justify-content-left p-3" value="tab-5">
              <div
                class="col-12 col-sm-6 col-md-3"
                v-for="(donation, index) in woman"
                :key="index"
              >
                <v-card max-width="400" style="overflow:hidden;">
                  <div style="height:200px;width:100%;">
                    <img
                      class="donation-img white--text"
                      :src="getImg(donation.img)"
                      @click="getDetail(donation.donationid)"
                    />
                  </div>
                  <v-card-title
                    class="pb-0 mb-1 donation-title"
                    @click="getDetail(donation.donationid)"
                    >{{ donation.title }}</v-card-title
                  >
                  <v-card-text class="text--primary">
                    <div class="mb-3">{{ donation.receiver }}</div>
                    <v-progress-linear
                      rounded
                      :value="toPercent(donation.nowcnt, donation.maxcnt)"
                      height="10"
                      color="yellow darken-2"
                    ></v-progress-linear>
                    <div class="d-flex justify-content-between mt-2">
                      <div
                        class="d-flex"
                        style="font-size:large; color:#F9A825"
                      >
                        {{ toPercent(donation.nowcnt, donation.maxcnt) }}%
                      </div>
                      <div class="d-flex" style="font-size:large;">
                        {{ donation.nowcnt }}장
                      </div>
                    </div>
                  </v-card-text>
                </v-card>
              </div>
            </v-tab-item>
            <v-tab-item class="row justify-content-left p-3" value="tab-6">
              <div
                class="col-12 col-sm-6 col-md-3"
                v-for="(donation, index) in multi"
                :key="index"
              >
                <v-card max-width="400" style="overflow:hidden;">
                  <div style="height:200px;width:100%;">
                    <img
                      class="donation-img white--text"
                      :src="getImg(donation.img)"
                      @click="getDetail(donation.donationid)"
                    />
                  </div>
                  <v-card-title
                    class="pb-0 mb-1 donation-title"
                    @click="getDetail(donation.donationid)"
                    >{{ donation.title }}</v-card-title
                  >
                  <v-card-text class="text--primary">
                    <div class="mb-3">{{ donation.receiver }}</div>
                    <v-progress-linear
                      rounded
                      :value="toPercent(donation.nowcnt, donation.maxcnt)"
                      height="10"
                      color="yellow darken-2"
                    ></v-progress-linear>
                    <div class="d-flex justify-content-between mt-2">
                      <div
                        class="d-flex"
                        style="font-size:large; color:#F9A825"
                      >
                        {{ toPercent(donation.nowcnt, donation.maxcnt) }}%
                      </div>
                      <div class="d-flex" style="font-size:large;">
                        {{ donation.nowcnt }}장
                      </div>
                    </div>
                  </v-card-text>
                </v-card>
              </div>
            </v-tab-item>
            <v-tab-item class="row justify-content-left p-3" value="tab-7">
              <div
                class="col-12 col-sm-6 col-md-3"
                v-for="(donation, index) in others"
                :key="index"
              >
                <v-card max-width="400" style="overflow:hidden;">
                  <div style="height:200px;width:100%;">
                    <img
                      class="donation-img white--text"
                      :src="getImg(donation.img)"
                      @click="getDetail(donation.donationid)"
                    />
                  </div>
                  <v-card-title
                    class="pb-0 mb-1 donation-title"
                    @click="getDetail(donation.donationid)"
                    >{{ donation.title }}</v-card-title
                  >
                  <v-card-text class="text--primary">
                    <div class="mb-3">{{ donation.receiver }}</div>
                    <v-progress-linear
                      rounded
                      :value="toPercent(donation.nowcnt, donation.maxcnt)"
                      height="10"
                      color="yellow darken-2"
                    ></v-progress-linear>
                    <div class="d-flex justify-content-between mt-2">
                      <div
                        class="d-flex"
                        style="font-size:large; color:#F9A825"
                      >
                        {{ toPercent(donation.nowcnt, donation.maxcnt) }}%
                      </div>
                      <div class="d-flex" style="font-size:large;">
                        {{ donation.nowcnt }}장
                      </div>
                    </div>
                  </v-card-text>
                </v-card>
              </div>
            </v-tab-item>
          </v-tabs-items>
        </v-card>
        <!-- infinite loading -->
        <infinite-loading
          v-if="!isSearch"
          :identifier="infiniteId"
          @infinite="infiniteHandler"
          spinner="waveDots"
        >
          <div slot="no-results"></div>
        </infinite-loading>
      </v-container>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
import InfiniteLoading from "vue-infinite-loading";
import { mapGetters, mapState } from "vuex";

export default {
  name: "DonationListComp",
  components: {
    InfiniteLoading
  },
  data() {
    return {
      tab: null,
      donationList: [],
      page: [1, 1, 1, 1, 1, 1, 1],
      isFirst: true,
      infiniteId: 0,
      all: [],
      child: [],
      old: [],
      disabled: [],
      woman: [],
      multi: [],
      others: [],
      searchTxt: "",
      searchList: [],
      isSearch: false
    };
  },

  created() {
    // this.init();
  },

  methods: {
    init() {
      http
        .get("/donation/getList/0")
        .then(res => {
          this.donationList = res.data;
          this.all = this.donationList[0];
          this.child = this.donationList[1];
          this.old = this.donationList[2];
          this.disabled = this.donationList[3];
          this.woman = this.donationList[4];
          this.multi = this.donationList[5];
          this.others = this.donationList[6];
        })
        .catch(err => {
          console.log(err);
        });
    },
    infiniteHandler($state) {
      if (this.isFirst) {
        http
          .get("/donation/getList/0")
          .then(res => {
            this.donationList = res.data;
            this.all = this.donationList[0];
            this.child = this.donationList[1];
            this.old = this.donationList[2];
            this.disabled = this.donationList[3];
            this.woman = this.donationList[4];
            this.multi = this.donationList[5];
            this.others = this.donationList[6];
            $state.loaded();
            this.IsEndList();
          })
          .catch(err => {
            console.log(err);
          });
        this.isFirst = false;
      } else {
        var curTab = this.tab.split("-")[1] - 1;
        http
          .get(`/donation/getCategoryList/${curTab}/${this.page[curTab]}`)
          .then(res => {
            setTimeout(() => {
              if (res.data.length != 0) {
                if (curTab == 0) {
                  this.all = this.all.concat(res.data);
                  this.IsEndList();
                  $state.loaded();
                  this.page[curTab] += 1;

                  this.IsEndList();
                  if (this.all.length / 8 < 1) {
                    $state.complete();
                  }
                } else if (curTab == 1) {
                  this.child = this.child.concat(res.data);
                  $state.loaded();
                  this.page[curTab] += 1;
                  if (this.child.length / 8 < 1) {
                    $state.complete();
                  }
                } else if (curTab == 2) {
                  this.old = this.old.concat(res.data);
                  $state.loaded();
                  this.page[curTab] += 1;
                  if (this.old.length / 8 < 1) {
                    $state.complete();
                  }
                } else if (curTab == 3) {
                  this.disabled = this.disabled.concat(res.data);
                  $state.loaded();
                  this.page[curTab] += 1;
                  if (this.disabled.length / 8 < 1) {
                    $state.complete();
                  }
                } else if (curTab == 4) {
                  this.woman = this.woman.concat(res.data);
                  $state.loaded();
                  this.page[curTab] += 1;
                  if (this.woman.length / 8 < 1) {
                    $state.complete();
                  }
                } else if (curTab == 5) {
                  this.multi = this.multi.concat(res.data);
                  $state.loaded();
                  this.page[curTab] += 1;
                  if (this.multi.length / 8 < 1) {
                    $state.complete();
                  }
                } else {
                  this.others = this.others.concat(res.data);
                  $state.loaded();
                  this.page[curTab] += 1;
                  if (this.others.length / 8 < 1) {
                    $state.complete();
                  }
                }
              } else {
                $state.complete();
              }
            }, 500);
          })
          .catch(err => {
            console.log(err);
          });
      }
    },
    getDetail(donationid) {
      scroll(0, 0);
      this.$router.push({
        name: "DonationRequestDetail",
        params: { ID: donationid }
      });
    },
    toPercent(now, max) {
      var percent = (now / max) * 100;
      return Math.round(percent * 10) / 10.0;
    },
    getImg(img) {
      img = img.split("|")[0];
      return "../../../contents/" + img;
    },
    goCreate() {
      this.$router.push({
        name: "DonationRequestCreate"
      });
    },
    IsEndList() {
      for (var i = 0; i < this.all.length; i++) {
        var now = this.all[i].nowcnt;
        var max = this.all[i].maxcnt;
        if (now == max) {
          http
            .put(`/donation/template/${this.all[i].donationid}`)
            .then(() => {
              location.reload();
            })
            .catch(e => {
              console.log(e);
            });
        }
      }
    }
  },
  watch: {
    searchTxt(req) {
      if (req == "" || req == null) {
        this.all = this.donationList[0];
        this.isSearch = false;
      } else {
        http
          .get(`/donation/searchList/${req}`)
          .then(res => {
            this.all = res.data;
            this.isSearch = true;
            this.tab = "#tab-1";
          })
          .catch(err => {
            console.log(err);
          });
      }
    }
  },
  computed: {
    ...mapGetters(["isAuthenticated", "isProfileLoaded"]),
    ...mapState({
      authLoading: state => state.auth.status === "loading"
    })
  }
};
</script>
<style scoped>
.donation-img {
  height: 200px !important;
  width: 100% !important;
  /* object-fit: cover; */
  transform: scale(1);
  transition: all 0.3s ease-in-out;
}

.donation-img:hover {
  transform: scale(1.1);
  transition: all 0.3s ease-in-out;
  cursor: pointer;
}

.donation-title {
  cursor: pointer;
  height: 70px;
  align-items: baseline;
  text-overflow: ellipsis;
  overflow: hidden;
  line-height: 1.8rem;
}
.basil {
  background-color: #fffbe6 !important;
}
.basil--text {
  color: #356859 !important;
}
.tab-text {
  font-weight: bold;
  text-decoration: none;
}
.header_img {
  background: url("../../assets/donation_hd2.jpg") no-repeat;
  /* background: linear-gradient(to right,  rgb(183, 166, 141),  rgb(174, 155, 126)); */
  /* opacity: 0.8; */
  width: 100%;
  height: 20rem;
  display: block;
  /* background-size: 100% 6.3rem; */
  background-position: center;
  /* box-shadow: 10px 10px 10px 10px red; */
  background-size: cover;
  /* z-index:2; */
  /* filter: blur(2px);  */
  /* border-bottom:5px solid #fffbe6; */
}
/* .test{
  background: url("../../assets/donation_hd.jpg") no-repeat;
  /* opacity: 0.8; 
  width: 100%;
  height: 100%;
  /* background-size: 100% 6.3rem; 
  background-position: center;
  background-size:cover;
}*/
</style>