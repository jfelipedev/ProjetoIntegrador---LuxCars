import { useForm } from "react-hook-form";
import { useState } from "react";
import './login.css';
import { Link } from 'react-router-dom';
import {yupResolver} from "@hookform/resolvers/yup"
import * as yup from "yup"
import api from "../../services/api"
import { login } from "../../services/auth";
import { useNavigate } from "react-router-dom";



const validation = yup.object().shape({
  email: yup.string("Necessario preencher o campo login")
  .email("Email fora do padrao normal")
  .required("Necessario preencher o campo login"),
  
  password: yup.string("Necessario preencher o campo senha")
  .required("Necessario preencher o campo senha")
  .min(8, "A senha precisa ter no mínimo 8 caracteres")
   //.matches(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{7,}$/, "Tamanho da senha fora do padrao") 
   //A senha precisa ter no mínimo 8 caracteres, ' +
  // 'uma letra maiúscula e uma letra minúscula, ' +
  // 'um número e um caracter especial'
});


function Login() {

  const navigate = useNavigate();

  const [firstName, setFirstName] = useState("");
  const [surname, setSurname] = useState("");

  const [errorLogin, setErrorLogin] = useState(null);


  const { register, handleSubmit, formState: { errors }, } = useForm(
    {
      resolver: yupResolver(validation)
    }
  );

  function loginUser(value) { 

    api.post("/auth", {
          email: value.email,
          password: value.password,
      })
    .then((response) => {
      const data = response.data;
      login(response.data.jwt, data.user.firstName, data.user.surname, data.user.role, data.user.email);
      //alert("Usuário Cadastrado")
      navigate("/")
    })
    .catch((erro) => {
      let error = erro.response
      setErrorLogin(error.data.password)
      setErrorLogin(error.data.email)
      //Colocar as sms de erro aqui 500 , 404 etc, e essas linhas comentadas são da api o que ta em cima é um exemplo para setar um token no sessionStorage

    })
  }
  

  return (
    <div className="login">

      <form action="" className="loginbar" onSubmit={handleSubmit(loginUser)}>
        <h1 className='loginTitle'><span className="anima">LUX</span>CARS</h1>

        <div className="inputs">

          <div className="inputLogin">
            <h5 className='acessar'>Acesse pela sua conta Lux:</h5>

            <input className='input1'
              type="email"
              placeholder="Email"
              {...register("email")}
            />
            { <span className="spanError">{errors.email?.message}</span>}

            <input className='input1'
              type="password"
              placeholder="Senha"
              {...register("password")}
            />
            {  <span className="spanError">{errors.password?.message}</span>}
            <span className="spanError" style={{display: (errorLogin === null) ? "none" : "block"}}> {errorLogin} </span>

            <button type='submit' className='buttonE '>Entrar</button>
          </div>

          <div className="createaccount">
            <h5 className='cadastrar'>Ainda não é cadastrado?</h5>

            <Link to={"/criar-conta"} className="registeLink"><button className='buttonE'>Criar Conta</button></Link>

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
