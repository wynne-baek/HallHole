import React from "react";

import { Box } from "@mui/material";
import { styled } from "@mui/system";

import ProfileImage from "../atom/ProfileImage";
import Text from "../atom/Text";

const Content = styled(Box)`
  margin-top: 10px;
  max-width: 70vw;
  display: flex;
  justify-content: flex-start;
`;

const ChatBody = styled(Box)`
  width: 70vw;
  dispaly: flex;
  flex-direction: column;
`;

const SenderInfoBox = styled(Box)`
  display: flex;
  justify-content: space-between;
  max-width: 70vw;
`;

const MessageBox = styled(Box)`
  word-break: break-all;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 5px;
  box-shadow: 1px 1px 1px #ccc;
`;

const NameBox = styled(Box)``;

const TimeBox = styled(Box)``;

export default function ChatItem({ name, message, time, currentTime }) {
  function getFormattedTime(time) {
    const seconds = (currentTime - time) / 1000;
    if (seconds < 60) return `방금`;
    const minutes = seconds / 60;
    if (minutes < 60) return `${Math.floor(minutes)}분`;
    const hours = minutes / 60;
    if (hours < 24) return `${Math.floor(hours)}시간`;
    const days = hours / 24;
    if (days < 7) return `${Math.floor(days)}일`;
    const weeks = days / 7;
    if (weeks < 5) return `${Math.floor(weeks)}주`;
    const months = days / 30;
    if (months < 12) return `${Math.floor(months)}개월`;
    const years = days / 365;
    return `${Math.floor(years)}년`;
  }

  return (
    <Content>
      <ProfileImage size="small" />
      <ChatBody>
        <SenderInfoBox>
          <NameBox>
            <Text>{name}</Text>
          </NameBox>
          <TimeBox>
            <Text size="smallest" variant="grey" weight="lighter">
              {getFormattedTime(new Date(time))} 전
            </Text>
          </TimeBox>
        </SenderInfoBox>
        <MessageBox>{message}</MessageBox>
      </ChatBody>
    </Content>
  );
}
