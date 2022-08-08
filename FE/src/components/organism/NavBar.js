import React, { useEffect, useState } from "react";
import Box from "@mui/material/Box";
import styled from "@emotion/styled";

import Logo from "../atom/Logo";
import ProfileImage from "../atom/ProfileImage";

import { Link } from "react-router-dom";
import { useStore } from "react-redux";

// width 수정 필요. 상위 요소 정해
const NavBox = styled(Box)`
  position: fixed;
  top: 0;
  left: 0;
  background-color: black;
  width: 100vw;
  height: 10vh;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

export default function NavBar() {
  const store = useStore();
  const [user, setUser] = useState();

  store.subscribe(() => {
    setUser(store.getState().user.info);
  });

  return (
    <NavBox sx={{ zIndex: 1000 }}>
      {/* 홈 화면으로 돌아가는 링크 추가 */}
      <Link to="/main">
        <Logo sx={{ padding: "10px" }} src="logo.png" />
      </Link>
      {/* 메뉴서랍? 오픈하기! */}
      <Link to={`profile/${user?.idTag}`}>
        <ProfileImage type="thumb" src="" />
      </Link>
    </NavBox>
  );
}
