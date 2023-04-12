import React, { useEffect, useState } from 'react';
import { getTokenName, getTokenSurname, getTokenEmail } from '../../services/auth';
import { useNavigate, useParams } from "react-router-dom";
import './rentConfirm.css';


function RentConfirm() {

    const [name, setTokenName] = useState(getTokenName());
    const [lastName, setTokenSurname] = useState(getTokenSurname());
    const [email, setTokenEmail] = useState(getTokenEmail());
    const navigate = useNavigate();

    

    const [userInfo, setUserInfo] = useState({
        name: "",
        lastName: "",
        email: "",
      });

      useEffect (() =>{
        setUserInfo({
            name: name,
            lastName: lastName,
            email: email,
        });
      }, [setUserInfo]);

      console.log(userInfo);


  const { id } = useParams();
  const baseUrl = 'https://carlux-grupo1.s3.us-east-2.amazonaws.com';

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
    const response = await fetch(`http://api.carlux.viniciusofagundes.com.br/car/${id}`);

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
      image: jsonData.images[0].url,
    });
  };

  useEffect(() => {
    fetchData();
  }, []);

 
  const redirectToHome = () => {
    setTimeout(() => {
      navigate('/');
    }, 5000); // Tempo em milissegundos
  };

  // Chama a função para redirecionar para a página inicial
  useEffect(() => {
    redirectToHome();
  }, []);

  return (
    <div className="rentConfirmContainer">
      <div className="confirmBox">
        <h1 className="userNameConfirm"></h1>

        <div className="rentConfirmDescrip">
            <h2 className='rentConfirmCar'>Sr(a) {userInfo.name} {userInfo.lastName}</h2>
          <h2 className="rentConfirmCar">{carInfo.nameCar}</h2>
          <h2 className="rentConfirmCar Year">{carInfo.year}</h2>
          <h2 className="rentConfirmCar City">{carInfo.city}, {carInfo.country}</h2>
        </div>

        <div className="rentConfirmImgContainer">
          <img className="rentConfirmImg" src={baseUrl + carInfo.image} alt={carInfo.nameCar} />
        </div>

        <h3>Reserva confirmada com sucesso</h3>
      </div>
    </div>
  );
}

export default RentConfirm;