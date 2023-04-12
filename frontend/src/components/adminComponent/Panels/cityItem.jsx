import React, { useState } from 'react';
import api from '../../../services/api';
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function CityItemPanel(props){
    const [nameCity, setNameCity] = useState(props.nameCity);
    const [country, setCountry] = useState(props.country);
    function deleteItem() {
        api.delete("/city/" + props.id)
        .then((response) => {
            console.log("Deletado com sucesso!");
            toast.success('Cidade foi deletado com sucesso!', {
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
            if((error.response.status === 501 || error.response.status === 502)){
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
    function saveCity(){
        api.put("/city/" + props.id,{
            nameCity: nameCity,
            country: country
        })
        .then((response) => {
            console.log(response.data);
            toast.success('As alterações da cidade foi salvar com sucesso!', {
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
                if(data.descritpion !== null){
                    erro += "Descrisão: " + data.descritpion
                }
                if(data.country !== null){
                    erro += "\nNome da categoria: " + data.qualification
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
            <input onChange={(event)=>setNameCity(event.target.value)} value={nameCity}/>
            <input onChange={(event)=>setCountry(event.target.value)} value={country}/>
            <button onClick={()=>saveCity()}>Salvar</button>
            <button onClick={()=>deleteItem()}>Excluir</button>
        </form>
    </div>)
}

export default CityItemPanel;