import api from '../../../services/api';
import React, { useState } from 'react';
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function NewCategory(props){
    const [qualification, setQualification] = useState("");
    const [descritpion, setDescritpion] = useState("");
    function addCity(descritpion, qualification){
        api.post("/category",{
            descritpion: descritpion,
            qualification: qualification
        })
        .then((response) => {
            console.log(response.data);
            setDescritpion("");
            setQualification("");
            toast.success('Nova categoria foi adicionado!', {
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
                    erro += "Descrição: " + data.descritpion
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
            <input placeholder='Nome da categoria' onChange={(event)=>setQualification(event.target.value)} value={qualification}/>
            <textarea placeholder='Descrição' onChange={(event)=>setDescritpion(event.target.value)} value={descritpion}/>
            <button onClick={()=>{addCity(descritpion, qualification)}}>Adicionar</button>
        </form>
    </div>)
}
export default NewCategory;