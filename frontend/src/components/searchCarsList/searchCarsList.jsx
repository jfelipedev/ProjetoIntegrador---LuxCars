import React, { useState, useEffect } from 'react';
import "./searchCarsList.css";
import api from '../../services/api';
import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.css';

function SearchCarsList({ selectedCity, selectedCategory }) {

  const [car, setCar] = useState(null);
  const [filteredCars, setCars] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    async function fetchData() {
      try {
        setIsLoading(false);
        const response = await api.get(`/car`);
        let filteredCars = response.data;
        if (selectedCity) {
          filteredCars = filteredCars.filter(car => car.city.nameCity === selectedCity);
        }
        if (selectedCategory) {
          filteredCars = filteredCars.filter(car => car.category.qualification === selectedCategory);
        }

        // Agrupa os carros e soma a quantidade
        const carsWithQuantity = filteredCars.reduce((acc, car) => {
          const existingCarIndex = acc.findIndex(c => c.nameCar === car.nameCar);
          if (existingCarIndex === -1) {
            acc.push({ ...car, quantity: 1 });
          } else {
            acc[existingCarIndex].quantity += 1;
          }
          return acc;
        }, []);

        setCars(carsWithQuantity);
      } catch (error) {
        setIsLoading(false);
        console.error(error);
      }
    }

    const timeoutId = setTimeout(() => {

      if (isLoading) {
        Swal.fire({
          icon: 'warning',
          title: 'Carregando lista',
          allowOutsideClick: false,
          showConfirmButton: false,
        });
      }
    }, 5000);
    fetchData();
    return () => clearTimeout(timeoutId);
  }, [selectedCity, selectedCategory]);

  useEffect(() => {
    if (isLoading) {
      Swal.fire({
        icon: 'warning',
        title: 'Carregando...',
        allowOutsideClick: false,
        showConfirmButton: false,
      });
    } else {
      Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: 'Sua lista de carros foi carregada com sucesso!',
        showConfirmButton: false,
        timer: 1000
      });
    }
  }, [isLoading]);

  return (
    <div className='searchCarSection'>
      <h2>Lista de carros {selectedCity ? `em ${selectedCity}` : `na categoria ${selectedCategory}`} </h2>
      <div className="cards-container">
        {filteredCars?.map((car) => (
          <div key={car.id} className="card">
            <img src={`https://carlux-grupo1.s3.us-east-2.amazonaws.com${car.urlImage}`} alt={car.nameCar} />
            {car.highlight ? <p className="highlight">Destaque da semana!</p> : null}
            <h3 className="name">{car.nameCar}</h3>
            <p className="description">{car.description}</p>
            <p className="price">Preço: R$ {car.price.toFixed(2)}</p>
            <p className="year">Ano: {car.year}</p>
            <p className="quantity">Quantidade: {car.quantity}</p>
            <button className="button-reserva" /*onClick={() => </div>navigate(`/rent/${car.id}`)}</div>*/>Reservar agora</button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default SearchCarsList;

