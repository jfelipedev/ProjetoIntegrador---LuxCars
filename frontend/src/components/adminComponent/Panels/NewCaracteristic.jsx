import api from '../../../services/api';
import React, { useState } from 'react';
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function NewCaracteristic(props){
    const [name, setName] = useState("");
    const [unitOfMeasurement, setUnitOfMeasurement] = useState("");
    function addCaracteristic(name, unitOfMeasurement){
        let unit = unitOfMeasurement;
        if(unit === ""){
            unit = null
        }
        api.post("/caracteristic",{
            name: name,
            unitOfMeasurement: unit
        })
        .then((response) => {
            console.log(response.data);
            setName("");
            setUnitOfMeasurement("");
            toast.success('Uma nova característica foi adicionado!', {
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
                if(data.name !== null){
                    erro += "Nome da característica: " + data.name
                }
                if(data.country !== null){
                    erro += "\nUnidade de medida: " + data.unitOfMeasurement
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
            <input placeholder='Nome da característica' onChange={(event)=>setName(event.target.value)} value={name}/>
            <input placeholder='Unidade de medida' onChange={(event)=>setUnitOfMeasurement(event.target.value)} value={unitOfMeasurement}/>
            <button onClick={()=>{addCaracteristic(name, unitOfMeasurement)}}>Adicionar</button>
        </form>
    </div>)
}
export default NewCaracteristic;