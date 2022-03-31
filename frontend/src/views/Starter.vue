<template>
  <div>
    <!-- ----------------------------------------------- -->
    <!-- top cards -->
    <!-- ----------------------------------------------- -->
    <b-row>
      <b-col cols="12" md="6" lg="3">
        <b-card class="mb-4">
          <h4 class="card-title mb-1">87.6%</h4>
          <b-card-text class="mb-2">전국 1차 접종</b-card-text>
          <b-progress value="87" max="100"></b-progress>
        </b-card>
      </b-col>
      <b-col cols="12" md="6" lg="3">
        <b-card class="mb-4">
          <h4 class="card-title mb-1">63.7%</h4>
          <b-card-text class="mb-2">전국 3차 접종</b-card-text>
          <b-progress variant="danger" value="63" max="100"></b-progress>
        </b-card>
      </b-col>
      <b-col cols="12" md="6" lg="3">
        <b-card class="mb-4">
          <h4 class="card-title mb-1">424,641명</h4>
          <b-card-text class="mb-2">일일 확진자 수</b-card-text>
          <b-progress variant="success" value="50" max="100"></b-progress>
        </b-card>
      </b-col>
      <b-col cols="12" md="6" lg="3">
        <b-card class="mb-4">
          <h4 class="card-title mb-1">432명</h4>
          <b-card-text class="mb-2">일일 사망자 수</b-card-text>
          <b-progress variant="warning" value="30" max="100"></b-progress>
        </b-card>
      </b-col>
    </b-row>
    <!-- ----------------------------------------------- -->
    <!-- end top cards -->
    <!-- ----------------------------------------------- -->
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

    <!-- ----------------------------------------------- -->
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>

    <b-row>
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
    </b-row>
    <!-- ----------------------------------------------- -->
    <!-- top selling products -->
    <!-- ----------------------------------------------- -->
    <b-card class="mb-4" no-body>
      <b-card-body>
        <h4 class="card-title">Top Selling Products</h4>
        <h6 class="card-subtitle font-weight-normal text-muted">
          Overview of Latest Month
        </h6>
      </b-card-body>
      <SellingProduct />
    </b-card>
    <!-- ----------------------------------------------- -->
    <!-- end top selling products -->
    <!-- ----------------------------------------------- -->
    <!-- ----------------------------------------------- -->
    <!-- cards row -->
    <!-- ----------------------------------------------- -->
    <TopCards />
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

import SalesSummary from "./dashboard-components/SalesSummary";
import SalesIncome from "./dashboard-components/SalesIncome";
import SellingProduct from "./dashboard-components/SellingProduct";
import TopCards from "./dashboard-components/TopCards";
export default {
  name: "Starter",
  data: () => ({}),
  components: {
    Infection,
    InfoText1,
    InfoText2,
    InfoText3,
    InfoText4,
    SalesSummary,
    SalesIncome,
    SellingProduct,
    TopCards,
  },
};
</script>
