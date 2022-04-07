<template>
  <b-container>
    <b-card elevation="10" outlined width="100%" class="mx-auto">
      <div class="p-35">
        <b-card-title>
          <span class="mr-2">글 수정</span>
        </b-card-title>
        <b-card-text>
          <!-- <v-text-field label="Title" v-model="title"> </v-text-field> -->
          <b-form-input id="title" v-model="title"></b-form-input>
          <Editor ref="editor" />
        </b-card-text>
        <b-button
          class="btn float-right"
          variant="primary"
          size="sm"
          @click="updateBoard"
          ><b-icon icon="pencil"></b-icon>
          수정
        </b-button>
        <b-button type="button" variant="secondary" size="sm" @click="moveList">
          <b-icon icon="arrow-left"></b-icon>
          목록
        </b-button>
      </div>
    </b-card>
  </b-container>
</template>

<script>
import Editor from "@/components/board/Editor";
import { getBoardDetailAPI, updateBoardAPI } from "@/api/board";
export default {
  name: "BoardEdit",
  components: {
    Editor,
  },
  data: () => ({
    title: "",
  }),
  mounted() {
    getBoardDetailAPI(
      this.$route.params.boardNo,
      (response) => {
        this.title = response.data.boardTitle;
        this.$refs.editor.setContent(response.data.boardContent);
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveList() {
      this.$router.push({ name: "Board" });
    },
    updateBoard() {
      updateBoardAPI(
        {
          boardId: this.$route.params.boardNo,
          boardTitle: this.title,
          boardContent: this.$refs.editor.getContent(),
        },
        ({ data }) => {
          if (data === "success") {
            alert("수정 완료");
          }
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>
