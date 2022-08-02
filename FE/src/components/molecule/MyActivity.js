import React from "react";
import { List } from "@mui/material";
import { Box } from "@mui/system";

import CategorySelected from '../atom/CategorySelected'
import TextStyle from "../atom/Text";
import ReviewItem from "./ReviewItem";

function getList(selected) {
  if (selected === "review") {
    return reviewList
  } else if (selected === "comment") {
    return commentList
  } else if (selected === "reaction") {
    return reactionList
  }
}


export default function MyActivity(props) {
  return (
    <Box sx={{ width: 1, marginY: 2, marginLeft: 1 }}>
      <CategorySelected type="primary"></CategorySelected>
      <CategorySelected type="primary"></CategorySelected>
      <CategorySelected type="primary"></CategorySelected>
      <List>
        {`${getList}`.map(item => (
          <ReviewItem
            key={item.id}
            title={item.title}
            date={item.date}
            star_eval={item.star_eval}
            performance_name={item.performance_name}
          ></ReviewItem>
        ))}
      </List>
    </Box>
  )
}