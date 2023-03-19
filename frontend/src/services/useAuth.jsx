import { createContext, useState, useEffect } from "react";

const AuthContext = createContext();

const AuthProvider = (props) => {
  const authLocalStorage = localStorage.getItem("auth");

  const [auth, setAuth] = useState(
    authLocalStorage === null ? "" : authLocalStorage
  );

  const saveToken = (tokenReceived) => {
    if (tokenReceived !== auth) {
      setAuth(tokenReceived);
      localStorage.setItem("auth", tokenReceived);
    }
  };

  const removeToken = () => {
    setAuth("");
    localStorage.removeItem("auth");
    alert("Usuário deslogado");
  };

  return (
    <AuthContext.Provider value={{ auth, saveToken, removeToken }}>
      {props.children}
    </AuthContext.Provider>
  );
};

export { AuthContext, AuthProvider };