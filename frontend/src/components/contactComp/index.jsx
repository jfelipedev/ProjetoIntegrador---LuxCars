import React, { useState } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './styles.css'
import api from "../../services/api"

const Contact = () => {
  const [name, setName] = useState('');
  const [erroName, setErroName] = useState(null);
  const [email, setEmail] = useState('');
  const [erroEmail, setErroEmail] = useState(null);
  const [message, setMessage] = useState('');
  const [erroMessage, setErroMessage] = useState(null);

  const handleSubmit = (e) => {
    e.preventDefault();
    api.post("/contactUsMessage", {
      email: email,
      title: name,
      body: message
    })
    .then((response) =>{
      console.log(response.data);
      setName('');
      setEmail('');
      setMessage('');
      setErroName(null)
      setErroEmail(null)
      setErroMessage(null)
      toast.success('Sua mensagem foi enviado com sucesso!', {
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
    .catch((erro)=>{
      if(erro.response.status === 400){
        setErroName(erro.response.data.title)
        setErroEmail(erro.response.data.email)
        setErroMessage(erro.response.data.body)
      }
      else if(erro.response.status === 500){
        toast.error(erro.response.data, {
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
      else if(erro.response.status === 501 || erro.response.status === 502){
        toast.error("Os nossos serviços estão indisponivel do momento! Aguarde...", {
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
  };

  return (  
    <div className="formContainer">
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
      <h1>Fale Conosco</h1>
      <form onSubmit={handleSubmit} className="form">
        <label htmlFor="name">Nome:</label>
        <input
          type="text"
          id="name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        /> <br />
        <span className="spanError" style={{display: (erroName === null) ? "none" : "block"}}> {erroName} </span>
        <label htmlFor="email">Email:</label>
        <input
          type="email"
          id="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        /> <br />
        <span className="spanError" style={{display: (erroEmail === null) ? "none" : "block"}}> {erroEmail} </span>
        <label htmlFor="message" className='msgLabel'>Mensagem:</label> <br />
        <textarea
          id="message"
          value={message}
          maxLength="800"
          onChange={(e) => setMessage(e.target.value)}
        /><br />
        <span className="spanError" style={{display: (erroMessage === null) ? "none" : "block"}}> {erroMessage} </span>
        <button type="submit" className='button'>Enviar</button>
      </form>
    </div>
  );
};

export default Contact;
