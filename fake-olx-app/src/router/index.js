import Vue from "vue";
import VueRouter from "vue-router";
import Login from "../views/Login.vue";
import Home from "../views/Home.vue";

import ProductDetailsPage from "../views/ProductDetailsPage.vue";
import NewProductPage from "../views/NewProductPage.vue";
import ProfilePage from "../views/ProfilePage.vue";
import ProductEditPage from "../views/ProductEditPage.vue";
import PessoasPage from "../views/PessoasPage.vue";
import PessoaEditPage from "../views/PessoaEditPage.vue";
import NewPessoaPage from "../views/NewPessoaPage.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/home",
    name: "Home",
    component: Home,
  },
  {
    path: "/produto/:id",
    name: "ProductDetailsPage",
    component: ProductDetailsPage,
  },
  {
    path: "/newProduct",
    name: "newProduct",
    component: NewProductPage,
  },
  {
    path: "/profile",
    name: "profile",
    component: ProfilePage,
  },
  {
    path: "/productEdit/:id",
    name: "ProductEditPage",
    component: ProductEditPage,
  },
  {
    path: "/pessoas",
    name: "PessoasPage",
    component: PessoasPage,
  },
  {
    path: "/pessoaEdit/:id",
    name: "PessoaEditPage",
    component: PessoaEditPage,
  },
  {
    path: "/newPessoa",
    name: "NewPessoa",
    component: NewPessoaPage,
  },
];

const router = new VueRouter({
  mode: "history",
  routes,
});

export default router;
