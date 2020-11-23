<template>
  <div class="container">
    <h1>Login</h1>
    <input class="input" type="text" placeholder="Login" v-model="login" />
    <input class="input" type="text" placeholder="Senha" v-model="senha" />
    <button class="button" @click="onLogin">
      {{ loading ? "Carregando" : "Clique para Login" }}
    </button>
  </div>
</template>

<script>
import { api } from "../services/api";
export default {
  name: "App",
  components: {},
  data: () => ({
    login: "",
    senha: "",
    loading: false,
  }),
  methods: {
    onLogin() {
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
          data.pessoa ? console.log("ok") : console.log("not ok");
          localStorage.setItem("@fakeolx:login", data.login);
          localStorage.setItem("@fakeolx:senha", data.senha);
          this.loading = false;
        });
    },
  },
};
</script>

<style>
.container {
  display: flex;
  flex-direction: column;
  margin: auto;
  max-width: 500px;
  padding: 10px;
}
.input {
  font-size: 20px;
  padding: 10px;
}
.button {
  font-size: 20px;
  padding: 20px;
}
</style>
