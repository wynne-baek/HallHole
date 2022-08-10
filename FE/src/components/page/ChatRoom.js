import React, { useEffect, useState } from "react";

import { Link, useParams } from "react-router-dom";
import { Outlet } from "react-router-dom";

import { Box } from "@mui/material";

import { getWebsocket } from "../../helper/websocket";
import { fetchChatLog } from "../../apis/chat";

import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";

const CHAT_TYPE = {
  ENTER: "ENTER",
  TALK: "TALK",
  OUT: "OUT",
};

export default function ChatRoom() {
  const navigate = useNavigate();
  const user = useSelector(state => state.user.info);
  const { chatId } = useParams();
  const ws = getWebsocket();

  const [messages, setMessages] = useState([]);
  const [message, setMessage] = useState("");

  function fetchChatLogSuccess(response) {
    console.log("채팅 로그 가져오기 성공", response);
  }

  function fetchChatLogFail(response) {
    console.log("채팅 로그 가져오기 실패", response);
  }

  function receiveMessage(response) {
    console.log("새로운 채팅 메시지 수신", response);
    const recv = JSON.parse(response.body);
    setMessages([...messages, recv]);
  }

  function connectSuccess(frame) {
    console.log("connected: " + frame);
    ws.subscribe(`/topic/chat/room/${chatId}`, receiveMessage);
    sendMessage(CHAT_TYPE.ENTER, "");
  }

  function connectFail(error) {
    console.log("connect failed: " + error);
  }

  function sendMessage(type, message = "") {
    const msg = {
      type: type,
      performanceId: chatId,
      memberNickName: user.name,
      message: message,
      idTag: user.idTag,
    };
    console.log(msg);
    ws.send("/app/chat/message", {}, JSON.stringify(msg));
  }

  function connect() {
    console.log("connect");
    ws.connect({}, connectSuccess, connectFail);
    fetchChatLog(chatId, fetchChatLogSuccess, fetchChatLogFail);
  }

  function disconnect() {
    console.log("disconnect");
    sendMessage(CHAT_TYPE.OUT);
    ws.disconnect();
  }

  useEffect(() => {
    connect();
    return disconnect;
  }, []);

  return (
    <Box>
      <button
        onClick={() => {
          navigate(-1);
        }}
      >
        뒤로 가기
      </button>
      This is Chat Room with {chatId}
      <button onClick={() => sendMessage(CHAT_TYPE.TALK, "안녕하세요")}>안녕하세요</button>
      <Outlet />
    </Box>
  );
}
