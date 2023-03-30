import React, {useState} from 'react'
import './productFeatured.css'
// import { productFeaturedData } from './productFeaturedData';

import { Data } from '../cars/Data'
import { Carousel } from 'react-responsive-carousel';
import "react-responsive-carousel/lib/styles/carousel.min.css";

const ProductFeatured = ({slides}) => {

     
  return (
     <div className="productfeaturedCarousel">
          <h3 className="productFeaturedTitle">Você também pode gostar de</h3>
          <Carousel className="productFeaturedContainer" showArrows={true} showThumbs={false}>

            
            {Data.map(({id, image, year, distance, linkMap, title, description, linkdescription}) => {
              return(
              <div className="productFeaturedCard " key={id} >              
                <img src={image} alt="Carro" className="productFeturedImg" />          
                <div className="productFeturedInfo">
                    <h5 className="productFeturedYear">{year}</h5>
                    <h2 className="productFeturedName">{title}</h2>
                    <h5 className="productFeturedDistance">{distance} <a href="" className="linkMap"> {linkMap}</a></h5>
                    <div className="productFeturedInfo1">
                      <p className="productFeturedDesciption">{description}<a href="" className="linkdescription"> {linkdescription}</a></p>
                      <button className="productFeturedbutton button">Ver mais</button>
                    </div>
                </div>
              </div>
              )
            })}
          

          </Carousel>
   </div>
  )
}

export default ProductFeatured