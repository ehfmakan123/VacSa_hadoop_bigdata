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

// 게시글 상세보기
function getBoardDetailAPI(boardId, success, fail) {
  api
    .get(COMMON + `/${boardId}`)
    .then(success)
    .catch(fail);
}

// 게시글 삭제
function deleteBoardAPI(boardId, success, fail) {
  api
    .delete(COMMON + `/${boardId}`)
    .then(success)
    .catch(fail);
}

// 게시글 수정
function updateBoardAPI(board, success, fail) {
  api
    .put(COMMON + `/update`, JSON.stringify(board))
    .then(success)
    .catch(fail);
}

export {
  getBoardList,
  writeBoardAPI,
  getBoardDetailAPI,
  deleteBoardAPI,
  updateBoardAPI,
};
