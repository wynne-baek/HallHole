import React from "react";
import { useState } from "react";
import { List } from "@mui/material";
import { Box } from "@mui/system";

import CategorySelectButton from "../molecule/CategorySelectButton";
import ProfileReviewItem from "../molecule/ProfileReviewItem";

const ActivityCategory = ["후기", "댓글", "리액션"];
const reviewList = [{}];
const commentList = [{}];
const reactionList = [{}];

export default function MyActivity(props) {
  const [selectedCategory, setSelectedCategory] = useState(ActivityCategory[0]);
  const [categoryList, setCategoryList] = useState(reviewList);

  function selectActivityCategory(e) {
    e.preventDefault();
    setSelectedCategory(e.target.innerText);
    if (e.target.innerText === ActivityCategory[0]) {
      setCategoryList(reviewList);
    } else if (e.target.innerText === ActivityCategory[1]) {
      setCategoryList(commentList);
    } else {
      setCategoryList(reactionList);
    }
  }

  return (
    <Box sx={{ width: "95%", marginY: 2, marginLeft: 2 }}>
      <Box sx={{ display: "flex" }}>
        {ActivityCategory.map((item, i) => (
          <CategorySelectButton
            key={i}
            category={item}
            selected={selectedCategory === item}
            onClick={selectActivityCategory}
          ></CategorySelectButton>
        ))}
      </Box>
      <List>
        {categoryList.map(item => (
          <ProfileReviewItem
            key={item.id}
            title={item.title}
            date={item.date}
            star_eval={item.star_eval}
            performance_name={item.performance_name}
          ></ProfileReviewItem>
        ))}
      </List>
    </Box>
  );
}
