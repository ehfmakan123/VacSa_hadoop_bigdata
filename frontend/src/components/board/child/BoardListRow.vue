<template>
  <b-tr>
    <b-td>{{ boardId }}</b-td>
    <b-th class="text-left">
      {{ boardTitle }}
    </b-th>
    <b-td>{{ author }}</b-td>
    <b-td>{{ boardCreateTime }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListRow",
  props: {
    boardId: Number,
    author: String,
    boardTitle: String,
    boardCreateTime: String,
  },
  created() {
    this.boardCreateTime = moment(new Date(this.boardCreateTime)).format(
      "YYYY-MM-DD hh:mm:ss"
    );
  },
  computed: {},
};
</script>

<style></style>
