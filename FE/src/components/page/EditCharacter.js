import React from "react";
import { Box } from "@mui/system";
import { styled } from "@mui/system";

import ToggleButton from "../molecule/ToggleButton";
import CategoryDivider from "../atom/CategoryDivider";
import TextStyle from "../atom/Text";
import SaveCancel from "../molecule/SaveCancel";

import CircleIcon from '@mui/icons-material/Circle';
import Accessory from "../atom/Accessory";
import PosterImage from "../atom/PosterSize";

const ToggleBox = styled(Box)`
  margin-top: 5%;
  text-align: center;
`;

export default function EditCharacter(props) {
  const [choose, setChoose] = React.useState(true);

  function characterColorChange(e) {
    e.preventDefault();
    console.log('색깔 변경')
  }
  
  function accessoryChange(e) {
    e.preventDefault();
    console.log('소품 변경')
  }

  return (
    <Box>
      <Box sx={{ ml: 2 }}>
        <Box sx={{ mt: 1 }}>
          <TextStyle size="large" variant="black">
            🎨 캐릭터 꾸미기
          </TextStyle>
        </Box>
        <Box sx={{ my: 0.5, mr: 2 }}>
          <CategoryDivider type="dark"></CategoryDivider>
        </Box>
        <Box>
          <TextStyle size="small" variant="black">
            나만의 캐릭터를 꾸며보아요
          </TextStyle>
        </Box>
      </Box>
      <Box sx={{ mt: 2 }}>
        <Box sx={{ display: "flex", justifyContent: "center", alignItems: "center" }}>
          <TextStyle size="medium" variant="black">
            🔎 미리보기
          </TextStyle>
        </Box>
        <Box sx={{ display: "flex", justifyContent: "center", alignItems: "center", mt: 1 }}>
          <Box sx={{ width: 250, height: 200, backgroundColor: "beige", p: 2, borderRadius: 5 }}>
            {/* 캐릭터 색상 선택 및 소품 선택 시 해당 아래 박스의 png가 변경되어야 함 */}
            <Box sx={{ display: "flex", justifyContent: "center", alignItems: "center", mt:5 }}>
              <PosterImage size="small" src="holymoly.png"/>
            </Box>
          </Box>
        </Box>
      </Box>
      <ToggleBox>
        <ToggleButton
          textLeft="　🧪색상"
          textRight="　　💎소품　 "
          onClickLeft={() => setChoose(true)}
          onClickRight={() => setChoose(false)}
        />
      </ToggleBox>
      {/* 캐릭터 색상 선택 창 -> 몸통, 표정, 팔 각각 파편화 되어있으며 합쳐져서 보입니다 */}
      <Box sx={{ display: "flex", justifyContent: "center", alignItems: "center" }}>
        {choose && (
          <Box sx={{ width: 250, height: 250, backgroundColor: "skyblue", p: 2, borderRadius: 5 }}>
            <Box sx={{ display: "flex", justifyContent: "center", alignItems: "center" }}>
              <CircleIcon sx={{ fontSize: 80, color: "white" }} onClick={characterColorChange}/>
              <CircleIcon sx={{ fontSize: 80, color: "black" }} onClick={characterColorChange}/>
              <CircleIcon sx={{ fontSize: 80, color: "#aece2d" }} onClick={characterColorChange} />
            </Box>
            <Box sx={{ display: "flex", justifyContent: "center", alignItems: "center" }}>
              <CircleIcon sx={{ fontSize: 80, color: "#f8ea67" }} />
              <CircleIcon sx={{ fontSize: 80, color: "#e0712c" }} />
              <CircleIcon sx={{ fontSize: 80, color: "#a63d36" }} />
            </Box>
            <Box sx={{ ml: 0.5 }}>
              <CircleIcon sx={{ fontSize: 80, color: "#e37373" }} />
            </Box>
          </Box>
        )}
        {!choose && (
          <Box sx={{ width: 250, height: 250, backgroundColor: "skyblue", p: 2, borderRadius: 5 }}>
            <Box sx={{ display: "flex", justifyContent: "center", alignItems: "center" }}>
              <Box sx={{ width:60, height:60, border: '1px dashed grey', borderRadius: 2, mr: 1 }}/>
              <Accessory src="acc/death_note.png"/>
              <Accessory src="acc/death_wing.png"/>
            </Box>
            <Box sx={{ display: "flex", justifyContent: "center", alignItems: "center" }}>
              <Accessory src="acc/kinky_boots.png"/>
              <Accessory src="acc/mozart_hair.png"/>
              <Accessory src="acc/mozart_paper.png"/>
            </Box>
            <Box sx={{ display: "flex", justifyContent: "center", alignItems: "center" }}>
              <Accessory src="acc/opera_mask.png"/>
              <Accessory src="acc/smileman.png"/>
              <Accessory src="acc/death_apple.png"/>
            </Box>
          </Box>
        )}
      </Box>
      <Box sx={{ mt: 1 }}>
        <SaveCancel></SaveCancel>
      </Box>
    </Box>
  );
}
