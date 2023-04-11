import React, { useState, useEffect } from "react";
import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.css';
import "./searchCars.css";
import Select from "react-select";
import api from '../../services/api';
import { Link, useNavigate } from 'react-router-dom';
import { DateRangePicker } from 'rsuite';
import "rsuite/dist/rsuite.css";
import ptBR from 'rsuite/locales/pt_BR';
import { CustomProvider } from 'rsuite';
import moment from 'moment';

function SearchCars() {

  const navigate = useNavigate();
  const [categories, setCategories] = useState([]);
  const [cities, setCities] = useState([]);
  const [selectedCity, setSelectedCity] = useState(null);
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [startDate, setStartDate] = useState(null);
  const [endDate, setEndDate] = useState(null);
  const [searchable, setSearchable] = useState(false);

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


  const handleSelectCategory = (event) => {
    setSelectedCategory(event.value);
  };

  const handleSelectCity = (event) => {
    setSelectedCity(event.value);
  }

  useEffect(() => {
    console.log(selectedCity);
    console.log(selectedCategory);
    //console.log(startDate);
  }, [selectedCity, selectedCategory]);

  // Desabilitar datas anteriores a hoje
  const disabledDate = (date) => {
    return moment(date).isBefore(moment().startOf('day')) && !moment(date).isSame(moment().startOf('day'));
  };

  const handleEvent = (dates) => {
    const [start, end] = dates;
    const formattedStartDate = start.toISOString();
    const formattedEndDate = end.toISOString();
    console.log("INICIO " + formattedStartDate + " e FIM " + formattedEndDate);
    setStartDate(formattedStartDate);
    setEndDate(formattedEndDate);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    if (!selectedCategory && !selectedCity && !startDate) {
      Swal.fire({
        icon: 'warning',
        title: 'Por favor, selecione pelo menos uma opção',
        confirmButtonText: 'OK'
      });
      return;
    } else {
      setSearchable(true);
      navigate('/produtos', {
        state: {
          selectedCity,
          selectedCategory,
          startDate,
          endDate,
        },
      });
    }
  };

  return (
    <div className="searchSection">
      <h1 className="sectionTitle">ALUGUE O CARRO DOS SEUS SONHOS</h1>

      <form className="searchContainer search-form " onSubmit={handleSubmit}>
        <div className="dropDown">
          <Select
            options={categories}
            onChange={handleSelectCategory}
            className="select"
            placeholder="Categoria"
          />
        </div>

        <div className="dropDown">
          <Select
            options={cities}
            onChange={handleSelectCity}
            className="select cidade"
            placeholder="Onde Vamos?"
          />
        </div>

        <div className=" dropDown">
          <CustomProvider locale={ptBR}>
            <DateRangePicker className="select" placeholder='Período de reserva'
              disabledDate={disabledDate}
              format="dd/MM/yyyy"
              onChange={handleEvent}
              character=" até "
              startDate={startDate}
              endDate={endDate}
              selectsRange
              selected={startDate}
            />
          </CustomProvider>
        </div>
        <div className="search-button-container">
          <button className="button button1" type='submit'>BUSCAR</button>
        </div>
      </form>

    </div>
  );
}

export default SearchCars;