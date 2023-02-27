
import React from "react";
import "./App.css";
import Header from "./components/header/header";
import Cars from "./components/cars/cars";
import SearchCars from "./components/searchCars/searchCars";
import Footer from "./components/footer/footer";


import { Outlet } from "react-router-dom";

function App() {
  return (
    <div>
      <Header />
      <Cars />
      <SearchCars />
      <Outlet />
      <Footer />
    </div>
  );
}

export default App;

