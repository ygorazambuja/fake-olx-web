<template>
  <b-navbar type="dark" style="background-color: #6d0ad7" sticky>
    <b-navbar-brand to="/home">
      <img
        src="../assets/olx.png"
        class="d-inline-block align-top"
        alt="Logo"
        width="100"
      />
    </b-navbar-brand>
    <b-navbar-nav class="ml-auto">
      <b-button-group vertical>
        <b-button variant="info" class="mb-2" to="/profile">
          <b-icon icon="person" aria-hidden="true"></b-icon>
          {{ pessoa.pessoa.apelido }}
        </b-button>
        <b-button variant="danger" class="mb-2" @click="doLogout()">
          <b-icon icon="power" aria-hidden="true" />
          Logout
        </b-button>
      </b-button-group>
    </b-navbar-nav>
  </b-navbar>
</template>

<script>
import doLogout from "../usecases/login/doLogout";
import usuarioLogado from "../usecases/login/usuarioLogado";
export default {
  data: () => ({
    isLogin: false,
    pessoa: JSON.parse(localStorage.getItem("@fakeolx:userData")),
  }),
  methods: {
    doLogout() {
      if (usuarioLogado()) {
        doLogout();
        this.redirectToLogin();
      }
    },
    redirectToLogin() {
      this.$router.push("/");
    },
  },
};
</script>

<style></style>
