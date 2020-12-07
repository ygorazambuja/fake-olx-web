import { api } from "../../services/api";
export default async function addNewPessoa(pessoa) {
  const login = localStorage.getItem("@fakeolx:login");
  const senha = localStorage.getItem("@fakeolx:senha");
  const { data } = await api.post("/pessoa", pessoa, {
    headers: {
      login,
      senha,
    },
  });
  return data;
}
