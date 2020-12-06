<template>
  <b-container>
    <navbar-vue></navbar-vue>
    <h1>{{ userData }}</h1>
  </b-container>
</template>

<script>
import NavbarVue from "../components/Navbar.vue";
import { mapGetters } from "vuex";
export default {
  components: {
    "navbar-vue": NavbarVue,
  },
  computed: {
    ...mapGetters(["userData"]),
  },
};
</script>

<style></style>
