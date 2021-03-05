<template>
  <v-container fluid class="pa-0">
    <v-main class="pa-0">
      <section id="hero">
        <v-row no-gutters>
          <v-img
            :min-height="'calc(100vh - ' + $vuetify.application.top + 'px)'"
            :src="require(`@/assets/main-smile.jpg`)"
          >
            <v-theme-provider dark>
              <v-container fill-height>
                <v-row
                  align="center"
                  class="white--text mx-auto"
                  justify="center"
                >
                  <v-col class="white--text text-center" cols="12"><br/>
                    <span
                      class=""
                      style="font-family: 'Gamja Flower',cursive;font-weight:bold; font-size:4rem;"
                    >
                      세상에서 가장 쉬운 기부, 웃음
                    </span>

                    <br />

                    <span
                      style="font-family: 'Gamja Flower',cursive;font-weight:bold; font-size:6rem;"
                    >
                      Let's 스마힐
                    </span>
                  </v-col>

                  <v-btn
                    class="align-self-end"
                    fab
                    outlined
                    @click="$vuetify.goTo('#features')"
                  >
                    <v-icon>mdi-chevron-double-down</v-icon>
                  </v-btn>
                </v-row>
              </v-container>
            </v-theme-provider>
          </v-img>
        </v-row>
      </section>

      <!-- <section id="about-me">
        <div class="py-12"></div>

        <v-container class="text-center">
          <h2 class="display-2 font-weight-bold mb-3">ABOUT US</h2>

          <v-responsive class="mx-auto mb-8" width="56">
            <v-divider class="mb-1"></v-divider>

            <v-divider></v-divider>
          </v-responsive>

          <v-responsive
            class="mx-auto title font-weight-light mb-8"
            max-width="720"
          >
            Vuetify is the #1 component library for Vue.js and has been in
            active development since 2016. The goal of the project is to provide
            users with everything that is needed to build rich and engaging web
            applications using the Material Design specification. It
            accomplishes that with a consistent update cycle, Long-term Support
            (LTS) for previous versions, responsive community engagement, a vast
            ecosystem of resources and a dedication to quality components.
          </v-responsive>

          <v-avatar class="elevation-12 mb-12" size="128">
            <v-img :src="require(`@/assets/smile-avatar.jpg`)"></v-img>
          </v-avatar>

          <div></div>
        </v-container>

        <div class="py-12"></div>
      </section> -->

      <section id="features" style="background-color:#fffbe6;">
        <div class="py-12"></div>

        <v-container class="text-center">
          <!-- <h2 class="display-2 font-weight-bold mb-3">SMAHEAL FEATURES</h2>

          <v-responsive class="mx-auto mb-12" width="56">
            <v-divider class="mb-1"></v-divider>

            <v-divider></v-divider>
          </v-responsive> -->

          <v-row>
            <v-col
              v-for="({ icon, title, text }, i) in features"
              :key="i"
              cols="12"
              md="4"
            >
              <v-card
                class="py-12 px-4 rounded-lg"
                flat
                style="background-color:#fffbe6; border:3px solid #356859"
              >
                <v-theme-provider dark>
                  <div>
                    <v-avatar color="#356859" size="88">
                      <v-icon large v-text="icon"></v-icon>
                    </v-avatar>
                  </div>
                </v-theme-provider>

                <v-card-title
                  class="justify-center font-weight-black text-uppercase"
                  v-text="title"
                ></v-card-title>

                <v-card-text
                  class="font-weight-black"
                  style="font-size:1rem; line-height:2rem; word-spacing:2px; "
                  v-text="text"
                >
                </v-card-text>
              </v-card>
            </v-col>
          </v-row>
        </v-container>

        <div class="py-12"></div>
      </section>

      <!-- 기부수 -->
      <section id="stats">
        <v-parallax
          :height="$vuetify.breakpoint.smAndDown ? 700 : 500"
          :src="require(`@/assets/main-happy.jpg`)"
        >
          <v-container fill-height>
            <v-row cols="12" class="d-flex justify-content-center">
              <v-row cols="6" class="d-flex justify-content-center">
                <div
                  class="text-center my-auto mr-10"
                  style="font-size:4em;line-height:1.3em;"
                >
                  TODAY's <br />웃음기부
                </div>
                <div class="iCountUp my-auto">
                  <ICountUp
                    :endVal="endVal"
                    :options="options"
                    @ready="onReady2"
                  />
                </div>
              </v-row>
              <v-row cols="6" class="d-flex justify-content-center">
                <div
                  class="text-center my-auto mr-10"
                  style="font-size:4em; line-height:1.3em;"
                >
                  TOTAL's <br />웃음기부
                </div>
                <div class="iCountUp my-auto">
                  <ICountUp
                    :endVal="endVal"
                    :options="options"
                    @ready="onReady"
                  />
                </div>
              </v-row>
              <!-- <v-col
                v-for="[value, title] of stats"
                :key="title"
                cols="12"
                md="3"
              >
                <div class="text-center">
                  <div
                    class="display-3 font-weight-black mb-4"
                    v-text="value"
                  ></div>

                  <div
                    class="title font-weight-regular text-uppercase"
                    v-text="title"
                  ></div>
                </div>
              </v-col> -->
            </v-row>
          </v-container>
        </v-parallax>
      </section>

      <section id="smileKing" style="background-color:#fffbe6;">
        <div class="py-5"></div>
        <SmileKingComp />
        <div class="py-5"></div>
      </section>

      <!-- <section id="blog">
        <div class="py-12"></div>

        <v-container>
          <h2
            class="display-2 font-weight-bold mb-3 text-uppercase text-center"
          >
            Blog
          </h2>

          <v-responsive class="mx-auto mb-12" width="56">
            <v-divider class="mb-1"></v-divider>

            <v-divider></v-divider>
          </v-responsive>

          <v-row>
            <v-col
              v-for="({ src, text, title }, i) in articles"
              :key="i"
              cols="12"
              md="4"
            >
              <v-img
                :src="src"
                class="mb-4"
                height="275"
                max-width="100%"
              ></v-img>

              <h3
                class="font-weight-black mb-4 text-uppercase"
                v-text="title"
              ></h3>

              <div class="title font-weight-light mb-5" v-text="text"></div>

              <v-btn class="ml-n4 font-weight-black" text>
                Continue Reading
              </v-btn>
            </v-col>
          </v-row>
        </v-container>

        <div class="py-12"></div>
      </section> -->
    </v-main>
  </v-container>
</template>
<script>
import http from "@/util/http-common.js";
import ICountUp from "vue-countup-v2";
import SmileKingComp from "@/components/smile/SmileKingComp.vue";
export default {
  name: "HelloWorld",
  components: {
    ICountUp,
    SmileKingComp
  },
  data: () => ({
    articles: [
      {
        src:
          "https://images.unsplash.com/photo-1423784346385-c1d4dac9893a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80",
        title: "Mobile first & Responsive",
        text:
          "Phasellus lorem enim, luctus ut velit eget, convallis egestas eros. Sed ornare ligula eget tortor tempor, quis porta tellus dictum."
      },
      {
        src:
          "https://images.unsplash.com/photo-1475938476802-32a7e851dad1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80",
        title: "Think outside the box",
        text:
          "Nam ut leo ipsum. Maecenas pretium aliquam feugiat. Aenean vel tempor est, vitae tincidunt risus. Sed sodales vestibulum nibh."
      },
      {
        src:
          "https://images.unsplash.com/photo-1416339442236-8ceb164046f8?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1892&q=80",
        title: "Small changes, big difference",
        text:
          "Vestibulum in dictum velit, in rhoncus nibh. Maecenas neque libero, interdum a dignissim in, aliquet vitae lectus. Phasellus lorem enim, luctus ut velit eget."
      }
    ],
    features: [
      {
        icon: "mdi-account-group-outline",
        title: "사람들과 함께 웃음을 기부해요",
        text:
          "SMAHEAL에서는 다양한 사람들과 함께 웃음을 기부할 수 있습니다. 혼자만의 기부가 아닌 같이하는 기부를 SMAHEAL에서 직접 느낄 수 있습니다. 웃음을 기부하여 기쁨을 함께 느껴보세요 !"
      },
      {
        icon: "mdi-emoticon-excited-outline",
        title: "즐기면서 기부해요",
        text:
          "당신은 기부를 위한 형식적인 웃음을 지어도 되지 않습니다. SMAHEAL에서 행복하고 건강한 웃음을 기부할 수 있게 도와드립니다. 각종 미디어를 통해서 즐거움도 느끼고 웃음 기부도 실천해보세요 !"
      },
      {
        icon: "mdi-monitor-dashboard",
        title: "다양한 템플릿으로 웃음을 나눠요",
        text:
          "웃음 기부를 신청한 당신은 템플릿이 적용된 기부를 받을 수 있습니다. SMAHEAL에서는 웃음 기부를 모아서 다양한 템플릿을 적용해 단체 혹은 개인에게 전달해드립니다. 지금 당장 신청해보세요 !"
      }
    ],
    options: {
      useEasing: true,
      useGrouping: true,
      separator: ",",
      decimal: ".",
      prefix: "",
      suffix: ""
    },
    endVal: 0,
    todayCnt: 0,
    totalCnt: 0,
    inst1: "",
    inst2: ""
  }),
  created() {
    http
      .get("/smile/getSmileCnt/")
      .then(res => {
        this.totalCnt = Number(res.data[0]);
        this.todayCnt = Number(res.data[1]);
      })
      .catch(err => {
        console.log(err);
      });
    window.addEventListener("scroll", () => {
      var cur = document.documentElement.scrollTop;
      if (cur >= 1000) {
        if (this.inst1 != "") this.inst1.update(this.totalCnt);
        if (this.inst2 != "") this.inst2.update(this.todayCnt);
      }
      // console.log(document.documentElement.scrollTop);
    });
  },
  methods: {
    onReady: function(instance) {
      this.inst1 = instance;
    },
    onReady2: function(instance) {
      this.inst2 = instance;
    }
  }
};
</script>
<style scoped>
.iCountUp {
  font-size: 10em;
  margin: 0;
  color: white;
}
</style>