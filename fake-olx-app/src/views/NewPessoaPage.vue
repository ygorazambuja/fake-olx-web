<template>
  <b-container>
    <navbar-vue></navbar-vue>
    <b-form>
      <b-form-select :options="tipo" v-model="pessoa.tipoPessoa">
        <template #first>
          <b-form-select-option :value="null" disabled
            >Tipo de Pessoa</b-form-select-option
          >
        </template></b-form-select
      >

      <b-form-select :options="situacao" v-model="pessoa.situacaoPessoa">
        <template #first>
          <b-form-select-option :value="null" disabled
            >Situacao Pessoa</b-form-select-option
          >
        </template></b-form-select
      >
      <b-input
        v-show="pessoa.tipoPessoa == 'FISICA'"
        placeholder="CPF:"
        v-model="pessoa.cpf"
      ></b-input>
      <b-input
        v-show="pessoa.tipoPessoa == 'JURIDICA'"
        placeholder="CNPJ:"
        v-model="pessoa.cnpj"
      ></b-input>
      <b-input
        v-show="pessoa.tipoPessoa == 'FISICA'"
        placeholder="RG:"
        v-model="pessoa.rg"
      ></b-input>
      <b-input placeholder="Nome: " v-model="pessoa.nome"></b-input>
      <b-input placeholder="Apelido: " v-model="pessoa.apelido"> </b-input>
      <b-input type="date" v-model="dataNascimento"></b-input>
      <b-form-input
        v-show="idade <= 18"
        placeholder="Id Responsavel"
      ></b-form-input>
      <b-form-invalid-feedback :state="idade > 18">
        Você tem menos de 18, precisa de um responsavel
      </b-form-invalid-feedback>
      <b-button block variant="success" @click="addPessoa"
        >Salvar
        <b-icon icon="cloud-upload"></b-icon>
      </b-button>
    </b-form>
  </b-container>
</template>

<script>
import NavbarVue from "../components/Navbar.vue";
import moment from "moment";
import addNewPessoa from "../usecases/pessoas/addNewPessoa";
export default {
  components: {
    "navbar-vue": NavbarVue,
  },
  data: () => ({
    tipo: ["FISICA", "JURIDICA"],
    situacao: ["ATIVO", "INATIVO"],

    pessoa: {
      tipoPessoa: null,
      cpf: null,
      rg: null,
      cnpj: null,
      situacaoPessoa: null,
      nome: null,
      apelido: null,
    },
    dataNascimento: null,
    idade: null,
  }),
  methods: {
    async addPessoa() {
      this.pessoa.dataNascimento = this.dataNascimento;
      await addNewPessoa(this.pessoa);
      this.$router.history.push("/pessoas");
    },
    async verificaIdadePessoa() {
      const anos = moment().diff(this.dataNascimento, "years");
      this.idade = anos;
    },
  },
  watch: {
    dataNascimento: function() {
      this.verificaIdadePessoa();
    },
  },
};
</script>

<style></style>
