import { apiInstance } from "./index.js";

const api = apiInstance();
const COMMON = "/comment";

// 댓글 작성
function writeReply(reply, success, fail) {
  api
    .post(COMMON + `/create`, JSON.stringify(reply))
    .then(success)
    .catch(fail);
}

// 댓글 목록
function listReply(boardId, success, fail) {
  api
    .get(COMMON + `/${boardId}`)
    .then(success)
    .catch(fail);
}

// 댓글 삭제
function deleteReply(replyId, success, fail) {
  api
    .delete(COMMON + `/${replyId}`)
    .then(success)
    .catch(fail);
}

// 댓글 수정
function updateReply(reply, success, fail) {
  api
    .put(COMMON, JSON.stringify(reply))
    .then(success)
    .catch(fail);
}

export { writeReply, listReply, deleteReply, updateReply };
