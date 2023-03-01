import React from "react";
import "./App.css";
import Header from "./components/header/header";
import Footer from "./components/footer/footer";
import { Outlet } from "react-router-dom";
 import Cars from './components/cars/cars';
 import SearchCars from './components/searchCars/searchCars'

function App() {
  return (
    <div>
      <Header />
      <Cars />
      <>
        <Outlet />
      </>
      <SearchCars />
      <Footer />
    </div>
  );
}

export default App;
