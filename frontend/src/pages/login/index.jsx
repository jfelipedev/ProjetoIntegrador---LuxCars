import React from "react";
import Login from "../../components/login/login";
import Header from "../../components/header/header";
import Footer from "../../components/footer/footer";


function LoginScreen() {
  return (
    <div>
      <Header />
      <Login />
      <Footer />  
    </div>
  );
}

export default LoginScreen;