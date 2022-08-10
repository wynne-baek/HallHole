import React, { useRef, useEffect, useState } from "react";

import { Box } from "@mui/material";
import { styled } from "@mui/system";

import ChatItem from "../molecule/ChatItem";
import Input from "../atom/Input";
import Button from "../atom/Button";
import Text from "../atom/Text";

import { CHAT_TYPE } from "../../helper/constants";

const Content = styled(Box)`
  width: 90vw;
  margin: auto;
`;

const ChatHistoryArea = styled(Box)`
  height: 40vh;
  border: 1px solid #ccc;
  border-radius: 5px;
  overflow: scroll;
`;

const ChatInputArea = styled(Box)`
  margin-top: 5%;
  display: flex;
  justify-content: space-between;
`;

export default function ChatBox({ messages, sendMessage }) {
  const [message, setMessage] = useState("");
  const [currentTime, setCurrentTime] = useState(new Date());
  const contentRef = useRef(null);

  useEffect(() => {
    setCurrentTime(new Date());
    contentRef.current.scrollTop = contentRef.current.scrollHeight;
    // contentRef.current.scrollIntoView({ behavior: "smooth" });
  }, [messages]);

  function getChatList(chats) {
    let id = 0;
    return chats
      .map(chat => {
        return (
          <ChatItem
            key={id++}
            name={chat.memberNickName}
            message={chat.message}
            time={chat.messageTime}
            currentTime={currentTime}
          />
        );
      })
      .reverse();
  }

  function onInputChange(e) {
    setMessage(e.target.value);
  }

  function onClickSend() {
    sendMessage(CHAT_TYPE.TALK, message);
    setMessage("");
  }

  return (
    <Content>
      <ChatHistoryArea ref={contentRef}>{getChatList(messages)}</ChatHistoryArea>
      <ChatInputArea>
        <Input size="large" value={message} onChange={onInputChange}></Input>
        <Button size="smaller" onClick={onClickSend}>
          전송
        </Button>
      </ChatInputArea>
      <Text size="smaller" variant="grey" weight="normal">
        함께 대화하는 모두를 위해 에티켓을 지켜주세요.
      </Text>
    </Content>
  );
}
