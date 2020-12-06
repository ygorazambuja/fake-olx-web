import { api } from "../../services/api";

export default async function getProductById(id = 0) {
  if (!id) return;
  const { data } = await api.get(`produto/${id}`);
  return data;
}
