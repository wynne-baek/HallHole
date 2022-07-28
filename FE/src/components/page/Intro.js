import React from "react";

import Logo from "../atom/Logo";
import { Box } from "@mui/material";

const contentStyle = {
  width: "100vw",
  height: "100vh",
  backgroundColor: "#f50057",
};

const screenCoverStyle = {
  position: "fixed",
  padding: 0,
  margin: 0,
  top: 0,
  left: 0,

  width: "100%",
  height: "100%",

  backgroundColor: theme => theme.palette.primary.main,
};

export default function Intro() {
  return (
    <Box sx={contentStyle}>
      <Box sx={screenCoverStyle}>
        <Logo size="large" src="https://picsum.photos/216/103" />
      </Box>
    </Box>
  );
}
