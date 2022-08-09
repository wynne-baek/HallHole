import React, { useEffect, useState } from "react";

import { Link, useParams } from "react-router-dom";
import { Outlet } from "react-router-dom";

import { Box } from "@mui/material";

import { createWebSocket } from "../../helper/websocket";

export default function ChatRoom() {
  let params = useParams();
  const ws = createWebSocket();

  useEffect(() => {
    console.log(ws);
  }, []);

  return (
    <Box>
      <Link to="/nomatch">뒤로 가기</Link>
      This is Chat Room with {params.chatId}
      <Outlet />
    </Box>
  );
}
