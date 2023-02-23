import React, {useState} from "react";
import './createAccount.css'

function Createaccount() {

     const [name, setName] = useState('')
     const [sobrenome, setSobrenome] = useState('')  

     const [email, setEmail] = useState('')
     const [password, setPassword] = useState('')
     const [password1, setpassword1] = useState('')  
  

     const handleCreateAccount = (event, key) => {
          event.preventDefault()
          console.log({name, sobrenome, email, password, password1})
     }
     return(
       <div className="createAccount">
         <form action="" className="createaccountBar" onSubmit={handleCreateAccount}>
           <h1 className="createTitle">Criar Conta</h1>

           <div className="input">
             
             <input type="text" placeholder='Nome' required  value={name} onChange={(event) => setName(event.target.value)}/>
              
             <input type="text" placeholder='Sobrenome' required value={sobrenome} onChange={(event) => setSobrenome(event.target.value)}/>
               
             <input type="email" placeholder="Email" value={email} onChange={(event) => setEmail(event.target.value)}/>
             <i class="uil uil-envelope EmailIcon"></i>

             <input type="senha" placeholder="Senha" value={password} onChange={(event) => setPassword(event.target.value)}/>
             <i class="uil uil-eye-slash eye"></i>

             <input type="senha" placeholder="Confirmar Senha" value={password1} onChange={(event) => setpassword1(event.target.value)}/>
             <i class="uil uil-eye-slash eye"></i>
             
           </div>
           
           <button type='submit' className='button'>Entrar</button>
         </form>       
       </div>
     )
}

export default Createaccount