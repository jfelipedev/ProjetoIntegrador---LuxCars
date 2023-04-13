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
import RentScreen from "./pages/rent/index";
import ProductListScreen from "./pages/productList";
import Faq from './pages/faq/index'
import Contact from './pages/contact/index'
import RentConfirmScreen from "./pages/rentConfirm";
import AllCarsScreen from './pages/allCars/index'




const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route exact path="/" element={<App />} />
        <Route index element={<Home />} />

        <Route path="/alugue/:id" element={<RentScreen />} />
        <Route path="/aluguel-confirmado/:id" element={<RentConfirmScreen />} />
        <Route path="/entrar" element={<LoginScreen />} />
        <Route path="/produtos" element={<AllCarsScreen />} />
        <Route path="/lista-produtos" element={<ProductListScreen />} />
        <Route path="/criar-conta" element={<CreateAccountScreen />} />
        <Route path="/produtos/:id" element={<ProductScreen />} />
        <Route path="/faq" element={<Faq />} />
        <Route path="/contato" element={<Contact />} />

      </Routes>
    </BrowserRouter>
  </React.StrictMode>
);

reportWebVitals();
