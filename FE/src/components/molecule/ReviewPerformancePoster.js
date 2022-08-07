import React from "react";

import { Box } from "@mui/system";

import TextStyle from "../atom/Text";


export default function ReviewPerformancePoster() {
  const boxStyle = {
    height: "30vh",
    backgroundImage: `${props.url}`
  }
  return (
    <Box sx={boxStyle}>
      {/* 글자 색상, 포스터가 밝아지면 흰색은 안보일 거 같음... */}
      <TextStyle >{props.title}</TextStyle>
    </Box>
  )
}