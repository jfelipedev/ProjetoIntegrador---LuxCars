import React, { useState, useEffect } from "react";
import Slider from "react-slick";
import "./styles.css";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

function HomeCarrossel() {
  const url = "https://carlux-grupo1.s3.us-east-2.amazonaws.com";

  const [images, setImages] = useState([]);

  useEffect(() => {
    fetch("http://api.carlux.viniciusofagundes.com.br/car/highlight")
      .then((response) => response.json())
      .then((data) => setImages(data));
  }, []);

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
    <>
    <h2>Os mais requisitados</h2>
    <div className="sliderContainer">
      <Slider {...settings}>
        {images.map((item) => (
          <div key={item.id}>
            <h3>{item.nameCar}</h3>
            <img src={url + item.urlImage} alt="" className="slider-img" />
          </div>
        ))}
      </Slider>
    </div>
    </>
  );
}

export default HomeCarrossel;
