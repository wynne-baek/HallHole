import { ImageList, ImageListItem, } from "@mui/material";
import React from "react";


export default function LikePerformances(props) {
  // 좋아요 누른 공연 제목
  <ImageList sx={{ flexWrap: 'nowrap' }}>
    {props.likePerformances.map((item) => (
      <ImageListItem key={item.img}>
        <img src={`${item.img}`} alt={item.title}/>
      </ImageListItem>
    ))}
  </ImageList>
}