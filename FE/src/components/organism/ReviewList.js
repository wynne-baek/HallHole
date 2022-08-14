import React from "react";

import ReviewItem from "../molecule/ReviewItem";
import CategoryDivider from "../atom/CategoryDivider";
import { Box } from "@mui/system";
import TextStyle from "../atom/Text";

export default function ReviewList({ performanceReviewList }) {
  return (
    <Box sx={{ mt: 3, width: "95%"}}>
      <CategoryDivider type="negative" />
      <br></br>
      <TextStyle size="medium" variant="primary">관람 후기</TextStyle>
      {performanceReviewList.map((item, i) => (
        <ReviewItem
          key={i}
          title={item.title}
          written_date={item.writing_time}
          star_eval={item.star_eval}
          user={item.name}
        ></ReviewItem>
      ))}
    </Box>
  );
}
