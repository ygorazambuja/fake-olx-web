<template>
  <b-container>
    <navbar></navbar>
    <b-container>
      <b-col inline>
        <router-link to="newProduct">
          <b-button variant="success" size="lg">
            <b-icon icon="plus"></b-icon>
          </b-button>
        </router-link>
      </b-col>
      <b-col>
        <b-button-toolbar> <b-button> asdhiausd</b-button> </b-button-toolbar>
      </b-col>
    </b-container>

    <b-row>
      <product-list :produtos="produtos"> </product-list>
    </b-row>
  </b-container>
</template>

<script>
import NavbarVue from "../components/Navbar.vue";
import ProductListVue from "../components/ProductList.vue";
// import getAllProdutos from "../usecases/produtos/getAllProdutos";
import { mapActions, mapGetters } from "vuex";
export default {
  components: {
    navbar: NavbarVue,
    "product-list": ProductListVue,
  },
  name: "Home",
  data: () => ({}),
  computed: {
    ...mapGetters(["produtos"]),
  },
  mounted() {
    this.getProdutos();
  },
  methods: {
    ...mapActions(["getProdutos"]),
  },
};
</script>

<style scoped></style>
