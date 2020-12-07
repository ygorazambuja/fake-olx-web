import { api } from "../../services/api";
export default async function getPessoasPorFiltro(
  tipo,
  situacao,
  nomeResponsavel
) {
  const login = localStorage.getItem("@fakeolx:login");
  const senha = localStorage.getItem("@fakeolx:senha");
  let query = "";
  if (situacao) query = query.concat(`situacaoPessoa=${situacao}&`);
  if (tipo) query = query.concat(`tipoPessoa=${tipo}&`);
  if (nomeResponsavel)
    query = query.concat(`nomeResponsavel=${nomeResponsavel}`);

  const response = await api.get(`/pessoa/pessoaFiltro?${query}`, {
    headers: { login, senha },
  });
  return response.data;
}
