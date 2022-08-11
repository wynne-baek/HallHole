import React from "react";

import { styled } from "@mui/system";

const Logo = styled("img")`
  width: ${props => (props.size === "large" ? "40vw" : "20vw")};
  height: ${props => (props.size === "large" ? "20vw" : "10vw")};
  src: ${props => props.src};
  onclick: ${props => props.onClick};
`;

export default Logo;
