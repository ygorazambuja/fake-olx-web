import { api } from "../../services/api";
export default async function getProdutoPorFiltro(
  descricao,
  precoMinimo,
  precoMaximo
) {
  const login = localStorage.getItem("@fakeolx:login");
  const senha = localStorage.getItem("@fakeolx:senha");

  let query = "";

  if (descricao) query = query.concat(`descricao=${descricao}&`);
  if (precoMinimo) query = query.concat(`precoMinimo=${precoMinimo}&`);
  if (precoMaximo) query = query.concat(`precoMaximo=${precoMaximo}`);

  const { data } = await api.get(`/produto/produtoFiltro?${query}`, {
    headers: { login, senha },
  });

  return data;
}
