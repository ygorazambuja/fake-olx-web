import { api } from "../../services/api";
export default async function getPessoaPorId(id) {
  const response = await api.get(`/pessoa/${id}`);
  return response.data;
}
