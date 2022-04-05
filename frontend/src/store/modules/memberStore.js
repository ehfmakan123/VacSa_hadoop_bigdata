import jwt_decode from "jwt-decode";
import { login } from "@/api/member.js";
import { findById } from "../../api/member";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
  },
  getters: {
    checkUserInfo: function(state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = {
        username: userInfo.userId,
        nickname: userInfo.nickname,
        email: userInfo.email,
      };
    },
    SET_LOGOUT: (state, userInfo) => {
      state.userInfo = userInfo;
    },
    SET_USER_INTERESTAREA: (state, interestarea) => {
      state.userInterestArea = interestarea;
    },
    ADD_AREA_INTERESTAREA: (state, area) => {
      state.userInterestArea.push(area);
    },
    DELETE_AREA: (state, area) => {
      const index = state.userInterestArea.indexOf(area);
      state.userInterestArea.splice(index, 1);
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["accessToken"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("accessToken", token);
            alert("로그인 성공");
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {}
      );
    },
    async getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      await findById(
        decode_token.userId,
        (response) => {
          if (response.data.message === "success") {
            console.log(response.data);
            commit("SET_USER_INFO", response.data);
            //console.log(response.data.userInfo);
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default memberStore;
