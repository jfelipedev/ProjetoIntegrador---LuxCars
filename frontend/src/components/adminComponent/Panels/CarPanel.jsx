import "./categoryPanel.css";
import React, { useState, useEffect } from 'react';
import api from '../../../services/api';
import NewCar from "./NewCar";
import CarItemPanel from "./CarItemPanel";
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function CarPanel(){
    const [car, setCar] = useState([]);
    function loadData() {
        setCar([]);
        setTimeout(()=>{
            api.get("/car")
            .then((response) => {
                setCar(response.data);
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
        <NewCar loadData={loadData}/>
        {
            car.map(({id,nameCar,descritpion,price,year,highlight,category,city})=><CarItemPanel key={id} loadData={loadData} id={id} nameCar={nameCar} descritpion={descritpion} price={price} year={year} highlight={highlight} category={category} city={city} />)
        }
    </div>)
}

export default CarPanel;