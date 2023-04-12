import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./rent.css";
import { Calendar } from "react-multi-date-picker";
import Image1 from "../../assets/carBMW-M440i.jpg";
import { useEffect } from "react";
import { useLocation, useParams, useNavigate } from "react-router-dom";
import {
  TOKEN_FIRST,
  getToken,
  getTokenName,
  getTokenSurname,
  getTokenEmail,
} from "../../services/auth";

function Rent({ filtroProduct }) {
  const { id } = useParams();
  const [value, setValue] = useState([new Date(), new Date()]);

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

  const [userInfo, setUserInfo] = useState({
    name: "",
    lastName: "",
    email: "",
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

// TENTANDO FAZER POST DA RESERVA

  async function postData() {
    try {
      const response = await fetch(
        "http://api.carlux.viniciusofagundes.com.br/booking",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: "Bearer " + getToken,
          },
          body: JSON.stringify({
            startDate: value[0].toDate().toString(),
            startTime: "",
            endDate: value[1].toDate().toString(),
            idCar: id,
          }),
        }
      );

      const data = await response.json();
      console.log(data);
    } catch (error) {
      console.error(error);
    }
  }

  postData();

  // console.log(filtroProduct);

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

  // console.log(carInfo);

  const handleDateChange = (date) => {
    setValue(date);
    console.log("olha aqui" + date[0].toString());
  };

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

  //pra confirmar a reserva
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [token, setToken] = useState(getToken());
  const navigate = useNavigate();

  useEffect(() => {
    const handleStorage = () => {
      setToken(getToken());
    };
    window.addEventListener("storage", handleStorage());
    return () => window.removeEventListener("storage", handleStorage());
  }, []);

  const [tokenName, setTokenName] = useState(getTokenName());
  const [tokenNameSurname, setTokenSurname] = useState(getTokenSurname());
  const [tokenEmail, setTokenEmail] = useState(getTokenEmail());
  //console.log(tokenName + " " + tokenNameSurname);

  useEffect(() => {
    setIsAuthenticated(!!token);
    if (isAuthenticated) {
      setUserInfo({
        name: tokenName,
        lastName: tokenNameSurname,
        email: tokenEmail,
      });
    }
  }, [token, isAuthenticated, setUserInfo]);

  const handleConfirm = () => {
    if (isAuthenticated) {
      navigate(`/aluguel-confirmado/${id}`);
    } else {
      navigate("/entrar");
    }
  };

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
          <button onClick={() => navigate(-1)}>
            <i class="uil uil-angle-left-b"></i>
          </button>
        </div>

        <div className="rentMain">
          <div className="rentForm">
            <h1 className="rentFromTitle">Dados da sua conta</h1>
            <form action="" className="rentFormInfo">
              <div className="rentFormInfo1">
                <label htmlFor="name">Nome</label>
                <input
                  className="rentInfo"
                  type="text"
                  id="name"
                  value={userInfo.name}
                  disabled
                />

                <label htmlFor="surName">Sobrenome</label>
                <input
                  className="rentInfo"
                  type="text"
                  id="surName"
                  value={userInfo.lastName}
                  disabled
                />
              </div>
              <div className="rentFormInfo1">
                <label htmlFor="city">Cidade</label>
                <input
                  className="rentInfo"
                  type="text"
                  id="city"
                  value={carInfo.city}
                  disabled
                />

                <label htmlFor="city">Email</label>
                <input
                  className="rentInfo"
                  type="text"
                  id="city"
                  value={userInfo.email}
                  disabled
                />
              </div>
            </form>

            <div className="calendar">
              <div className="rentCalendarContainer">
                <div className="rentCalendar">
                  <div style={{ width: 480 }} className="rentCalendar">
                    <h1 className="rentFromTitle">
                      Selecione sua data de reserva
                    </h1>
                    <Calendar
                      value={value}
                      weekDays={weekDays}
                      months={months}
                      onChange={handleDateChange}
                      numberOfMonths={2}
                      format="DD/MM/YYYY"
                      size="large"
                      range
                    ></Calendar>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div ClassName="car-info">
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
                      value={value}
                      type="text"
                      name="Checkin"
                      id="Checkin"
                      className="inputDate"
                    />
                  </div>
                </form>
              </div>
              <button
                className="rentDetailsButton button"
                onClick={handleConfirm}
              >
                Confirmar Reserva
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Rent;
