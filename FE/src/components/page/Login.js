import React from "react";

import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";

import { Box, Modal as MUIModal } from "@mui/material";
import { styled } from "@mui/system";
import CloseIcon from "@mui/icons-material/Close";

import Text from "../atom/Text";
import Logo from "../atom/Logo";
import Input from "../atom/Input";
import Button from "../atom/Button";
import Modal from "../organism/Modal";
import ToggleButton from "../molecule/ToggleButton";
import { requestJoin, requestLogin, requestMyInfo } from "../../apis/user";
import { setTokenToStore } from "../../stores/user";

import storage from "../../helper/storage";

const LoginModal = styled(Modal)``;

const closeIconStyle = {
  color: "white",
  fontSize: "3rem",
};

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: "40vw",
  bgcolor: "white",
  borderRadius: 5,
  boxShadow: 24,
  p: 4,
};

const LoginModalHeader = styled(Box)`
  width: 100vw;
  height: 5vh;
  padding: 5%;
`;

const LoginModalBody = styled(Box)`
  text-align: center;
`;

const LogoBox = styled(Box)`
  margin-top: 10vh;
`;

const ToggleBox = styled(Box)`
  margin-top: 5%;
  text-align: center;
`;

const InputBox = styled(Box)`
  margin-top: 5%;
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 0.5rem;
`;

export default function Login(props) {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const [isLogin, setIsLogin] = React.useState(true);
  const [email, setEmail] = React.useState("");
  const [name, setName] = React.useState("");
  const [password, setPassword] = React.useState("");
  const [confirm, setConfirm] = React.useState("");

  const [errorMessageEmail, setErrorMessageEmail] = React.useState("");
  const [errorMessageName, setErrorMessageName] = React.useState("");
  const [errorMessagePassword, setErrorMessagePassword] = React.useState("");
  const [errorMessageConfirm, setErrorMessageConfirm] = React.useState("");

  const [openModal, setOpenModal] = React.useState(false);
  const [modalTitle, setModalTitle] = React.useState("");
  const [modalMessage, setModalMessage] = React.useState("");

  function modalOpen(title, message) {
    setModalTitle(title);
    setModalMessage(message);
    setOpenModal(true);
  }
  function modalClose() {
    setOpenModal(false);
  }

  function onChangeEmail(e) {
    const email = e.target.value;
    setEmail(email);
    validateEmail(email);
  }

  function onChangeName(e) {
    const name = e.target.value;
    setName(name);
    validateName(name);
  }

  function onChangePassword(e) {
    const password = e.target.value;
    setPassword(password);
    validatePassword(password);
  }

  function onChangeConfirm(e) {
    const confirm = e.target.value;
    setConfirm(confirm);
    validateConfirm(confirm);
  }

  function loginSuccess(res) {
    const token = res.data.token;
    storage.set("token", token);
    dispatch(setTokenToStore(token));
    navigate("/main");
  }

  function loginFail(res) {
    modalOpen("????????? ??????", "????????? ?????? ??????????????? ?????????????????????.");
  }

  function joinSuccess(res) {
    modalOpen("???????????? ??????", "??????????????? ?????????????????????.");

    setEmail("");
    setName("");
    setPassword("");
    setConfirm("");
  }

  function joinFail(res) {
    modalOpen("???????????? ??????", "?????? ???????????? ??????????????????.");
  }

  function validateEmail(email) {
    if (email.length === 0) {
      setErrorMessageEmail("???????????? ??????????????????.");
      return false;
    }

    const regex = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
    if (!regex.test(email)) {
      setErrorMessageEmail("?????? ??? ????????? ???????????????.");
      return false;
    }

    setErrorMessageEmail("");
    return true;
  }

  function validateName(name) {
    if (name.length === 0) {
      setErrorMessageName("????????? ??????????????????.");
      return false;
    }

    const regex = /^[???-???|???-???|a-z|A-Z|0-9|]+$/;
    if (!regex.test(name)) {
      setErrorMessageName("????????? ????????? ????????? ????????? ??? ????????????.");
      return false;
    }

    if (name.length < 2) {
      setErrorMessageName("????????? ?????? ????????????.");
      return false;
    }

    if (name.length > 16) {
      setErrorMessageName("????????? ?????? ?????????.");
      return false;
    }

    setErrorMessageName("");
    return true;
  }

  function validatePassword(password, forceValidate = false) {
    if (!isLogin || forceValidate) {
      if (password.length === 0) {
        setErrorMessagePassword("??????????????? ??????????????????.");
        return false;
      }

      var reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]+$/;
      if (!reg.test(password)) {
        setErrorMessagePassword("??????????????? ?????????, ??????, ??????????????? ?????????????????????.");
        return false;
      }

      if (password.length < 8) {
        setErrorMessagePassword("??????????????? ?????? ????????????.");
        return false;
      }

      if (password.length > 20) {
        setErrorMessagePassword("??????????????? ?????? ?????????.");
        return false;
      }
    }

    setErrorMessagePassword("");
    return true;
  }

  function validateConfirm(confirm) {
    if (confirm != password) {
      setErrorMessageConfirm("??????????????? ???????????? ????????????.");
      return false;
    }

    setErrorMessageConfirm("");
    return true;
  }

  function validate(isLogin) {
    if (isLogin) {
      if (validateEmail(email)) {
        return true;
      }
    } else {
      if (validateEmail(email) && validateName(name) && validatePassword(password, true) && validateConfirm(confirm)) {
        return true;
      }
    }

    return false;
  }

  function onClickButton() {
    if (validate(isLogin)) {
      if (isLogin) requestLogin(email, password, loginSuccess, loginFail);
      else requestJoin(email, name, password, joinSuccess, joinFail);
    }
  }

  return (
    <LoginModal toggle={props.toggle}>
      <LoginModalHeader>
        <CloseIcon sx={closeIconStyle} onClick={props.onClose} />
      </LoginModalHeader>

      <LoginModalBody>
        <LogoBox>
          <Logo size="large" src="logo.png" />
        </LogoBox>
        <ToggleBox>
          <ToggleButton
            textLeft="?????????"
            textRight="????????????"
            onClickLeft={() => {
              setIsLogin(true);
              setErrorMessagePassword("");
            }}
            onClickRight={() => {
              setIsLogin(false);
              validatePassword(password, true);
            }}
          />
        </ToggleBox>
        <InputBox>
          <Input
            value={email}
            size="large"
            variant="filled"
            label="Email"
            onChange={onChangeEmail}
            errorMessage={errorMessageEmail}
          />

          {!isLogin && (
            <Input
              value={name}
              size="large"
              variant="filled"
              label="Name"
              onChange={onChangeName}
              errorMessage={errorMessageName}
            />
          )}
          <Input
            value={password}
            size="large"
            variant="filled"
            label="Password"
            type="password"
            onChange={onChangePassword}
            errorMessage={errorMessagePassword}
          />
          {!isLogin && (
            <Input
              value={confirm}
              size="large"
              variant="filled"
              label="Confirm Password"
              type="password"
              onChange={onChangeConfirm}
              errorMessage={errorMessageConfirm}
            />
          )}

          <Button size="large" variant="primary" onClick={onClickButton}>
            <Text size="medium" variant="white">
              {isLogin ? "?????????" : "????????????"}
            </Text>
          </Button>
        </InputBox>
      </LoginModalBody>

      <MUIModal
        open={openModal}
        onClose={modalClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <div id="modal-modal-title">
            <Text size="medium">{modalTitle}</Text>
          </div>
          <div id="modal-modal-description">
            <Text weight="lighter">{modalMessage}</Text>
          </div>
        </Box>
      </MUIModal>
    </LoginModal>
  );
}
