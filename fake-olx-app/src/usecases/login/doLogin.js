import { api } from "../../services/api";

export default async function doLogin(login = "", senha = "") {
  const response = await api.post(
    "/login",
    {},
    {
      headers: {
        login,
        senha,
      },
    }
  );
  if (response.data !== "") {
    localStorage.setItem("@fakeolx:login", login);
    localStorage.setItem("@fakeolx:senha", senha);
    localStorage.setItem("@fakeolx:userData", JSON.stringify(response.data));
    return true;
  } else {
    return false;
  }
}
