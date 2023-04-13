import React from "react";
import Login from "../../components/login/login";
import Header from "../../components/header/header";
import Footer from "../../components/footer/footer";
import { useLocation } from 'react-router-dom'

function LoginScreen() {

  const { state } = useLocation();
  console.log(state);

  return (
    <div>
      <Header />
      <Login state={state}/>
      <Footer />  
    </div>
  );
}

export default LoginScreen;