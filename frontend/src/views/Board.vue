<template>
  <b-row>
    <b-col class="d-flex align-items-stretch">
      <BoardList />
    </b-col>
  </b-row>
</template>

<script>
export default {
  name: "Board",

  data: () => ({
    page: {
      title: "Badge",
    },
  }),
  components: {
    BoardList: () => import("@/components/board/BoardList"),
  },
};
</script>
