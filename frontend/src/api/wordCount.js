import { apiInstance } from "./index.js";

const api = apiInstance();
const COMMON = "/wordcloud";

function getWord(success, fail) {
  api
    .get(COMMON)
    .then(success)
    .catch(fail);
}

export { getWord };
