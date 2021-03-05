<template>
  <v-container>
    <v-row>
        <v-col>
            <v-btn @click="showCam()" text>
              show Cam
            </v-btn>
        </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "@/util/http-common.js";

export default {
  name: "DonationDetailComp",

  data(){
   return {

   };
  },
  methods: {
      showCam: function() {
          http.get("/emotion/video")
          .then(response => {
              console.log(response)
          })
          .catch((e) => {
              console.log(e)
          })
      }
  },
};
</script>
