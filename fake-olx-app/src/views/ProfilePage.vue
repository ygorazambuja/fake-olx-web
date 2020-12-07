<template>
  <b-container>
    <navbar-vue></navbar-vue>
    <b-list-group>
      <b-list-group-item> ID: {{ userData.id }} </b-list-group-item>
      <b-list-group-item>
        Administrador: {{ userData.adminstrador }}
      </b-list-group-item>
      <b-list-group-item> Login: {{ userData.login }} </b-list-group-item>
      <b-list-group-item v-show="userData.pessoa.idResponsavel">
        : {{ userData.pessoa.idResponsavel }}
      </b-list-group-item>
      <b-list-group-item>
        Tipo Pessoa: {{ userData.pessoa.tipoPessoa }}
      </b-list-group-item>
      <b-list-group-item>
        Situação: {{ userData.pessoa.situacaoPessoa }}
      </b-list-group-item>
      <b-list-group-item> Nome: {{ userData.pessoa.nome }} </b-list-group-item>
      <b-list-group-item v-show="userData.pessoa.cpf"
        >Cpf: {{ userData.pessoa.cpf }}</b-list-group-item
      >
      <b-list-group-item v-show="userData.pessoa.rg">
        RG: {{ userData.pessoa.rg }}
      </b-list-group-item>
      <b-list-group-item>
        Idade: {{ userData.pessoa.idade }}
      </b-list-group-item>
    </b-list-group>
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
