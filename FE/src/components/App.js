import React, { Component } from "react";

import ButtonTest from "./ButtonTest";
import MUITest from "./MUITest";

class App extends Component {
  render() {
    return (
      <div>
        <h1>Hello 홀홀!</h1>
        <h2>리액트로 만드는 홀홀</h2>
        <ButtonTest></ButtonTest>
        <MUITest></MUITest>
      </div>
    );
  }
}

export default App;
