import React, {useState} from 'react'
import './login.css'
import { Link } from 'react-router-dom'


function Login() {
     const [email, setEmail] = useState('')
     const [password, setPassword] = useState('')  

     const handleSignupForm = (event) => {
          event.preventDefault()
          console.log({email, password})
     }
     return(
       <div className="Login">
         <form action="" className="loginbar" onSubmit={handleSignupForm}>
           <h1 className='loginTitle'><span>LUX</span>CAR</h1>
           
           <div className="inputs">
             <div className="inputLogin">
               <h5 className='acessar'>Acesse pela sua conta Lux:</h5>
               
               <input className='input1' type="email" placeholder='Email' required value={email} onChange={(event) => setEmail(event.target.value)} />
               {/* <i class="uil uil-envelope EmailIcon"></i> */}
               
               
               <input className='input1' type="password" placeholder='Senha' requiredvaleu={password} onChange={(event) => setPassword(event.target.value)}/>
               {/* <i class="uil uil-eye-slash eye"></i> */}
               
               
               <button type='submit' className='buttonE button'>Entrar</button>
             </div>

             <div className="createaccount">
               <h5 className='cadastrar'>Ainda não é cadastrado?</h5>
               <button className='button buttonE'> <Link to="/createAccount" className="navLink activeLink">Criar Conta</Link></button>


               <div className="icons">
                 <i class="uil uil-check-circle checkedIcon"><span className='iconBig'>Rápido e fácil reservar</span></i>
                 <i class="uil uil-check-circle checkedIcon"><span className='iconBig'>Descontos de até 40%</span></i>
                 <i class="uil uil-check-circle checkedIcon"><span className='iconBig'>Acesso a ofertas exclusivas  </span></i>
               </div>

               <button className=' buttonE button'>Avançar sem cadastrado</button>
             </div>
           </div>
         </form>
       </div>
     )
}

export default Login