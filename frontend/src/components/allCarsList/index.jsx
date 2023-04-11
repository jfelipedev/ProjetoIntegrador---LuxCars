import React, { useState, useEffect } from "react";
import AllCarsComp from "../allCars/index";
import api from '../../services/api'
import './styles.css'
import { Link } from 'react-router-dom'


const AllCarsList = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    api.get("/car").then((response) => {
      setProducts(response.data);
    });
  }, []);


  const unique = products.filter(
    (obj, index) =>
    products.findIndex((item) => item.nameCar === obj.nameCar) === index
  );

  return (
    <div className="containerAllCarsList">
    <div className="product-list">
      {unique.map((product) => (
       <Link to={'/produtos/' + product.id}> <AllCarsComp key={product.id} product={product} /> </Link>
      ))}
    </div>
    </div>
  );
};

export default AllCarsList;
