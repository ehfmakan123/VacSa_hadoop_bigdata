<template>
  <b-row>
    <b-col>
      <kakao-Map />
    </b-col>
  </b-row>
</template>

<script>
import KakaoMap from "@/components/map/KakaoMap.vue";
export default {
  name: "Map",

  data: () => ({
    page: {
      title: "Map",
    },
  }),
  components: {
    KakaoMap,
  },
};
</script>
