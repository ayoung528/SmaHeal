<template>
  <div class="p-3" align="center">
    <v-simple-table style="text-align:center; width:90%" v-if="tempList.length != 0">
      <thead>
        <tr style="background-color:#fffbe6;">
          <td>번호</td>
          <td>제목</td>
          <td>작성일시</td>
          <td></td>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(temp, index) in tempList" :key="index">
          <td style="width:10%">{{ (page-1)*8+index + 1 }}</td>
          <td
            style="width:50%; text-overflow:ellipsis; overflow: hidden; white-space: nowrap;"
          >
            <router-link
              v-if="temp.donationid"
              v-bind:to="{
                name: 'TempDetailComp',
                params: { ID: temp.donationid }
              }"
              class="btn--text"
              style="color:black;"
              >{{ temp.title }}</router-link
            >
          </td>
          <td style="width:30%">{{ temp.createdate.split("T")[0] }}</td>
          <td
            style="width:10%; color:crimson; cursor:pointer;"
            @click="deleteSet(temp.donationid)"
          >
            삭제
          </td>
        </tr>
      </tbody>
    </v-simple-table>
    <div class="text-center m-3" v-if="tempList.length != 0">
      <v-pagination
        v-model="page"
        :length="length"
        color="#356859"
        prev-icon="mdi-menu-left"
        next-icon="mdi-menu-right"
      ></v-pagination>
    </div>
    <div v-else>
      <v-row class="text-center ma-5">
        <v-col class="text-h2 font-weight-bold">
          💬 No data
        </v-col>
      </v-row>
      <v-row class="text-center">
        <v-col class="text-subtitle-1 black--text">
          임시저장된 글이 없습니다.
        </v-col>
      </v-row>
    </div>
    <v-row justify="center">
      <v-dialog v-model="dialog" persistent max-width="290">
        <v-card>
          <v-card-title>
            글을 삭제하시겠습니까?
          </v-card-title>
          <v-card-text></v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn style="color:crimson" text @click="deleteTemp()">
              삭제
            </v-btn>
            <v-btn text @click="dialog = false">
              취소
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
    <!-- <div class="text-center pt-2">
      <v-pagination v-model="page" :length="pageCount"></v-pagination>
    </div> -->
    <v-snackbar v-model="flag" top right :timeout="3000" color="#356859">
      삭제되었습니다.
      <template v-slot:action="{ attrs }">
        <v-btn text v-bind="attrs" @click="flag = false">Close</v-btn>
      </template>
    </v-snackbar>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
import { mapGetters, mapState } from "vuex";
export default {
  data() {
    return {
      tempList: [],
      dialog: false,
      deleteId: "",
      page: 1,
      length: 0,
      flag: false,
    };
  },
  created() {
    this.init();
    http
        .get(`/donation/getTempCnt/${this.getProfile}`)
        .then(res => {
          if (res.data % 8 == 0) {
            this.length = Math.floor(res.data / 8);
          } else {
            this.length = Math.floor(res.data / 8) + 1;
          }
        })
        .catch(err => {
          console.log(err);
        });
  },
  methods: {
    init() {
      http
        .get(`/donation/getTempList/${this.getProfile}/${this.page - 1}`)
        .then(res => {
          this.tempList = res.data;
        })
        .catch(err => {
          console.log(err);
        });
    },
    deleteSet(id) {
      this.deleteId = id;
      this.dialog = true;
    },
    deleteTemp() {
      this.dialog = false;
      http
        .delete(`/donation/delete/${this.deleteId}`)
        .then(() => {
          this.flag=true;
          this.init();
        })
        .catch(err => {
          console.log(err);
        });
    }
  },
  computed: {
    ...mapGetters(["getProfile"]),
    ...mapState({
      uname: state => `${state.user.getProfile}`
    })
  },
  watch:{
    page(){
      this.init();
    }
  }
};
</script>