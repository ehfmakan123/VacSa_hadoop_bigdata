<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <!-- <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            > -->
            <b-form-group label="아이디:" label-for="username">
              <b-form-input
                id="username"
                v-model="user.username"
                ref="username"
                required
                @keyup="checkIdRepeat"
                placeholder="아이디 입력...."
              ></b-form-input>
            </b-form-group>
            <div
              class="mt-1"
              :class="{
                success: isSuccess,
                fail: isFail,
              }"
              v-html="idresult"
            ></div>
            <b-form-group label="닉네임:" label-for="nickname">
              <b-form-input
                id="nickname"
                v-model="user.nickname"
                ref="nickname"
                required
                placeholder="닉네임 입력...."
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="password">
              <b-form-input
                type="password"
                id="password"
                v-model="user.password"
                ref="password"
                required
                placeholder="비밀번호 입력...."
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이메일:" label-for="email">
              <b-form-input
                id="email"
                v-model="user.email"
                ref="email"
                required
                placeholder="이메일 입력...."
              ></b-form-input>
            </b-form-group>
            <b-button
              type="button"
              variant="primary"
              class="m-1"
              @click="moveMain"
              >회원가입</b-button
            >
            <b-button type="button" variant="danger" class="m-1" @click="cancel"
              >취소</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { registerUser, checkRepeatIdById } from "../../api/member.js";

export default {
  name: "MemberJoin",
  components: {},
  data() {
    return {
      user: {
        username: null,
        password: null,
        email: null,
        nickname: null,
      },
      idresult: "",
      isSuccess: false,
      isFail: false,
      idLenValidate: false,
    };
  },

  methods: {
    checkValue() {
      // 사용자 입력값 체크하기
      // 아이디, 이름, 비밀번호, 이메일 이 없을 경우 각 항목에 맞는 메세지를 출력
      let err = true;
      let msg = "";
      !this.user.username &&
        ((msg = "아이디 입력해주세요"),
        (err = false),
        this.$refs.username.focus());
      err &&
        this.isFail &&
        ((msg = "중복된 아이디 입니다. 변경해주세요."),
        (err = false),
        this.$refs.username.focus());
      err &&
        !this.idLenValidate &&
        ((msg = "아이디 길이가 이상합니다. 변경해주세요"),
        (err = false),
        this.$refs.username.focus());

      err &&
        !this.user.username &&
        ((msg = "이름 입력해주세요"),
        (err = false),
        this.$refs.username.focus());
      err &&
        !this.user.password &&
        ((msg = "비밀번호 입력해주세요"),
        (err = false),
        this.$refs.password.focus());
      err &&
        !this.user.email &&
        ((msg = "이메일 입력해주세요"),
        (err = false),
        this.$refs.email.focus());

      if (!err) alert(msg);
      // 만약, 내용이 다 입력되어 있다면 register호출
      else this.register();
    },
    //취소버튼 클릭시 로그인 화면으로
    cancel() {
      this.$router.push({ name: "MemberLogin" });
    },
    //입력이 다 되어있다면 register호출
    register() {
      registerUser(this.user);
      this.$router.push({ name: "Starter" });
    },

    moveMain() {
      this.$router.push({ name: "Starter" });
    },

    // 아이디 중복검사
    checkIdRepeat() {
      // 키가 눌릴 때마다 아이디 중복검사
      var ckid = this.user.username;
      if (ckid.length < 4 || ckid.length > 16) {
        this.idresult = "아이디는 6자이상 16자이하입니다.";
        this.$refs.username.focus();
        this.isSuccess = false;
        this.isFail = false;
        this.idLenValidate = false;
        return;
      } else {
        this.idLenValidate = true;
        checkRepeatIdById(this.user.username, response => {
          if (response.data == 1) {
            this.idresult = `<b>${ckid}</b>는 이미 사용중입니다. 사용할 수 없습니다.`;
            this.isFail = true;
            this.isSuccess = false;
          } else {
            this.idresult = `<b>${ckid}</b>는 사용할 수 있습니다.`;
            this.isSuccess = true;
            this.isFail = false;
          }
        });
      }
    },
  },
};
</script>

<style>
.success {
  color: dodgerblue;
}
.fail {
  color: darkred;
}
</style>
