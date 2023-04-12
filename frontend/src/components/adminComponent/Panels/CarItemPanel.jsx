import React, { useEffect, useState } from 'react';
import api from '../../../services/api';
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Modal from 'react-modal';
import Select from "react-select";
import './categoryItemPanel.css';

function CategoryItemPanel(props){
    const [categories, setCategories] = useState([]);
    const [cities, setCities] = useState([]);

    const [name, setName] = useState(props.nameCar);
    const [descritpion, setDescritpion] = useState(props.descritpion);
    const [price, setPrice] = useState(props.price)
    const [year, setYear] = useState(props.year);
    const [highlight, setHighlight] = useState(props.highlight);
    const [idCategory, setIdCategory] = useState(props.category.id);
    const [idCity, setIdCity] = useState(props.city.id);
    const [car, setCar] = useState({});

    const [modalImage, setModalImage] = useState(false);
    const [modalCaracteristic, setModalCaracteristic] = useState(false);

    const [listImages, setListImages] = useState([]);
    const [listCaracteristics, setListCaracteristics] = useState([]);
    function deleteItem() {
        api.delete("/car/" + props.id)
        .then((response) => {
            console.log("Deletado com sucesso!");
            toast.success('O carro foi deletado com sucesso!', {
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
        console.log(car);
        let listCaracteristics = [];
        car.caracteristics.forEach(({id, value})=>{
            listCaracteristics.push({
                id: id,
                value: value
            });
        });
        let listImages = [];
        car.images.forEach(({id})=>{
            listImages.push({
                id: id
            });
        });
        api.put("/car/" + props.id,{
            nameCar: name,
            descritpion: descritpion,
            price: price,
            year: year,
            highlight: highlight,
            idCategory: idCategory,
            idCity: idCity,
            inputCarCaracteristicDTO: listCaracteristics,
            inputCarImageDTO: listImages
        })
        .then((response) => {
            console.log(response.data);
            toast.success('As alterações do carro foi salvo com sucesso!', {
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
                if(data.nameCar !== null){
                    erro += "Nome do carro: " + data.nameCar
                }
                if(data.category !== null){
                    erro += "\nCategoria: " + data.category
                }
                if(data.descritpion !== null){
                    erro += "\nDescrição: " + data.descritpion
                }
                if(data.price !== null){
                    erro += "\nPreço: " + data.price
                }
                if(data.year !== null){
                    erro += "\nAno: " + data.year
                }
                if(data.city !== null){
                    erro += "\nCidade: " + data.city
                }
                if(data.caracteristics !== null){
                    erro += "\nCaracteristicas: " + data.caracteristics
                }
                if(data.images !== null){
                    erro += "\nImagens: " + data.images
                }
                if(data.highlight !== null){
                    erro += "\nDestaque: " + data.highlight
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
    function saveItemImage(){
        console.log(car);
        let listC = [];
        car.caracteristics.forEach(({id, value})=>{
            listC.push({
                id: id,
                value: value
            });
        });
        let listImg = [];
        listImages.forEach(({id, checked})=>{
            if(checked){
                listImg.push({
                    id: id
                });
            }
        });
        api.put("/car/" + props.id,{
            nameCar: car.nameCar,
            descritpion: car.descritpion,
            price: car.price,
            year: car.year,
            highlight: car.highlight,
            idCategory: car.category.id,
            idCity: car.city.id,
            inputCarCaracteristicDTO: listC,
            inputCarImageDTO: listImg
        })
        .then((response) => {
            console.log(response.data);
            toast.success('As alterações da lista de imagens do carro foi salvo com sucesso!', {
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
                if(data.nameCar !== null){
                    erro += "Nome do carro: " + data.nameCar
                }
                if(data.category !== null){
                    erro += "\nCategoria: " + data.category
                }
                if(data.descritpion !== null){
                    erro += "\nDescrição: " + data.descritpion
                }
                if(data.price !== null){
                    erro += "\nPreço: " + data.price
                }
                if(data.year !== null){
                    erro += "\nAno: " + data.year
                }
                if(data.city !== null){
                    erro += "\nCidade: " + data.city
                }
                if(data.caracteristics !== null){
                    erro += "\nCaracteristicas: " + data.caracteristics
                }
                if(data.images !== null){
                    erro += "\nImagens: " + data.images
                }
                if(data.highlight !== null){
                    erro += "\nDestaque: " + data.highlight
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
    function saveItemCaracteristic(){
        console.log(car);
        console.log(car);
        let listC = [];
        listCaracteristics.forEach(({id, value, checked})=>{
            if(checked === true){
                let v = value;
                if(v === ""){
                    v = null;
                }
                listC.push({
                    id: id,
                    value: v
                });
            }
        });
        let listImg = [];
        car.images.forEach(({id})=>{
            listImg.push({
                id: id
            });
        });
        api.put("/car/" + props.id,{
            nameCar: car.nameCar,
            descritpion: car.descritpion,
            price: car.price,
            year: car.year,
            highlight: car.highlight,
            idCategory: car.category.id,
            idCity: car.city.id,
            inputCarCaracteristicDTO: listC,
            inputCarImageDTO: listImg
        })
        .then((response) => {
            console.log(response.data);
            toast.success('As alterações da lista de característica do carro foi salvo com sucesso!', {
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
                if(data.nameCar !== null){
                    erro += "Nome do carro: " + data.nameCar
                }
                if(data.category !== null){
                    erro += "\nCategoria: " + data.category
                }
                if(data.descritpion !== null){
                    erro += "\nDescrição: " + data.descritpion
                }
                if(data.price !== null){
                    erro += "\nPreço: " + data.price
                }
                if(data.year !== null){
                    erro += "\nAno: " + data.year
                }
                if(data.city !== null){
                    erro += "\nCidade: " + data.city
                }
                if(data.caracteristics !== null){
                    erro += "\nCaracteristicas: " + data.caracteristics
                }
                if(data.images !== null){
                    erro += "\nImagens: " + data.images
                }
                if(data.highlight !== null){
                    erro += "\nDestaque: " + data.highlight
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
        api.get("/category")
        .then((response) => {
            let list = [];
            response.data.forEach((item) => {
            list.push({
                label: item.qualification,
                value: item.id
            })
            })
            setCategories(list)
            console.log("Deu certo - category")
        })
        .catch((error) => {
            console.log(error)
            console.log("Deu errado - category")
        })
        api.get("/city")
        .then((response) => {
            let list = [];
            response.data.forEach((item) => {
                list.push({
                label: item.nameCity,
                value: item.id
                })
            })
            setCities(list);
            console.log("Deu certo - city")
        })
        .catch((error) => {
            console.log(error);
            console.log("Deu errado - city")
        })
        api.get("/car/" + props.id)
        .then((response) => {
            console.log("Deu certo!");
            let carR = response.data
            setCar(carR);
            api.get("/image")
            .then((response) => {
                console.log("Deu certo!");
                let lista = [];
                response.data.forEach(({id,title})=>{
                    let idn = id;
                    let state = false;
                    console.log(carR);
                    carR.images.forEach(({id})=>{if(idn === id){state = true}});
                    lista.push({
                        id: idn,
                        title: title,
                        checked: state
                    });
                })
                console.log(lista);
                setListImages(lista);
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
            api.get("/caracteristic")
            .then(reponse=>{
                console.log("Deu certo!");
                let lista = [];
                reponse.data.forEach(({id,name,unitOfMeasurement})=>{
                    let idn = id;
                    let state = false;
                    let v = null;
                    console.log(carR);
                    carR.caracteristics.forEach(({id,value})=>{if(idn===id){state =true; v=value}});
                    lista.push({
                        id: idn,
                        name: name,
                        checked: state,
                        value: v,
                        unit: unitOfMeasurement
                    });
                });
                console.log(lista);
                setListCaracteristics(lista);
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
        });
    },[])
    function openModalImage() {
        setModalImage(true);
    }
    function closeModalImage() {
        setModalImage(false);
    }
    function openModalCaracteristic() {
        setModalCaracteristic(true);
    }
    function closeModalCaracteristic() {
        setModalCaracteristic(false);
    }
    return(<div>
        <Modal
            isOpen={modalImage}
            onRequestClose={closeModalImage}
            contentLabel="modal"
            className="ModalContent"
            overlayClassName="ModalOverlay"
        >
            {
                listImages.map(({id,title,checked}, index)=>{
                    return(<label key={id}>
                        {title}
                        <input type="checkbox" checked={checked} onChange={()=>{
                            let lista = [...listImages];
                            lista[index].checked = !checked;
                            console.log(lista);
                            setListImages(lista);
                        }}/>
                    </label>)
                })
            }
            <button onClick={()=>saveItemImage()}>Salvar</button>
        </Modal>
        <Modal
            isOpen={modalCaracteristic}
            onRequestClose={closeModalCaracteristic}
            contentLabel="modal"
            className="ModalContent"
            overlayClassName="ModalOverlay"
        >
            {
                listCaracteristics.map(({id, name, checked, value, unit}, index)=>{
                    return(<label key={id}>
                        <input type="checkbox" checked={checked} onChange={()=>{
                            let lista = [...listCaracteristics];
                            lista[index].checked = !checked;
                            console.log(lista);
                            setListCaracteristics(lista);
                        }}/>
                        <input type='text' onChange={(event)=>{
                            let lista = [...listCaracteristics];
                            lista[index].value = event.target.value;
                            console.log(lista);
                            setListCaracteristics(lista);
                        }} value={listCaracteristics[index].value} placeholder={name + " " + unit}/>
                    </label>)
                })
            }
             <button onClick={()=>saveItemCaracteristic()}>Salvar</button>
        </Modal>
        <form onSubmit={(event)=>{event.preventDefault()}}>
        <input placeholder='Nome do carro' onChange={(event)=>setName(event.target.value)} value={name}/>
            <textarea placeholder='Descrição' onChange={(event)=>setDescritpion(event.target.value)} value={descritpion}/>
            <input placeholder='Preço' type='number' onChange={(event)=>setPrice(event.target.value)} value={price} />
            <input placeholder='Ano' type='number' onChange={(event)=>setYear(event.target.value)} value={year} />
            <label>
            Destaque<input placeholder='destaque' type='checkbox' className='checkboxInput' onChange={(event)=>setHighlight(!highlight)} checked={highlight} />
                
            </label>
            <Select
                options={cities}
                onChange={(event)=>setIdCity(event.value)}
                className="select"
                placeholder="Cidade"
                defaultValue={{label: props.city.nameCity, value: idCity}}
            />
            <Select
                options={categories}
                onChange={(event)=>setIdCategory(event.value)}
                className="select"
                placeholder="Categoria"
                defaultValue={{label: props.category.qualification, value: idCategory}}
            />
            <button onClick={()=>openModalImage()}>Imagens</button>
            <button onClick={()=>openModalCaracteristic()}>Caracteristicas</button>
            <button onClick={()=>saveItem()}>Salvar</button>
            <button onClick={()=>deleteItem()}>Excluir</button>
        </form>
    </div>)
}
export default CategoryItemPanel;