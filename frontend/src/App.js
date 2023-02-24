
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
    <div>
      < Header />
      < Cars />
      {/* <Login /> */}
      {/* <Createaccount /> */}
      < SearchCars />
      < Footer />
    </div>
  );
}

export default App;
