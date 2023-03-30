import React, { useState } from 'react';
import './styles.css'

const Contact = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    // Code to handle form submission
    console.log('Form submitted!');
    setName('');
    setEmail('');
    setMessage('');
  };

  return (
    <div className="formContainer">
      <h1>Fale Conosco</h1>
      <form onSubmit={handleSubmit} className="form">
        <label htmlFor="name">Nome:</label>
        <input
          type="text"
          id="name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        /> <br />
        <label htmlFor="email">Email:</label>
        <input
          type="email"
          id="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        /> <br />
        <label htmlFor="message" className='msgLabel'>Mensagem:</label> <br />
        <textarea
          id="message"
          value={message}
          maxLength="800"
          onChange={(e) => setMessage(e.target.value)}
        /><br />
        <button type="submit" className='button'>Enviar</button>
      </form>
    </div>
  );
};

export default Contact;
