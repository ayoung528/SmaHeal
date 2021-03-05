<template>
  <v-container class="pa-0" fluid>
    <v-col class="pa-0">
      <v-img :src="require(`@/assets/main-smile.jpg`)" height="653">
        <div style="padding-top: 25px; width: 75%; margin: 150px auto;">
          <v-col style="width: 50%; margin: 0 auto;">
            <v-form action="/template/search">
              <v-row>
                <v-text-field
                placeholder="일련번호를 입력하세요. 기부된 사진을 검색합니다."
                outlined
                color="#356859"
                clearable
                name="template"
                class="pa-0"
                background-color="white"
                v-model="keyword"
                ref="search"
                />
                <v-btn color="#356859" dark @click="searchTemplate()"
                large
                class="ma-1"
                >
                  찾기
                </v-btn>
              </v-row>
            </v-form>
          </v-col>
        </div>
      </v-img>
    </v-col>
  </v-container>
</template>

<script>
export default {
    name: "searchComp",
    data: function() {
        return {
          keyword: "",
        }
    },
    methods: {
      searchTemplate: function() {
        if (this.keyword == "") {
          this.$refs.search.focus();
        } else {
          this.$router.push(`/template/search?template=${this.keyword}`);
        }
      }
    },
}
</script>