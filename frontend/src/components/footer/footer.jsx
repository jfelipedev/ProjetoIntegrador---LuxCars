import React from "react";
import './footer.css'

function Footer() {
     return(
       <div className="footer">
         <div className="footerInfo ">
           <img src="" alt="LuxCars" className="brand1" />

           <h5 className="copyRight">©️2023 LuxCars.com LTDA</h5>

           <div className="socilaMedia">
             <i class="uil uil-facebook-f"></i>
             <i class="uil uil-linkedin"></i>
             <i class="uil uil-twitter"></i>
             <i class="uil uil-instagram"></i>
             <a href="https://gitlab.ctd.academy/ctd/brasil/projeto-integrador-1/0223/turma-5/grupo-1"><i class="uil uil-gitlab"></i></a>
           </div>


         </div>
       </div>
     )
}

export default Footer;