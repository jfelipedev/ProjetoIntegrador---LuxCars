import React, { useState, useEffect } from "react";
import "./searchCars.css";
import Select from "react-select";
import api from '../../services/api';
import { Link } from 'react-router-dom';




import { DateRangePicker } from 'rsuite';
import "rsuite/dist/rsuite.css";
import ptBR from 'rsuite/locales/pt_BR';
import { CustomProvider } from 'rsuite';
import moment from 'moment';

function SearchCars() {

  const [categories, setCategories] = useState([]);
  const [cities, setCities] = useState([]);

  useEffect(() => {
    api.get("/category")
      .then((response) => {
        let list = [];
        response.data.map((item) => {
          list.push({
            label: item.qualification,
            value: item.qualification
          })
        })
        setCategories(list)
        console.log("Deu certo - category")
      })
      .catch((error) => {
        console.log(error)
        console.log("Deu errado - category")
      })

    api.get("/city")
      .then((response) => {
        let list = [];
        response.data.map((item) => {
          list.push({
            label: item.nameCity,
            value: item.nameCity
          })
        })
        setCities(list)
        console.log("Deu certo - city")
      })
      .catch((error) => {
        console.log(error)
        console.log("Deu errado - city")
      })
  }, [])


  const handleSelectChange = (event) => {
    console.log(event);
  };

    // Desabilitar datas anteriores a hoje
  const disabledDate = (date) => {
    return moment(date).isBefore(moment().startOf('day')) && !moment(date).isSame(moment().startOf('day'));
  };
  const handleEvent = (event, picker) => {
    console.log(picker.startDate);
  };
  const handleCallback = (start, end, label) => {
    console.log(start, end, label);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
  };


  return (
    <div className="searchSection">
      <h1 className="sectionTitle">ALUGUE O CARRO DOS SEUS SONHOS</h1>

      <form className="searchContainer " onSubmit={handleSubmit}>
        <div className="dropDown">
          <Select
            options={categories}
            onChange={handleSelectChange}
            className="select"
            placeholder="Categoria"
          />
        </div>

        <div className="dropDown">
          <Select
            options={cities}
            onChange={handleSelectChange}
            className="select"
            placeholder="Onde Vamos?"
          />
        </div>

        <div className=" drop">

          <CustomProvider locale={ptBR}>
          <DateRangePicker className='select' placeholder='Período de reserva' 
          disabledDate={disabledDate}
          format="dd/MM/yyyy"
          onEvent={handleEvent}
          onCallback={handleCallback}
          character=" até "
          label="Datas"
          />
          </CustomProvider>
        </div>

        <Link to="/produtos" className="buttonLink"><button className="button button1" type='submit'>BUSCAR</button></Link>
        
        
      </form>

    </div>
  );
}

export default SearchCars;