import React from "react";
import Box from "@mui/material/Box";
import styled from "@emotion/styled";

import Logo from "../atom/Logo";
import ProfileImage from "../atom/ProfileImage";

// width 수정 필요. 상위 요소 정해
const NavBox = styled(Box)`
  position: fixed;
  top: 0;
  background-color: black;
  width: 100vw;
  display: flex;
  justify-content: space-between;
`;

export default function NavBar() {
  return (
    <NavBox sx={{ zIndex: 1000 }}>
      {/* 홈 화면으로 돌아가는 링크 추가 */}
      {/* <Link to=""> */}
      <Logo src="logo.png" />
      {/* </Link> */}
      {/* 메뉴서랍? 오픈하기! */}
      {/* <Link to=""> */}
      <ProfileImage type="thumb" src="" />
      {/* </Link> */}
    </NavBox>
  );
}
