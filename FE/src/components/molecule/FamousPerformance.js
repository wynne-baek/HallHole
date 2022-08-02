import { ImageList, ImageListItem } from "@mui/material";
import { Box } from "@mui/system";
import React from "react";

import Text from "../atom/Text";
import CategoryDivider from "../atom/CategoryDivider";

const flexContainer = {
  display: "flex",
  flexDirection: "row",
  flexWrap: "nowrap",
  padding: 0,
  whiteSpace: "nowrap",
  overflow: "auto",
};

export default function FamousPerformances(props) {
  return (
    <Box sx={{ width: 1, marginY: 3, marginLeft: 1 }}>
      <Text>
        <h2 style={{ marginLeft: "20px" }}>
          🔥 요즘 가장 <Text variant="primary">인기</Text>있어요
        </h2>
      </Text>
      <Box sx={{ boxShadow: 6 }}>
        <Box>
          <ImageList style={flexContainer}>
            {props.FamousPerformances.map(item => (
              <ImageListItem key={item.id}>
                <img key={item.id} src={item.img} alt={item.title} style={{ width: 130 }} />
              </ImageListItem>
            ))}
          </ImageList>
        </Box>
      </Box>
    </Box>
  );
}
