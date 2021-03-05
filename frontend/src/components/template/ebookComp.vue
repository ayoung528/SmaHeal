<template>
  <v-container>
    <v-timeline-item v-for="(item, i) in items" :key="i" color="amber lighten-4" large>
      <v-card color="amber lighten-4">
        <v-card-title class="title black--text"> 🧡{{ item.userId }}🧡 </v-card-title>
        <img height="300px" :src="getImg(item.photo)" />
        <v-card-text class="white text--primary">
          <p>💌응원의 한 마디💌 "{{ item.comment }}"</p>
        </v-card-text>
      </v-card>
    </v-timeline-item>
  </v-container>
</template>

<script>
export default {
  name: 'ebookComp',
  props: {
    propsitem: { type: Array },
  },
  data() {
    return {
      items: this.propsitem,
    };
  },
  methods: {
    getImg(img) {
      return '../../../images/' + img;
    },
  },
};
</script>
