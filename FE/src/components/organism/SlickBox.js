import React, { useEffect, useState } from "react";
import Slider from "react-slick";
import { useNavigate } from "react-router-dom";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

import { requestUserInfo } from "../../apis/user";
import { Link } from "react-router-dom";

import { fetchChatJoin } from "../../apis/chat";

import PosterSize from "../atom/PosterSize";
import Card from "@mui/material/Card";
import Box from "@mui/material/Box";

export default function SimpleSlider({ rooms = [] }) {
  const navigate = useNavigate();

  const sliderSetting = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
    arrows: false,
  };
  const cardStyle = {
    maxWidth: 300,
    width: "100vw",
    marginX: "auto",
    maxHeight: 400,
    boxShadow: 3,
    borderRadius: 10,
  };

  function getSliderItems(rooms) {
    return rooms.map(room => {
      return (
        <Card sx={cardStyle} key={room?.performance?.id}>
          <Box>
            <PosterSize
              size="large"
              src={room?.performance?.poster}
              onClick={() => navigate(`/performancedetail/${room?.performance?.id}`)}
            ></PosterSize>
          </Box>
        </Card>
      );
    });
  }

  function getDefaultCard() {
    return (
      <Card sx={cardStyle}>
        <PosterSize size="large" src="enter_new_chat.png" onClick={() => navigate(`/performancechatlist`)}></PosterSize>
      </Card>
    );
  }

  return (
    <Box>
      <Slider {...sliderSetting}>{rooms.length > 0 ? getSliderItems(rooms) : getDefaultCard()}</Slider>
    </Box>
  );
}
