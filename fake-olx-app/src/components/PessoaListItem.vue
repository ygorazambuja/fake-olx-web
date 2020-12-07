<template>
  <b-container>
    <b-list-group-item>
      <b-card :title="pessoa.nome" bg-variant="dark" text-variant="white">
        <b-list-group>
          <b-list-group-item variant="dark" v-show="pessoa.cpf"
            >CPF:{{ pessoa.cpf }}</b-list-group-item
          >
          <b-list-group-item variant="dark" v-show="pessoa.cnpj"
            >CNPJ: {{ pessoa.cnpj }}</b-list-group-item
          >
          <b-list-group-item variant="dark"
            >Data de Nascimento: {{ pessoa.dataNascimento }}</b-list-group-item
          >
          <b-list-group-item variant="dark"
            >Tipo Pessoa: {{ pessoa.tipoPessoa }}
          </b-list-group-item>
          <b-list-group-item variant="dark"
            >Situacao: {{ pessoa.situacaoPessoa }}</b-list-group-item
          >
          <b-list-group-item variant="dark">
            Idade: {{ pessoa.idade }}</b-list-group-item
          >
          <b-list-group-item variant="dark" v-show="pessoa.idResponsavel">
            Id Responsavel: {{ pessoa.idResponsavel }}</b-list-group-item
          >
        </b-list-group>
        <b-card-footer footer-border-variant="danger" footer-bg-variant="dark">
          <b-btn-group>
            <b-button
              variant="warning"
              :to="{ name: 'PessoaEditPage', params: { id: pessoa.id } }"
              >Editar
              <b-icon icon="pencil-square"></b-icon>
            </b-button>
            <b-button variant="danger" @click="onDeleteButton"
              >Excluir
              <b-icon icon="trash"></b-icon>
            </b-button>
          </b-btn-group>
        </b-card-footer>
      </b-card>
    </b-list-group-item>
  </b-container>
</template>

<script>
import { mapActions } from "vuex";
export default {
  props: ["pessoa"],
  methods: {
    ...mapActions(["deletePessoaAction"]),
    onDeleteButton() {
      this.deletePessoaAction(this.pessoa.id);
    },
  },
};
</script>

<style></style>
