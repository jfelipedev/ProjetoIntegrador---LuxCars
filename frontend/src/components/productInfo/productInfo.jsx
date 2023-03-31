import React, { useState, useEffect } from "react";
import "./productInfo.css";
import { Link } from "react-router-dom";
import api from "../../services/api";

function ProductInfo() {
  const url = "https://carlux-grupo1.s3.us-east-2.amazonaws.com";
  const [carInfo, setCarInfo] = useState({
    nameCar:'',
    descritpion:'',
    category: '',
  });

  useEffect(() => {
    const fetchData = async () => {
      const response = await fetch(
        `http://api.carlux.viniciusofagundes.com.br/car/1`
      );
      const jsonData = await response.json();
      console.log(jsonData.category)
      setCarInfo({
        nameCar: jsonData.nameCar,
        descritpion: jsonData.descritpion,
        category: jsonData.category.qualification,
      });
    };
    fetchData();
  }, []);
console.log(carInfo.category.qualification)
  return (
    <div className="productInfosection">
      {/* <img src={url + carInfo.images.url} alt=""/> */}
      <h1 className="productInfoTitle">{carInfo.nameCar}</h1>
      <div className="ProductInfoContainer container">
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
              Ano <span className="textProduct"></span>{" "}
            </h3>
          </div>

          <div className="pro0ductInfoState">
            <h3 className="descript">
              Disponibilidade <span className="textProduct">Disponível</span>
            </h3>
          </div>
        </div>
        <div className="productInfoLocal">
          <button className="productInfobutton">Conversivel</button>
          <button className="productInfobutton">São Paulo, Brasil</button>
          <button className="productInfobutton">Check-in Check-out</button>
          <Link to="/rent">
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
