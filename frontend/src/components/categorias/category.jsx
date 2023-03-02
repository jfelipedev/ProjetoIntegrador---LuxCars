import React from 'react'
import "./category.css"
import {categoryData} from "./categoryData"
import Image1 from "../../assets/Car1.jpg"

function Category() {
  return (
    
    <div className='categorySection'>
      <h3 className='categoryTitle'>Buscar por categoria de carro</h3>

      
      <div className="categoryContainer">
        <div className="categoryCard">
          <div className="imgBx">
            <img src={Image1} alt="img" />
            <h4></h4>
            <h6><span>807.105 Carros</span></h6>
          </div>
          <div className="contentBx">
            <div>
              <h4>Conversível</h4>
              <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ratione illo veritatis nihil, deleniti, sit iure dolorum error facilis repudiandae officia, quia doloremque quibusdam quidem maxime veniam necessitatibus hic labore voluptatum?</p>
            </div>
          </div>
        </div>

        <div className="categoryCard">
          <div className="imgBx">
            <img src={Image1} alt="img" />
            <h4></h4>
            <h6><span>807.105 Carros</span></h6>
          </div>
          <div className="contentBx">
            <div>
              <h4>Conversível</h4>
              <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ratione illo veritatis nihil, deleniti, sit iure dolorum error facilis repudiandae officia, quia doloremque quibusdam quidem maxime veniam necessitatibus hic labore voluptatum?</p>
            </div>
          </div>
        </div>

        <div className="categoryCard">
          <div className="imgBx">
            <img src={Image1} alt="img" />
            <h4></h4>
            <h6><span>807.105 Carros</span></h6>
          </div>
          <div className="contentBx">
            <div>
              <h4>Conversível</h4>
              <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ratione illo veritatis nihil, deleniti, sit iure dolorum error facilis repudiandae officia, quia doloremque quibusdam quidem maxime veniam necessitatibus hic labore voluptatum?</p>
            </div>
          </div>
        </div>

        <div className="categoryCard">
          <div className="imgBx">
            <img src={Image1} alt="img" />
            <h4></h4>
            <h6><span>807.105 Carros</span></h6>
          </div>
          <div className="contentBx">
            <div>
              <h4>Conversível</h4>
              <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ratione illo veritatis nihil, deleniti, sit iure dolorum error facilis repudiandae officia, quia doloremque quibusdam quidem maxime veniam necessitatibus hic labore voluptatum?</p>
            </div>
          </div>
        </div>
      </div>

      
       
      
    </div>
  )
}

export default Category
