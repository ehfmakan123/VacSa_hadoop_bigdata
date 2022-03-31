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
import memberStore from "./store/modules/memberStore";

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);

Vue.config.productionTip = false;

new Vue({
  store,
  router,
  async beforeCreate() {
    let token = sessionStorage.getItem("access-token");
    if (memberStore.state.userInfo == null && token) {
      await memberStore.dispatch("getUserInfo", token);
    }
  },
  render: h => h(App),
}).$mount("#app");
