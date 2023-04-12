import "./categoryPanel.css";
import React, { useState, useEffect } from 'react';
import api from '../../../services/api';
import NewCategory from "./NewCategory";
import CategoryItemPanel from "./CategoryItemPanel";
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function CategoryPanel(){
    const [category, setCategory] = useState([]);
    function loadData() {
        setCategory([]);
        setTimeout(()=>{
            api.get("/category")
            .then((response) => {
                setCategory(response.data);
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
        <NewCategory loadData={loadData}/>
        {
            category.map(({id,descritpion,qualification})=><CategoryItemPanel key={id} loadData={loadData} id={id} qualification={qualification} descritpion={descritpion} />)
        }
    </div>)
}

export default CategoryPanel;