import { api } from "../../services/api";
export default async function deletePessoa(id) {
  const login = localStorage.getItem("@fakeolx:login");
  const senha = localStorage.getItem("@fakeolx:senha");
  await api.delete(`/pessoa/${id}`, {
    headers: {
      login,
      senha,
    },
  });
}
