import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import Home from "./pages/home/index";
import LoginScreen from "./pages/login/index";
import CreateAccountScreen from "./pages/createAccount/";
import reportWebVitals from "./reportWebVitals";
import 'bootstrap/dist/css/bootstrap.min.css';
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import 'bootstrap-daterangepicker/daterangepicker.css';
import ProductScreen from "./pages/product";
import RentScreen from "./pages/rent";
import ProductListScreen from "./pages/productList";


const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<App />} />
        <Route index element={<Home />} />
        <Route path="/login" element={<LoginScreen />} />
        <Route path="/createAccount" element={<CreateAccountScreen />} />
        <Route path="/product" element={<ProductScreen />} />
        <Route path="/rent" element={<RentScreen />} />
        <Route path="/productList" element={<ProductListScreen />} />
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
);

reportWebVitals();
