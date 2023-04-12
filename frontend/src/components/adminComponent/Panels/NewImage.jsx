import api from '../../../services/api';
import React, { useState } from 'react';
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function NewImage(props){
    const [title, setTitle] = useState("");
    function addCity(descritpion, qualification){
        api.post("/image",{
            title: title
        })
        .then((response) => {
            console.log(response.data);
            setTitle("");
            toast.success('Nova imagem foi adicionado!', {
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
                if(data.title !== null){
                    erro += "Titulo da  imagem: " + data.title
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
            <input placeholder='Titulo da imagem' onChange={(event)=>setTitle(event.target.value)} value={title}/>
            <button onClick={()=>{addCity(title)}}>Adicionar</button>
        </form>
    </div>)
}
export default NewImage;