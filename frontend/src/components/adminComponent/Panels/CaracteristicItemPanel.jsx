import React, { useEffect, useState } from 'react';
import api from '../../../services/api';
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Modal from 'react-modal';
import './CaracteristicItemPanel.css';

function CaracteristicItemPanel(props){
    console.log(props);
    const [icon, setIcon] = useState("");
    const [selectedFile, setSelectedFile] = useState([]);
    const [modal, setModal] = React.useState(false);
    const [name, setName] = useState(props.name);
    const [unitOfMeasurement, setUnitOfMeasurement] = useState(props.unitOfMeasurement);
    function deleteItem() {
        api.delete("/caracteristic/" + props.id)
        .then((response) => {
            console.log("Deletado com sucesso!");
            toast.success('A característica foi deletado com sucesso!', {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: true,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "dark",
            });
        })
        .catch((error)=>{
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
    function saveItem(){
        let unit = unitOfMeasurement;
        if(unit === ""){
            unit = null
        }
        api.put("/caracteristic/" + props.id,{
            name: name,
            unitOfMeasurement: unit
        })
        .then((response) => {
            console.log(response.data);
            toast.success('As alterações da característica foi salvar com sucesso!', {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: true,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "dark",
            });
        })
        .catch((error)=>{
            console.log(error.response);
            let data = error.response.data;
            if(error.response.status === 400){
                let erro = "";
                if(data.name !== null){
                    erro += "Nome da característica: " + data.name
                }
                if(data.unitOfMeasurement !== null){
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
    function openModal() {
        setModal(true);
    }
    function closeModal() {
        setModal(false);
    }
    useEffect(()=>{
        api.get("/caracteristic/" + props.id)
        .then((response) => {
            setIcon(response.data.icon);
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
    })
    function upload() {
        const formData = new FormData();
        formData.append("file", selectedFile);
        api.post("/caracteristic/" + props.id + "/upload", formData)
        .then((response) => {
            setIcon(response.data.icon);
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
    }
    return(<div>
        <Modal
            isOpen={modal}
            onRequestClose={closeModal}
            contentLabel="modal"
            className="ModalContent"
            overlayClassName="ModalOverlay"
        >
            {
                (icon === "Imagem ainda não foi inserida!")?<p>Essa categoria ainda não tem imagem!</p>:<img height="200px" src={"https://carlux-grupo1.s3.us-east-2.amazonaws.com" + icon} alt="imagem da categoria"/>
            }
            <input type='file' onChange={(e)=> setSelectedFile(e.target.files[0])}/>
            <button onClick={()=>upload()}>Enviar</button>
        </Modal>
        <form onSubmit={(event)=>{event.preventDefault()}}>
        <input placeholder='Nome da característica' onChange={(event)=>setName(event.target.value)} value={name}/>
            <input placeholder='Unidade de medida' onChange={(event)=>setUnitOfMeasurement(event.target.value)} value={unitOfMeasurement}/>
            <button onClick={()=>openModal()}>Ver imagem</button>
            <button onClick={()=>saveItem()}>Salvar</button>
            <button onClick={()=>deleteItem()}>Excluir</button>
        </form>
    </div>)
}
export default CaracteristicItemPanel;