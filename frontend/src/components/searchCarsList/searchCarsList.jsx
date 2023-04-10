import React, { useState, useEffect } from 'react';
import "./searchCarsList.css";
import api from '../../services/api';
import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.css';
import { parseISO, isWithinInterval } from 'date-fns';

function SearchCarsList({ selectedCity, selectedCategory, startDate, endDate }) {

  const [filteredCars, setCars] = useState(null);
  const [isLoading, setIsLoading] = useState();

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
        if (carsWithQuantity > 0) {
          setCars(carsWithQuantity);
        }
        //verifica a disponibilidade do carro de acordo com data de inicio e fim
        let carsWithAvailability = [];
        if (startDate && endDate) {
          carsWithAvailability = await Promise.all(
            carsWithQuantity.map(async (car) => {
              const isAvailable = await checkAvailability(car.id);
              return { ...car, isAvailable };
            })
          );
          const availableCars = carsWithAvailability.filter(car => car.isAvailable);
          filteredCars = availableCars.length > 0 ? availableCars : null;
        } else {
          filteredCars = carsWithQuantity;
        }
        if (filteredCars) {
          setCars(filteredCars);
        } else {
          setCars(filteredCars);
        }
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
  }, [selectedCity, selectedCategory, startDate, endDate]);

  console.log(filteredCars);

  useEffect(() => {
    if (isLoading) {
      Swal.fire({
        icon: 'warning',
        title: 'Carregando...',
        allowOutsideClick: false,
        showConfirmButton: false,
      });
    } /*else {
      Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: 'Sua lista de carros foi carregada com sucesso!',
        showConfirmButton: false,
        timer: 1000
      });
    }*/
  }, [isLoading]);

  const checkAvailability = async (carId) => {
    try {
      const response = await api.get(`/car/${carId}/availability`);
      const availabilityList = response.data;
      const isAvailable = availabilityList.every(interval => {
        const [start, end] = interval.map(parseISO);
        return (
          isWithinInterval(parseISO(startDate), { start, end }) &&
          isWithinInterval(parseISO(endDate), { start, end })
        );
      });
      return isAvailable;
    } catch (error) {
      console.error(error);
      return false;
    }
  };

  console.log(isLoading);
  console.log(filteredCars);
  //console.log(filteredCars.length);

  let noCarsModal = null;

  if (!isLoading && filteredCars == null) {
    noCarsModal = (
      Swal.fire({
        icon: 'warning',
        title: 'Não há carros disponíveis para a data selecionada.',
        allowOutsideClick: true,
        showConfirmButton: false,
      })
    );
  }



  return (
    <div className='searchCarSection'>
      <h2>Lista de carros {selectedCity ? `em ${selectedCity}` : ''} {selectedCategory ? `na categoria ${selectedCategory}` : ''} {startDate && endDate ? `disponíveis no período de ${new Date(startDate).toLocaleDateString("pt-BR")} a ${new Date(endDate).toLocaleDateString("pt-BR")}` : ''}</h2>
      {!isLoading && filteredCars != null && filteredCars.length > 0 ? (
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
      ) : (
        <div>noCarsModal</div>
      )}
    </div>
  );

}
export default SearchCarsList;
