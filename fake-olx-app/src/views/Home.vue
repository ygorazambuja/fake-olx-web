<template>
  <div class="container">
    <b-jumbotron>
      <b-col>
        <h1>Home</h1>
        <ul v-for="produto in produtos" :key="produto.id">
          <li>
            Nome : {{ produto.descricao }} / Preço : {{ produto.precoCompra }}
          </li>
        </ul>
      </b-col>
    </b-jumbotron>
  </div>
</template>

<script>
import { api } from "../services/api";
export default {
  name: "Home",
  data: () => ({
    produtos: [],
  }),
  mounted() {
    this.onLoad();
  },
  methods: {
    onLoad() {
      api
        .get("produto", {
          headers: {
            login: localStorage.getItem("@fakeolx:login"),
            senha: localStorage.getItem("@fakeolx:senha"),
          },
        })
        .then(({ data }) => (this.produtos = data));
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
