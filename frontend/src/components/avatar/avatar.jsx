import React, {useState} from "react";
import "./avatar.css";

function Avatar({nameInitials, onLogout}) {


  const [isMenuOpen, setIsMenuOpen] = useState(false);

  const toggleMenu = () => {
    setIsMenuOpen(!isMenuOpen);
  }

  const handleLogout = (event) => {
    event.preventDefault();
    onLogout();
  };


  
  return (
    <div className="avatar">
      <span onClick={toggleMenu}>{nameInitials}</span>
      {isMenuOpen && (<div className="avatar-menu">
        <div>Minhas Reservas</div>
        <div onClick={handleLogout}>Logout</div>
        </div>
      )}
    </div>
  );
}

export default Avatar;