import { api } from "../../services/api";
export default async function getAllPessoas() {
  const login = localStorage.getItem("@fakeolx:login");
  const senha = localStorage.getItem("@fakeolx:senha");

  const response = await api.get("/pessoa", {
    headers: {
      login: login,
      senha: senha,
    },
  });
  return response.data;
}
