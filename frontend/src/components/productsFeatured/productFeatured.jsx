import React, {useState} from 'react'
import './productFeatured.css'
// import { productFeaturedData } from './productFeaturedData';
// import { GoKebabHorizontal } from 'react-icons/go'
import Image1 from "../../assets/carBMW-M440i.jpg"

const ProductFeatured = ({slides}) => {

     
  return (
     <div className="productfeaturedCarousel">
          <h3 className="productFeaturedTitle">Você também pode gostar de</h3>
          <div className="productFeaturedContainer">

          <div className="productFeaturedCard" >              
          <img src={Image1} alt="Carro" className="productFeturedImg" />          
            <div className="productFeturedInfo">
                <h5 className="productFeturedYear">Ano</h5>
                <h2 className="productFeturedName">Chevrolet Camaro 6.2 SS</h2>
                <h5 className="productFeturedDistance">A 940 m de você -<a href="" className="linkMap"> MOSTRAR NO MAPA</a></h5>
                <div className="productFeturedInfo">
                  <p className="productFeturedDesciption">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean justo elit, volutpat eu ullamcorper ac, ultrices eu erat.<a href="" className="linkdescription"> mais...</a></p>
                  <button className="productFeturedbutton button">Ver mais</button>
                </div>
            </div>
          </div>

          </div>
   </div>
  )
}

export default ProductFeatured