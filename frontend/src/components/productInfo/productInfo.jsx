import React, { useState, useEffect } from "react";
import "./productInfo.css";
import { Link } from "react-router-dom";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

function ProductInfo() {
  const baseUrl = "https://carlux-grupo1.s3.us-east-2.amazonaws.com";
  const [carInfo, setCarInfo] = useState({
    nameCar:null,
    descritpion: null,
    category: null,
    city: null,
    country: null,
    year: null,
    image: null
  });
  const fetchData = async () => {
      const response = await fetch(
        `http://api.carlux.viniciusofagundes.com.br/car/1`
      );
      const jsonData = await response.json();
      const response1 = await fetch(
        `http://api.carlux.viniciusofagundes.com.br/city/${jsonData.city.id}`
      );
      const jsonData1 = await response1.json();
      setCarInfo({
        nameCar: jsonData.nameCar,
        descritpion: jsonData.descritpion,
        category: jsonData.category.qualification,
        city: jsonData.city.nameCity,
        country: jsonData1.country,
        year: jsonData.year,
        image: jsonData.images
      });
    };
    useEffect(()=> {
      fetchData();
    }, []);

    const settings = {
      dots: true,
      infinite: true,
      speed: 500,
      autoplay: true,
      autoplaySpeed: 5000,
      slidesToShow: 2,
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
    <div className="productInfosection">
        <h1>{carInfo.nameCar}</h1>
    
      <div className="sliderContainer">
      <Slider {...settings}>
    {
      (carInfo.image !== null)?
        carInfo.image.map(({id, title, url})=>{
          return <img key={id} src={baseUrl + url} alt={title} className="slider-img"/>
        })
        : ""
    }
    </Slider>
    </div>
    <div className="infoContainer">     
   
      
        <div className="productInfoDescription">
          <h3 className="descript">{carInfo.descritpion}</h3>
          <span className="script"></span>
          <div className="productInfoCategory">
            <h3 className="descript">{carInfo.category}</h3>              
              <span className="textProduct"></span>      
          </div>

          <div className="productInfoCaracteris">
            <h3 className="descript">
              Características <span className="textProduct"></span>{" "}
            </h3>
          </div>

          <div className="pro0ductInfoYear">
            <h3 className="descript">
              Ano <span className="textProduct"></span>{carInfo.year}
            </h3>
          </div>

          <div className="pro0ductInfoState">
            <h3 className="descript">
              Disponibilidade <span className="textProduct">Disponível</span>
            </h3>
          </div>
        </div>
      
     
      

      <div className="productInfoLocal">
          <button className="productInfobutton">{carInfo.category}</button>
          <button className="productInfobutton">{carInfo.city}, {carInfo.country}</button>
          <button className="productInfobutton">Check-in Check-out</button>
          <Link to="/rent">
            <button className="productInfobutton black">
              Prosseguir com Aluguel
            </button>
          </Link>
        </div>
      
      
      
      
      
      
      
      </div>





      {/* <div className="rentSection"> */}

  {/* </div> */}






      </div>
      

  );
}

export default ProductInfo;
