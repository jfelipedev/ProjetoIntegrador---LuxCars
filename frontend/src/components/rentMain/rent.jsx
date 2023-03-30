import React, {useState} from 'react'
import './rent.css'
import {Calendar} from "react-multi-date-picker"
import Image1 from '../../assets/carBMW-M440i.jpg'


function Rent() {

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



          <div className="rentForm">
          <h1>Complete Seus dados</h1>
               <form action="" className="rentFormInfo">
                <label htmlFor="">Nome</label>
                <input type="text" />

                <label htmlFor="">Sobrenome</label>
                <input type="text"  />

                <label htmlFor="">E-mail</label>
                <input type="text"  />

                <label htmlFor="">Cidade</label>
                <input type="text"/>

               </form>
          </div>

          <div className="rentCalendar">
            <h1>Selecione sua data de reserva</h1>
            <Calendar
            weekDays={weekDays}
            months={months}
            value={values}
            onChange={setValues}
            numberOfMonths={2}
            format="DD/MM/YYYY"
            range
            >
              <button onClick={handleDate}>Salve a Data</button>
            </Calendar>
          </div>

          <div className="rentDetails">
            <h1>Detalhes da reserva</h1>
            <img src={Image1} alt="" />

          </div>
     </div>

    </div>
  )
}

export default Rent