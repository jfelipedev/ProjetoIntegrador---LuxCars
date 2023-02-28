import React from "react";
import Cars from "../../components/cars/cars";
import SearchCars from "../../components/searchCars/searchCars";
import Header from "../../components/header/header";
import Footer from "../../components/footer/footer";

function index() {
  return (
    <div>
      <Header />
      <Cars />
      <SearchCars />
      <Footer />
    </div>
  );
}

export default index;
