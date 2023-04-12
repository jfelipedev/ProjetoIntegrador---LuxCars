import "./ImagePanel.css";
import React, { useState, useEffect } from 'react';
import api from '../../../services/api';
import NewImage from "./NewImage";
import ImageItemPanel from "./ImageItemPanel";
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function ImagePanel(){
    const [image, setImage] = useState([]);
    function loadData() {
        setImage([]);
        setTimeout(()=>{
            api.get("/image")
            .then((response) => {
                setImage(response.data);
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
        <NewImage loadData={loadData}/>
        {
            image.map(({id,title})=><ImageItemPanel key={id} loadData={loadData} id={id} title={title} />)
        }
    </div>)
}

export default ImagePanel;