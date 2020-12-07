<template>
  <b-container>
    <b-list-group v-for="pessoa in pessoas" :key="pessoa.id">
      <pessoa-list-item :pessoa="pessoa"></pessoa-list-item>
    </b-list-group>
  </b-container>
</template>

<script>
import PessoaListItemVue from "./PessoaListItem.vue";
export default {
  props: ["pessoas"],
  components: {
    "pessoa-list-item": PessoaListItemVue,
  },
};
</script>

<style></style>
