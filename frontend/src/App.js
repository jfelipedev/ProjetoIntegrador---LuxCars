
import React from "react"
import './App.css';
import Header from "./components/header/header";
import Cars from "./components/cars/cars"
import Login from "./components/login/login";
import Createaccount from "./components/createAccount/createAccount";

function App() {
  return (
    <div>
      < Header />
      < Cars />
      {/* <Login /> */}
      {/* <Createaccount /> */}
    </div>
  );
}

export default App;
