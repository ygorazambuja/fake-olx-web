<template>
  <div class="container">
    <b-jumbotron style="height: 100%">
      <b-col>
        <h1>Login</h1>
      </b-col>
      <b-col align-v="center">
        <b-form @submit="onLogin">
          <b-form-input
            class="input"
            type="text"
            required
            placeholder="Login"
            v-model="login"
          />
          <b-form-input
            class="input"
            type="text"
            required
            placeholder="Senha"
            v-model="senha"
          />
          <b-button type="submit" @submit="onLogin" block>
            {{ loading ? "Carregando" : "Clique para Login" }}
          </b-button>
        </b-form>
      </b-col>
    </b-jumbotron>
  </div>
</template>

<script>
import { api } from "../services/api";
export default {
  name: "Login",
  components: {},
  data: () => ({
    login: "",
    senha: "",
    loading: false,
  }),
  methods: {
    onLogin(evt) {
      evt.preventDefault();
      this.loading = true;
      api
        .post(
          "/login",
          {},
          {
            headers: {
              login: this.login,
              senha: this.senha,
            },
          }
        )
        .then(({ data }) => {
          if (data.pessoa) {
            localStorage.setItem("@fakeolx:login", data.login);
            localStorage.setItem("@fakeolx:senha", data.senha);
            this.$router.history.push("home");
          }
          data.pessoa ? console.log("ok") : console.log("not ok");
          this.loading = false;
        })
        .catch((err) => console.log(err));
    },
  },
};
</script>

<style>
.container {
  display: flex;
  flex-direction: column;
}
</style>
