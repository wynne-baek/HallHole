import React, { useEffect } from "react";
import { styled } from "@mui/system";

const ModalDiv = styled("div")`
  position: fixed;
  top: ${props => (props.toggle == "on" ? "0" : "100%")};
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
 */
export default function Modal(props) {
  function onToggleChange(on, off) {
    if (props.toggle) on && on();
    else off && off();
  }

  useEffect(() => {
    onToggleChange(props.on, props.off);
  }, [props.toggle]);

  return <ModalDiv toggle={props.toggle}>{props.children}</ModalDiv>;
}
