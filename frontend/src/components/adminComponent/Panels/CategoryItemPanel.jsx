import React, { useEffect, useState } from 'react';
import api from '../../../services/api';
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Modal from 'react-modal';
import './categoryItemPanel.css';

function CategoryItemPanel(props){
    const [urlImage, setUrlImage] = useState("");
    const [selectedFile, setSelectedFile] = useState([]);
    const [modal, setModal] = React.useState(false);
    const [qualification, setQualification] = useState(props.qualification);
    const [descritpion, setDescritpion] = useState(props.descritpion);
    function deleteItem() {
        api.delete("/category/" + props.id)
        .then((response) => {
            console.log("Deletado com sucesso!");
            toast.success('A categoria foi deletado com sucesso!', {
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
        api.put("/category/" + props.id,{
            descritpion: descritpion,
            qualification: qualification
        })
        .then((response) => {
            console.log(response.data);
            toast.success('As alterações da categoria foi salvar com sucesso!', {
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
    function openModal() {
        setModal(true);
    }
    function closeModal() {
        setModal(false);
    }
    useEffect(()=>{
        api.get("/category/" + props.id)
        .then((response) => {
            setUrlImage(response.data.urlImage);
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
        api.post("/category/" + props.id + "/upload", formData)
        .then((response) => {
            setUrlImage(response.data.urlImage);
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
                (urlImage === "Imagem ainda não foi inserida!")?<p>Essa categoria ainda não tem imagem!</p>:<img height="200px" src={"https://carlux-grupo1.s3.us-east-2.amazonaws.com" + urlImage} alt="imagem da categoria"/>
            }
            <input type='file' onChange={(e)=> setSelectedFile(e.target.files[0])}/>
            <button onClick={()=>upload()}>Enviar</button>
        </Modal>
        <form onSubmit={(event)=>{event.preventDefault()}}>
            <input placeholder='Nome da categoria' onChange={(event)=>setQualification(event.target.value)} value={qualification}/>
            <textarea placeholder='Descrição' onChange={(event)=>setDescritpion(event.target.value)} value={descritpion}/>
            <button onClick={()=>openModal()}>Ver imagem</button>
            <button onClick={()=>saveItem()}>Salvar</button>
            <button onClick={()=>deleteItem()}>Excluir</button>
        </form>
    </div>)
}
export default CategoryItemPanel;