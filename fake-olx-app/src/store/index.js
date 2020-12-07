import Vuex from "vuex";
import Vue from "vue";

import getAllProdutos from "../usecases/produtos/getAllProdutos";
import adicionaNovoProduto from "../usecases/produtos/adicionaNovoProduto";
import deletaProduto from "../usecases/produtos/deletaProduto";
import editProduto from "../usecases/produtos/editProduto";
import getAllPessoas from "../usecases/pessoas/getAllPessoas";
import deletePessoa from "../usecases/pessoas/deletePessoa";

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    produtos: {},
    userData: {},
    pessoas: {},
  },
  getters: {
    produtos: (state) => state.produtos,
    userData: (state) => state.userData,
    pessoas: (state) => state.pessoas,
  },
  mutations: {
    getProdutos(state, payload) {
      state.produtos = payload;
    },
    addUserData(state, { payload }) {
      state.userData = payload;
    },
    getAllPessoas(state, payload) {
      state.pessoas = payload;
    },
  },
  actions: {
    async getProdutos({ commit }) {
      const data = await getAllProdutos();
      commit("getProdutos", data);
    },
    async addProduto({ commit }, payload) {
      await adicionaNovoProduto(payload);
      const data = await getAllProdutos();
      commit("getProdutos", { data });
    },
    async deleteProduto({ commit }, payload) {
      await deletaProduto(payload);
      const data = await getAllProdutos();
      commit("getProdutos", { data });
    },
    async editProdutoAction({ commit }, payload) {
      await editProduto(payload);
      const data = await getAllProdutos();
      commit("getProdutos", { data });
    },
    async addUserData({ commit }, payload) {
      commit("addUserData", { payload });
    },
    async getAllPessoasAction({ commit }) {
      const data = await getAllPessoas();
      commit("getAllPessoas", data);
    },
    async deletePessoaAction({ commit }, payload) {
      await deletePessoa(payload);
      const data = await getAllPessoas();
      commit("getAllPessoas", data);
    },
    async getPessoasPorFiltroAction({ commit }, payload) {
      commit("getAllPessoas", payload);
    },
    async getProdutoPorFiltroAction({ commit }, payload) {
      commit("getProdutos", payload);
    },
  },
});

export { store };
