<template>
  <div>
      <ul v-for="produto in produtos" :key="produto.id">
          <li>{{produto.nome}}</li>
      </ul>
  </div>
</template>

<script>
import { api } from "../services/api";
export default {
  data: () => ({
    produtos: [],
  }),
  methods: {
    onLoad() {
      api
        .get("produtos", {
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

<style></style>
