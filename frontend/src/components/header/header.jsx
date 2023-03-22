import { useEffect, useRef, useState } from 'react';
import { login } from '../../services/auth';
import React from 'react';
import Avatar from '../avatar/avatar';
import './header.css'
import { Link } from 'react-router-dom'
import Image1 from '../../assets/logoWhiteLetters.png'
//import Image2 from '../../assets/logoWhiteBox.png'
import { getToken, isAuthenticated} from '../../services/auth';
import HeaderModal from '../headerModal/headerModal';



function Header() {
  
  const navRef = useRef();

  const [visible, setVisible] = useState(false);
  
  const [isAuthenticated, setIsAuthenticated] = useState(false); 
  
  const user = { name: "Usuário autenticado" };
  
  const initials = user.name
    .split(" ")
    .map((word) => word[0])
    .slice(0, 2)
    .join("");


  const [token, setToken] = useState(getToken())
 

  useEffect(() => {
	  const handleStorage = () => {
		setToken(getToken())
	}

	window.addEventListener('storage', handleStorage())
	return () => window.removeEventListener('storage', handleStorage())
}, [])

useEffect(() => { 
  setIsAuthenticated(!!token); 
}, [token]);

  return (
    <header className="header">
      <nav className="nav " >
      <a href="/"><img src={Image1} width="100" height="95" alt="LuxCars" className="brand" /></a>

        <ul className="navList" ref={navRef}>
          <li className="navItem">
            <Link to="/" className="navLink activeLink">Carros</Link>
          </li>

          <li className="navItem">
            <Link to="/product" className="navLink">Ofertas</Link>
          </li>

          <li className="navItem">
            <Link to="/" className="navLink">Contato</Link>
          </li>

          <li className="navItem">
            <Link to="/" className="navLink">Duvidas</Link>
          </li>

          {!isAuthenticated &&  <ul className="navList1 grid">
            <li className="navItem1">
              <Link to="/login" className="navLink1">Login</Link>
            </li>

            <li className="navItem1">
              <Link to="/" className="navLink1">Minhas Reservas</Link>
            </li>
          </ul>}
          {isAuthenticated && 
          <div className="user-info">
          <h1>Ola</h1>
          <span className="user-name">{user.name}</span>
          <Avatar initials={initials} />
        </div>}
            
        </ul>
          
          <div className="navToggler">
          <i class="uil uil-align-center-alt" onClick={() => setVisible(true)}></i>
        </div>
      
      </nav>
      {visible ? <HeaderModal onClose={() => setVisible(false)} /> : null} 
    </header>
  )
}

export default Header
