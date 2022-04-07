<template>
  <b-tr>
    <b-td class="col-md-2">{{ commentUser }}</b-td>
    <b-td class="col-md-5" style="text-align:left;" v-if="con">{{
      replyContent
    }}</b-td>
    <b-td>
      <b-form-textarea
        class="replyContent"
        placeholder="내용 입력"
        rows="2"
        size="sm"
        v-model="content"
        v-if="update"
      />
    </b-td>
    <b-td class="col-md-2">{{ regDate }}</b-td>
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
      <b-button
        type="button"
        variant="outline-danger"
        class="m-1"
        size="sm"
        @click="deleteReply(commentId)"
        v-if="userInfo.username == commentUser && !update"
        >삭제
      </b-button>
    </b-td>
  </b-tr>
</template>

<script>
import { mapState } from "vuex";
import { updateReply, deleteReply } from "@/api/reply.js";
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
      regDate: "",
    };
  },
  created() {
    this.regDate = moment(new Date(this.commentCreateTime)).format(
      "YYYY-MM-DD kk:mm"
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
    deleteReply(commentId) {
      deleteReply(commentId, () => {
        location.reload();
      });
    },
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
            location.reload();
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>
