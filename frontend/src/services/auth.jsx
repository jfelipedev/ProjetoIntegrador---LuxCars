//SeessionStorage pra teste qdo terminar usar o local

export const TOKEN_KEY = "@Luxcars-Token";
export const isAuthenticated = () => sessionStorage.getItem(TOKEN_KEY) !== null;
export const getToken = () => sessionStorage.getItem(TOKEN_KEY);
export const login = token => {
  // localStorage.setItem(TOKEN_KEY, token);
  sessionStorage.setItem(TOKEN_KEY, token);

};


export const logout = () => {
  sessionStorage.removeItem(TOKEN_KEY);
};

