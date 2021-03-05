<template>
  <div>
    <div class="r_header_img d-flex justify-content-center">
      <div style="color:white; position:absolute; top:200px; font-size:3rem;">
        감사게시판
      </div>
    </div>
    <div
      class="d-flex justify-content-center"
      style="background-color:rgb(60,79,86)"
    >
      <v-container fluid class="mt-0 col-md-11 p-0">
        <v-card class="topbar d-none d-md-block">
          <v-toolbar color="#fffbe6" flat>
            <!-- <v-toolbar-title class="pl-5 tab-text basil--text">후기 게시판</v-toolbar-title> -->
            <v-spacer></v-spacer>
            <v-btn v-if="getProfile" class="green-mbtn" outlined>{{
              tabTitle
            }}</v-btn>

            <template v-slot:extension>
              <v-tabs center-active color="basil" class="basil--text" grow>
                <v-tab id="tab1" class="tab-text" @click="goTab(1)"
                  >아동/청소년</v-tab
                >
                <v-tab id="tab2" class="tab-text" @click="goTab(2)"
                  >어르신</v-tab
                >
                <v-tab id="tab3" class="tab-text" @click="goTab(3)"
                  >장애인</v-tab
                >
                <v-tab id="tab4" class="tab-text" @click="goTab(4)"
                  >가족/여성</v-tab
                >
                <v-tab id="tab5" class="tab-text" @click="goTab(5)"
                  >다문화</v-tab
                >
                <v-tab id="tab6" class="tab-text" @click="goTab(6)">기타</v-tab>
              </v-tabs>
            </template>
          </v-toolbar>
        </v-card>
        <v-card class="mobileTopbar d-block d-md-none">
          <v-toolbar color="#fffbe6" flat>
            <v-toolbar-title class="pl-5 tab-text basil--text"
              >후기 게시판</v-toolbar-title
            >
            <v-spacer></v-spacer>
            <v-btn v-if="getProfile" class="green-mbtn" outlined>{{
              tabTitle
            }}</v-btn>

          <template v-slot:extension>
            <v-tabs center-active color="basil" class="basil--text" grow>
              <v-tab id="tab1" class="tab-text" @click="goTab(1)">아동/청소년</v-tab>
              <v-tab id="tab2" class="tab-text" @click="goTab(2)">어르신</v-tab>
              <v-tab id="tab3" class="tab-text" @click="goTab(3)">장애인</v-tab>
              <v-tab id="tab4" class="tab-text" @click="goTab(4)">가족/여성</v-tab>
              <v-tab id="tab5" class="tab-text" @click="goTab(5)">다문화</v-tab>
              <v-tab id="tab6" class="tab-text" @click="goTab(6)">기타</v-tab>
            </v-tabs>
          </template>
        </v-toolbar>
      </v-card>
      <!-- 아동,청소년 -->
      <v-sheet v-if="child.length != 0" class="col-12 mb-15 sheet" elevation="8">
        <v-slide-group v-model="model" center-active show-arrows>
          <v-slide-item v-for="(item, index) in child" :key="index">
            <v-card class="ma-4 " height="280" width="200">
              <ReviewListComp
                :num="item.num"
                :nickName="item.nickName"
                :title="item.title"
                :content="item.content"
                :img="item.img"
                :likeCnt="item.likeCnt"
                :visit="item.visit"
                :createdAt="item.createdAt"
              />
            </v-card>
          </v-slide-item>
        </v-slide-group>
      </v-sheet>
      <v-sheet v-if="child.length == 0" class="col-12 mb-15 sheet" elevation="8">
        <v-row class="text-center pt-10">
          <v-col class="h3 pt-10">
            👨‍🎓 아동/청소년에 관한 해당 후기글이 없습니다.
          </v-col>
        </v-row>
        <v-row class="text-center">
          <v-col class="text-subtitle-2">
            후기를 남기고 사람들과 따뜻함을 공유하세요 :)
          </v-col>
        </v-row>
      </v-sheet>
      <!-- 어르신 -->
      <v-sheet v-if="senior.length != 0" class="col-12 mb-15 sheet" elevation="8">
        <v-slide-group v-model="model" center-active show-arrows>
          <v-slide-item v-for="(item, index) in senior" :key="index">
            <v-card class="ma-4 " height="280" width="200">
              <ReviewListComp
                :num="item.num"
                :nickName="item.nickName"
                :title="item.title"
                :content="item.content"
                :img="item.img"
                :likeCnt="item.likeCnt"
                :visit="item.visit"
                :createdAt="item.createdAt"
              />
            </v-card>
          </v-slide-item>
        </v-slide-group>
      </v-sheet>
      <v-sheet v-if="senior.length == 0" class="col-12 mb-15 sheet" elevation="8">
        <v-row class="text-center pt-10">
          <v-col class="h3 pt-10">
            👵 어르신에 관한 해당 후기글이 없습니다.
          </v-col>
        </v-row>
        <v-row class="text-center">
          <v-col class="text-subtitle-2">
            후기를 남기고 사람들과 따뜻함을 공유하세요 :)
          </v-col>
        </v-row>
      </v-sheet>
      <!-- 장애인 -->
      <v-sheet v-if="disabled.length != 0" class="col-12 mb-15 sheet" elevation="8">
        <v-slide-group v-model="model" center-active show-arrows>
          <v-slide-item v-for="(item, index) in disabled" :key="index">
            <v-card class="ma-4 " height="280" width="200">
              <ReviewListComp
                :num="item.num"
                :nickName="item.nickName"
                :title="item.title"
                :content="item.content"
                :img="item.img"
                :likeCnt="item.likeCnt"
                :visit="item.visit"
                :createdAt="item.createdAt"
              />
            </v-card>
          </v-slide-item>
        </v-slide-group>
      </v-sheet>
      <v-sheet v-if="disabled.length == 0" class="col-12 mb-15 sheet" elevation="8">
        <v-row class="text-center pt-10">
          <v-col class="h3 pt-10">
            ♿ 장애인에 관한 해당 후기글이 없습니다.
          </v-col>
        </v-row>
        <v-row class="text-center">
          <v-col class="text-subtitle-2">
            후기를 남기고 사람들과 따뜻함을 공유하세요 :)
          </v-col>
        </v-row>
      </v-sheet>
      <!-- 가족,여성 -->
      <v-sheet v-if="family.length != 0" class="col-12 mb-15 sheet" elevation="8" >
        <v-slide-group v-model="model" center-active show-arrows>
          <v-slide-item v-for="(item, index) in family" :key="index">
            <v-card class="ma-4 " height="280" width="200">
              <ReviewListComp
                :num="item.num"
                :nickName="item.nickName"
                :title="item.title"
                :content="item.content"
                :img="item.img"
                :likeCnt="item.likeCnt"
                :visit="item.visit"
                :createdAt="item.createdAt"
              />
            </v-card>
          </v-slide-item>
        </v-slide-group>
      </v-sheet>
      <v-sheet v-if="family.length == 0" class="col-12 mb-15 sheet" elevation="8">
        <v-row class="text-center pt-10">
          <v-col class="h3 pt-10">
            👪 가족/여성에 관한 해당 후기글이 없습니다.
          </v-col>
        </v-row>
        <v-row class="text-center">
          <v-col class="text-subtitle-2">
            후기를 남기고 사람들과 따뜻함을 공유하세요 :)
          </v-col>
        </v-row>
      </v-sheet>
      <!-- 다문화 -->
      <v-sheet v-if="cultural.length != 0" class="col-12 mb-15 sheet" elevation="8">
        <v-slide-group v-model="model" center-active show-arrows>
          <v-slide-item v-for="(item, index) in cultural" :key="index">
            <v-card class="ma-4 " height="280" width="200">
              <ReviewListComp
                :num="item.num"
                :nickName="item.nickName"
                :title="item.title"
                :content="item.content"
                :img="item.img"
                :likeCnt="item.likeCnt"
                :visit="item.visit"
                :createdAt="item.createdAt"
              />
            </v-card>
          </v-slide-item>
        </v-slide-group>
      </v-sheet>
      <v-sheet v-if="cultural.length == 0" class="col-12 mb-15 sheet" elevation="8">
        <v-row class="text-center pt-10">
          <v-col class="h3 pt-10">
            🌎 다문화에 관한 해당 후기글이 없습니다.
          </v-col>
        </v-row>
        <v-row class="text-center">
          <v-col class="text-subtitle-2">
            후기를 남기고 사람들과 따뜻함을 공유하세요 :)
          </v-col>
        </v-row>
      </v-sheet>
      <!-- 기타 -->
      <v-sheet v-if="etc.length != 0" class="col-12 mb-15 sheet" elevation="8">
        <v-slide-group v-model="model" center-active show-arrows>
          <v-slide-item v-for="(item, index) in etc" :key="index">
            <v-card class="ma-4 " height="280" width="200">
              <ReviewListComp
                :num="item.num"
                :nickName="item.nickName"
                :title="item.title"
                :content="item.content"
                :img="item.img"
                :likeCnt="item.likeCnt"
                :visit="item.visit"
                :createdAt="item.createdAt"
              />
            </v-card>
          </v-slide-item>
        </v-slide-group>
      </v-sheet>
      <v-sheet v-if="etc.length == 0" class="col-12 mb-15 sheet" elevation="8">
        <v-row class="text-center pt-10">
          <v-col class="h3 pt-10">
            🎸 기타에 관한 해당 후기글이 없습니다.
          </v-col>
        </v-row>
        <v-row class="text-center">
          <v-col class="text-subtitle-2">
            후기를 남기고 사람들과 따뜻함을 공유하세요 :)
          </v-col>
        </v-row>
      </v-sheet>
      <div style="height:340px;"></div>
    </v-container>
    <br />
  </div>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
import ReviewListComp from "@/components/review/ReviewListComp.vue";
import { mapGetters } from "vuex";

export default {
  name: "ReviewList",
  data() {
    return {
      model: null,
      len: 0,
      child: [],
      senior: [],
      disabled: [],
      family: [],
      cultural: [],
      etc: [],
      toTop: 0,
      tabTitle: "아동/청소년"
    };
  },
  components: {
    ReviewListComp
  },
  created() {
    this.getChild();
    this.getSenior();
    this.getDiabled();
    this.getFamily();
    this.getCultural();
    this.getEtc();
    window.addEventListener("scroll", this.handleScroll);
  },
  computed: {
    ...mapGetters(["getProfile"])
  },
  methods: {
    getChild() {
      http
        .get("/review/categoryList/아동,청소년")
        .then(res => {
          this.child = res.data;
        })
        .catch(err => {
          console.log(err);
        });
    },
    getSenior() {
      http
        .get("/review/categoryList/어르신")
        .then(res => {
          this.senior = res.data;
        })
        .catch(err => {
          console.log(err);
        });
    },
    getDiabled() {
      http
        .get("/review/categoryList/장애인")
        .then(res => {
          this.disabled = res.data;
        })
        .catch(err => {
          console.log(err);
        });
    },
    getFamily() {
      http
        .get("/review/categoryList/가족,여성")
        .then(res => {
          this.family = res.data;
        })
        .catch(err => {
          console.log(err);
        });
    },
    getCultural() {
      http
        .get("/review/categoryList/다문화")
        .then(res => {
          this.cultural = res.data;
        })
        .catch(err => {
          console.log(err);
        });
    },
    getEtc() {
      http
        .get("/review/categoryList/기타")
        .then(res => {
          this.etc = res.data;
        })
        .catch(err => {
          console.log(err);
        });
    },
    goTab(num) {
      if (num == 1) {
        scroll(0, 0);
      } else if (num == 2) {
        scroll(0, 730);
      } else if (num == 3) {
        scroll(0, 1130);
      } else if (num == 4) {
        scroll(0, 1530);
      } else if (num == 5) {
        scroll(0, 1930);
      } else if (num == 6) {
        scroll(0, 2330);
      }
    },
    handleScroll() {
      this.toTop = document.documentElement.scrollTop;
      if (this.toTop >= 700 && this.toTop < 1100) {
        this.tabTitle = '어르신';
        document.getElementById('tab1').setAttribute('aria-selected', false);
        document.getElementById('tab1').classList.remove('v-tab--active');
        document.getElementById('tab2').setAttribute('aria-selected', true);
        document.getElementById('tab2').classList.add('v-tab--active');
        document.getElementById('tab3').setAttribute('aria-selected', false);
        document.getElementById('tab3').classList.remove('v-tab--active');
        document.getElementById('tab4').setAttribute('aria-selected', false);
        document.getElementById('tab4').classList.remove('v-tab--active');
        document.getElementById('tab5').setAttribute('aria-selected', false);
        document.getElementById('tab5').classList.remove('v-tab--active');
        document.getElementById('tab6').setAttribute('aria-selected', false);
        document.getElementById('tab6').classList.remove('v-tab--active');
      } else if (this.toTop >= 1100 && this.toTop < 1500) {
        this.tabTitle = '장애인';
        document.getElementById('tab1').setAttribute('aria-selected', false);
        document.getElementById('tab1').classList.remove('v-tab--active');
        document.getElementById('tab2').setAttribute('aria-selected', false);
        document.getElementById('tab2').classList.remove('v-tab--active');
        document.getElementById('tab3').setAttribute('aria-selected', true);
        document.getElementById('tab3').classList.add('v-tab--active');
        document.getElementById('tab4').setAttribute('aria-selected', false);
        document.getElementById('tab4').classList.remove('v-tab--active');
        document.getElementById('tab5').setAttribute('aria-selected', false);
        document.getElementById('tab5').classList.remove('v-tab--active');
        document.getElementById('tab6').setAttribute('aria-selected', false);
        document.getElementById('tab6').classList.remove('v-tab--active');
      } else if (this.toTop >= 1500 && this.toTop < 1900) {
        this.tabTitle = '가족/여성';
        document.getElementById('tab1').setAttribute('aria-selected', false);
        document.getElementById('tab1').classList.remove('v-tab--active');
        document.getElementById('tab2').setAttribute('aria-selected', false);
        document.getElementById('tab2').classList.remove('v-tab--active');
        document.getElementById('tab3').setAttribute('aria-selected', false);
        document.getElementById('tab3').classList.remove('v-tab--active');
        document.getElementById('tab4').setAttribute('aria-selected', true);
        document.getElementById('tab4').classList.add('v-tab--active');
        document.getElementById('tab5').setAttribute('aria-selected', false);
        document.getElementById('tab5').classList.remove('v-tab--active');
        document.getElementById('tab6').setAttribute('aria-selected', false);
        document.getElementById('tab6').classList.remove('v-tab--active');
      } else if (this.toTop >= 1900 && this.toTop < 2300) {
        this.tabTitle = '다문화';
        document.getElementById('tab1').setAttribute('aria-selected', false);
        document.getElementById('tab1').classList.remove('v-tab--active');
        document.getElementById('tab2').setAttribute('aria-selected', false);
        document.getElementById('tab2').classList.remove('v-tab--active');
        document.getElementById('tab3').setAttribute('aria-selected', false);
        document.getElementById('tab3').classList.remove('v-tab--active');
        document.getElementById('tab4').setAttribute('aria-selected', false);
        document.getElementById('tab4').classList.remove('v-tab--active');
        document.getElementById('tab5').setAttribute('aria-selected', true);
        document.getElementById('tab5').classList.add('v-tab--active');
        document.getElementById('tab6').setAttribute('aria-selected', false);
        document.getElementById('tab6').classList.remove('v-tab--active');
      } else if (this.toTop >= 2300) {
        this.tabTitle = '기타';
        document.getElementById('tab1').setAttribute('aria-selected', false);
        document.getElementById('tab1').classList.remove('v-tab--active');
        document.getElementById('tab2').setAttribute('aria-selected', false);
        document.getElementById('tab2').classList.remove('v-tab--active');
        document.getElementById('tab3').setAttribute('aria-selected', false);
        document.getElementById('tab3').classList.remove('v-tab--active');
        document.getElementById('tab4').setAttribute('aria-selected', false);
        document.getElementById('tab4').classList.remove('v-tab--active');
        document.getElementById('tab5').setAttribute('aria-selected', false);
        document.getElementById('tab5').classList.remove('v-tab--active');
        document.getElementById('tab6').setAttribute('aria-selected', true);
        document.getElementById('tab6').classList.add('v-tab--active');
      } else {
        this.tabTitle = "아동/청소년";
        document.getElementById("tab1").setAttribute("aria-selected", true);
        document.getElementById("tab1").classList.add("v-tab--active");
        document.getElementById("tab2").setAttribute("aria-selected", false);
        document.getElementById("tab2").classList.remove("v-tab--active");
        document.getElementById("tab3").setAttribute("aria-selected", false);
        document.getElementById("tab3").classList.remove("v-tab--active");
        document.getElementById("tab4").setAttribute("aria-selected", false);
        document.getElementById("tab4").classList.remove("v-tab--active");
        document.getElementById("tab5").setAttribute("aria-selected", false);
        document.getElementById("tab5").classList.remove("v-tab--active");
        document.getElementById("tab6").setAttribute("aria-selected", false);
        document.getElementById("tab6").classList.remove("v-tab--active");
      }
    }
  },
  beforeDestroy: function() {
    window.removeEventListener("scroll", this.handleScroll);
  },
  watch: {
    page(page) {
      page = (page - 1) * 8;
      http
        .get(`/review/getList/${page}`)
        .then(({ data }) => {
          this.items = data;
        })
        .catch(err => {
          console.log(err);
        });
    }
  }
};
</script>

<style>
.topbar {
  z-index: 10;
  position: -webkit-sticky;
  position: sticky;
  top: 0px;
}
.mobileTopbar {
  z-index: 2;
  position: -webkit-sticky;
  position: sticky;
  top: 55px;
}
.tab-text {
  font-weight: bold;
}
.sheet {
  height: 340px;
}
.r_header_img {
  background: url("../../assets/donation_hd.jpg") no-repeat;
  width: 100%;
  height: 20rem;
  display: block;
  background-position: center;
  background-size: cover;
}
</style>
