import React from 'react'
import { useEffect } from 'react'
import { useState } from 'react'
import './categoriasCard.css'
//import { categoriasCardData } from './categoriasCardData'
import api from '../../services/api'


function CategoriasCard({filtro}) {

  const [listCategorys, setListCategorys] = useState([])
  
  useEffect(() => {

    api.get("/car?idCategory=" + filtro).then((response) => {
      setListCategorys(response.data)
      console.log(response)
    })
    .catch(() => {
      setListCategorys([])
    })
  }, [filtro]);

  return (
    <div className="categoryCardSection">
      <div className="categoryCardContainer">

        {listCategorys.map(({id, nameCar, urlImage, year, price}) => {
          return(
            <div className="categoryCardCard" >
            <div className="box" key={id}>
              
                <img src={urlImage} alt="" className='categoryCardImg'/>
                <h4 className='textCard1'>{nameCar}</h4>
                <h6 className='textCard2'>{year} | {price}</h6>
              
            </div>
      </div>
          )
        })}
      

      </div>
    </div>
  )
}

export default CategoriasCard