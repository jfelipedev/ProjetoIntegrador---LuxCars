import React from "react";
import "./headerModal.css";
import { Link } from "react-router-dom";
import { useEffect, useRef, useState } from "react";
import { getToken } from "../../services/auth";

const HeaderModal= ({id="headerModal", onClose = () => {}}) => {

  const navRef = useRef();
 
  const [token, setToken] = useState(getToken());

  useEffect(() => {
    const handleStorage = () => {
      setToken(getToken());
    };

    window.addEventListener("storage", handleStorage());
    return () => window.removeEventListener("storage", handleStorage());
  }, []);

  const handleOutsideClick = (e) => {
     if(e.target.id === id) onClose();
  }

  return (
    <div id={id} className="headerModal" onClick={handleOutsideClick}>
      <div className="headerModalcontent">
        <div className="headerModalHeader">
          <i class="uil uil-times navClose" onClick={onClose}></i>
          <div className="headerModalUser">
            {token && <h1 className="headerModalTitleUser">Ola</h1>}
            {!token && <h1 className="headerModalTitle">Menu</h1>}
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

            {token && (
            <li className="navItemModal">
               <Link to="/" className="navLink1Modal">
                    Minhas Reservas
               </Link>
            </li>
            )}
            

            {!token && (
            <li className="navItem1Modal">
               <Link to="/login" className="navLink1Modal">
                    Login
               </Link>
            </li>
            )}

            {!token && (
            <li className="navItem1Modal">
               <Link to="/createAccount" className="navLink1Modal">
                    Criar Conta
               </Link>
            </li>
            )}
           
            
          </ul>
        </div>
        <div className="headerModalFooter">
          {token && <button className="headerButton button" >Sair da Conta</button>}
        </div>
      </div>
    </div>
  );
}

export default HeaderModal;
