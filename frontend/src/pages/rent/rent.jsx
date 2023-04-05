import React, {useState} from 'react'
import './rent.css'
import {Calendar} from "react-multi-date-picker"
import Image1 from '../../assets/carBMW-M440i.jpg'
// import { useEffect } from 'react'
// import api from '../../services/api'



function Rent() {

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

  const weekDays = ["Do", "Se", "Te", "Qu", "Qu", "Se", "Sá"]
  const months = ["jan", "fev", "mar", "abr", "mai", "jun", "jul", "ago", "set", "out", "nov", "dez"]
  const [values, setValues] = useState()

  const handleDate = () => {
    console.log(values)
  }
  return (
    <div className='rentSection'>

     <div className="rentContainer">
          <div className="rendHeader">
            <div className="carSelected">
              <h2 className='carSelectScript'>Carro Selecionado</h2>
              <h1 className='carnameScript' >Ferrari California T</h1>
            </div>
            <i class="uil uil-angle-left-b"></i>
          </div>

          <h1 className='rentFromTitle'>Complete seus Dados</h1>
          <div className="rentForm">
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
                <input className='rentInfo' type="text" id='city'/>
                </div>

               </form>
          </div>

          <h1 className='rentFromTitle'>Selecione sua data de reserva</h1>
          <div style={{ width: 480 }} className="rentCalendar">
            <Calendar
            
            weekDays={weekDays}
            months={months}
            value={values}
            onChange={setValues}
            numberOfMonths={2}
            format="DD/MM/YYYY"
            size="large"
            range
            >
              <button onClick={handleDate}>Salve a Data</button>
            </Calendar>
          </div>

          <div className="rentDetails">
            <h1>Detalhes da reserva</h1>
            <img src={Image1} alt="" />
            <h2>2014</h2>
            <h1>Ferrari California</h1>

            <h4>São Paulo, Brasil</h4>

            <input type="date" />
            <input type="date" name="" id="" />
            <button className="button">Confirmar Reserva</button>
          </div>
     </div>

    </div>
  )
}

export default Rent
