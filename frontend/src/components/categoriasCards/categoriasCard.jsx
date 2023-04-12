import { useEffect } from 'react'
import { useState } from 'react'
import './categoriasCard.css'
import api from '../../services/api'
import { Link } from 'react-router-dom';


function CategoriasCard({filtro}) {

  const url = "https://carlux-grupo1.s3.us-east-2.amazonaws.com";
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
console.log (listCategorys)
const unique = listCategorys.filter(
  (obj, index) =>
  listCategorys.findIndex((item) => item.nameCar === obj.nameCar) === index
);
  return (
    <>
        <h2 id="motion-point">Estes são os resultados</h2>
    <div className="categoryCardSection">
      <div className="categoryCardContainer">

        {unique.map(({id, nameCar, urlImage, year, price}) => {
          return(
            
            <div className="categoryCardCard"  id="categoryCardCard">
            <div className="box" key={id}>
              

               <Link to={'/produtos/' + id} ><img src={url + urlImage} alt="" className='categoryCardImg'/></Link>

                <h4 className='textCard1'>{nameCar}</h4>
                <h6 className='textCard2'>{year} | {price}</h6>
              
            </div>
      </div>
          )
        })}
      

      </div>
    </div>
    </>
  )
}

export default CategoriasCard