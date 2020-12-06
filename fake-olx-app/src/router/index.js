import Vue from "vue";
import VueRouter from "vue-router";
import Login from "../views/Login.vue";
import Home from "../views/Home.vue";

import ProductDetailsPage from "../views/ProductDetailsPage.vue";
import NewProductPage from "../views/NewProductPage.vue";
import ProfilePage from "../views/ProfilePage.vue";

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
];

const router = new VueRouter({
  mode: "history",
  routes,
});

export default router;
