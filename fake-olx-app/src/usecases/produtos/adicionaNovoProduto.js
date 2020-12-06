import { api } from "../../services/api";

export default async function adicionaNovoProduto(produto) {
  const login = localStorage.getItem("@fakeolx:login");
  const senha = localStorage.getItem("@fakeolx:senha");
  return await api.post(
    "/produto",
    { ...produto },
    {
      headers: {
        login,
        senha,
      },
    }
  );
}
