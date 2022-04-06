<template>
  <div>
    <b-form>
      <b-form-group id="content-group" label="" label-for="content">
        <b-row>
          <b-col cols="12" md="11" style="padding: 0px 12px;">
            <b-form-textarea
              id="content"
              placeholder="댓글 입력"
              rows="2"
              max-rows="10"
              size="sm"
              v-model="comment"
            >
            </b-form-textarea>
          </b-col>
          <b-col md="1" align-self="center" style="padding: 0px 10px;">
            <b-button @click="replySave" variant="info"
              ><b-icon icon="pencil"></b-icon>
            </b-button>
          </b-col>
        </b-row>
      </b-form-group>
    </b-form>
  </div>
</template>
<script>
import { mapState } from "vuex";
import { writeReply } from "@/api/reply";

const memberStore = "memberStore";
export default {
  name: "ReplyWrite",
  data() {
    return {
      comment: "",
    };
  },
  props: {
    boardNo: Number,
  },
  methods: {
    replySave() {
      if (this.comment !== null) {
        writeReply(
          {
            boardComment: this.boardNo,
            commentUser: this.userInfo.username,
            commentContent: this.comment,
          },
          ({ data }) => {
            if (data === "success") {
              this.comment = "";
            }
            location.reload();
          },
          (error) => {
            console.log(this.boardNo + this.userInfo.username + this.comment);
            console.log(error);
          }
        );
      }
    },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>
