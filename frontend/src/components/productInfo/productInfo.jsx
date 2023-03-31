import React, { useState, useEffect } from "react";
import "./productInfo.css";
import { Link } from "react-router-dom";

function ProductInfo() {
  const [car, setCar] = useState();
  useEffect(() => {
    fetch("http://api.carlux.viniciusofagundes.com.br/car/1")
      .then((response) => response.json())
      .then((data) => setCar(data));
  }, []);

  return (
    <>
      {car.map((item) => (
        <div className="productInfosection">
          <h1 className="productInfoTitle"></h1>
          <div className="ProductInfoContainer container">
            <div className="productInfoDescription">
              <h3 className="descript">Descrição</h3>
              <span className="script"></span>
              <div className="productInfoCategory">
                <h3 className="descript">
                  Categoria <span className="textProduct">Conversível</span>
                </h3>
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
                  Disponibilidade{" "}
                  <span className="textProduct">Disponível</span>
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
      ))}
    </>
  );
}

export default ProductInfo;
