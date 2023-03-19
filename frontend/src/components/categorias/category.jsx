import React, { useState } from 'react'
import "./category.css"
import { categoryData } from './categoryData'
import CategoriasCard from '../categoriasCards/categoriasCard'


function Category() {

  
  const [visible, setVisible] = useState(false)
  
  return (
    
    
    <div className='categorySection'>
      <h3 className='categoryTitle'>Buscar por categoria de carro</h3>

      <div className="categoryContainer">

        {categoryData.map(({id, image, title, quantity,  text }) => {
          return(
            <div className="categoryCard" key={id} onClick={() => setVisible(true)} >
            <div className="box">
              <div className='contentFront'>
                <img src={image} alt="" className='img'/>
                <h4 className='text1'>{title}</h4>
                <h6 className='text2'>{quantity} Carros</h6>
              </div>
          
              <div className="contentBx">
                <div className="contentBack">
                  <h4 className='text3'>{title}</h4>
                  <p className='text4'>{text}</p>
                </div>
              </div>
            </div>
          </div>
          )
        })}
      </div> 
      {visible ? <CategoriasCard /> : null}

    </div>
  )
}

export default Category
