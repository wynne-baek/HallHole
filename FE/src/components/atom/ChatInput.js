import React, { Component } from "react";
import SendIcon from '@mui/icons-material/Send';

// https://codesandbox.io/s/friendly-fire-6ww18?file=/src/InputGroup.js 
// 참고 코드... 작성하다가 뇌아픔

const SendButton = styled("button")`
  height: 40;
  border-radius: 20;
  border: none;
  right: 30
`;

const TypeInput = styled("input")`
  position: fixed;
  display: flex;
  width: 50%;
  height: 0;
`;

export default function InputGroup(props) {
    return (
      <div className="input-group">
        <textarea
          value={this.props.inputValue}
          type="text"
          className={TypeInput}
          placeholder="함께 대화하는 모두를 위해 에티켓을 지켜주세요."
          onChange={this.props.onChange}
          onKeyPress={this.props.onKeyPress}
        ></textarea>
        <div className="input-group-append ">
          <SendButton
            className="btn btn-primary"
            type="button"
            onClick={this.props.onClick}
          >
            <SendIcon />
          </SendButton>
        </div>
      </div>
    );
  }

