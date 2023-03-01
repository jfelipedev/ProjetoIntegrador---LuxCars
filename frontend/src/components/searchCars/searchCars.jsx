
import React from 'react';
import { useState } from 'react';
import './searchCars.css'
import Select from "react-select";





import Box from '@mui/material/Box';
import { LocalizationProvider } from '@mui/x-date-pickers-pro';
import { AdapterDayjs } from '@mui/x-date-pickers-pro/AdapterDayjs';
import { DateRangePicker } from '@mui/x-date-pickers-pro/DateRangePicker';


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


  const handleSelectChange = (event) => {
    console.log(event)
  }

  const [value, setValue] = React.useState([null, null]);



 
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
          
          <LocalizationProvider dateAdapter={AdapterDayjs}>
            <DateRangePicker
              
              localeText={{ start: 'Check-in', end: 'Check-out' }}
              label="Advanced keyboard"
              value={value}
              onChange={(newValue) => setValue(newValue)}
              renderInput={(startProps, endProps) => (
                <React.Fragment>
                  <input ref={startProps.inputRef} {...startProps.inputProps} className="inputCalendar" />
                  <Box sx={{ mx: 1 }}> to </Box>
                  <input ref={endProps.inputRef} {...endProps.inputProps} className="inputCalendar" />
                </React.Fragment>
        )}
      />
          </LocalizationProvider>

        </div>

        <button className="button button1">BUSCAR</button>
      </div>
    </div>
  );
}

export default SearchCars;
