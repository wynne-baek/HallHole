import React from "react";

import Logo from "../atom/Logo";
import Text from "../atom/Text";
import Button from "../atom/Button";
import { Box, ImageList, ImageListItem } from "@mui/material";
import { styled } from "@mui/system";

const contentStyle = {
  width: "100vw",
  height: "100vh",

  backgroundColor: theme => theme.palette.base.black,
};

const posterListStyle = {
  position: "fixed",
  padding: 0,
  margin: "3%",
  top: 0,
  left: 0,

  width: "94%",
  height: "100%",
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

const itemData = [
  {
    img: "poster_1.gif",
    title: "AIDA",
  },
  {
    img: "poster_2.gif",
    title: "엘리자벳",
  },
  {
    img: "poster_3.gif",
    title: "신데렐라",
  },
  {
    img: "poster_4.gif",
    title: "사의찬미",
  },
  {
    img: "poster_5.gif",
    title: "VEVERAFTER",
  },
  {
    img: "poster_6.gif",
    title: "살아있는 자를 수선하기",
  },
];

export default function Intro() {
  return (
    <Box sx={contentStyle}>
      <ImageList sx={posterListStyle} cols={2} gap={10}>
        {itemData.map(item => (
          <ImageListItem sx={{ width: 180, height: 50 }} key={item.img}>
            <img src={item.img} alt={item.title} loading="lazy" />
          </ImageListItem>
        ))}
      </ImageList>

      <Box sx={screenCoverStyle}>
        <Box sx={logoBoxStyle}>
          <IntroLogo size="large" src="logo.png" />
        </Box>
        <Box sx={mainDescriptionBoxStyle}>
          <Text size="large" variant="white">
            홀홀, 공연 채팅을
            <br /> 실시간으로
          </Text>
        </Box>
        <Box sx={subDescriptionBoxStyle}>
          <Text size="small" variant="white">
            다양한 공간에서 실시간으로 자유롭게
            <br /> 채팅하며 공연을 즐겨보세요
          </Text>
        </Box>
        <Box sx={startButtonBoxStyle}>
          <Button size="large" variant="primary">
            <Text size="medium" variant="white">
              시작하기
            </Text>
          </Button>
        </Box>
      </Box>
    </Box>
  );
}
