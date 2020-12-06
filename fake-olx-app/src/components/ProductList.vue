<template>
  <b-container fluid>
    <b-list-group v-for="produto in produtos" :key="produto.id">
      <product-list-item-vue :produto="produto"> </product-list-item-vue>
    </b-list-group>
  </b-container>
</template>

<script>
import ProdutoListItemVue from "./ProdutoListItem.vue";
export default {
  components: {
    "product-list-item-vue": ProdutoListItemVue,
  },
  props: ["produtos"],
};
</script>

<style></style>
