
import React, {Component} from 'react';
import { useState } from 'react';
import './searchCars.css'
import Select from "react-select";

import DateRangePicker from 'react-bootstrap-daterangepicker';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-daterangepicker/daterangepicker.css';
import { event } from 'jquery';








const suppliers = [
  {label: "Conversivel", value: "Conversivel"},
  {label: "Coupé", value: "Coupé"},
  {label: "Esportivo", value: "Esportiv"},
  {label: "Sedan", value: "Sedan"},
] 

const DBsuppliers = [
  {label: "Argentina", value: "Conversivel"},
  {label: "Angola", value: "Coupé"},
  {label: "Brasil", value: "Esportiv"},
  {label: "Paraguai", value: "Sedan"},
] 







function SearchCars() {


  const handleSelectChange = (event) => {
    console.log(event)
  }

  const handleEvent = (event, picker) => {
    console.log(picker.startDate);
  }
  const handleCallback = (start, end, label) => {
    console.log(start, end, label);
  }



  const handleSubmit = (event) => {
    event.preventDefault();
  }



  return (
    <div className="searchSection">
      <h1 className="sectionTitle">ALUGUE O CARRO DOS SEUS SONHOS</h1>

      <div className="searchContainer ">
        <div className="dropDown">
          <Select 
            options={suppliers}
            onChange= {handleSelectChange}
            className="select"
            placeholder="Categoria"
          />
            
        </div>

        <div className="dropDown">
          <Select 
            options={DBsuppliers}
            onChange= {handleSelectChange}
            className="select"
            placeholder="Onde Vamos?"
          />
          
        </div>

        <div className=" drop">
          <DateRangePicker 
            onEvent={handleEvent} onCallback={handleCallback}
            
          > 
            <input  className="form-control" />
          </DateRangePicker>
          

        </div>

        <button className="button button1" type='submit' onSubmit={handleSubmit}>BUSCAR</button>
      </div>
    </div>
  );
}

export default SearchCars;
