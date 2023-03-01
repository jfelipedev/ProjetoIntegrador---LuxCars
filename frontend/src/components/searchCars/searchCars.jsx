
import React, {useState} from "react";
import './searchCars.css'
import Select from "react-select";
import { DateRange } from 'react-date-range';
import { addDays } from 'date-fns';





const suppliers = [
  {label: "Conversivel", value: "Conversivel"},
  {label: "Coupé", value: "Coupé"},
  {label: "Esportivo", value: "Esportiv"},
  {label: "Sedan", value: "Sedan"},
] 

const DBsuppliers = [
  {label: "Argentina", value: "Conversivel"},
  {label: "Angola", value: "Coupé"},
  {label: "Brasil", value: "Esportiv"},
  {label: "Paraguai", value: "Sedan"},
] 







function SearchCars() {


  const [state, setState] = useState([
    {
      startDate: new Date(),
      endDate: addDays(new Date(), 7),
      key: 'selection'
    }
  ]);

  const handleSelectChange = (event) => {
    console.log(event)
  }

 
  return (
    <div className="searchSection">
      <h1 className="sectionTitle">ALUGUE O CARRO DOS SEUS SONHOS</h1>

      <div className="searchContainer ">
        <div className="dropDown">
          <Select 
            options={suppliers}
            onChange= {handleSelectChange}
            className="select"
          />
            
        </div>

        <div className="dropDown">
          <Select 
            options={DBsuppliers}
            onChange= {handleSelectChange}
            className="select"
          />
        </div>

        <div className="dropDown drop">
          
          <DateRange
            editableDateInputs={true} 
            onChange={item => setState([item.selection])}
            showSelectionPreview={true}
            moveRangeOnFirstSelection={false}
            months={1}
            ranges={state}
            rangeColors={state}
          
          />
        </div>

        <button className="button button1">BUSCAR</button>
      </div>
    </div>
  );
}

export default SearchCars;
