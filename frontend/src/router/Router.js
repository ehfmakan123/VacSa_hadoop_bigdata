import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

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
          name: "User",
          path: "/user",
          component: () => import("@/views/User"),
        },
        {
          name: "Board",
          path: "/board",
          component: () => import("@/views/Board"),
        },
        {
          name: "Login",
          path: "/ui-elements/breadcrumb",
          component: () => import("@/views/ui-elements/Breadcrumb"),
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
