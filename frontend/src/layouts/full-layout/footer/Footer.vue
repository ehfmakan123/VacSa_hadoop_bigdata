<template>
  <footer class="p-4 text-center" >
  
    <!-- <div>여기에 뭐 더 넣을게 있나요?????? ㅠㅠ</div> -->
    <!-- p 태그 넣으면 공간이 이상하게 띄워지는데 이유를 모르겠어요 ㅠ -->
    <div>SSAFY 특화 프로젝트 - 하두비둡</div>
    <div> 사용 API : Kakao Map api , 보건복지부 코로나 감염 현황 api , 질병관리청 코로나 예방접종 현황 api</div> 
    <div class="footer-link">
    <a href="https://lab.ssafy.com/s06-bigdata-dist-sub2/S06P22A402">
      <b-icon-github></b-icon-github> Gitlab &nbsp;&nbsp;&nbsp;
    </a>
    <a href="https://www.notion.so/Vac-5f10f27991244f918f1d0ce2d40ffc1a">
      <b-icon-book></b-icon-book> Notion&nbsp;
    </a>
    <a href="https://docs.google.com/spreadsheets/d/19vXPyx62kMPhcAxkOsz04n78jGdsw0DXfBVK7zR9udU/edit#gid=0">
      <b-icon-google></b-icon-google> Googledocs 명세서
    </a>
    </div>
    © {{ new Date().getFullYear() }} — Xtreme Admin by wrappixel.com
  </footer>

</template>

<script>
export default {
  name: "Footer",
};
</script>

<style>
</style>