<template>
  <v-container>
    <v-row class="text-center">
      <v-col class="h1">
          🚫 {{code}} :{{msg}}
      </v-col>
    </v-row>
    <br />
    <v-row class="text-center">
        <v-col class="h3">
            죄송합니다. 요청하신 페이지를 찾을 수 없습니다.
        </v-col>
    </v-row>
    <v-row class="text-center">
        <v-col>
            방문하시려는 페이지의 주소가 잘못 입력되었거나, 페이지의 주소가 변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다.
        </v-col>
    </v-row>
    <v-row class="text-center">
        <v-col>
             입력하신 주소가 정확한지 다시 한번 확인해 주시기 바랍니다.
        </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "error",
  data() {
    return {
      code: "404",
      msg: "PAGE NOT FOUND"
    };
  }
};
</script>
