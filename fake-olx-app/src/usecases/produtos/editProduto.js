import { api } from "../../services/api";
export default async function editProduto(produto) {
  await api.put(`/produto/${produto.id}`, {
    ...produto,
  });
}
