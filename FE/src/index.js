import React from "react";
import ReactDom from "react-dom";
import App from "./components/App";

import rootReducer from "./states";
import { createStore } from "redux";
import { Provider } from "react-redux";

const store = createStore(rootReducer);
ReactDom.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById("app"),
);
