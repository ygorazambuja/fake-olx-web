import { api } from "../../services/api";
export default async function editPessoa(pessoa) {
  await api.put(`/pessoa/${pessoa.id}`, { ...pessoa });
}
