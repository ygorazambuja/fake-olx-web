<template>
  <b-container>
    <navbar-vue></navbar-vue>
    <b-list-group-item>
      <b-card :title="pessoa.nome" bg-variant="dark" text-variant="white">
        <b-list-group>
          <b-list-group-item variant="dark" v-show="pessoa.nome"
            >Nome: <b-input v-model="pessoa.nome"> </b-input></b-list-group-item
          ><b-list-group-item variant="dark" v-show="pessoa.apelido"
            >Apelido: <b-input v-model="pessoa.apelido"> </b-input
          ></b-list-group-item>
          <b-list-group-item variant="dark" v-show="pessoa.cpf"
            >CPF: <b-input v-model="pessoa.cpf" disabled> </b-input
          ></b-list-group-item>
          <b-list-group-item variant="dark" v-show="pessoa.cnpj"
            >CNPJ: <b-input v-model="pessoa.cnpj"> </b-input
          ></b-list-group-item>
          <b-list-group-item variant="dark"
            >Data de Nascimento:
            <b-input type="date" v-model="pessoa.dataNascimento"></b-input
          ></b-list-group-item>
          <b-list-group-item variant="dark"
            >Tipo Pessoa:
            <b-form-select
              disabled
              v-model="pessoa.tipoPessoa"
              :options="tipo"
            ></b-form-select>
          </b-list-group-item>
          <b-list-group-item variant="dark"
            >Situacao:
            <b-form-select v-model="pessoa.situacaoPessoa" :options="situacao">
            </b-form-select>
          </b-list-group-item>
          <b-list-group-item variant="dark">
            Idade:
            <b-input v-model="pessoa.idade"></b-input
          ></b-list-group-item>
          <b-list-group-item variant="dark" v-show="pessoa.idResponsavel">
            Id Responsavel: {{ pessoa.idResponsavel }}</b-list-group-item
          >
        </b-list-group>
        <b-card-footer footer-border-variant="danger" footer-bg-variant="dark">
          <b-button variant="success" block @click="editPessoa"
            >Salvar
            <b-icon icon="trash"></b-icon>
          </b-button>
        </b-card-footer>
      </b-card>
    </b-list-group-item>
  </b-container>
</template>

<script>
import NavbarVue from "../components/Navbar.vue";
import getPessoaPorId from "../usecases/pessoas/getPessoaPorId";
import editPessoa from "../usecases/pessoas/editPessoa";
export default {
  components: {
    "navbar-vue": NavbarVue,
  },
  data: () => ({
    pessoa: {},
    tipo: ["FISICA", "JURIDICA"],
    situacao: ["ATIVO", "INATIVO"],
  }),
  methods: {
    async onLoad() {
      const id = this.$router.currentRoute.params.id;
      this.pessoa = await getPessoaPorId(id);
      this.pessoa.dataNascimento = `${this.pessoa.dataNascimento[0]}-${this.pessoa.dataNascimento[1]}-${this.pessoa.dataNascimento[2]}`;
    },
    async editPessoa() {
      await editPessoa(this.pessoa);
      this.$router.history.push("/pessoas");
    },
    async deletePessoa() {},
  },
  mounted() {
    this.onLoad();
  },
};
</script>

<style></style>
