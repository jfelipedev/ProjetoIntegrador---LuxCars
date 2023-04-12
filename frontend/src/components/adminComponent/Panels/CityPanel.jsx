import "./CityPanel.css";
import React, { useState, useEffect } from 'react';
import api from '../../../services/api';
import CityItemPanel from "./cityItem";
import NewCity from "./newCity";
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function CityPanel(){
    const [city, setCity] = useState([]);
    function loadData() {
        setCity([]);
        setTimeout(()=>{
            api.get("/city")
            .then((response) => {
                setCity(response.data);
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
        }, 100);
    }
    useEffect(() => {
        loadData();
    }, []);
    return (<div className="lista">
        <NewCity loadData={loadData}/>
        {
            city.map(({id,nameCity,country})=><CityItemPanel key={id} loadData={loadData} id={id} nameCity={nameCity} country={country}/>)
        }
    </div>)
}

export default CityPanel;