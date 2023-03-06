import { useForm } from "react-hook-form";
import "./createAccount.css";
import React from "react";

function Createaccount() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  function newUser(data) {
    if (data.password !== data.repassword) {
      alert("Senhas diferentes!");
    }
    console.log(data);
  }
  return (
    <div className="createAccount">
      <form
        action=""
        className="createaccountBar"
        onSubmit={handleSubmit(newUser)}
      >
        <h1 className="createTitle">Criar Conta</h1>

        <div className="input">
          <input
            type="text"
            placeholder="Nome"
            {...register(
              "name",
              { minLength: 3, maxLength: 16 },
              { required: true }
            )}
          />
          {errors.name && <span>Nome inválido</span>}

          <input
            type="text"
            placeholder="Sobrenome"
            {...register(
              "lastName",
              { minLength: 3, maxLength: 16 },
              { required: true }
            )}
          />
          {errors.lastName && <span>Sobrenome inválido</span>}

          <input
            type="email"
            placeholder="Email"
            {...register("email", {
              pattern: /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/,
            })}
          />
          {errors.email && <span>Email inválido</span>}
          <input
            type="password"
            placeholder="Senha"
            {...register(
              "password",
              { minLength: 6, maxLength: 16 },
              { required: true }
            )}
          />
          {errors.password && <span>Senha inválida</span>}
          <input
            type="password"
            placeholder="Confirmar Senha"
            {...register("repassword", { required: true })}
          />
        </div>

        <button type="submit" className="button buttonR">
          Registrar
        </button>

        <div className="log">Já tem uma conta?<a href="/login"> Faça login</a></div>
      </form>
    </div>
  );
}

export default Createaccount;
