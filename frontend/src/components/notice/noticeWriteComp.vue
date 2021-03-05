<template>
  <v-container class="pa-0">
    <v-sheet class="pa-6 mt-4" color="#fffbe6">
      <v-icon class="mr-2" color="#356859">mdi-clipboard-edit-outline</v-icon>
      공지사항 작성페이지
    </v-sheet>
    <v-sheet color="#fffbe6" class="pa-5 mb-5">
        <v-text-field
        outlined
        placeholder="제목을 입력하세요."
        color="#356859"
        v-model="title"
        ref="title"
        class="headline"
        prepend-inner-icon="mdi-lightbulb-on"
        clearable
        :counter="20"
        :rules="titleRules"
        />
        <v-textarea
        placeholder="공지사항 내용을 입력하세요."
        color="#356859"
        v-model="content"
        ref="content"
        outlined
        clearable
        height="300"
        :counter="1000"
        :rules="contentRules"
        />
    </v-sheet>
    <v-row>
        <v-col>
            <v-btn @click="noticeTempRegist()" color="#356859" dark>
                임시저장
            </v-btn>
        </v-col>
        <v-col class="text-right">
            <v-btn class="mr-5" @click="goBack()" color="#356859" dark>
                취소
            </v-btn>
            <v-btn @click="noticeRegist()" color="#356859" dark>
                등록
            </v-btn>
        </v-col>
    </v-row>

    <v-dialog v-model="temp" max-width="400">
      <v-card>
        <v-card-title class="headline">글을 임시저장하시겠습니까?</v-card-title>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn color="#356859" dark @click="cancelTemp()">취소</v-btn>

          <v-btn color="#356859" dark @click="noticeTempRegistHandler()">확인</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-dialog v-model="tempLoad" max-width="400">
      <v-card>
        <v-card-title class="headline">임시저장한 글이 있습니다. 불러오겠습니까?</v-card-title>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn color="#356859" dark @click="cancelTempLoad()">취소</v-btn>

          <v-btn color="#356859" dark @click="noticeTempLoadHandler()">확인</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-dialog v-model="regist" max-width="400">
      <v-card>
        <v-card-title class="headline">공지사항을 등록하겠습니까?</v-card-title>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn color="#356859" dark @click="cancelRegist()">취소</v-btn>

          <v-btn color="#356859" dark @click="noticeRegistHandler()">확인</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
import http from "@/util/http-common.js";

export default {
  name: "noticeWriteComp",
  data(){
   return {
       title: "",
       content: "",
       regist: false,
       temp: false,
       tempLoad: false,
       alertMsg: "",
       alert: false,
       titleRules: [
           v => (v && v.length <= 20) || "제목은 20글자 이내만 가능합니다."
       ],
       contentRules: [
           v => (v && v.length <= 1000) || "내용은 1000글자 이내만 가능합니다."
       ],
   };
  },
  methods: {
      noticeRegist: function() {
          let err = true;
          let msg = "";

          !this.title && ((msg = "제목을 입력해주세요"),
          (err = false), this.$refs.title.focus());
          !this.content && ((msg = "내용을 입력해주세요"),
          (err = false), this.$refs.content.focus());

          if (!err) {
              this.alertMsg = msg;
              this.alert = true;
          } else {
              this.regist = true;
          }
      },
      noticeRegistHandler: function() {
          http.post("/notice/write", {
              title: this.title,
              content: this.content,
          })
          .then(() => {
            this.regist = false;
            this.$router.push('/notice');
            location.reload();
          })
          .catch(e => {
              this.alertMsg = "등록 처리시 에러가 발생했습니다.";
              this.alert = true;
              console.log(e)
          })
      },
      noticeTempRegist: function() {
          this.temp = true;
      },
      noticeTempRegistHandler: function() {
        localStorage.setItem("title", this.title);
        localStorage.setItem("content", this.content);
        this.temp = false;
      },
      noticeTempLoadHandler: function() {
        this.title = localStorage.getItem("title");
        this.content = localStorage.getItem("content");
        localStorage.removeItem("title");
        localStorage.removeItem("content");
        this.tempLoad = false;
      },
      goBack: function() {
          this.$router.push('/notice').catch(() => {})
      },
      cancelTemp: function() {
        this.temp = false;
      },
      cancelTempLoad: function() {
        this.tempLoad = false;
      },
      cancelRegist: function() {
        this.regist = false;
      }
  },
  created() {
    if (localStorage.getItem("title") !== null) {
      this.tempLoad = true;
    }
  }
};
</script>
