import React, { useRef, useEffect } from "react";

import { Box } from "@mui/material";
import { styled } from "@mui/system";

import ChatItem from "../molecule/ChatItem";

const Content = styled(Box)`
  width: 90vw;
  height: 50vh;
  margin: auto;
  background-color: green;
  overflow: scroll;
`;

export default function ChatBox({ messages = [] }) {
  const contentRef = useRef(null);

  useEffect(() => {
    contentRef.current.scrollTop = contentRef.current.scrollHeight;
  });

  function getChatList(chats) {
    let id = 0;
    return chats
      .map(chat => {
        return <ChatItem key={id++} name={chat.memberNickName} message={chat.message} time={chat.messageTime} />;
      })
      .reverse();
  }

  return <Content ref={contentRef}>{getChatList(messages)}</Content>;
}
