import React from "react";
import './searchCars.css'

function SearchCars() {
     return(
       <div className="section">
         <h1 className="sectionTitle">ALUGUE O CARRO DOS SEUS SONHOS</h1>

         <div className="searchContainer">

           <i class="uil uil-car"></i>
           <button className="futureSelect">Categoria</button>

           <i class="uil uil-location-point"></i>
           <button className="futureSelect">Onde vamos?</button>

           <i class="uil uil-location-point"></i>
           <button className="futureSelect">Check in - Check out</button>
           
           <button className="button button1">BUSCAR</button>

         </div>

       </div>
     )
}

export default SearchCars