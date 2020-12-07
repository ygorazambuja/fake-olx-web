<template>
  <b-container>
    <navbar-vue></navbar-vue>
    <b-container>
      <b-button variant="primary" block to="newPessoa">Nova Pessoa</b-button>
      <b-form inline @submit.prevent="">
        <b-input
          placeholder="Nome responsavel"
          v-model="nomeResponsavel"
        ></b-input>
        <b-form-select :options="tipo" v-model="tipoSelect">
          <template #first>
            <b-form-select-option :value="null" disabled
              >Tipo de Pessoa</b-form-select-option
            >
          </template>
        </b-form-select>
        <b-form-select :options="situacao" v-model="situacaoSelect">
          <template #first>
            <b-form-select-option :value="null" disabled
              >Situacao Pessoa</b-form-select-option
            >
          </template>
        </b-form-select>
      </b-form>
    </b-container>
    <pessoa-list :pessoas="pessoas"></pessoa-list>
  </b-container>
</template>

<script>
import NavbarVue from "../components/Navbar.vue";
import { mapActions, mapGetters } from "vuex";
import PessoaListVue from "../components/PessoaList.vue";
import getPessoasPorFiltro from "../usecases/pessoas/getPessoasPorFiltro";
export default {
  components: {
    "navbar-vue": NavbarVue,
    "pessoa-list": PessoaListVue,
  },
  data: () => ({
    tipo: ["FISICA", "JURIDICA"],
    tipoSelect: null,
    situacao: ["ATIVO", "INATIVO"],
    situacaoSelect: null,
    nomeResponsavel: null,
  }),
  computed: {
    ...mapGetters(["pessoas"]),
  },
  methods: {
    ...mapActions(["getAllPessoasAction", "getPessoasPorFiltroAction"]),
    async onLoad() {
      await this.getAllPessoasAction();
    },
    async filtro() {
      const pessoas = await getPessoasPorFiltro(
        this.tipoSelect,
        this.situacaoSelect,
        this.nomeResponsavel
      );
      this.getPessoasPorFiltroAction(pessoas);
    },
  },
  mounted() {
    this.onLoad();
  },
  watch: {
    tipoSelect: function() {
      this.filtro();
    },
    situacaoSelect: function() {
      this.filtro();
    },
    nomeResponsavel: function() {
      this.filtro();
    },
  },
};
</script>

<style></style>
