import React, { useState, useEffect } from 'react';
import Slider from 'react-slick';
import './productCarousel.css'
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';



function Carousel() {
  const [images, setImages] = useState([]);

  useEffect(() => {
    fetch('http://api.carlux.viniciusofagundes.com.br/swagger-ui/index.html#/Car/allHighlight')
      .then((response) => response.json())
      .then((data) => setImages(data));
  }, []);

  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 3,
    slidesToScroll:3,
    responsive: [
      {
        breakpoint: 1024,
        settings: {
          slidesToShow: 2,
          slidesToScroll: 2,
          infinite: true,
          dots: true,
        },
      },
      {
        breakpoint: 600,
        settings: {
          slidesToShow: 1,
          slidesToScroll: 1,
          initialSlide: 1,
        },
      },
    ],
  };

  return (
    <Slider {...settings}>
      {images.map((image) => (
        <div key={image.id}>
          <img src={image.url} alt={image.alt} />
        </div>
      ))}
    </Slider>
  );
}

export default Carousel;
