import { useEffect, useRef, useState } from 'react';
import Avatar from '../avatar/avatar';
import './header.css'
import { Link, Navigate } from 'react-router-dom'
import Image1 from '../../assets/logoWhiteLetters.png'
import { getTokenRole, getToken, isAuthenticated, getTokenName, getTokenSurname, logout } from '../../services/auth';
import HeaderModal from '../headerModal/headerModal';
import { useNavigate } from "react-router-dom";


function Header() {

  const navRef = useRef();
  const navigate = useNavigate();
  const [visible, setVisible] = useState(false);
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [token, setToken] = useState(getToken());
  const [firstName, setFirstaName] = useState(getTokenName());
  const [surname, setSurname] = useState(getTokenSurname());
  const [role, setRole] = useState(getTokenRole());
  const [nameInitials, setInitials] = useState("");


  useEffect(() => {
    const handleStorage = () => {
      setToken(getToken())
    }
    window.addEventListener('storage', handleStorage())
    return () => window.removeEventListener('storage', handleStorage())
  }, [])


  useEffect(() => {
    setIsAuthenticated(!!token);
    if (isAuthenticated) {
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


  return (
    <header className="header">
      <nav className="nav " >

        <a href="/"><img src={Image1} width="100" height="95" alt="LuxCars" className="brand" /></a>

        <ul className="navList" ref={navRef}>

        {role === "Administrador" && isAuthenticated && (
            <li className="navItem">
              <Link to="/admin" className="navLink">
                Painel Administrativo
              </Link>
            </li>
          )}

          <li className="navItem">
            <Link to="/" className="navLink">Início</Link>
          </li>

          <li className="navItem">
            <Link to="/produtos" className="navLink">Carros</Link>
          </li>

          <li className="navItem">
            <Link to="/contato" className="navLink">Contato</Link>
          </li>

          <li className="navItem">
            <Link to="/faq" className="navLink">FAQ</Link>
          </li>

          {!isAuthenticated && <ul className="navList1 grid">
            <li className="navItem1">
              <Link to="/entrar" className="navLink1">Login</Link>
            </li>
            <li className="navItem1">
              <Link to="/criar-conta" className="navLink1">Cadastrar</Link>
            </li>
          </ul>}
          {isAuthenticated && (
            <div className="user-info"><div className="gretting">Olá, {firstName} {surname}</div><Avatar nameInitials={nameInitials} onLogout={handleLogout} />
            </div>)}
        </ul>

        <div className="navToggler">
          <i class="uil uil-align-center-alt" onClick={() => setVisible(true)}></i>
        </div>

      </nav>
      {visible ? <HeaderModal onClose={() => setVisible(false)} /> : null}
    </header >
  )
}

export default Header
