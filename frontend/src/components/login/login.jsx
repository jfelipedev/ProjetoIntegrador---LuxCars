import { useForm } from "react-hook-form";
import './login.css';
import { Link } from 'react-router-dom';
import {yupResolver} from "@hookform/resolvers/yup"
import * as yup from "yup"
import api from "../../services/api"
import { login } from "../../services/auth";
import { useNavigate } from "react-router-dom"


const validation = yup.object().shape({
  email: yup.string("Necessario preencher o campo login")
  .email("Email fora do padrao normal")
  .required("Necessario preencher o campo login"),
  
  password: yup.string("Necessario preencher o campo senha")
  .required("Necessario preencher o campo senha")
  .min(8, "A senha precisa ter no mínimo 6 caracteres")
   //.matches(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{7,}$/, "Tamanho da senha fora do padrao") 
   //A senha precisa ter no mínimo 8 caracteres, ' +
  // 'uma letra maiúscula e uma letra minúscula, ' +
  // 'um número e um caracter especial'
})

function Login() {
  const { register, handleSubmit, formState: { errors }, } = useForm(
    {
      resolver: yupResolver(validation)
    }
  );

  /*const handleSignupForm = (event) => {
    event.preventDefault()
  }*/


  const navigate = useNavigate();


  function loginUser(value) {
    console.log(value);
    
//Colocar as sms de erro aqui 500 , 404 etc, e essas linhas comentadas são da api o que ta em cima é um exemplo para setar um token no sessionStorage
    api.post("/auth", {
          email: value.email,
          password: value.password,
          isAdmin: true
      })
    .then((response) => {
       login(response.data.jwt)
      console.log(response)
      //alert("Usuário Cadastrado")
      navigate("/")
    })
    .catch((erro) => {
      console.log(erro)
      console.log("Deu errado")
    })
  }

  return (
    <div className="Login">

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

            <button type='submit' className='buttonE '>Entrar</button>
          </div>

          <div className="createaccount">
            <h5 className='cadastrar'>Ainda não é cadastrado?</h5>

            <Link to={"/createAccount"} className="registeLink"><button className='buttonE'>Criar Conta</button></Link>

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
