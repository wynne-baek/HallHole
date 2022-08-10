import React, { useEffect, useState } from "react";

import { useSelector, useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";

import { setChatToggle } from "../../stores/chat";

import { Box } from "@mui/material";
import { styled } from "@mui/system";
import CloseIcon from "@mui/icons-material/Close";

import Modal from "../organism/Modal";
import { getWebsocket } from "../../helper/websocket";
import { fetchChatLog } from "../../apis/chat";

const ChatModal = styled(Modal)``;

const ChatModalHeader = styled(Box)``;

const ChatModalBody = styled(Box)``;

const CHAT_TYPE = {
  ENTER: "ENTER",
  TALK: "TALK",
  OUT: "OUT",
};

export default function ChatRoom(props) {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const user = useSelector(state => state.user.info);
  const chatId = useSelector(state => state.chat.id);
  const toggle = useSelector(state => state.chat.toggle);

  // const { chatId } = useParams();
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
      memberNickName: user?.name,
      message: message,
      idTag: user.idTag,
    };
    console.log(msg);
    ws.send("/app/chat/message", {}, JSON.stringify(msg));
  }

  function connect() {
    ws.connect({}, connectSuccess, connectFail);
    fetchChatLog(chatId, fetchChatLogSuccess, fetchChatLogFail);
  }

  function disconnect() {
    sendMessage(CHAT_TYPE.OUT);
    ws.disconnect();
  }

  function on() {
    connect();
  }

  function off() {
    disconnect();
  }

  useEffect(() => {
    return () => {
      dispatch(setChatToggle("off"));
    };
  }, []);

  return (
    <ChatModal toggle={toggle} openHeight="15vh" closeHeight="100vh" on={on} off={off}>
      <ChatModalHeader>
        <button
          onClick={() => {
            dispatch(setChatToggle("off"));
          }}
        >
          닫기
        </button>
      </ChatModalHeader>
      <ChatModalBody>
        This is Chat Room with {chatId}
        <button onClick={() => sendMessage(CHAT_TYPE.TALK, "안녕하세요")}>안녕하세요</button>
      </ChatModalBody>
    </ChatModal>
  );
}
