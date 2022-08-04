import React from "react";

import { Box } from "@mui/material";
import { styled } from "@mui/system";

import Modal from "../organism/Modal";
import Text from "../atom/Text";
import CloseIcon from "@mui/icons-material/Close";
import Logo from "../atom/Logo";

const LoginModal = styled(Modal)``;

const closeIconStyle = {
  color: "white",
  fontSize: "3rem",
};

const LoginModalHeader = styled(Box)`
  padding: 5%;
`;

const LoginModalContent = styled(Box)`
  text-align: center;
`;

const LogoBox = styled(Box)`
  margin-top: 20%;
`;

export default function Login(props) {
  return (
    <LoginModal toggle={props.toggle}>
      <LoginModalHeader>
        <CloseIcon sx={closeIconStyle} onClick={props.onClose} />
      </LoginModalHeader>

      <LoginModalContent>
        <LogoBox>
          <Logo size="large" src="logo.png" />
        </LogoBox>
        <Text size="large" variant="white">
          로그인
        </Text>
      </LoginModalContent>
    </LoginModal>
  );
}
