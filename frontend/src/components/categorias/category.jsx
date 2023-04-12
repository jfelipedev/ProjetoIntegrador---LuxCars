import React, { useState, useEffect } from 'react'
import "./category.css"
import api from '../../services/api'
import CategoriasCard from '../categoriasCards/categoriasCard'



function Category() {

  const [visible, setVisible] = useState(false)
  const [categorys, setCategorys] = useState([])

  const [filtro, setFiltro] = useState()

  useEffect(() => {
    
    api.get("/category")
    
    .then((response) => {
      console.log(response)
      setCategorys(response.data)
      
    })
    .catch((error)=>{
      console.log(error)
      console.log("Deu errado - category")
    })

  }, [])


  return (
    
    <div className='categorySection'>
      <h3 className='categoryTitle'>Buscar por categoria de carro</h3>

      <div className="categoryContainer">

        {categorys.map(({id, urlImage, qualification, numberCars, text }) => {
          return(
          <div className="categoryCard" key={id} onClick={() => {
            setFiltro(id)
            setVisible(true)
          }} >
            <div className="box">
              <div className='contentFront'>
                
                <h4 className='text3'>{qualification}</h4>
                  <p className='text4'>{text}</p>
              </div>
          
               <div className="contentBx">
                <div className="contentBack"> 
                <a href="#motion-point"> <img src={"https://carlux-grupo1.s3.us-east-2.amazonaws.com" + urlImage} alt="" className='img'/></a>
                <h4 className='text1'>{qualification}</h4>
                <h6 className='text2'>{numberCars} Carros</h6>
                </div>
              </div>
            </div>
          </div>
          )
        })}
      </div> 
      {visible ? <CategoriasCard filtro={filtro} /> : null}

    </div>
  )
}

export default Category
