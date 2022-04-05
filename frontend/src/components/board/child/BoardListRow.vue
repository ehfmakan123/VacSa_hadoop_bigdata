<template>
  <b-tr>
    <b-td>{{ boardId }}</b-td>
    <b-th class="text-left">
      <router-link :to="{ name: 'BoardDetail', params: { boardNo: boardId } }">
        {{ boardTitle }}
      </router-link>
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
      "YYYY-MM-DD kk:mm:ss"
    );
  },
  computed: {},
};
</script>

<style></style>
