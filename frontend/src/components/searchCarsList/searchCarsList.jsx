import React, { useState, useEffect } from 'react';
import "./searchCarsList.css";
import api from '../../services/api';

function SearchCarsList({ selectedCityId }) {

  console.log(selectedCityId);
  const [car, setCar] = useState(null);
  console.log(car);

  useEffect(() => {
    async function fetchData() {
      try {
        // const response = await api.get(`/car/${selectedCityId}`);
        const response = await api.get(`/car/${selectedCityId}/`);
        setCar(response.data);
      } catch (error) {
        console.error(error);
      }
    }
    fetchData();
  }, [selectedCityId]);

  return (
    <div className='searchCarSection'>
      <div>
        {car ? (
          <><h2>Lista de carros em {car.city.nameCity}</h2>

            <p>categoria: </p>
            <ul>
              {car.images?.map(image => (
                <li key={image.id}>
                  <img src={`https://carlux-grupo1.s3.us-east-2.amazonaws.com${image.url}`} alt={image.title} />
                </li>
              ))}
            </ul>
          </>

        ) : (
          <p>Carregando...</p>
        )}
      </div>
    </div>

  );
}

export default SearchCarsList;
