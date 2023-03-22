import React from "react";
import "./avatar.css";

function Avatar({ initials }) {
  return (
    <div className="avatar">
      <span>{initials}</span>
    </div>
  );
}

export default Avatar;