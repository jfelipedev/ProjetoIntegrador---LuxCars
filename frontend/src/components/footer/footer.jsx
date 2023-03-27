import React, { useState, useEffect } from "react";
import "./footer.css";
import Image1 from "../../assets/logoWhiteBox.png";

function Footer() {
  const [showFooter, setShowFooter] = useState(false);

  useEffect(() => {
    function handleScroll() {
      const scrollPosition =
        window.innerHeight + window.pageYOffset >= document.body.offsetHeight;
      setShowFooter(scrollPosition);
    }

    window.addEventListener("scroll", handleScroll);

    return () => {
      window.removeEventListener("scroll", handleScroll);
    };
  }, []);

  return (
    <div className={`footer ${showFooter ? "show-footer" : ""}`}>
      <img src={Image1} alt="LuxCars" className="brand1" />

      <span className="copyRight">&copy;2023 LuxCars.com LTDA</span>

      <div className="socialMedia">
        <i className="uil uil-facebook-f"></i>
        <i className="uil uil-linkedin"></i>
        <i className="uil uil-twitter"></i>
        <i className="uil uil-instagram"></i>
        <a href="https://gitlab.ctd.academy/ctd/brasil/projeto-integrador-1/0223/turma-5/grupo-1">
          <i className="uil uil-gitlab"></i>
        </a>
      </div>
    </div>
  );
}

export default Footer;
;