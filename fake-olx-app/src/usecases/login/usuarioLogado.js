export default function usuarioLogado() {
  const login = localStorage.getItem("@fakeolx:login");
  const senha = localStorage.getItem("@fakeolx:senha");

  return login && senha;
}
