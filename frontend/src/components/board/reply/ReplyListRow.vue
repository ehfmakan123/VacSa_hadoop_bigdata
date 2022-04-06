<template>
  <b-tr>
    <b-td>{{ commentUser }}</b-td>
    <b-td v-if="con">{{ replyContent }}</b-td>
    <b-td>{{ commentCreateTime }}</b-td>

    <b-td>
      <b-form-textarea
        class="replyContent"
        placeholder="내용 입력"
        rows="2"
        max-rows="10"
        size="sm"
        v-model="content"
        v-if="update"
      />
    </b-td>
    <b-td v-if="userInfo">
      <b-button
        type="button"
        variant="outline-primary"
        class="m-1"
        size="sm"
        @click="updateForm()"
        v-if="userInfo.username == commentUser && !update"
      >
        수정
      </b-button>
      <b-button
        type="button"
        variant="outline-primary"
        class="m-1"
        size="sm"
        @click="updateReply()"
        v-if="update"
        ><b-icon icon="check2"></b-icon>
      </b-button>
    </b-td>
  </b-tr>
</template>

<script>
import { mapState } from "vuex";
import { updateReply } from "@/api/reply.js";
import moment from "moment";
const memberStore = "memberStore";

export default {
  name: "ReplyListRow",
  data() {
    return {
      con: true,
      update: false, // update가 아직 되지 않은 상태
      replyContent: "",
      content: this.commentContent,
    };
  },
  created() {
    this.commentCreateTime = moment(new Date(this.commentCreateTime)).format(
      "YYYY-MM-DD kk:mm:ss"
    );
    this.replyContent = this.commentContent;
  },
  props: {
    boardComment: Number, // 댓글이 작성된 게시글 번호
    commentContent: String,
    commentCreateTime: String,
    commentId: Number,
    commentUser: String, // 댓글 작성자 id
    commentUserNickname: String, // 댓글 작성자 이름
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    updateForm() {
      this.update = !this.update;
      this.con = !this.con;
      this.commentContent = this.replyContent;
    },
    updateReply() {
      updateReply(
        {
          boardComment: this.boardComment,
          commentContent: this.content,
          commentId: this.commentId,
        },
        ({ data }) => {
          if (data === "success") {
            alert("수정 완료");
          }
          location.reload();
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>
