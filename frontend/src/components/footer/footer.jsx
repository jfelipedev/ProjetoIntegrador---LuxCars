import React, { useState, useEffect } from "react";
import "./footer.css";
import Image1 from "../../assets/logoWhiteLetters.png";
import { Link } from 'react-router-dom'

function Footer() {
  const year = new Date();
  return (
    <div className="wrapper">
      <div className="footer">
        <div className="top">
          <div className="item">
            <h1>Categorias</h1>
            <span>Conversível</span>
            <span>Sedan</span>
            <span>SUV</span>
            <span>Coupé</span>
          </div>
          <div className="item">
            <h1>Links</h1>
            <a href="/"> <span>Inicio</span></a>
            <Link to="/produtos"> <span>Carros</span></Link>
            <Link to="/contato"> <span>Contato</span></Link>
            <Link to="/faq"> <span>FAQ</span></Link>
          </div>

          <div className="item">
            <h1>Sobre A LuxCars</h1>
            <span>
              Desde 2006 no mercado, a LuxCars conta com a frota mais nova do
              Brasil e dispõe de lojas nas principais cidades do país. Criada em
              2015 para administrar as operações de Aluguel de Carros e de
              Gestão de Terceirização de Frotas de veículos leves.
            </span>
          </div>
          <div className="item">
            <h1>Contato</h1>
            <span>
              <b>Central de Reservas:</b> 0800 606 8686 <br></br>
              <b>Assistência 24h:</b> 0800 702 8787<br></br>
              <b>Central de Relacionamento:</b> 0800 749 0029<br></br>
            </span>
          </div>
        </div>

        <div className="bottom">
          <div className="bottom-content">
            <span className="logo">
              <a href="/">
                <img
                  src={Image1}
                  width="100"
                  height="95"
                  alt="LuxCars"
                  className="brand"
                />
              </a>
            </span>
            <span className="copyright">
              ©️{year.getFullYear()} LuxCars.com LTDA
            </span>
            <span className="social">
              <i class="uil uil-facebook-f"></i>
              <i class="uil uil-linkedin"></i>
              <i class="uil uil-twitter"></i>
              <i class="uil uil-instagram"></i>
              <a href="https://gitlab.ctd.academy/ctd/brasil/projeto-integrador-1/0223/turma-5/grupo-1">
                <i class="uil uil-gitlab"></i>
              </a>
            </span>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Footer;
