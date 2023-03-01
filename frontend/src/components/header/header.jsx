import { useRef } from 'react';
import React from 'react';
import './header.css'
import { Link } from 'react-router-dom'


function Header() {
  const navRef = useRef();

  const showNav = () => {
    navRef.current.classList.toggle("responsive")
  }

  return (
    <header className="header">
      <nav className="nav " >
        <img src="" alt="LuxCars" className="brand" />

        
        <ul className="navList grid" ref={navRef}>
          <li className="navItem">
            <Link to="/" className="navLink activeLink">Carros</Link>
          </li>

          <li className="navItem">
            <Link to="/" className="navLink">Ofertas</Link>
          </li>

          <li className="navItem">
            <Link to="/" className="navLink">Contato</Link>
          </li>

          <li className="navItem">
            <Link to="/" className="navLink">Duvidas</Link>
          </li>

          <ul className="navList1 grid">
            <li className="navItem1">
              <Link to="/login" className="navLink1">Login</Link>
            </li>

            <li className="navItem1">
              <Link to="/" className="navLink1">Minhas Reservas</Link>
            </li>
          </ul>

          <i class="uil uil-times navClose" onClick={showNav}></i>
        </ul>

        <div className="navToggler" onClick={showNav}>
          <i class="uil uil-align-center-alt"></i>
        </div>

      </nav>
    </header>
  )
}

export default Header
