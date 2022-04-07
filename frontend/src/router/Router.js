import Vue from "vue";
import Router from "vue-router";

import store from "@/store/Store.js";

Vue.use(Router);

const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "SignIn" });
    router.push({ name: "MemberLogin" });
  } else {
    next();
  }
};

const router = new Router({
  mode: "history",
  base: process.env.BASE_URL,

  routes: [
    {
      path: "/",
      redirect: "/starter",
      component: () => import("@/layouts/full-layout/FullLayout"),
      children: [
        {
          name: "Starter",
          path: "starter",
          component: () => import("@/views/Starter"),
        },
        {
          name: "Map",
          path: "/map",
          component: () => import("@/views/Map"),
        },
        {
          name: "Board",
          path: "/board",
          component: () => import("@/views/Board"),
        },
        {
          name: "BoardWrite",
          path: "/board/wirte",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/board/BoardWrite"),
        },
        {
          name: "BoardDetail",
          path: "/board/detail/:boardNo",
          component: () => import("@/components/board/BoardDetail"),
        },
        {
          name: "BoardEdit",
          path: "/board/edit/:boardNo",
          component: () => import("@/components/board/BoardEdit"),
        },
        {
          name: "Member",
          path: "/member",
          // beforeEnter: onlyAuthUser,
          component: () => import("@/views/Member"),
          children: [
            {
              path: "login",
              name: "MemberLogin",
              component: () => import("@/components/user/MemberLogin"),
            },
            {
              path: "join",
              name: "MemberJoin",
              component: () => import("@/components/user/MemberJoin"),
            },
          ],
        },
        {
          name: "Buttons",
          path: "/ui-elements/buttons",
          component: () => import("@/views/ui-elements/Buttons"),
        },
      ],
    },
    {
      path: "*",
      component: () => import("@/views/Starter"),
    },
  ],
});

export default router;
