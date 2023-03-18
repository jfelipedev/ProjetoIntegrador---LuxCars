import React from 'react'
import './productInfo.css'

function ProductInfo() {
  return (
    <div className='productInfosection'>
     <h1 className='productInfoTitle'>Ferrari Califórnia T</h1>

     <div className="ProductInfoContainer container">
          <div className="productInfoDescription">
               <h3 className='descript'>Descrição</h3>
               <span className='script'>Lorem ipsum dolor sit amet consectetur adipisicing elit. Consectetur quis accusantium minus. Asperiores provident at necessitatibus saepe vel alias obcaecati itaque esse blanditiis! Amet exercitationem sequi, temporibus deserunt accusantium repellendus.</span>
               <div className="productInfoCategory">
                    <h3 className='descript'>Categoria <span className='textProduct'>Conversível</span></h3>
               </div>

               <div className="productInfoCaracteris">
                    <h3 className='descript'>Características <span className='textProduct'>Lorem ipsum dolor sit amet consectetur adipisicing elit. Suscipit adipisci praesentium numquam odio. Labore ex architecto.</span> </h3>
               </div>

               <div className="pro0ductInfoYear">
                    <h3 className='descript'>Ano <span className='textProduct'>2014</span> </h3>
               </div>

               <div className="pro0ductInfoState">
                    <h3 className='descript'>Disponibilidade <span className='textProduct'>Disponível</span></h3>
               </div>
          </div>

          <div className="productInfoLocal">
               <button className='productInfobutton'>Conversivel</button>
               <button className='productInfobutton'>São Paulo, Brasil</button>
               <button className='productInfobutton'>Check-in Check-out</button>
               <button className='productInfobutton black'>Prosseguir com Aluguel</button>

          </div>
     </div>
    </div>
  )
}

export default ProductInfo