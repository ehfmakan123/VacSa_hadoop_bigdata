<template>
  <b-container>
    <b-card elevation="10" outlined width="100%" class="mx-auto">
      <div class="p-35">
        <b-card-title>
          <span class="mr-2">글 작성</span>
        </b-card-title>
        <b-card-text>
          <div paddgin>
            <b-form-input v-model="title" placeholder="Title"> </b-form-input>
          </div>
          <Editor ref="editor" />
        </b-card-text>

        <div align="right">
          <b-button type="button" class="btn btn-success" @click="save">
            <b-icon icon="pencil-square"></b-icon>
            Save
          </b-button>
          <b-button type="button" class="btn btn-secondary" @click="moveList">
            <b-icon icon="arrow-left"></b-icon>
            Back
          </b-button>
        </div>
      </div>
    </b-card>
  </b-container>
</template>

<script>
import Editor from "@/components/board/Editor";
import { writeBoardAPI } from "@/api/board";
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  name: "BoardWrite",
  components: {
    Editor,
  },
  data: () => ({
    rules: [(value) => !!value || "Required."],
    title: "",
  }),
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    moveList() {
      this.$router.push({ name: "Board" });
    },
    save() {
      if (!this.title) {
        alert("제목을 입력해주세요");
        this.$refs.title.focus();
        return;
      }
      var content = this.$refs.editor.getContent();
      var title = this.title;
      var username = this.userInfo.username;
      writeBoardAPI(
        // 서버로 전송하는 request 값
        {
          boardTitle: title,
          boardContent: content,
          author: username,
        },
        ({ data }) => {
          // 서버로부터 요청받은 값.
          // 서버 요청받은 후의 로직
          console.log(data);
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

<style></style>
