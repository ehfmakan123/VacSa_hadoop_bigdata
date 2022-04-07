<template>
  <b-card class="w-100 mb-4" no-body>
    <div class="p-35">
      <div class="d-flex align-items-start">
        <div>
          <h2 class="card-title mb-1">게시판</h2>
          <b-card-text class="text-muted">
            백신 접종 후 증상 공유
          </b-card-text>
        </div>
      </div>
      <b-row class="mb-1">
        <b-col class="text-right">
          <b-button variant="outline-dark" @click="moveWrite()"
            ><b-icon icon="pencil-square"></b-icon
          ></b-button>
        </b-col>
      </b-row>
      <div>
        <b-table-simple
          hover
          responsive
          style="text-align:center;"
          @row-clicked="rowClick"
        >
          <b-thead head-variant="light">
            <b-tr>
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <board-list-row
              v-for="(board, index) in boards"
              :key="index"
              v-bind="board"
            />
          </tbody>
        </b-table-simple>
      </div>
    </div>
    <hr class="m-0" />
  </b-card>
</template>

<script>
import BoardListRow from "@/components/board/child/BoardListRow";
import { getBoardList } from "@/api/board.js";
export default {
  name: "BoardList",
  components: { BoardListRow },
  data() {
    return {
      boards: [],
    };
  },
  created() {
    getBoardList(
      (response) => {
        this.boards = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    rowClick(index) {
      this.$router.push({
        path: `/board/detail/${index.boardId}`,
      });
    },
    moveWrite() {
      this.$router.push({ name: "BoardWrite" });
    },
  },
};
</script>
