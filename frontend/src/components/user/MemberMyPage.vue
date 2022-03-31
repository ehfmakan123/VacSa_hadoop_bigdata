<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>내정보</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-jumbotron>
          <template #header>My Page</template>

          <template #lead> 내 정보 확인페이지입니다. </template>

          <hr class="my-4" />

          <b-container class="mt-4" v-if="!modifying">
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">아이디</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.userid }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이름</b-col
              ><b-col cols="4" align-self="start">{{
                userInfo.username
              }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이메일</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.email }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">가입일</b-col
              ><b-col cols="4" align-self="start">{{
                userInfo.joindate
              }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <!-- <b-row>
              <b-col cols="2"></b-col>
              <b-col style="text-align: center">관심지역</b-col></b-row
            > -->
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end"> 관심지역</b-col>
              <b-col
                cols="8"
                align-self="start"
                v-if="userInterestArea && userInterestArea.length != 0"
              >
                <member-my-page-interest-row
                  v-for="(area, index) in userInterestArea"
                  :key="index"
                  :area="area"
                  isin="mypage"
                />
              </b-col>
              <b-col cols="4" align-self="start" v-else> 없음 </b-col>
            </b-row>
          </b-container>
          <b-container class="mt-4" v-if="modifying">
            <b-form class="text-left">
              <b-form-group label="아이디:" label-for="userid">
                <b-form-input
                  id="userid"
                  ref="userid"
                  v-model="user.userid"
                  required
                  readonly
                ></b-form-input>
              </b-form-group>
              <b-form-group label="이름:" label-for="username">
                <b-form-input
                  id="username"
                  ref="username"
                  v-model="user.username"
                  required
                ></b-form-input>
              </b-form-group>
              <b-form-group label="이메일:" label-for="email">
                <b-form-input
                  id="email"
                  ref="email"
                  v-model="user.email"
                  required
                ></b-form-input>
              </b-form-group>
              <b-form-group label="관심지역코드:" label-for="interestarea">
                <b-row>
                  <b-col class="sm-3">
                    <b-form-select
                      v-model="sidoCode"
                      :options="sidos"
                      @change="gugunList"
                    ></b-form-select>
                  </b-col>
                  <b-col class="sm-3">
                    <b-form-select
                      v-model="gugunCode"
                      :options="guguns"
                      @change="dongList"
                    ></b-form-select>
                  </b-col>
                  <b-col class="sm-3">
                    <b-form-select
                      v-model="dongCode"
                      :options="dongs"
                    ></b-form-select>
                  </b-col>
                  <b-col>
                    <button @click.prevent="addInterArea">추가</button>
                  </b-col>
                </b-row>
                <b-container
                  v-if="userInterestArea && userInterestArea.length != 0"
                >
                  <member-my-page-interest-row
                    v-for="(area, index) in userInterestArea"
                    :key="index"
                    :area="area"
                    isin="mod"
                  />
                </b-container>
              </b-form-group>
            </b-form>
          </b-container>
          <hr class="my-4" />

          <b-button
            variant="primary"
            href="#"
            class="mr-1"
            v-if="!modifying"
            @click="modifyInfo"
            >정보수정</b-button
          >
          <b-button
            variant="primary"
            href="#"
            class="mr-1"
            v-if="modifying"
            @click="confirmModify"
            >수정</b-button
          >
          <b-button
            variant="danger"
            href="#"
            v-if="!modifying"
            @click="removeInfo"
            >회원탈퇴</b-button
          >
          <b-button
            variant="danger"
            href="#"
            v-if="modifying"
            @click="cancelModify"
            >취소</b-button
          >
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import MemberMyPageInterestRow from "@/components/user/MemberMyPageInterestRow.vue";
import { mapState, mapActions, mapMutations } from "vuex";
const memberStore = "memberStore";
const houseStore = "houseStore";
import {
  modifyUserById,
  deleteUserById,
  deleteAreaById,
  insertInterestAreaById,
} from "../../api/member.js";

export default {
  name: "MemberMyPage",
  components: { MemberMyPageInterestRow },
  data() {
    return {
      modifying: false,
      user: {
        userid: null,
        username: null,
        userpwd: null,
        email: null,
      },

      sidoCode: null,
      gugunCode: null,
      dongCode: null,
    };
  },
  created() {
    this.user.userid = this.userInfo.userid;
    this.user.username = this.userInfo.username;
    this.user.email = this.userInfo.email;
    this.tempareas = this.userInterestArea;
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  computed: {
    ...mapState(memberStore, ["userInfo", "userInterestArea"]),

    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
  },
  //updated() {},
  methods: {
    ...mapActions(memberStore, ["getUserInfo", "getInterestArea"]),
    ...mapMutations(memberStore, [
      "SET_IS_LOGIN",
      "SET_USER_INFO",
      "SET_USER_INTERESTAREA",
      "ADD_AREA_INTERESTAREA",
    ]),
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
      "getDBHouseList",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
    ]),

    modifyInfo() {
      console.log(this.userInfo);

      console.log(this.user);
      this.user.username = this.userInfo.username;
      this.user.email = this.userInfo.email;

      this.modifying = true;
    },
    cancelModify() {
      this.getInterestArea(this.userInfo.userid);
      this.modifying = false;
    },
    async confirmModify() {
      //수정한 내용대로 db수정하고
      await modifyUserById(this.user);
      let token = sessionStorage.getItem("access-token");
      await this.getUserInfo(token);
      await this.deleteInterestArea();
      await this.insertInterestArea();
      await this.getInterestArea(this.user.userid);
      //그리고 다시 mypage로감
      alert("수정완료!");
      this.modifying = false;
    },

    removeInfo() {
      if (confirm("정말 탈퇴하시겠습니까?")) {
        //삭제하고 로그아웃한 후 홈으로
        deleteUserById(this.user.userid);
        this.SET_IS_LOGIN(false);
        this.SET_USER_INFO(null);
        this.SET_USER_INTERESTAREA([]);
        sessionStorage.removeItem("access-token");
        alert("탈퇴완료!");
        this.$router.push({ name: "Home" });
      }
    },

    gugunList() {
      // console.log(this.sidoCode);
      console.log("시도코드", this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      console.log("구군코드", this.gugunCode);
      // this.$store.commi("CLEAR_GUGUN_LIST");
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    addInterArea() {
      // console.log();
      if (this.dongCode && !this.userInterestArea.includes(this.dongCode)) {
        this.ADD_AREA_INTERESTAREA(this.dongCode);
      } else {
        alert("동까지선택");
      }
    },
    async insertInterestArea() {
      for (const area of this.userInterestArea) {
        let param = { userid: this.userInfo.userid, area: area };
        console.log(param);
        await insertInterestAreaById(param);
      }
    },

    async deleteInterestArea() {
      await deleteAreaById(this.user.userid);
    },
    // registInterestArea() {
    //   //this.user.interestarea = this.dongCode;
    //   console.log(this.dongCode);
    // },

    // //동코드를 시구군동으로 나눔
    // setInterCode() {
    //   if (this.userInfo.interestarea) {
    //     const interCode = this.userInfo.interestarea;
    //     this.sidoCode = interCode.substring(0, 2);
    //     this.gugunCode = interCode.substring(0, 5);
    //     this.dongCode = interCode;
    //     this.getGugun(this.sidoCode);
    //     this.getDong(this.gugunCode);
    //   }
    // },
  },
};
</script>

<style></style>
