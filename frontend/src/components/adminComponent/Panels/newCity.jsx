import api from '../../../services/api';
import React, { useState } from 'react';
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function NewCity(props){
    const [nameCity, setNameCity] = useState("");
    const [country, setCountry] = useState("");
    function addCity(city, country){
        api.post("/city",{
            nameCity: city,
            country: country
        })
        .then((response) => {
            console.log(response.data);
            toast.success('Nova cidade foi adicionado!', {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: true,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "dark",
            });
            setNameCity("");
            setCountry("");
            props.loadData();
        })
        .catch((error)=>{
            console.log(error.response);
            let data = error.response.data;
            if(error.response.status === 400){
                let erro = "";
                if(data.nameCity !== null){
                    erro += "Nome da cidade: " + data.nameCity
                }
                if(data.country !== null){
                    erro += "\nPaís: " + data.country
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
            <input placeholder='Cidade' onChange={(event)=>setNameCity(event.target.value)} value={nameCity}/>
            <input placeholder='País' onChange={(event)=>setCountry(event.target.value)} value={country}/>
            <button onClick={()=>{addCity(nameCity,country)}}>Adicionar</button>
        </form>
    </div>)
}
export default NewCity;