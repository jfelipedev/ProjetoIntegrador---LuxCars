import "./ContactUSPanel.css";
import React, { useState, useEffect } from 'react'
import api from '../../../services/api'
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function ContactUSPanel(){
    const [messages, setMessages] = useState([]);
    useEffect(() => {
        api.get("/contactUsMessage")
        .then((response) => {
            console.log(response)
            setMessages(response.data);
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
    }, [])
    return (<div className="Lista">
        {
            messages.map(({id,title,email,body})=>{
                return(<div key={id}>
                    <h5>{title + "<" + email +">"}</h5>
                    <p>{body}</p>
                </div>)
            })
        }
    </div>)
}

export default ContactUSPanel;