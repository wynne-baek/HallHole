import React,  { useState }  from "react";
import { Box } from "@mui/system";
import { MenuItem, FormControl, Select, InputLabel } from "@mui/material"

import BirthPicker from "../atom/BirthPicker";
import TextStyle from "../atom/Text";
import Input from "../atom/Input"

const profileEdit = {
  nickname: "",
  introduction: "",
  email: "",
  gender: "",
  birth: "",
};

export default function ProfileEdit(props) {
  const [infos, setInfos] = useState(profileEdit)
  const [open, setOpen] = React.useState(false);

  const handleClose = () => {
    setOpen(false);
  };

  const handleOpen = () => {
    setOpen(true);
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setInfos({
      ...infos,
      [name]: value,
    });
  };
  
  return (
    // 프로필 설정에서 변경할 수 있는 모든 폼들이 모여있습니다.
    // 마진 관련 재설정 필요(px외 단위로), 위치 설정
    <Box>
      <Box sx={{ display:"flex", justifyContent:"center"}}>
        <Input 
          label="닉네임"
          size="large"
          value={infos.nickname}
          onChange={handleInputChange}
          name="nickname" />
        <Input 
          label="자기소개"
          size="large"
          value={infos.introduction}
          onChange={handleInputChange}
          name="introduction" />
        <Input 
          label="이메일"
          size="large"
          value={infos.email}
          onChange={handleInputChange}
          name="email" />
      </Box>
      <Box sx={{ display: 'flex' }}>
        <Box>
          <TextStyle size="medium">성별</TextStyle>
          <FormControl sx={{ m: 1, minWidth: 120 }}>
            <InputLabel id="gender">성별</InputLabel>
              <Select
                labelId="gender"
                name="gender"
                open={open}
                onClose={handleClose}
                onOpen={handleOpen}
                value={infos.gender}
                label="setGender"
                onChange={handleInputChange}
              >
                <MenuItem value="N">비공개</MenuItem>
                <MenuItem value="M">남자</MenuItem>
                <MenuItem value="F">여자</MenuItem>
              </Select>
          </FormControl>
        </Box>
        <Box>
          <TextStyle size="medium">생년월일</TextStyle>
            <BirthPicker />
        </Box>
      </Box>
    </Box>
  );
}
