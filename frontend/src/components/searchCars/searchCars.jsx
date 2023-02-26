import React, {useEffect, useRef, useState} from "react";
import './searchCars.css'
import Select from 'react-select'



const suppliers = [
  {label: "Conversivel", value: "Conversivel"},
  {label: "Coupé", value: "Coupé"},
  {label: "Esportivo", value: "Esportiv"},
  {label: "Sedan", value: "Sedan"},
] 

const DBsuppliers = ["Buenos Aires", "Mendoza", "Cordoba"]

function SearchCars() {

  const handleSelectChange = (event) => {
    console.log(event)
  }
  
  const nameInputRef = useRef(null);

  useEffect(() => {
    nameInputRef.current.focus()
  })

  function DropDownItem() {
    return(
      <div className="searchContainer ">
        <input type="text" name="category" placeholder="Categoria" ref={nameInputRef} onChange= {handleSelectChange}/>
           

        <div className="dropDown">
          <div className="dropDownList">
            <div className="item" id="item1" onMouseDown="category1">Conversível</div>
            <div className="item" id="item1" onMouseDown="category2">Coupé</div>
            <div className="item" id="item1" onMouseDown="category3">Esportivo</div>
            <div className="item" id="item1" onMouseDown="category4">Sedan</div>
          </div>
        </div>
        
      </div>
    )
  }

     return(
       <div className="searchSection">
         <h1 className="sectionTitle">ALUGUE O CARRO DOS SEUS SONHOS</h1>

         <div className="drop">
           <DropDownItem />
           <DropDownItem />
           <DropDownItem />
           <div className="button2">
             <button className="button button1">BUSCAR</button>
           </div>
         </div>
         
         
       </div>
     )
}

export default SearchCars