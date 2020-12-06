import { api } from "../../services/api";
export default async function deletaProduto(id) {
  await api.delete(`produto/${id}`);
}
