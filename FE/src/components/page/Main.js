import * as React from "react";
import Card from "@mui/material/Card";
import { CardActionArea } from "@mui/material";
import Button from "../atom/Button";
import Text from "../atom/Text";
import Box from "@mui/material/Box";
import PosterSize from "../atom/PosterSize";
import Input from "../atom/Input";
import TwitterBox from "../organism/TwitterBox";

export default function ActionAreaCard() {
  return (
    <div>
      <div style={{ position: "absolute", zIndex: 1, top: "60px" }}>
        <div>
          <Box style={{ position: "absolute", zIndex: 1 }}>
            <Box style={{ marginLeft: "3rem", marginTop: "15px" }}>
              <Text variant="white" size="medium">
                <Text variant="primary" size="large">
                  유저 네임
                </Text>
                님<br />이 채팅에 참여중이었어요!
              </Text>
            </Box>
          </Box>
          <Box style={{ backgroundColor: "#e37373", opacity: 0.6, width: "412px", height: "100px" }}></Box>
        </div>
      </div>
      <div
        style={{
          width: "100%",
          height: "548px",
          backgroundImage: `url("https://cdnticket.melon.co.kr/resource/image/upload/product/2019/11/2019112812171593082da1-3771-458f-ab20-7777146d2066.jpg")`,
          backgroundAttachment: "fixed",
          backgroundSize: "cover",
          backgroundRepeat: "no-repeat",
          backgroundPositionX: -100,
          backgroundPositionY: -100,
          filter: "blur(8px)",
        }}
      ></div>
      <div style={{ position: "relative", top: "-400px" }}>
        <Card sx={{ maxWidth: 300, maxHeight: 400, boxShadow: 6, marginTop: 5, marginLeft: 6.8 }}>
          <CardActionArea>
            <PosterSize
              size="large"
              src="https://cdnticket.melon.co.kr/resource/image/upload/product/2019/11/2019112812171593082da1-3771-458f-ab20-7777146d2066.jpg"
            ></PosterSize>
          </CardActionArea>
        </Card>

        <Box sx={{ marginLeft: 8, marginTop: 6 }}>
          <Text variant="black" size="medium">
            찾고있는 <Text variant="primary">뮤지컬</Text>이 있나요?
          </Text>
        </Box>
        <Box sx={{ marginLeft: 5, marginY: 2 }}>
          <Box sx={{ marginLeft: 3 }}>
            <Input size="large"></Input>
          </Box>
        </Box>
        <Box sx={{ marginY: 10 }}></Box>
        <TwitterBox></TwitterBox>
      </div>
    </div>
  );
}
