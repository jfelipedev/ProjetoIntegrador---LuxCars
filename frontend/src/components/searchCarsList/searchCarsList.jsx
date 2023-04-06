import React, { useState, useEffect } from 'react';
import "./searchCarsList.css";
import api from '../../services/api';

function SearchCarsList({ selectedCity }) {

  console.log(selectedCity);
  const [car, setCar] = useState(null);
  const [filteredCars, setCars] = useState(null);
  

  useEffect(() => {
    async function fetchData() {
      try {
        const response = await api.get(`/car`);
        const filteredCars = response.data.filter(car => car.city.nameCity === selectedCity);
        setCars(filteredCars);        
      } catch (error) {
        console.error(error);
      }
    }
    fetchData();
  }, [selectedCity]);

    console.log(filteredCars);

  return (
  <div className='searchCarSection'>
    <div>
      {filteredCars ? (
      <>
      <h2>Lista de carros em {selectedCity} </h2>
      <div className="cards-container">
        {filteredCars.map((car) => (
        <div key={car.id} className="card">
          <img src={`https://carlux-grupo1.s3.us-east-2.amazonaws.com${car.urlImage}`} alt={car.nameCar} />
          {car.highlight ? <p className="highlight">Destaque da semana!</p> : null}
          <h3 className="name">{car.nameCar}</h3>
          <p className="description">{car.description}</p>
          <p className="price">Preço: R$ {car.price.toFixed(2)}</p>
          <p className="year">Ano: {car.year}</p>
          <button className="button-reserva" /*onClick={() => </div>navigate(`/rent/${car.id}`)}</div>*/>Reservar agora</button>
          </div>
        ))}
        </div>
        </>
      ) : (
        <p>Carregando...</p>
      )}
    </div>
  </div>
  );
}

export default SearchCarsList;
