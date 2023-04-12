import "./CityPanel.css";
import React, { useState, useEffect } from 'react';
import api from '../../../services/api';
import BookingItemPanel from "./BookingItemPanel";
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function BookingPanel(){
    const [booking, setBooking] = useState([]);
    const [user, setUser] = useState("");
    function loadData() {
        setBooking([]);
        setTimeout(()=>{
            let url = "/booking";
            if(user !== ""){
                url += "?idUser=" + user
            }
            api.get(url)
            .then((response) => {
                setBooking(response.data);
                console.log(response.data);
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
    useEffect(() => {
        loadData();
    }, [user]);
    return (<div className="lista">
        <input type="number" value={user} onChange={(e)=>setUser(e.target.value)} placeholder="Id do usuário" />
        {
            booking.map(({id,startDate,startTime,endDate,car,user})=><BookingItemPanel key={id} loadData={loadData} id={id} startDate={startDate} startTime={startTime} endDate={endDate} car={car} user={user} />)
        }
    </div>)
}

export default BookingPanel;