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
      var container = document.getElementById("map");

      var map = null; // 지도를 생성합니다
      if (navigator.geolocation) {
        // 사용자가 위치 동의 한 경우
        // GeoLocation을 이용해서 접속 위치얻어서 맵에 저장
        navigator.geolocation.getCurrentPosition((position) => {
          var lat = position.coords.latitude;
          var lon = position.coords.longitude;
          const options = {
            center: new kakao.maps.LatLng(lat, lon), // 중심 좌표
            level: 3, // 지도 확대 레벨
          };

          var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성
            message = '<div style="padding:5px;">여기에 계신가요?!</div>'; // 인포윈도우에 표시될 내용입니다
          map = new kakao.maps.Map(container, options);
          // 마커와 인포윈도우를 표시합니다
          displayMarker(locPosition, message);
        });
      } else {
        // 위치정보를 동의하지 않은경우 임의로 위치 정해서 띄워줌
        const options = {
          center: new kakao.maps.LatLng(33.450701, 126.570667), // 중심 좌표
          level: 3, // 지도 확대 레벨
        };
        map = new kakao.maps.Map(container, options);
      }

      // 지도에 마커와 인포윈도우를 표시하는 함수입니다
      function displayMarker(locPosition, message) {
        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: map,
          position: locPosition,
        });

        var iwContent = message, // 인포윈도우에 표시할 내용
          iwRemoveable = true;

        // 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({
          content: iwContent,
          removable: iwRemoveable,
        });

        // 인포윈도우를 마커위에 표시합니다
        infowindow.open(map, marker);

        // 지도 중심좌표를 접속위치로 변경합니다
        map.setCenter(locPosition);
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
