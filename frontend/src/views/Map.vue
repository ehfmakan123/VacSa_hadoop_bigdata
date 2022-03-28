<template>
  <b-row>
    <b-col cols="12" class="d-flex align-items-stretch">
      <AlertContextual />
    </b-col>
    <b-col cols="12" class="d-flex align-items-stretch">
      <AlertLinks />
    </b-col>
    <b-col cols="12" class="d-flex align-items-stretch">
      <AlertDismissible />
    </b-col>
  </b-row>
</template>

<script>
export default {
  name: "Map",

  data: () => ({
    page: {
      title: "Map",
    },
  }),
  components: {
    AlertContextual: () => import("@/components/map/AlertContextual"),
    AlertLinks: () => import("@/components/map/AlertLinks"),
    AlertDismissible: () => import("@/components/map/AlertDismissible"),
  },
};
</script>
