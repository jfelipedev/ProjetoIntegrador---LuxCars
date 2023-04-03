import React, { useState, useEffect } from "react";
import "./styles.css";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import { Link } from 'react-router-dom'

export const getId = (item) => item.id;
function HomeCarrossel() {
  const url = "https://carlux-grupo1.s3.us-east-2.amazonaws.com";
  const [images, setImages] = useState([]);

  useEffect(() => {
    fetch("http://api.carlux.viniciusofagundes.com.br/car/highlight")
      .then((response) => response.json())
      .then((data) => setImages(data));
  }, []);

  const productUrl = 'http://api.carlux.viniciusofagundes.com.br/car/'
  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    autoplay: true,
    autoplaySpeed: 5000,
    slidesToShow: 3,
    slidesToScroll: 1,
    responsive: [
      {
        breakpoint: 1025,
        settings: {
          slidesToShow: 2,
          slidesToScroll: 1,
          infinite: true,
          dots: true,
        },
      },
      {
        breakpoint: 769,
        settings: {
          slidesToShow: 1,
          slidesToScroll: 1,
          initialSlide: 1,
        },
      },
    ],
  };
  
  
  return (
    <>
    <h2>Os mais requisitados</h2>
    <div className="sliderContainer">
    <Slider {...settings}>
          {images.map((item) => {
            const itemId = getId(item); // call the exported function with the item as an argument
            return (
              <div key={itemId}>
                <h3>{item.nameCar}</h3>            
                <Link to={'/produtos'} > <img src={url + item.urlImage} alt="" className="slider-img" /></Link>
              </div> 
            );
          })}
        </Slider>
    </div>    
    </>
  );
  
}
export default HomeCarrossel;

