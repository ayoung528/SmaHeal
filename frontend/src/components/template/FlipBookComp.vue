<template>
  <div class=" col-12" align="center" style="width:100%;">
    <flipbook class="flipbook " :pages="pages" v-slot="flipbook" :zooms="[1]" align="center">
      <v-row class="d-flex justify-content-between mt-2">
      <v-btn icon @click="flipbook.flipLeft">
        <v-icon style="font-size:3rem;">mdi-chevron-left</v-icon>
      </v-btn>
      <v-btn icon @click="flipbook.flipRight">
        <v-icon style="font-size:3rem;">mdi-chevron-right</v-icon>
      </v-btn>
      </v-row>
    </flipbook>
  </div>
</template>

<script>
import flipbook from "flipbook-vue";

export default {
  name: "FlipBookComp",
  components: { flipbook },
  data() {
    return {
      pages: [null,'/images/firstpage.png'],
      comments:[],
      el: [],
    };
  },
  props: {
    propsItem: { type: Array }
  },
  created(){
    for(var i = 0;i<this.propsItem.length;i++){
      this.pages.push('/images/'+this.propsItem[i].photo);
      this.comments.push(this.propsItem[i].comment);
    }
  },
  mounted() {
    this.el = document.getElementsByClassName("container");
    if(this.el != null) {
      this.el[0].setAttribute("class", "")
    }
    //page클래스 position없애면 위로 감
  },
};
</script>

<style>
.flipbook {
  max-width: 100%;
  height:700px;
  border: 2px solid lightgray;
  border-radius : 10px;
}
</style>