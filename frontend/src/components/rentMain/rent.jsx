import React, { useState } from 'react'
import './rent.css'
import { Calendar } from "react-multi-date-picker"
import Image1 from '../../assets/carBMW-M440i.jpg'
import { useEffect } from 'react'
import api from '../../services/api'
import { useLocation } from 'react-router-dom'



function Rent({ filtroProduct }) {

  // const [booking, setBooking] = useState([])

  // useEffect(() => {

  //   api.get("/booking/{id}").then((response) => {
  //     setBooking(response.data)
  //     console.log(response)
  //   })
  //   .cath(() => {
  //     setBooking([])
  //   })
  // })

  console.log(filtroProduct)

  const weekDays = ["Do", "Se", "Te", "Qu", "Qu", "Se", "Sá"]
  const months = ["jan", "fev", "mar", "abr", "mai", "jun", "jul", "ago", "set", "out", "nov", "dez"]
  const [values, setValues] = useState(new Date())

  // const handleDate = (values) => {
  //   setValues()
  //   console.log(values)
  // }


  return (
    <div className='rentSection'>

      <div className="rentContainer">
        <div className="rentHeader">
          <div className="carSelected">
            <h2 className='carSelectScript'>Carro Selecionado</h2>
            <h1 className='carnameScript' >Ferrari California T</h1>
          </div>
          <i class="uil uil-angle-left-b"></i>
        </div>


        <div className="rentMain">
          <div className="rentMain1">
            <div className="rentForm">
              <h1 className='rentFromTitle'>Complete seus Dados</h1>
              <form action="" className="rentFormInfo">

                <div className="rentFormInfo1">
                  <label htmlFor="name" >Nome</label>
                  <input className='rentInfo' type="text" id='name' />



                  <label htmlFor="surName">Sobrenome</label>
                  <input className='rentInfo' type="text" id='surName' />
                </div>

                <div className="rentFormInfo1">
                  <label htmlFor="email">E-mail</label>
                  <input className='rentInfo' type="text" id='email' />



                  <label htmlFor="city">Cidade</label>
                  <input className='rentInfo' type="text" id='city' />
                </div>

              </form>
            </div>


            <div style={{ width: 480 }} className="rentCalendar">
              <h1 className='rentFromTitle'>Selecione sua data de reserva</h1>
              <Calendar

                weekDays={weekDays}
                months={months}

                onChange={setValues}
                numberOfMonths={2}
                format="DD/MM/YYYY"
                size="large"
                range
              >
                {/* <button onChange= {setValues}>Enviar</button> */}
              </Calendar>
            </div>
          </div>

          <div className="rentDetails">
            <h1 className='rentDetailsTitle'>Detalhe da reserva</h1>
            <img src={Image1} alt="" className='rentDetailsImage' />

            <div className="rentDetailsInfo">
              <h2 className='rentDetailsYear'>2014</h2>
              <h1 className='rentDetailsName'>Ferrari California</h1>
              <h4 className='rentDetailsLocation'>São Paulo, Brasil</h4>

              <form action="" className='rentDetailsForm'>
                <div className="single-input">
                  <label htmlFor="Checkin">Check in - Check out</label>
                  <input value={values} type="text" name="Checkin" id="Checkin" className='inputDate' />
                </div>

                {/* <div className="single-input">
                  <label htmlFor="Checkout">Check out</label>
                  <input type="text" name="Checkout" id="Checkout" className='inputDate' />
                </div> */}

              </form>
            </div>
            <button className="rentDetailsButton button">Confirmar Reserva</button>
          </div>
        </div>
      </div>

    </div>
  )
}

export default Rent;