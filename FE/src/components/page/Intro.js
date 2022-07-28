import React from "react";

import Logo from "../atom/Logo";
import Text from "../atom/Text";
import Button from "../atom/Button";
import { Box } from "@mui/material";
import { styled } from "@mui/system";

const contentStyle = {
  width: "100vw",
  height: "100vh",

  backgroundColor: theme => theme.palette.base.black,
};

const screenCoverStyle = {
  position: "fixed",
  padding: 0,
  margin: 0,
  top: 0,
  left: 0,

  textAlign: "center",

  width: "100%",
  height: "100%",

  backgroundColor: theme => theme.palette.base.blackDim,

  display: "flex",
  flexDirection: "column",
};

const logoBoxStyle = {
  marginTop: "25vh",
};
const mainDescriptionBoxStyle = {
  marginTop: "5vh",
};
const subDescriptionBoxStyle = {};
const startButtonBoxStyle = {
  marginTop: "auto",
  marginBottom: "5vh",
};

const IntroLogo = styled(Logo)`
  margin: auto;
`;

export default function Intro() {
  return (
    <Box sx={contentStyle}>
      <Box sx={screenCoverStyle}>
        <Box sx={logoBoxStyle}>
          <IntroLogo size="large" src="logo.png" />
        </Box>
        <Box sx={mainDescriptionBoxStyle}>
          <Text size="large" color="white">
            홀홀, 공연 채팅을
            <br /> 실시간으로
          </Text>
        </Box>
        <Box sx={subDescriptionBoxStyle}>
          <Text size="small" color="white">
            다양한 공간에서 실시간으로 자유롭게
            <br /> 채팅하며 공연을 즐겨보세요
          </Text>
        </Box>
        <Box sx={startButtonBoxStyle}>
          <Button size="large" variant="primary">
            <Text size="medium" color="white">
              시작하기
            </Text>
          </Button>
        </Box>
      </Box>
    </Box>
  );
}
