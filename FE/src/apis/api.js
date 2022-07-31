import axios from "axios";

const ApiInstance = () => {
  return axios.create({
    baseURL: "https://random-data-api.com/api/",
    timeout: 1000,
    headers: {
      "Content-Type": "application/json",
    },
  });
};

export { ApiInstance };
