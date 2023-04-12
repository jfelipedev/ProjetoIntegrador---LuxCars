import React, { useState, useEffect } from 'react';
import api from '../../../services/api';
import {toast} from 'react-toastify';
import Modal from 'react-modal';
import 'react-toastify/dist/ReactToastify.css';

function ImageItemPanel(props){
    const [url, setUrl] = useState("");
    const [selectedFile, setSelectedFile] = useState([]);
    const [modal, setModal] = React.useState(false);
    const [title, setTitle] = useState(props.title);
    function deleteItem() {
        api.delete("/image/" + props.id)
        .then((response) => {
            console.log("Deletado com sucesso!");
            toast.success('Imagem foi deletado com sucesso!', {
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
    function saveImage(){
        api.put("/image/" + props.id,{
            title: title,
        })
        .then((response) => {
            console.log(response.data);
            toast.success('As alterações da imagem foi salvar com sucesso!', {
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
                    erro += "Titulo da imagem: " + data.title
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
    useEffect(()=>{
        api.get("/image/" + props.id)
        .then((response) => {
            setUrl(response.data.url);
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
        api.post("/image/" + props.id + "/upload", formData)
        .then((response) => {
            setUrl(response.data.urlImage);
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
    function openModal() {
        setModal(true);
    }
    function closeModal() {
        setModal(false);
    }
    return(
    <div>
        <Modal
            isOpen={modal}
            onRequestClose={closeModal}
            contentLabel="modal"
            className="ModalContent"
            overlayClassName="ModalOverlay"
        >
            {
                (url === "Imagem ainda não foi inserida!")?<p>Essa imagem ainda não tem imagem!</p>:<img height="200px" src={"https://carlux-grupo1.s3.us-east-2.amazonaws.com" + url} alt="imagem da categoria"/>
            }
            <input type='file' onChange={(e)=> setSelectedFile(e.target.files[0])}/>
            <button onClick={()=>upload()}>Enviar</button>
        </Modal>
        <form onSubmit={(event)=>{event.preventDefault()}}>
            <input onChange={(event)=>setTitle(event.target.value)} value={title}/>
            <button onClick={()=>openModal()}>Ver imagem</button>
            <button onClick={()=>saveImage()}>Salvar</button>
            <button onClick={()=>deleteItem()}>Excluir</button>
        </form>
    </div>)
}

export default ImageItemPanel;