import React, { useState, useEffect } from "react";

import { Box } from "@mui/system";

import PosterImage from "../atom/PosterSize";
import TextStyle from "../atom/Text";
import ButtonStyle from "../atom/Button";

const posterBackgroundStyle = {
  position: "absolute",
  padding: 0,
  margin: 0,
  top: 0,
  left: 0,
  zIndex: -1,
};

const smallPosterStyle = {
  position: "relative",
  top: "25vh",
  zIndex: 1,
  marginX: 2,
  display: "flex",
  justifyContent: "space-between",
  alignItems: "end",
};

const performanceDetailStyle = {
  position: "relative",
  top: "17vh",
  width: 1,
  height: "40vh",
};

export default function PerformanceInformation({performanceInfo}) {
  const [performanceLike, setPerformanceLike] = useState(false);
  
  //const imageUrl = props.performanceInfo.performance.poster
  
  function changePerformanceLike(e) {
    e.preventDefault();
    setPerformanceLike(!performanceLike);
  }
  // performance actor와 time에 대한 것 추가 작성하기
  
  return (
    <Box>
      <Box sx={posterBackgroundStyle}>
        <PosterImage type="blur" size="full" src={performanceInfo.poster}></PosterImage>
      </Box>
      <Box sx={smallPosterStyle}>
        <PosterImage size="small" src={performanceInfo.poster}></PosterImage>
        <ButtonStyle size="small" variant={performanceLike ? "grey" : "primary"} onClick={changePerformanceLike}>
          {performanceLike ? "좋아요취소" : "좋아요"}
        </ButtonStyle>
      </Box>
      <Box bgcolor="white" sx={performanceDetailStyle}>
        <Box height={80}></Box>
        <Box marginLeft={2}>
          <TextStyle size="large" variant="black">
            {performanceInfo.name}
          </TextStyle>
          <br></br>
          <TextStyle size="small" variant="black">
            {performanceInfo.genre}
          </TextStyle>
          <br></br>
          <TextStyle size="small" variant="black">
            📍 {performanceInfo.facility_name}
          </TextStyle>
          <br></br>
          <TextStyle size="small" variant="black">
            🗓 {performanceInfo.startDate} - {performanceInfo.endDate}
          </TextStyle>
          <br></br>
          <TextStyle size="small" variant="black">
            👫 출연진 :{""}
          </TextStyle>
        </Box>
      </Box>
    </Box>
  );
}
