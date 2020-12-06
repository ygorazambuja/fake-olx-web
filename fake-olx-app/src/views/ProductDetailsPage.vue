<template>
  <b-container>
    <navbar-vue />
    {{ product }}
  </b-container>
</template>

<script>
import NavbarVue from "../components/Navbar.vue";
import getProductById from "../usecases/produtos/getProductById";
export default {
  components: {
    "navbar-vue": NavbarVue,
  },
  data: () => ({
    product: {},
  }),
  methods: {
    async onLoad() {
      this.product = await getProductById(this.$router.currentRoute.params.id);
    },
  },
  mounted() {
    this.onLoad();
  },
};
</script>

<style></style>
