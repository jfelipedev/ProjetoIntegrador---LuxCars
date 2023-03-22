import React, {useState} from 'react'
import './productFeatured.css'
// import { productFeaturedData } from './productFeaturedData';
import { GoKebabHorizontal } from 'react-icons/go'
import Image1 from "../../assets/carBMW-M440i.jpg"
import { Data } from '../cars/Data'

const ProductFeatured = ({slides}) => {

  
  

 
  
     
  return (
     <div className="productfeaturedCarousel">
          <h3 className="productFeaturedTitle">Você também pode gostar de</h3>
          <div className="productFeaturedContainer">

            <GoKebabHorizontal className='left-Goke'/>
            <GoKebabHorizontal className='right-Goke'/>
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
          

          </div>
   </div>
  )
}

export default ProductFeatured