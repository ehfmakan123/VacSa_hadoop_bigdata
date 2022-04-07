<template>
  <b-card elevation="10" outlined width="100%" class="mx-auto">
    <b-card-title>
      <!-- <span class="mr-2">Detail</span> -->
    </b-card-title>
    <b-row class="mb-1">
      <b-col>
        <b-card class="mb-2" no-body>
          <template #header>
            <b-row>
              <b-col>
                <h4 class="mb-2">{{ title }}</h4>
              </b-col>
            </b-row>
            <b-row>
              <b-col
                ><h6>작성자 : {{ writer }}</h6></b-col
              >
              <b-col class="text-sm-right"
                ><h6>{{ regDate }}</h6></b-col
              >
            </b-row>
          </template>
          <b-card-body class="text-left">
            <div style="min-height:300px;"><Viewer ref="viewer" /><br /></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <reply-list />
    <reply-write :boardNo="board.boardId" />
    <b-card-text>
      <b-button-group
        class="btn float-right"
        size="sm"
        v-if="userInfo && userInfo.username == board.author"
      >
        <b-button type="button" variant="primary" @click="moveEditBoard">
          <b-icon icon="pencil"></b-icon>
          수정
        </b-button>
        <b-button type="button" variant="primary" @click="delBoard">
          <b-icon icon="trash-fill"></b-icon>
          삭제
        </b-button>
      </b-button-group>
      <b-button type="button" variant="secondary" size="sm" @click="moveList">
        <b-icon icon="arrow-left"></b-icon>
        목록
      </b-button>
    </b-card-text>
  </b-card>
</template>
<script>
import { mapState } from "vuex";
import Viewer from "@/components/board/Viewer";
import { getBoardDetailAPI, deleteBoardAPI } from "@/api/board";
import ReplyWrite from "@/components/board/reply/ReplyWrite";
import ReplyList from "@/components/board/reply/ReplyList";
import moment from "moment";

const memberStore = "memberStore";
export default {
  name: "BoardDetail",
  components: {
    Viewer,
    ReplyWrite,
    ReplyList,
  },
  data() {
    return {
      board: {},
      boardId: 0,
      title: "",
      writer: "",
      regDate: "",
    };
  },
  created() {
    getBoardDetailAPI(
      this.$route.params.boardNo,
      (response) => {
        this.board = response.data;
        this.boardId = response.data.boardId;
        this.title = response.data.boardTitle;
        this.writer = response.data.author;
        this.regDate = moment(new Date(response.data.boardCreateTime)).format(
          "YYYY-MM-DD kk:mm"
        );
        this.$refs.viewer.setContent(response.data.boardContent);
      },
      (error) => {
        console.log(error);
      }
    );
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.board.boardContent)
        return this.board.boardContent.split("\n").join("<br>");
      return "";
    },
  },
  methods: {
    moveEditBoard() {
      this.$router.replace({
        name: "BoardEdit",
        params: { boardNo: this.board.boardId },
      });
    },
    delBoard() {
      deleteBoardAPI(this.board.boardId, () => {
        this.$router.push({ name: "Board" });
      });
    },
    moveList() {
      this.$router.push({ name: "Board" });
    },
  },
};
</script>
