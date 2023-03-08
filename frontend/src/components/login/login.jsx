import React, { useState } from 'react'
import './login.css'
import { Link } from 'react-router-dom'


function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/;





  const handleSignupForm = (event) => {
    event.preventDefault()

    if (!validate()) return;
    //console.log({email, password})
  }

  const [status, setStatus] = useState({
    type: '',
    message: ''
  })

  const valueLoginUser = {
    email: email,
    password: password,


  }


  function validate() {
    if (valueLoginUser.email === '') return setStatus({ type: 'error', message: 'Necessario preencher o campo login' });
    if (valueLoginUser.password === '') return setStatus({ type: 'error', message: 'Necessario preencher o campo senha' });
    if (valueLoginUser.password.length < 8) return setStatus({ type: 'error', message: 'Tamanho da senha insuficiente' });
    if (valueLoginUser.password.length > 20) return setStatus({ type: 'error', message: 'Tamanho da senha fora do padrao' });
    if (!regex.test(valueLoginUser.password)) return setStatus({
      type: 'error', message: 'Minimo de 8 caracteres; uma letra maiúscula; uma letra minúscula e um número'
    });

    //apagar o status da mensagem
    setStatus({
      type: '',
      message: ''
    })
    return true;
  }
  console.log(password)

  return (
    <div className="Login">

      <form action="" className="loginbar" onSubmit={handleSignupForm}>
        <h1 className='loginTitle'><span>LUX</span>CAR</h1>

        <div className="inputs">

          <div className="inputLogin">
            <h5 className='acessar'>Acesse pela sua conta Lux:</h5>
            {status.type === 'error' ? <p style={{ color: "red" }}>{status.message}</p> : ""}

            <input className='input1' type="email" name='email' placeholder='Email' required value={email} onChange={(event) => setEmail(event.target.value)} />
            {/* <i class="uil uil-envelope EmailIcon"></i> */}


            <input className='input1' type="password" name='password' placeholder='Senha' requiredvaleu={password} onChange={(event) => setPassword(event.target.value)} />
            {/* <i class="uil uil-eye-slash eye"></i> */}


            <button type='submit' className='buttonE '>Entrar</button>
          </div>

          <div className="createaccount">
            <h5 className='cadastrar'>Ainda não é cadastrado?</h5>

              <Link to={"/createAccount"}><button className='buttonE'>Criar Conta</button></Link>

            <div className="icons">
              <i class="uil uil-check-circle checkedIcon"><span className='iconBig'>Rápido e fácil reservar</span></i>
              <i class="uil uil-check-circle checkedIcon"><span className='iconBig'>Descontos de até 40%</span></i>
              <i class="uil uil-check-circle checkedIcon"><span className='iconBig'>Acesso a ofertas exclusivas  </span></i>
            </div>

          </div>
        </div>
      </form>
    </div>
  );
};

export default Login;