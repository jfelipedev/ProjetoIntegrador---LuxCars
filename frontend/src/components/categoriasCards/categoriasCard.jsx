import React from 'react'
import './categoriasCard.css'
import { categoriasCardData } from './categoriasCardData'

function CategoriasCard() {
  return (
    <div className="categoryCardSection">
      <div className="categoryCardContainer">

        {categoriasCardData.map(({id, title, image, year, color}) => {
          return(
            <div className="categoryCardCard" >
            <div className="box" key={id}>
              
                <img src={image} alt="" className='categoryCardImg'/>
                <h4 className='textCard1'>{title}</h4>
                <h6 className='textCard2'>{year} | {color}</h6>
              
            </div>
      </div>
          )
        })}
      

      </div>
    </div>
  )
}

export default CategoriasCard