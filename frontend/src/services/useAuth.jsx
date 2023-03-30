//Mudar pra local storage dps

import { createContext, useState, useEffect } from "react";

const AuthContext = createContext();

const AuthProvider = (props) => {
  const authSessionStorage = sessionStorage.getItem("auth");

  const [auth, setAuth] = useState(
    authSessionStorage === null ? "" : authSessionStorage
  );

  const saveToken = (tokenReceived) => {
    if (tokenReceived !== auth) {
      setAuth(tokenReceived);
      sessionStorage.setItem("auth", tokenReceived);
    }
  };

  const removeToken = () => {
    setAuth("");
    sessionStorage.removeItem("auth");
    alert("Usuário deslogado");
  };

  return (
    <AuthContext.Provider value={{ auth, saveToken, removeToken }}>
      {props.children}
    </AuthContext.Provider>
  );
};

export { AuthContext, AuthProvider };