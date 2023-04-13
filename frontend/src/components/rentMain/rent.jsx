import React, { useState } from "react";
import "./rent.css";
import { Calendar } from "react-multi-date-picker";
import Image1 from "../../assets/carBMW-M440i.jpg";
import { useEffect } from "react";
import { useLocation, useParams, useNavigate } from "react-router-dom";
import api from "../../services/api";
import { getToken } from '../../services/auth'

function Rent({ filtroProduct }) {
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

  // const [booking, setBooking] = useState([])

  // useEffect(() => {

  //   api.post("/booking").then((response) => {
      
  //     setBooking(response.data)
  //     console.log(response)
  //   })
  //   .cath((error) => {
  //     console.log(error)
  //   })
  // })


  console.log(filtroProduct);

  const weekDays = ["Do", "Se", "Te", "Qu", "Qu", "Se", "Sá"];
  const months = [
    "jan",
    "fev",
    "mar",
    "abr",
    "mai",
    "jun",
    "jul",
    "ago",
    "set",
    "out",
    "nov",
    "dez",
  ];
  const [values, setValues] = useState(new Date());

  console.log(carInfo);

  // const oneImgOnly = carInfo.filter(
  //   (obj, index) =>
  //   carInfo.findIndex((item) => item.nameCar === obj.nameCar) === index
  // );

  //SLIDER~~~~~~~~
  const [sliderPosition, setSliderPosition] = useState(0);

  function moveSliderLeft() {
    setSliderPosition(sliderPosition - 1);
    const slider = document.querySelector(".slider");
    slider.classList.add("moveLeft");
    setTimeout(() => slider.classList.remove("moveLeft"), 500);
  }

  function moveSliderRight() {
    setSliderPosition(sliderPosition + 1);
    const slider = document.querySelector(".slider");
    slider.classList.add("moveRight");
    setTimeout(() => slider.classList.remove("moveRight"), 500);
  }
  //END SLIDER ~~~~~~

  //PRa confirmar a reserva
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [token, setToken] = useState(getToken());
  const navigate = useNavigate();

  useEffect(() => {
	  const handleStorage = () => {
		setToken(getToken())
	}
    window.addEventListener('storage', handleStorage())
    return () => window.removeEventListener('storage', handleStorage())
  }, [])

  const handleConfirm = () => {
    if(isAuthenticated){
      navigate("/aluguel-confirmado/" + id)
    }else{
      navigate("/entrar" , { state:{origin: '/alugue/' + id} } 
      )
    }
  }

  useEffect(() => { 
    setIsAuthenticated(!!token); 
  }, []);



  return (
    <div className="rentSection">
      <div className="rentContainer">
        <div className="rentHeader">
          <div className="carSelected">
            <h2 className="carSelectScript">Carro Selecionado</h2>
            <h1 className="carnameScript">{carInfo.nameCar}</h1>
          </div>
          <i class="uil uil-angle-left-b"></i>
        </div>

        <div className="rentMain">
        
            {/* <div className="rentForm">
              <h1 className="rentFromTitle">Complete seus Dados</h1>
              <form action="" className="rentFormInfo">
                <div className="rentFormInfo1">
                  <label htmlFor="name">Nome</label>
                  <input className="rentInfo" type="text" id="name" />

                  <label htmlFor="surName">Sobrenome</label>
                  <input className="rentInfo" type="text" id="surName" />
                </div>

                <div className="rentFormInfo1">
                  <label htmlFor="email">E-mail</label>
                  <input className="rentInfo" type="text" id="email" />

                  <label htmlFor="city">Cidade</label>
                  <input className="rentInfo" type="text" id="city" />
                </div>
              </form>
            </div> */}

            <div style={{ width: 480 }} className="rentCalendar">
              <h1 className="rentFromTitle">Selecione sua data de reserva</h1>
              <Calendar
                weekDays={weekDays}
                months={months}
                onChange={setValues}
                numberOfMonths={2}
                format="DD/MM/YYYY"
                size="large"
                range
              ></Calendar>
            </div>
          

          <div className="rentDetails">
            <div className="rentDetailsImages">
              <div
                className="slider"
                style={{ transform: `translateX(${sliderPosition * -100}%)` }}
              >
                {carInfo.image &&
                  carInfo.image.map(({ id, url, title }) => (
                    <img
                      key={id}
                      className="rentDetailsImage"
                      src={baseUrl + url}
                      alt={title}
                    />
                  ))}
              </div>
              {/* <button onClick={moveSliderLeft}>{"<"}</button>
              <button onClick={moveSliderRight}>{">"}</button> */}
            </div>
            <h1 className="rentDetailsTitle">Detalhe da reserva</h1>

            <div className="rentDetailsInfo">
              <h2 className="rentDetailsYear">{carInfo.year}</h2>
              <h1 className="rentDetailsName">{carInfo.nameCar}</h1>
              <h4 className="rentDetailsLocation">
                {carInfo.city}, {carInfo.country}
              </h4>

              <form action="" className="rentDetailsForm">
                <div className="single-input">
                  {/* <label htmlFor="Checkin">Check in - Check out</label> */}
                  <input
                    value={values}
                    type="text"
                    name="Checkin"
                    id="Checkin"
                    className="inputDate"
                  />
                </div>
              </form>
            </div>
            <button className="rentDetailsButton button" onClick={handleConfirm}>
              Confirmar Reserva
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Rent;
