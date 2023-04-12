import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import "./searchCarsList.css";
import api from '../../services/api';
import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.css';
import { parseISO, isWithinInterval } from 'date-fns';

function SearchCarsList({ selectedCity, selectedCategory, startDate, endDate }) {

  const [filteredCars, setCars] = useState([]);
  const [isLoading, setIsLoading] = useState();
  const [showNoCarsModal, setShowNoCarsModal] = useState(false);
  const navigate = useNavigate();


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



  useEffect(() => {
    async function fetchData() {
      try {
        setIsLoading(false);
        const response = await api.get(`/car`);
        let filteredCars = response.data;
        if (selectedCity) {
          filteredCars = filteredCars.filter(car => car.city.nameCity === selectedCity);
          console.log(filteredCars + "cidade selecionada");
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
        if (carsWithQuantity.length > 0) {
          setCars(carsWithQuantity);
        }
        //verifica a disponibilidade do carro de acordo com data de inicio e fim
        let availableCars = carsWithQuantity;
        if (startDate && endDate) {
          availableCars = await Promise.all(
            carsWithQuantity.map(async (car) => {
              const isAvailable = await checkAvailability(car.id);
              return { ...car, isAvailable };
            })
          ).then(carsWithAvailability => carsWithAvailability.filter(car => car.isAvailable));
        }
        if (availableCars.length > 0) {
          setCars(availableCars);
          console.log(availableCars + "carros available")
        } else {
          console.log(availableCars + "carros no else de available")
          setCars(null);
          setIsLoading(true);
          setShowNoCarsModal(true);
        }
      } catch (error) {
        console.error(error);
      }
    }
    fetchData();
  }, [selectedCity, selectedCategory, startDate, endDate]);

  console.log(filteredCars);

  useEffect(() => {
    if (isLoading) {
      Swal.fire({
        icon: 'warning',
        title: 'Carros não disponíveis. Por favor, escolha outros filtros/datas para busca',
        allowOutsideClick: true,
        showConfirmButton: true,
        confirmButtonText: 'Voltar',
      }).then((result) => {
        if (result.isConfirmed) {
          navigate('/');
        }
      });
    }
  }, [isLoading]);


  useEffect(() => {
    if (filteredCars == null) {
      setShowNoCarsModal(true);
      console.log(filteredCars + "true pro setShowNoCarsModal");
    } else {
      setShowNoCarsModal(false);
      console.log(filteredCars + "false pro setShowNoCarsModal");
    }
  }, [filteredCars]);

  /*useEffect(() => {
    if (filteredCars == null) {
      setShowNoCarsModal(true);
      console.log(filteredCars + "true pro setShowNoCarsModal");
    } else {
      setShowNoCarsModal(false);
      console.log(filteredCars + "false pro setShowNoCarsModal");
    }
  }, [filteredCars]);*/

  const noCarsModal = (
    <div className="no-cars-modal">
      <></>
    </div>
  );


  return (
    <div className='searchCarSection'>
      <h2>Lista de carros {selectedCity ? `em ${selectedCity}` : ''} {selectedCategory ? `na categoria ${selectedCategory}` : ''} {startDate && endDate ? `disponíveis no período de ${new Date(startDate).toLocaleDateString("pt-BR")} a ${new Date(endDate).toLocaleDateString("pt-BR")}` : ''}</h2>
      {!isLoading && filteredCars != null && filteredCars.length > 0 ? (
        <div className="cards-container">
          {filteredCars?.map((car) => (
            (
              <div key={car.id} className="card">
                <img src={`https://carlux-grupo1.s3.us-east-2.amazonaws.com${car.urlImage}`} alt={car.nameCar} />
                {car.highlight ? <p className="highlight">Destaque da semana!</p> : null}
                <h3 className="name">{car.nameCar}</h3>
                <p className="description">{car.description}</p>
                <p className="price">Preço: R$ {car.price.toFixed(2)}</p>
                <p className="year">Ano: {car.year}</p>
                <p className="quantity">Quantidade: {car.quantity}</p>
                <button className="button-reserva" onClick={() => navigate(`/produtos/${car.id}`)}>Detalhes</button>
              </div>
            )
          ))}
        </div>
      ) : (
        <div>{showNoCarsModal ? noCarsModal : null}</div>
      )}
    </div>
  );

}
export default SearchCarsList;

