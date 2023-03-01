
import React from "react"
import './App.css';
import Header from "./components/header/header";
import Cars from "./components/cars/cars"
import Login from "./components/login/login";
import Createaccount from "./components/createAccount/createAccount";
import SearchCars from "./components/searchCars/searchCars";
import Footer from "./components/footer/footer";

function App() {
  return (
    <>
      < Header />
      <div className="home">
        {/* < Cars /> */}
        <Login />
        {/* <Createaccount /> */}
        {/* < SearchCars /> */}
      </div>
      < Footer />
    </>
  );
}

export default App;


