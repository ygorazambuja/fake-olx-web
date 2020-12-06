export default function doLogout() {
  localStorage.removeItem("@fakeolx:login");
  localStorage.removeItem("@fakeolx:senha");
}
