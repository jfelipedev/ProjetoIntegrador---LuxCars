import api from '../../../services/api';
import React, { useState, useEffect } from 'react';
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Select from "react-select";

function NewCar(props){
    const [name, setName] = useState("");
    const [descritpion, setDescritpion] = useState("");
    const [price, setPrice] = useState(null)
    const [year, setYear] = useState(null);
    const [highlight, setHighlight] = useState(false);
    const [idCategory, setIdCategory] = useState(null);
    const [idCity, setIdCity] = useState(null);

    const [categories, setCategories] = useState([]);
    const [cities, setCities] = useState([]);

    useEffect(() => {
        api.get("/category")
          .then((response) => {
            let list = [];
            response.data.forEach((item) => {
              list.push({
                label: item.qualification,
                value: item.id
              })
            })
            setCategories(list)
            console.log("Deu certo - category")
          })
          .catch((error) => {
            console.log(error)
            console.log("Deu errado - category")
          })
    
        api.get("/city")
          .then((response) => {
            let list = [];
            response.data.forEach((item) => {
              list.push({
                label: item.nameCity,
                value: item.id
              })
            })
            setCities(list)
            console.log("Deu certo - city")
          })
          .catch((error) => {
            console.log(error)
            console.log("Deu errado - city")
          })
      }, []);

    function addCar(name, descritpion, price, year, highlight, idCategory, idCity){
        api.post("/car",{
            nameCar: name,
            descritpion: descritpion,
            price: price,
            year: year,
            highlight: highlight,
            idCategory: idCategory,
            idCity: idCity,
            inputCarCaracteristicDTO: [],
            inputCarImageDTO: []
          })
        .then((response) => {
            console.log(response.data);
            setName("");
            setDescritpion("");
            setPrice(null);
            setYear(null);
            setHighlight(false);
            setIdCategory(null);
            setIdCity(null);
            toast.success('Novo carro foi adicionado!', {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: true,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "dark",
            });
            props.loadData();
        })
        .catch((error)=>{
            console.log(error.response);
            let data = error.response.data;
            if(error.response.status === 400){
                let erro = "";
                if(data.nameCar !== null){
                    erro += "Nome do carro: " + data.nameCar
                }
                if(data.category !== null){
                    erro += "\nCategoria: " + data.category
                }
                if(data.descritpion !== null){
                    erro += "\nDescrição: " + data.descritpion
                }
                if(data.price !== null){
                    erro += "\nPreço: " + data.price
                }
                if(data.year !== null){
                    erro += "\nAno: " + data.year
                }
                if(data.city !== null){
                    erro += "\nCidade: " + data.city
                }
                if(data.caracteristics !== null){
                    erro += "\nCaracteristicas: " + data.caracteristics
                }
                if(data.images !== null){
                    erro += "\nImagens: " + data.images
                }
                if(data.highlight !== null){
                    erro += "\nDestaque: " + data.highlight
                }
                toast.error(erro, {
                    position: "top-center",
                    autoClose: 5000,
                    hideProgressBar: true,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                    theme: "dark",
                });
            }
            else if((error.response.status === 501 || error.response.status === 502)){
                toast.error("Servidor está indisponivel do momento! tente mais tarde...", {
                    position: "top-center",
                    autoClose: 5000,
                    hideProgressBar: true,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                    theme: "dark",
                });
            }
            else {
                toast.error(error.response.data, {
                    position: "top-center",
                    autoClose: 5000,
                    hideProgressBar: true,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                    theme: "dark",
                });
            }
        })
        props.loadData();
    }

    return(<div>
        <form onSubmit={(event)=>{event.preventDefault()}}>
            <input placeholder='Nome do carro' onChange={(event)=>setName(event.target.value)} value={name}/>
            <textarea placeholder='Descrição' onChange={(event)=>setDescritpion(event.target.value)} value={descritpion}/>
            <input placeholder='Preço' type='number' onChange={(event)=>setPrice(event.target.value)} value={price} />
            <input placeholder='Ano' type='number' onChange={(event)=>setYear(event.target.value)} value={year} />
            <label>
             <input placeholder='destaque' className='checkboxInput' type='checkbox' onChange={(event)=>setHighlight(!highlight)} checked={highlight} />
             Destaque
            </label>
            <Select
                options={cities}
                onChange={(event)=>setIdCity(event.value)}
                className="select"
                placeholder="Cidade"
            />
            <Select
                options={categories}
                onChange={(event)=>setIdCategory(event.value)}
                className="select"
                placeholder="Categoria"
            />
            <button onClick={()=>{addCar(name, descritpion, price, year, highlight, idCategory, idCity)}}>Adicionar</button>
        </form>
    </div>)
}
export default NewCar;