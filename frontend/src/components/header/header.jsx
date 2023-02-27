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
            <a href="#" className="navLink activeLink">Carros</a>
          </li>

          <li className="navItem">
            <a href="#" className="navLink">Ofertas</a>
          </li>

          <li className="navItem">
            <a href="#" className="navLink">Contato</a>
          </li>

          <li className="navItem">
            <a href="#" className="navLink">Duvidas</a>
          </li>

          <ul className="navList1 grid">
            <li className="navItem1">
              <a href="#" className="navLink1">Login</a>
            </li>

            <li className="navItem1">
              <a href="#" className="navLink1">Minhas Reservas</a>
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

