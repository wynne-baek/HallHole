import * as React from "react";
import Card from "@mui/material/Card";
import { CardActionArea } from "@mui/material";
import Button from "../atom/Button";
import Text from "../atom/Text";
import FamousPerformances from "../molecule/FamousPerformance";
import Box from "@mui/material/Box";
import PosterSize from "../atom/PosterSize";
import Input from "../atom/Input";

export default function ActionAreaCard() {
  const FamousPerformanceList = [
    {
      title: "웃는 남자",
      img: "https://cdnticket.melon.co.kr/resource/image/upload/product/2019/11/2019112812171593082da1-3771-458f-ab20-7777146d2066.jpg",
    },
    {
      title: "웃는 남자",
      img: "https://cdnticket.melon.co.kr/resource/image/upload/product/2019/11/2019112812171593082da1-3771-458f-ab20-7777146d2066.jpg",
    },
    {
      title: "웃는 남자",
      img: "https://cdnticket.melon.co.kr/resource/image/upload/product/2019/11/2019112812171593082da1-3771-458f-ab20-7777146d2066.jpg",
    },
    {
      title: "웃는 남자",
      img: "https://cdnticket.melon.co.kr/resource/image/upload/product/2019/11/2019112812171593082da1-3771-458f-ab20-7777146d2066.jpg",
    },
    {
      title: "웃는 남자",
      img: "https://cdnticket.melon.co.kr/resource/image/upload/product/2019/11/2019112812171593082da1-3771-458f-ab20-7777146d2066.jpg",
    },
  ];

  return (
    <div>
      <div style={{ position: "absolute", zIndex: 1, top: "60px" }}>
        <div>
          <Box style={{ position: "absolute", zIndex: 1 }}>
            <Text variant="white">
              <h2 style={{ marginLeft: "60px", marginRight: "60px" }}>
                <Text variant="primary">유저 네임</Text> 님<br />이 채팅에 참여중이었어요!
              </h2>
            </Text>
          </Box>
        </div>
        <div>
          <Box style={{ backgroundColor: "#e37373", opacity: 0.6 }}>
            <Text variant="white">
              <h2 style={{ marginLeft: "60px", marginRight: "60px" }}>
                <Text variant="primary">유저 네임</Text> 님<br />이 채팅에 참여중이었어요!
              </h2>
            </Text>
          </Box>
        </div>
      </div>
      <div>
        <PosterSize
          type="blur"
          size="full"
          src="https://cdnticket.melon.co.kr/resource/image/upload/product/2019/11/2019112812171593082da1-3771-458f-ab20-7777146d2066.jpg"
        ></PosterSize>
      </div>
      <div style={{ position: "relative", top: "-400px" }}>
        <Card sx={{ maxWidth: 300, maxHeight: 400, boxShadow: 6, my: 5, mx: 6 }}>
          <CardActionArea>
            <PosterSize
              size="large"
              src="https://cdnticket.melon.co.kr/resource/image/upload/product/2019/11/2019112812171593082da1-3771-458f-ab20-7777146d2066.jpg"
            ></PosterSize>
          </CardActionArea>
        </Card>

        <Button size="large" variant="primary">
          <Text variant="white">
            <h2>💬 채팅 하러 가기</h2>
          </Text>
        </Button>
        <Box style={{ marginTop: "50px", marginLeft: "50px" }}>
          <Text variant="black" size="medium">
            찾고있는 <Text variant="primary">뮤지컬</Text>이 있나요?
          </Text>
        </Box>
        <Box sx={{ marginLeft: 5, marginY: 5 }}>
          <Input size="large">검색</Input>
        </Box>
        <br />
        <Box>
          <FamousPerformances FamousPerformances={FamousPerformanceList} />
        </Box>
      </div>
    </div>
  );
}
