<template>
  <b-container>
    <navbar-vue />
    <h1>Novo produto</h1>
    <b-form @submit.prevent="onSubmit()">
      <b-form-group label="Descrição">
        <b-form-input v-model="produto.descricao" type="text"></b-form-input>
      </b-form-group>

      <b-form-group label="Quantidade Em Estoque">
        <b-form-input
          v-model="produto.quantidadeEstoque"
          type="number"
        ></b-form-input>
      </b-form-group>
      <b-form-group label="Idade Permitida">
        <b-form-input
          v-model="produto.idadePermitida"
          type="number"
        ></b-form-input>
      </b-form-group>
      <b-form-group label="Preco Compra">
        <b-form-input
          v-model="produto.precoCompra"
          type="number"
        ></b-form-input>
      </b-form-group>
      <b-form-group label="Preco Venda Fisica">
        <b-form-input
          v-model="produto.precoVendaFisica"
          type="number"
        ></b-form-input> </b-form-group
      ><b-form-group label="Preco Venda Juridica">
        <b-form-input
          v-model="produto.precoVendaJuridica"
          type="number"
        ></b-form-input>
      </b-form-group>
      <b-button
        block
        variant="success"
        @click="onSubmit()"
        @submit.prevent="onSubmit()"
        >Adicionar</b-button
      >
    </b-form>
  </b-container>
</template>

<script>
import NavbarVue from "../components/Navbar.vue";
import { mapActions } from "vuex";
export default {
  components: {
    "navbar-vue": NavbarVue,
  },
  data: () => ({
    produto: {
      descricao: "",
      quantidadeEstoque: 0,
      idadePermitida: 0,
      precoCompra: 0.0,
      precoVendaFisica: 0.0,
      precoVendaJuridica: 0.0,
    },
  }),
  methods: {
    ...mapActions(["addProduto"]),
    async onSubmit() {
      this.addProduto(this.produto);
      this.$router.history.push("/home");
    },
  },
};
</script>

<style></style>
