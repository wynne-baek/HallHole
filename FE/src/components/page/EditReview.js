import { Box } from "@mui/system";
import React, { useEffect, useState } from "react";

import TextStyle from "../atom/Text";
import CategoryDivider from "../atom/CategoryDivider";
import { fetchPerformance } from "../../apis/performance";
import EditForm from "../organism/EditForm";
import { useParams } from "react-router-dom";

export default function WriteReview() {
  const { id } = useParams();

  return (
    <Box>
      <Box sx={{ width: "90%", margin: "auto", mt: 3 }}>
        <TextStyle variant="black" size="medium" weight="bold">
          ✍🏼 후기 수정
        </TextStyle>
        <CategoryDivider type="dark"></CategoryDivider>
        <TextStyle variant="black" size="small">
          {"관람한 공연에 대한 후기를 공유해볼까요? :)"}
        </TextStyle>
      </Box>
      {/* 리뷰 폼 */}
      <EditForm id={id}></EditForm>
    </Box>
  );
}
