<template>
  <b-container>
    <navbar></navbar>
    <b-container>
      <b-btn-group>
        <router-link to="newProduct">
          <b-button variant="success" size="lg">
            Novo Produto
            <b-icon icon="plus"></b-icon>
          </b-button>
        </router-link>
        <b-button to="pessoas" variant="primary" size="lg">
          Pessoas
          <b-icon icon="people"></b-icon>
        </b-button>
        <b-input placeholder="Descrição" v-model="descricao"> </b-input>
        <b-input placeholder="Preço Minimo" v-model="precoMinimo"> </b-input>
        <b-input placeholder="Preço Maximo" v-model="precoMaximo"> </b-input>
      </b-btn-group>
    </b-container>

    <b-row>
      <product-list :produtos="produtos"> </product-list>
    </b-row>
  </b-container>
</template>

<script>
import NavbarVue from "../components/Navbar.vue";
import ProductListVue from "../components/ProductList.vue";
import getProdutoPorFiltro from "../usecases/produtos/getProdutoPorFiltro";
import { mapActions, mapGetters } from "vuex";
export default {
  components: {
    navbar: NavbarVue,
    "product-list": ProductListVue,
  },
  name: "Home",
  data: () => ({
    descricao: "",
    precoMinimo: null,
    precoMaximo: null,
  }),
  computed: {
    ...mapGetters(["produtos"]),
  },
  mounted() {
    this.onLoad();
  },
  methods: {
    async onLoad() {
      await this.getProdutos();
    },
    ...mapActions(["getProdutos", "getProdutoPorFiltroAction"]),
    async filtro() {
      const produtos = await getProdutoPorFiltro(
        this.descricao,
        this.precoMinimo,
        this.precoMaximo
      );
      this.getProdutoPorFiltroAction(produtos);
    },
  },
  watch: {
    descricao: function() {
      this.filtro();
    },
    precoMaximo: function() {
      this.filtro();
    },
    precoMinimo: function() {
      this.filtro();
    },
  },
};
</script>

<style scoped></style>
