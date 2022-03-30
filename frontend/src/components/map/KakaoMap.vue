<template>
  <div>
    <h1>카카오맵</h1>
    <div id="map"></div>
  </div>
</template>

<script>
import { VUE_APP_KAKAO_KEY } from "@/config/index";
export default {
  name: "KakaoMap",
  data() {
    return {
      map: null,
      around: {
        aniHos: 0,
        park: 0,
        phar: 0,
        pet: 0,
        cafe: 0,
        schl: 0,
      },
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${VUE_APP_KAKAO_KEY}&libraries=services`;
      document.head.appendChild(script);
      console.log("mounted map 생성");
    }
  },
  methods: {
    initMap() {
      console.log("initMap");
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 중심 좌표
        level: 5, // 지도 확대 레벨
      };

      this.map = new kakao.maps.Map(container, options);
    },
  },
};
</script>

<style>
#map {
  width: 70%;
  height: 300px;
  margin: auto;
}
</style>
