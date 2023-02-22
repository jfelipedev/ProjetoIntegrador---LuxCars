import {useState} from "react";

import React from "react";
import './cars.css'
import { Data } from "./Data";




function Cars() {
     return(
       <section className="section">
         <h2 className="sectionTitle">Encontre o carro dos seus sonhos para alugar</h2>

         <div className="carsContainer ">
           {Data.map(({id, image, year, distance, title, description}) => {
            return(
              <div className="carsCard" key={id}>
                
                <img src={image} alt="" className="carsImg" />
                

                
                <h5 className="carsYear">{year}</h5>
                <h2 className="carsName">{title}</h2>
                <h5 className="carsDistance">{distance}</h5>
                <p className="carsDesciption">{description}</p>
                <button className="seeMore button">Ver mais</button>
                
              </div>
            )
           })}
         </div>
       </section>
     )
}

 export default Cars



