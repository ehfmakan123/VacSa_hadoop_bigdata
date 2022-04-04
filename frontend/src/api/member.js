import { apiInstance } from "./index.js";

const api = apiInstance();
const COMMON = "/user";
async function login(user, success, fail) {
  await api
    .post(COMMON + `/login`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

function checkRepeatIdById(userid, success, fail) {
  api
    .get(COMMON + `/idcheck`, {
      params: {
        userId: userid,
      },
    })
    .then(success)
    .catch(fail);
}

async function registerUser(user, success, fail) {
  await api
    .post(COMMON + `/signup`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

// async function findById(userid, success, fail) {
//   api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
//   await api.get(`/user/info/${userid}`).then(success).catch(fail);
// }
// async function registerUser(user, success, fail) {
//   await api
//     .post(`/user/register`, JSON.stringify(user))
//     .then(success)
//     .catch(fail);
// }
// async function modifyUserById(user, success, fail) {
//   await api.put(`/user/update`, JSON.stringify(user)).then(success).catch(fail);
// }
// async function deleteUserById(userid, success, fail) {
//   await api.delete(`/user/delete/${userid}`).then(success).catch(fail);
// }
// async function checkRepeatIdById(userid, success, fail) {
//   await api.get(`/user/idcheck/${userid}`).then(success).catch(fail);
// }
// async function updateInterestArea(param, success, fail) {
//   await api
//     .put(`/user/updateArea`, null, { params: param })
//     .then(success)
//     .catch(fail);
// }
// async function convertCodeToString(code, success, fail) {
//   await api
//     .get(`/user/convert`, { params: { code: code } })
//     .then(success)
//     .catch(fail);
// }
// async function getInterestAreaById(userid, success, fail) {
//   console.log("api진입" + userid);
//   await api.get(`/user/getArea/${userid}`).then(success).catch(fail);
// }
// async function deleteAreaById(userid, success, fail) {
//   console.log("api진입" + userid);
//   await api
//     .delete(`/user/delArea/`, { params: { userid: userid } })
//     .then(success)
//     .catch(fail);
// }

// async function insertInterestAreaById(param, success, fail) {
//   console.log(param);
//   await api
//     .post(`/user/insertArea`, null, { params: param })
//     .then(success)
//     .catch(fail);
// }
// // function logout(success, fail)

export { login, registerUser, checkRepeatIdById };
