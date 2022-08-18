import React from "react";
import ReactDom from "react-dom";
import App from "./components/App";

<<<<<<< HEAD
ReactDom.render(<App />, document.getElementById("app"));
=======
import Theme from "./configs/theme";
import Store from "./configs/store";

const rootElement = document.getElementById("app");
if (!rootElement) throw new Error("Failed to find the root element");

ReactDOM.createRoot(rootElement).render(
  <React.StrictMode>
    <ThemeProvider theme={Theme}>
      <BrowserRouter>
        <Provider store={Store}>
          <App />
        </Provider>
      </BrowserRouter>
    </ThemeProvider>
  </React.StrictMode>,
);
>>>>>>> develop
