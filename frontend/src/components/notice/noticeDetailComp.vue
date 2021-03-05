<template>
  <v-container class="col-md-11 m-3" style="border:1px solid lightgray; border-radius:5px;">
    <v-sheet class="pa-5 mb-5">
      <div class="pa-4 text-center" style="font-size:1.5rem;">
        {{this.item.title}}
      </div>
      <div class="text-right pb-5" style="font-size:1rem;">
        {{getFormatDate(this.item.createdAt)}}
      </div>
      <v-divider></v-divider>
      <v-textarea readonly auto-grow solo
      flat
      class="pa-3"
      :value="this.item.content">
      </v-textarea>
    </v-sheet>
    <v-row>
      <v-col>
        <v-btn class="mr-3 ml-5" color="blue darken-4" @click="goWrite()" v-if="getUserID == 'admin'" style="color:white;font-weight:bold">
          <span style="display:inline-flex; vertical-align:middle;" ><v-icon style="font-size:1rem;">mdi-wrench</v-icon>수정</span>
        </v-btn>
        <v-btn @click="deleteNotice()" color="red darken-4" v-if="getUserID == 'admin'" style="color:white;font-weight:bold">
          <span style="display:inline-flex; vertical-align:middle;" ><v-icon style="font-size:1rem;">mdi-trash-can</v-icon>삭제</span>
        </v-btn>
      </v-col>
      <v-col class="text-right">
        <v-btn @click="goBack()" class="green-mbtn mr-2" dark>
            목록으로
        </v-btn>
      </v-col>
    </v-row>

    <v-dialog v-model="del" max-width="400">
      <v-card>
        <v-card-title style="font-size:1.5rem;">정말 삭제하시겠습니까?</v-card-title>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn text @click="cancelDel()" style="font-weight:bold;">취소</v-btn>

          <v-btn text @click="noticeDelHandler()" style="font-weight:bold;color:crimson">삭제</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
import moment from "moment";
import http from "@/util/http-common.js";
import { mapGetters, mapState } from "vuex";

export default {
  name: "noticeDetailComp",
  props: {
      propItem: {type: Object}
  },
  data(){
   return {
       item: this.propItem,
       del: false
   };
  },
  methods: {
    goBack() {
      window.history.back()
    },
    getFormatDate: function(regtime) {
      return moment(new Date(regtime)).format("YYYY년 MM월 DD일");
    },
    deleteNotice: function() {
      this.del = true
    },
    cancelDel: function() {
      this.del = false
    },
    noticeDelHandler: function() {
      http
      .delete(`/notice/delete/${this.item.noticeid}`)
      .then(({data}) => {
        if (data === "success") {
          this.del = false,
          this.$router.push('/notice')
        }
      })
    },
    goWrite: function() {
      this.$router.push(`/notice/modify/${this.item.noticeid}`)
    },
  },
  computed: {
    ...mapGetters([
      "isAuthenticated",
      "isProfileLoaded",
      "getProfile",
      "getRealName",
      "getUserNum",
      "getUserID",
      "getUserBirth",
    ]),
    ...mapState({
      authLoading: state => state.auth.status === "loading",
      uname: state => `${state.user.getProfile}`,
      userNum: state => `${state.user.getUserNum}`,
      userName: state => `${state.user.getRealName}`,
      userID: state => `${state.user.getUserID}`,
      userBirth: state => `${state.user.getUserBirth}`,
    }),
  },
};
</script>
