import React, {useState} from 'react'
import './login.css'



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
           <h1 className='loginTitle'>Iniciar Sessão</h1>
           
           <div className="inputs">
             <div className="inputLogin">
               <h5>Acesse pela sua conta Lux:</h5>
               
               <input type="email" placeholder='Email' required value={email} onChange={(event) => setEmail(event.target.value)} />
               <i class="uil uil-envelope EmailIcon"></i>
               
               
               <input type="password" placeholder='Senha' requiredvaleu={password} onChange={(event) => setPassword(event.target.value)}/>
               <i class="uil uil-eye-slash eye"></i>
               
               
               <button type='submit' className='button'>Entrar</button>
             </div>

             <div className="Createaccount">
               <h5>Ainda não é cadastrado?</h5>
               <button className='button'>Criar Conta</button>

               <i class="uil uil-check-circle checkedIcon">Rápido e fácil reservar</i>
               <i class="uil uil-check-circle checkedIcon">Descontos de até 40%</i>
               <i class="uil uil-check-circle checkedIcon">Acesso a ofertas exclusivas</i>

               <button className='button'>Avançar sem cadastrado</button>
             </div>
           </div>
         </form>
       </div>
     )
}

export default Login