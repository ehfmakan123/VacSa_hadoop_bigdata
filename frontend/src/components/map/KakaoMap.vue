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
    }
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");

      if (navigator.geolocation) {
        // 사용자가 위치 동의 한 경우
        // GeoLocation을 이용해서 접속 위치얻어서 맵에 저장
        navigator.geolocation.getCurrentPosition((position) => {
          const options = {
            center: new kakao.maps.LatLng(
              position.coords.latitude,
              position.coords.longitude
            ), // 중심 좌표
            level: 5, // 지도 확대 레벨
          };
          this.map = new kakao.maps.Map(container, options); // 맵 띄우기
        });
      } else {
        // 위치정보를 동의하지 않은경우 임의로 위치 정해서 띄워줌
        const options = {
          center: new kakao.maps.LatLng(33.450701, 126.570667), // 중심 좌표
          level: 5, // 지도 확대 레벨
        };
        this.map = new kakao.maps.Map(container, options);
      }
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
