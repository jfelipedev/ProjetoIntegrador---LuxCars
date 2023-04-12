import "./CaracteristicPanel.css";
import React, { useState, useEffect } from 'react';
import api from '../../../services/api';
import NewCaracteristic from "./NewCaracteristic";
import CaracteristicItemPanel from "./CaracteristicItemPanel";
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function CaracteristicPanel(){
    const [caracteristic, setCaracteristic] = useState([]);
    function loadData() {
        setCaracteristic([]);
        setTimeout(()=>{
            api.get("/caracteristic")
            .then((response) => {
                console.log(response);
                setCaracteristic(response.data);
            })
            .catch((error)=>{
                console.log("oi");
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
        }, 100);
    }
    useEffect(() => {
        loadData();
    }, []);
    return (<div className="lista">
        <NewCaracteristic loadData={loadData}/>
        {
            caracteristic.map(({id,name,unitOfMeasurement})=><CaracteristicItemPanel key={id} loadData={loadData} id={id} name={name} unitOfMeasurement={unitOfMeasurement}/>)
        }
    </div>)
}

export default CaracteristicPanel;