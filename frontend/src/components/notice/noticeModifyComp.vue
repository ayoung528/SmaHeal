<template>
  <v-container class="pa-0">
    <v-sheet class="pa-6 mt-4" color="#fffbe6">
      <v-icon class="mr-2" color="#356859">mdi-clipboard-edit-outline</v-icon>
      공지사항 수정페이지
    </v-sheet>
    <!-- prepend-icon="mdi-lightbulb-on" -->
    <v-sheet color="#fffbe6" class="pa-5 mb-5">
        <v-text-field
        outlined
        placeholder="제목을 입력하세요."
        color="#356859"
        v-model="title"
        ref="title"
        prepend-inner-icon="mdi-lightbulb-on"
        :counter="20"
        :rules="titleRules"
        class="mb-3"
        style="font-size:1.5rem"
        />
        <v-textarea
        placeholder="공지사항 내용을 입력하세요."
        color="#356859"
        v-model="content"
        ref="content"
        outlined
        height="300"
        :counter="1000"
        :rules="contentRules"
        />
    </v-sheet>
    <v-row>
        <v-col class="text-right m-3">
            <v-btn class="mr-5" @click="goBack()" color="red darken-4" dark style="font-weight:bold;">
                취소
            </v-btn>
            <v-btn @click="noticeModify()" color="blue darken-4" dark style="font-weight:bold;">
                수정
            </v-btn>
        </v-col>
    </v-row>

    <v-dialog v-model="regist" max-width="400">
      <v-card>
        <v-card-title style="font-size:1.5rem;">공지사항을 수정하겠습니까?</v-card-title>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn text @click="cancelRegist()" style="font-weight:bold;">취소</v-btn>

          <v-btn text @click="noticeModifyHandler()" style="font-weight:bold;color:#000080">수정</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
import http from "@/util/http-common.js";

export default {
  name: "noticeModifyComp",
  props: {
      propItems: {type: Object},
  },
  data(){
   return {
       title: this.propItems.title,
       content: this.propItems.content,
       regist: false,
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
      noticeModify: function() {
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
      noticeModifyHandler: function() {
          http.put(`/notice/update/${this.propItems.noticeid}`, {
              noticeid: this.propItems.noticeid,
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
      goBack: function() {
          this.$router.push('/notice').catch(() => {})
      },
      cancelRegist: function() {
          this.regist = false
      }
  },
};
</script>
