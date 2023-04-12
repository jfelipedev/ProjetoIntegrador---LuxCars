import React, { useState, useEffect } from "react";
import "./productInfo.css";
import { Link, useParams, useNavigate } from "react-router-dom";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";


function ProductInfo() {
     const baseUrl = "https://carlux-grupo1.s3.us-east-2.amazonaws.com";
     const [carInfo, setCarInfo] = useState({
          nameCar: null,
          descritpion: null,
          category: null,
          city: null,
          country: null,
          caracteristics: null,
          year: null,
          image: null
     });

     useEffect(() => {
          window.scrollTo(0, 0);
     }, []);

     const { id } = useParams();
     console.log(id);

     const fetchData = async () => {
          const response = await fetch(
               `https://api.carlux.viniciusofagundes.com.br/car/${id}`
          );
          const jsonData = await response.json();
          const response1 = await fetch(
               `https://api.carlux.viniciusofagundes.com.br/city/${jsonData.city.id}`
          );
          const jsonData1 = await response1.json();
          setCarInfo({
               nameCar: jsonData.nameCar,
               descritpion: jsonData.descritpion,
               category: jsonData.category.qualification,
               city: jsonData.city.nameCity,
               country: jsonData1.country,
               caracteristics: jsonData.caracteristics.name,
               year: jsonData.year,
               image: jsonData.images
          });
     };
     useEffect(() => {
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
                              (carInfo.image !== null) ?
                                   carInfo.image.map(({ id, title, url }) => {
                                        return <img key={id} src={baseUrl + url} alt={title} className="slider-img" />
                                   })
                                   : ""
                         }
                    </Slider>
               </div>
               <div className="infoContainer">
                    <div className="productInfoDescription">
                         <h3 className="descript"><span className="textProduct">Sobre: </span>{carInfo.descritpion}</h3>

                         <div className="productInfoCategory">
                              <h3 className="descript"><span className="textProduct">Carro:</span>  {carInfo.category}</h3>

                         </div>

                         <div className="productInfoCaracteris">
                              <h3 className="descript"><span className="textProduct">Especificação: </span>
                                   {carInfo.caracteristics}
                              </h3>
                         </div>

                         <div className="pro0ductInfoYear">
                              <h3 className="descript">
                                   <span className="textProduct">Ano: </span>{carInfo.year}
                              </h3>
                         </div>

                         <div className="pro0ductInfoState">
                              <h3 className="descript">
                                   <span className="textProduct">Disponibilidade: </span>Disponível
                              </h3>
                         </div>
                    </div>
                    <div className="productInfoLocal">
                         <button className="productInfobutton">{carInfo.category}</button>
                         <button className="productInfobutton">{carInfo.city}, {carInfo.country}</button>
                         <button className="productInfobutton">Check-in Check-out</button>
                         <Link to={'/alugue/' + id}>
                              <button className="productInfobutton black">
                                   Prosseguir com Aluguel
                              </button>
                         </Link>
                    </div>
               </div>
          </div>


     );

}

export default ProductInfo;
