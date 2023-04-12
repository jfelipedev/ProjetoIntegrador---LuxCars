import React from "react";
import "./headerModal.css";
import { Link } from "react-router-dom";
import { useEffect, useRef, useState } from "react";
import { getToken, isAuthenticated, getTokenName, getTokenSurname, logout, getTokenRole} from '../../services/auth';
import Avatar from '../avatar/avatar';
import { useNavigate } from "react-router-dom";

const HeaderModal= ({id="headerModal", onClose = () => {}}) => {

  const navRef = useRef();
  const navigate = useNavigate();
  const [token, setToken] = useState(getToken());
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [firstName, setFirstaName] = useState(getTokenName());
  const [surname, setSurname] = useState(getTokenSurname());
  const[nameInitials, setInitials] = useState("");
  const [role, setRole] = useState(getTokenRole());

  useEffect(() => {
    const handleStorage = () => {
      setToken(getToken());
    };

    window.addEventListener("storage", handleStorage());
    return () => window.removeEventListener("storage", handleStorage());
  }, []);

  useEffect(() => { 
    setIsAuthenticated(!!token); 
    if(isAuthenticated){
      const nameInitials = `${firstName.charAt(0)}${surname.charAt(0)}`;
      setInitials(nameInitials);
      sessionStorage.setItem('nameInitials', nameInitials);
    }
  }, [token, firstName, surname, isAuthenticated]);

  const handleLogout = () => {
    logout();
    setToken(null);
    setFirstaName(null);
    setSurname(null);
    setIsAuthenticated(false);
    setRole(null);
    navigate('/');
    sessionStorage.removeItem('nameInitials', nameInitials);
  }

  const handleOutsideClick = (e) => {
     if(e.target.id === id) onClose();
  }

  return (
    <div id={id} className="headerModal" onClick={handleOutsideClick}>
      <div className="headerModalcontent">
        <div className="headerModalHeader">
          <i class="uil uil-times navClose" onClick={onClose}></i>
          <div className="headerModalUser">
            {isAuthenticated && <div className="userPart"> 
              <Avatar nameInitials={nameInitials} />
              <span className="Hello">Olá,</span>
              <span className="completeName"> {firstName} {surname}</span>
            </div> }
            {!isAuthenticated && <h1 className="headerModalTitle">Menu</h1>}
          </div>
        </div>
        <div className="headerModalBody">
          <ul className="navListModal " ref={navRef}>
            <li className="navItemModal first">
              <Link to="/" className="navLinkModal activeLink1">
                Carros
              </Link>
            </li>

            <li className="navItemModal">
              <Link to="/product" className="navLinkModal">
                Ofertas
              </Link>
            </li>
            
            {role === "Administrador" && isAuthenticated && (
            <li className="navItemModal">
              <Link to="/admin" className="navLinkModal">
                Admin
              </Link>
            </li>
            )}


            <li className="navItemModal">
              <Link to="/" className="navLinkModal">
                Contato
              </Link>
            </li>

            <li className="navItemModal">
              <Link to="/" className="navLinkModal">
                Duvidas
              </Link>
            </li>

            {isAuthenticated && (
            <li className="navItemModal">
               <Link to="/" className="navLink1Modal">
                    Minhas Reservas
               </Link>
            </li>
            )}
            

            {!isAuthenticated && (
            <li className="navItem1Modal">
               <Link to="/login" className="navLink1Modal">
                    Login
               </Link>
            </li>
            )}

            {!isAuthenticated && (
            <li className="navItem1Modal">
               <Link to="/createAccount" className="navLink1Modal">
                    Criar Conta
               </Link>
            </li>
            )}
           
            
          </ul>
        </div>
        <div className="headerModalFooter">
          {isAuthenticated && <button className="headerButton button" onClick={handleLogout} >Sair da Conta</button>}
        </div>
      </div>
    </div>
  );
}

export default HeaderModal;
