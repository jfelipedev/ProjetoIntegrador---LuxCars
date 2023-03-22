import React, { useState, useEffect } from 'react'
import "./category.css"
//import { categoryData } from './categoryData'
import api from '../../services/api'
import CategoriasCard from '../categoriasCards/categoriasCard'



function Category() {

  const [visible, setVisible] = useState(false)
  const [categorys, setCategorys] = useState([])
  // /car?idCategory=[ID da categoria]

  useEffect(() => {
    let list = []
    api.get("/category")
    .then((response) => {
      response.data.map(({id, qualification, numberCars, urlImage}) => {
        api.get("/category/" + id)
        .then((response) => {
          list.push({
            id: id,
            qualification: qualification,
            urlImage: urlImage,
            numberCars: numberCars,
            text: response.data.description
          })
          setCategorys(list)
        })
      })
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

        {categorys.map(({id, urlImage, qualification, numberCars,  text }) => {
          return(
          <div className="categoryCard" key={id} onClick={() => setVisible(true)} >
            <div className="box">
              <div className='contentFront'>
                <img src={"https://carlux-grupo1.s3.us-east-2.amazonaws.com" + urlImage } alt="" className='img'/>
                <h4 className='text1'>{qualification}</h4>
                <h6 className='text2'>{numberCars} Carros</h6>
              </div>
          
              <div className="contentBx">
                <div className="contentBack">
                  <h4 className='text3'>{qualification}</h4>
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
