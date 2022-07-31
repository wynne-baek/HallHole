import { ApiInstance } from "./api";

const api = ApiInstance();

async function fetchNames(success, fail) {
  await api.get("name/random_name").then(success).catch(fail);
}

export { fetchNames };
