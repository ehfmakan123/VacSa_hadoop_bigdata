import "@babel/polyfill";
import "mutationobserver-shim";
import Vue from "vue";
import "./plugins/bootstrap-vue";
import store from "./store/Store";
import router from "./router/Router";
import "./assets/scss/style.scss";
import App from "./App.vue";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
// import memberStore from "./store/modules/memberStore";

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  async beforeCreate() {
    // 새로고침시 로그인 풀리는거 방지
    let token = sessionStorage.getItem("accessToken");
    if (token !== null) {
      await this.$store.dispatch("memberStore/getUserInfo", token);
    }
  },
  render: h => h(App),
}).$mount("#app");
