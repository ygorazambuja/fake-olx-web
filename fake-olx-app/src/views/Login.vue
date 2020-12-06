<template>
  <b-container>
    <b-col>
      <h1>Login</h1>
    </b-col>
    <b-col align-v="center" class="p-2">
      <b-form @submit.stop.prevent="onLogin">
        <b-form-input
          class="input"
          type="text"
          required
          placeholder="Login"
          v-model="login"
          :state="loginValidation"
        />
        <b-form-invalid-feedback :state="loginValidation">
          Seu usuario precisa ter mais do que 4 caracteres, e não pode ser vazio
        </b-form-invalid-feedback>
        <b-form-input
          class="input"
          type="password"
          required
          placeholder="Senha"
          v-model="senha"
          :state="passwordValidation"
        />
        <b-form-invalid-feedback :state="loginValidation">
          Sua senha precisa ter mais do que 4 caracteres, e não pode ser vazia
        </b-form-invalid-feedback>
        <b-button type="submit" @submit="onLogin" block>
          {{ loading ? "Carregando" : "Clique para Login" }}
        </b-button>
      </b-form>
    </b-col>
  </b-container>
</template>

<script>
import doLogin from "../usecases/login/doLogin";
import { mapActions } from "vuex";
export default {
  name: "Login",
  components: {},
  data: () => ({
    login: "",
    senha: "",
    loading: false,
  }),
  computed: {
    loginValidation() {
      return this.login !== "" && this.login.length > 4;
    },
    passwordValidation() {
      return this.senha !== "" && this.login.length > 4;
    },
  },

  methods: {
    ...mapActions(["addUserData"]),

    async onLogin(evt) {
      evt.preventDefault();
      this.loading = true;
      const response = await doLogin(this.login, this.senha);
      if (response) {
        this.loading = false;
        this.addUserData(JSON.parse(localStorage.getItem("@fakeolx:userData")));
        this.$router.push("/home");
      } else {
        alert("algo deu errado");
      }
    },
  },
};
</script>

<style></style>
