<template>
  <b-container>
    <navbar-vue />
    <h1>Editar produto</h1>
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
          step="0.1"
        ></b-form-input>
      </b-form-group>
      <b-form-group label="Preco Venda Fisica">
        <b-form-input
          v-model="produto.precoVendaFisica"
        ></b-form-input> </b-form-group
      ><b-form-group label="Preco Venda Juridica">
        <b-form-input v-model="produto.precoVendaJuridica"></b-form-input>
      </b-form-group>
      <b-button
        block
        variant="success"
        @click="onSubmit()"
        @submit.prevent="onSubmit()"
        >Salvar Edição</b-button
      >
    </b-form>
  </b-container>
</template>

<script>
import NavbarVue from "../components/Navbar.vue";
import { mapActions } from "vuex";
import getProductById from "../usecases/produtos/getProductById";
export default {
  components: {
    "navbar-vue": NavbarVue,
  },
  data: () => ({
    produto: {},
  }),
  mounted() {
    this.onLoad();
  },
  methods: {
    ...mapActions(["addProduto", "editProdutoAction"]),
    async onSubmit() {
      this.editProdutoAction(this.produto);
      this.$router.history.push("/home");
    },
    async onLoad() {
      const { id } = this.$router.currentRoute.params;
      const response = await getProductById(id);
      this.produto = response;
    },
  },
};
</script>

<style></style>
