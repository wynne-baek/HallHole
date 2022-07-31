import React from "react";

import { ThemeProvider, createTheme } from "@mui/material/styles";

import Intro from "./page/Intro";

const theme = createTheme({
  palette: {
    type: "light",
    primary: {
      main: "#e85d65",
    },
    secondary: {
      main: "#f50057",
    },
    base: {
      black: "#000000",
      blackDim: "#000000B0",
    },
  },
});

export default function App() {
  return (
    <ThemeProvider theme={theme}>
      <Intro></Intro>
    </ThemeProvider>
  );
}
