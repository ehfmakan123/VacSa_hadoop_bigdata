import { apiInstance } from "./index.js";

const api = apiInstance();
const COMMON = "/board";

function getBoardList(success, fail) {
  api
    .get(COMMON)
    .then(success)
    .catch(fail);
}

// 게시글 작성
function writeBoardAPI(board, success, fail) {
  api
    .post(COMMON + `/create`, JSON.stringify(board))
    .then(success)
    .catch(fail);
}

export { getBoardList, writeBoardAPI };
