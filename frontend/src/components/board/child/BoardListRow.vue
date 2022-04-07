<template>
  <b-tr>
    <b-td>{{ boardId }}</b-td>
    <b-th class="text-left">
      <router-link :to="{ name: 'BoardDetail', params: { boardNo: boardId } }">
        {{ boardTitle }}
      </router-link>
    </b-th>
    <b-td>{{ author }}</b-td>
    <b-td>{{ regDate }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListRow",
  data() {
    return {
      regDate: "",
    };
  },
  props: {
    boardId: Number,
    author: String,
    boardTitle: String,
    boardCreateTime: String,
  },
  created() {
    this.regDate = moment(new Date(this.boardCreateTime)).format(
      "YYYY-MM-DD kk:mm"
    );
  },
  computed: {},
};
</script>

<style></style>
