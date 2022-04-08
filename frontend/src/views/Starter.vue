<template>
  <div>
    <!-- ----------------------------------------------- -->
    <!-- top cards -->
    <!-- ----------------------------------------------- -->
    <b-row>
      <b-col cols="12" md="6" lg="3">
        <b-card class="mb-4">
          <h4 class="card-title mb-1">{{ data1.toFixed(1) }}%</h4>
          <b-card-text class="mb-2">전국 1차 접종</b-card-text>
          <b-progress variant="success" v-model="data1" max="100"></b-progress>
        </b-card>
      </b-col>
      <b-col cols="12" md="6" lg="3">
        <b-card class="mb-4">
          <h4 class="card-title mb-1">{{ data2.toFixed(1) }}%</h4>
          <b-card-text class="mb-2">전국 3차 접종</b-card-text>
          <b-progress variant="primary" v-model="data2" max="100"></b-progress>
        </b-card>
      </b-col>
      <b-col cols="12" md="6" lg="3">
        <b-card class="mb-4">
          <h4 class="card-title mb-1 " style="white-space:pre-line">
            {{ (field / 10000).toFixed(0) }}만명 확진
          </h4>
          <b-card-text class="mb-2">누적 확진자 수 {{ decnt }}%</b-card-text>
          <b-progress variant="warning" v-model="decnt" max="100"></b-progress>
        </b-card>
      </b-col>
      <b-col cols="12" md="6" lg="3">
        <b-card class="mb-4">
          <h4 class="card-title mb-1">{{ field2 }}명 사망</h4>
          <b-card-text class="mb-2">누적 사망자 수</b-card-text>
          <b-progress variant="danger" value="30" max="100"></b-progress>
        </b-card>
      </b-col>
    </b-row>
    <!-- ----------------------------------------------- -->
    <!-- end top cards -->
    <!-- ----------------------------------------------- -->
    <b-row>
      <b-col>
        <b-card>
          <word-cloud />
        </b-card>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="12" lg="6">
        <b-card class="mb-4">
          <h4 class="card-title">바이러스 감염 증상</h4>
          <Infection />
        </b-card>
      </b-col>
      <b-col cols="12" lg="6">
        <b-card class="mb-4">
          <h4 class="card-title">백신 이상반응 증상</h4>
          <SalesIncome />
        </b-card>
      </b-col>
    </b-row>

    <!-- ----------------------------------------------- -->

    <b-row>
      <b-col cols="12" lg="12">
        <b-card class="mb-4">
          <InfoText1 />
        </b-card>
      </b-col>
      <b-col cols="12" lg="12">
        <b-card class="mb-4">
          <InfoText2 />
        </b-card>
      </b-col>
      <b-col cols="12" lg="12">
        <b-card class="mb-4">
          <InfoText3 />
        </b-card>
      </b-col>
      <b-col cols="12" lg="12">
        <b-card class="mb-4">
          <InfoText4 />
        </b-card>
      </b-col>
    </b-row>

    <!-- <div>{{decnt}}</div> -->
    <!-- ----------------------------------------------- -->

    <br />
    <br />
    <br />

    <!-- <b-row>
      <b-col cols="12" lg="8">
        <b-card class="mb-4">
          <h4 class="card-title">Sales Summary</h4>
          <SalesSummary />
        </b-card>
      </b-col>
      <b-col cols="12" lg="4">
        <b-card class="mb-4">
          <h4 class="card-title">Sales Income</h4>
          <SalesIncome />
        </b-card>
      </b-col>
    </b-row> -->
    <!-- ----------------------------------------------- -->
    <!-- top selling products -->
    <!-- ----------------------------------------------- -->
    <!-- <b-card class="mb-4" no-body>
      <b-card-body>
        <h4 class="card-title">Top Selling Products</h4>
        <h6 class="card-subtitle font-weight-normal text-muted">
          Overview of Latest Month
        </h6>
      </b-card-body>
      <SellingProduct />
    </b-card>
    <div>
      
    </div> -->
    <!-- ----------------------------------------------- -->
    <!-- end top selling products -->
    <!-- ----------------------------------------------- -->
    <!-- ----------------------------------------------- -->
    <!-- cards row -->
    <!-- ----------------------------------------------- -->
    <!-- <TopCards /> -->
    <!-- ----------------------------------------------- -->
    <!-- end cards row -->
    <!-- ----------------------------------------------- -->
  </div>
</template>

<script>
import Infection from "./dashboard-components/Infection";
import InfoText1 from "./dashboard-components/InfoText1";
import InfoText2 from "./dashboard-components/InfoText2";
import InfoText3 from "./dashboard-components/InfoText3";
import InfoText4 from "./dashboard-components/InfoText4";
import WordCloud from "./dashboard-components/WordCloud";
// import SalesSummary from "./dashboard-components/SalesSummary";
import SalesIncome from "./dashboard-components/SalesIncome";
// import SellingProduct from "./dashboard-components/SellingProduct";
// import TopCards from "./dashboard-components/TopCards";
import axios from "axios";
// import AxiosPlugin from "vue-axios-cors";
// axios.defaults.withCredentials = true;
// Vue.use(AxiosPlugin);
// axios.defaults.headers.common["Content-Type"] =
// "application/x-www-form-urlencoded";

export default {
  name: "Starter",
  data() {
    return {
      data1: [],
      data2: [],
      field: [],
    };
  },
  components: {
    Infection,
    InfoText1,
    InfoText2,
    InfoText3,
    InfoText4,
    // SalesSummary,
    SalesIncome,
    // SellingProduct,
    // TopCards,
    WordCloud,
  },
  created() {
    var vm = this;
    axios
      .get("https://nip.kdca.go.kr/irgd/cov19stats.do?list=all")
      .then(function(response) {
        //console.log(response);
        vm.data1 = response.data.substring(458, 466);
        vm.data1 = Number(vm.data1);
        vm.data1 = (vm.data1 / 51638809) * 100;
        vm.data2 = response.data.substring(520, 528);
        vm.data2 = Number(vm.data2);
        vm.data2 = (vm.data2 / 51638809) * 100;
      })
      .catch(function(error) {
        console.log(error);
      });

    axios
      .get(
        "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?serviceKey=Mo7exllHksroP%2BskN4M7XGw%2FLNhgZ3HNCV%2BZkPLkAJvPpGb21ckO%2F6KynGVAf4dMAbe%2F6a7COLv2g252FeE%2FFw%3D%3D&pageNo=1&numOfRows=1&startCreateDt=20220310"
      )
      .then(function(response) {
        vm.field = response.data.response.body.items.item.decideCnt;
        vm.field2 = response.data.response.body.items.item.deathCnt;
        vm.decnt = (vm.field / 600000).toFixed(1); // 누적 확진자 비율 (확진자 / 전국민)
      })
      .catch(function(error) {
        console.log(error);
      });
  },
};
</script>
