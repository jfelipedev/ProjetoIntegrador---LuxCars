import React, {useEffect, useState} from 'react'
import { useParams } from 'react-router'
import './rentConfirm.css'

function RentConfirm() {

  const { id } = useParams();
  const baseUrl = "https://carlux-grupo1.s3.us-east-2.amazonaws.com";

  const [carInfo, setCarInfo] = useState({
    nameCar: null,
    descritpion: null,
    category: null,
    city: null,
    country: null,
    caracteristics: null,
    year: null,
    image: null,
  });

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);
  const fetchData = async () => {
    const response = await fetch(
      `http://api.carlux.viniciusofagundes.com.br/car/${id}`
    );

    const jsonData = await response.json();
    const response1 = await fetch(
      `http://api.carlux.viniciusofagundes.com.br/city/${jsonData.city.id}`
    );
    const jsonData1 = await response1.json();
    setCarInfo({
      nameCar: jsonData.nameCar,
      descritpion: jsonData.descritpion,
      category: jsonData.category.qualification,
      city: jsonData.city.nameCity,
      country: jsonData1.country,
      caracteristics: jsonData.caracteristics.name,
      year: jsonData.year,
      image: jsonData.images,
    });
  };
  useEffect(() => {
    fetchData();
  }, []);
  return (
    <div>
     <div className='rentConfirmContainer'>
      <div className="confirmBox">
        <h1 className="userNameConfirm"></h1>

        <div className="rentConfirmDescrip">
          <h2 className="rentConfirmCar">{carInfo.nameCar}</h2>
          <h2 className="rentConfirmCar Year">{carInfo.year}</h2>
          <h2 className="rentConfirmCar City">{carInfo.city}, {carInfo.country}</h2>
        </div>
      <button className="buttonRentConfirm button">Reservado</button>
      </div>
          
     </div>
    </div>
  )
}

export default RentConfirm