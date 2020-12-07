<template>
  <div>
    <b-list-group-item>
      <b-card
        :title="produto.descricao"
        :sub-title="`R\$ ${produto.precoCompra.toFixed(2).toString()}`"
      >
        <b-button-group size="sm">
          <b-button
            variant="info"
            :to="{ name: 'ProductDetailsPage', params: { id: produto.id } }"
          >
            Detalhes
          </b-button>
          <b-button
            variant="warning"
            :to="{ name: 'ProductEditPage', params: { id: produto.id } }"
          >
            Editar
            <b-icon icon="pencil-square"></b-icon>
          </b-button>

          <b-button variant="danger" @click="triggerDelete()">
            <b-icon icon="trash"></b-icon>
            Deletar
          </b-button>
        </b-button-group>
        <div class="ml-auto">
          <span>Quantidade Estoque </span>
          <b-badge variant="primary">{{ produto.quantidadeEstoque }}</b-badge>
        </div>
      </b-card>
    </b-list-group-item>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  props: ["produto"],
  methods: {
    ...mapActions(["deleteProduto"]),
    triggerDelete() {
      this.deleteProduto(this.produto.id);
    },
  },
};
</script>

<style></style>
