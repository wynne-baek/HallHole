import React from "react";

import { keyframes, styled, css } from "@mui/system";

const ScrollAnimation = keyframes`
  from {
    top: 0%;
  }
  to {
    top: -100%;
  }
`;

const ScrollParent = styled("div")`
  position: relative;

  width: inherit;
  height: inherit;

  overflow-x: hidden;
  overflow-y: hidden;
`;

const ScrollChild = styled("div")(
  ({ key }) => css`
    animation: ${ScrollAnimation} 3s linear infinite;
  `,
);

const Poster = styled("img")`
  width: 50%;
  border-radius: 10%;
`;

function createScrollChildren(items) {
  return items.map(item => {
    return (
      <ScrollChild key={item.img}>
        <Poster src={item.img} alt={item.title} loading="lazy" />
      </ScrollChild>
    );
  });
}

export default function InfiniteLoopScroll(props) {
  return <ScrollParent>{createScrollChildren(props.items)}</ScrollParent>;
}
