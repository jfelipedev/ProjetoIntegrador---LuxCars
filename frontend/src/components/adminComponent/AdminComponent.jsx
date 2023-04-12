import React, { useEffect, useState } from 'react';
import './style.css';
import Panel from './Panel';
import { ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { getToken, getTokenRole} from '../../services/auth';

function AdminComponent() {
    const [panel, setPanel] = useState(null);    
    let display = false;
 
    if(!!getToken()){
        if(getTokenRole() === "Administrador"){
            display = true;
        }
    }
    if(display){
        return(
            <div className='admin'>
                <ToastContainer
                    position="top-center"
                    autoClose={5000}
                    hideProgressBar
                    newestOnTop={false}
                    closeOnClick
                    rtl={false}
                    pauseOnFocusLoss
                    draggable
                    pauseOnHover
                    theme="dark"
                />
                <div className='menu'>
                    <button onClick={()=>setPanel("Category")}>Categoria</button>
                    <button onClick={()=>setPanel("Caracteristic")}>Característica</button>
                    <button onClick={()=>setPanel("City")}>Cidade</button>
                    <button onClick={()=>setPanel("Image")}>Imagem</button>
                    <button onClick={()=>setPanel("Car")}>Carros</button>
                    <button onClick={()=>setPanel("Booking")}>Reserva</button>
                    <button onClick={()=>setPanel("ContactUs")}>Mensagens recebidas</button>
                </div>
                <div className='panel'>
                    <Panel panel={panel} />
                </div>
            </div>
        )
    }
    else{
        return(<div className='admin'>
            <p>Não tem permissão para ver essa pagina!</p>
        </div>)
    }
};

export default AdminComponent;