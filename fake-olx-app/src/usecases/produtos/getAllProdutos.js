import { api } from "../../services/api";

export default async function getAllProdutos() {
  const { data } = await api.get("produto", {
    headers: {
      login: localStorage.getItem("@fakeolx:login"),
      senha: localStorage.getItem("@fakeolx:senha"),
    },
  });

  return data;
}
