import React from "react";

import { Box } from "@mui/material";
import { styled } from "@mui/system";

const Content = styled(Box)`
  margin-top: 5px;
  max-width: 70vw;
`;

export default function ChatItem({ name, message, time }) {
  return (
    <Content>
      <Box>{name}</Box>
      <Box>{message}</Box>
      <Box>{time}</Box>
    </Content>
  );
}
