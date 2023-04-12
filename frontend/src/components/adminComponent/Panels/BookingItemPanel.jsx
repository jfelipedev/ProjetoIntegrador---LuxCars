import React from 'react';
import api from '../../../services/api';
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function BookingItemPanel(props){
    function deleteItem() {
        api.delete("/booking/" + props.id)
        .then((response) => {
            console.log("Deletado com sucesso!");
            toast.success('A reserva foi deletado com sucesso!', {
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
    return(<div>
        <form onSubmit={(event)=>{event.preventDefault()}}>
            <p>Data inicio: {props.startDate} Hora Inicio: {props.startTime} Data fim: {props.endDate} Carro: {props.car.nameCar} ({props.car.year}) Cliente: {props.user.firstName} {props.user.surname} [{props.user.email}] Local: {props.car.city}, {props.car.country}</p>
            <button onClick={()=>deleteItem()}>Excluir</button>
        </form>
    </div>)
}

export default BookingItemPanel;