import Vuex from "vuex";
import Vue from "vue";

import getAllProdutos from "../usecases/produtos/getAllProdutos";
import adicionaNovoProduto from "../usecases/produtos/adicionaNovoProduto";
import deletaProduto from "../usecases/produtos/deletaProduto";
Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    produtos: {},
    userData: {},
  },
  mutations: {
    getProdutos(state, payload) {
      state.produtos = payload.data;
    },
    // getPessoa(state, payload) {},
    addUserData(state, { payload }) {
      console.log(payload);

      state.userData = payload;
    },
  },
  getters: {
    produtos: (state) => state.produtos,
    userData: (state) => state.userData,
  },
  actions: {
    async getProdutos({ commit }) {
      const data = await getAllProdutos();
      commit("getProdutos", { data });
    },
    async addProduto({ commit }, payload) {
      const data = await adicionaNovoProduto(payload);
      commit("getProdutos", { data });
    },
    async deleteProduto({ commit }, payload) {
      await deletaProduto(payload);
      const data = await getAllProdutos();
      commit("getProdutos", { data });
    },
    async addUserData({ commit }, payload) {
      commit("addUserData", { payload });
    },
  },
});

export { store };
