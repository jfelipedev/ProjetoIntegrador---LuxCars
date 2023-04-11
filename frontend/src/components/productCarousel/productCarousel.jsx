import React, { useState } from 'react'
import './productCarousel.css'
import { productData } from './productData'
import { GoKebabHorizontal } from 'react-icons/go'

const ProductCarousel = ({ slides }) => {

  const [current, setCurrent] = useState(0)
  const length = slides.length;

  const nextSlide = () => {
    setCurrent(current === length - 1 ? 0 : current + 1)
  }

  const prevSlide = () => {
    setCurrent(current === 0 ? length - 1 : current - 1)
  }

  console.log(current)

  //Para caso n tenhamos Data na api ou json
  if (!Array.isArray(slides) || slides.length <= 0) {
    return null
  }


  return (
    <div className="sectioncarousel">
      <GoKebabHorizontal className='left-arrow' onClick={prevSlide} />
      <GoKebabHorizontal className='right-arrow' onClick={nextSlide} />

      {productData.map((slide, index) => {
        return (
          <div className={index === current ? 'slide active' : 'slide'} key={index}>
            {index === current && (<img src={slide.image} alt="" className='image' />)}
          </div>
        )
      })}

    </div>
  )
}

export default ProductCarousel;