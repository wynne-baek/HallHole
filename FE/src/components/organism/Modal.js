import React, { useEffect } from "react";
import { styled } from "@mui/system";

const ModalDiv = styled("div")`
  position: fixed;
  top: ${props => (props.toggle == "on" ? props.openheight : props.closeheight)};
  left: 0;
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;

  background-color: ${props => props.theme.palette.base.blackDim};
  transition: all 600ms cubic-bezier(0.86, 0, 0.07, 1);
`;

/**
 * props
 *  - toggle : "on" or "off"
 *  - on : function to call when toggle is on
 *  - off : function to call when toggle is off
 *  - openHeight : height of the modal when opened( 0 ~ 100vh, default: "0")
 *  - closeHeight : height of the modal when opened( 0 ~ 100vh, default: "0")
 */
export default function Modal({ toggle, on, off, openHeight = "0", closeHeight = "100vh", children }) {
  function onToggleChange(on, off) {
    if (toggle) on && on();
    else off && off();
  }

  useEffect(() => {
    onToggleChange(on, off);
  }, [toggle]);

  return (
    <ModalDiv toggle={toggle} openheight={openHeight} closeheight={closeHeight}>
      {children}
    </ModalDiv>
  );
}
