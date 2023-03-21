import { useEffect, useRef, useState } from 'react';
import React from 'react';
import './header.css'
import { Link } from 'react-router-dom'
import Image1 from '../../assets/logoWhiteLetters.png'
//import Image2 from '../../assets/logoWhiteBox.png'
import { getToken } from '../../services/auth';
import HeaderModal from '../headerModal/headerModal';



function Header() {
  const navRef = useRef();

  const [visible, setVisible] = useState(false)

  // const showNav = () => {
  //   navRef.current.classList.toggle("responsive")
  // }

  const [token, setToken] = useState(getToken())

  useEffect(() => {
    const handleStorage = () => {
      setToken(getToken())
    }

    window.addEventListener('storage', handleStorage())
    return () => window.removeEventListener('storage', handleStorage())
  }, [])



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

          {!token && <ul className="navList1 grid">
            <li className="navItem1">
              <Link to="/login" className="navLink1">Login</Link>
            </li>

            <li className="navItem1">
              <Link to="/" className="navLink1">Minhas Reservas</Link>
            </li>
          </ul>}
          {token && <h1>Ola</h1>}
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
