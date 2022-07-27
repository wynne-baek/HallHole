import React from "react";

import { ThemeProvider, createTheme } from "@mui/material/styles";

const theme = createTheme({
  palette: {
    type: "light",
    primary: {
      main: "#e85d65",
    },
    secondary: {
      main: "#f50057",
    },
  },
});

export default function App() {
  return (
    <ThemeProvider theme={theme}>
      <div>
        <h1>Hello 홀홀!</h1>
        <h2>리액트로 만드는 홀홀</h2>
      </div>
    </ThemeProvider>
  );
}
