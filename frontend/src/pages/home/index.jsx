import React from "react";
import Cars from "../../components/cars/cars";
import SearchCars from "../../components/searchCars/searchCars";
import Header from "../../components/header/header";
import Footer from "../../components/footer/footer";
import Category from "../../components/categorias/category";


function index() {
  return (
    <div className="index">
      <Header /> 
      <SearchCars />
      <Cars />
      <Category />
      <Footer />
    </div>
  );
}

export default index;
