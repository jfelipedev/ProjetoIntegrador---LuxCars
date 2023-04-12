//SeessionStorage pra teste qdo terminar usar o local

export const TOKEN_KEY = "@Luxcars-Token";
export const TOKEN_FIRST = "@Luxcars-First";
export const TOKEN_SUR = "@Luxcars-Sur";
export const TOKEN_ROLE = "@Luxcars-Role";


export const isAuthenticated = () => {
  const token = sessionStorage.getItem(TOKEN_KEY)
  return token !== null;
}

export const getToken = () => sessionStorage.getItem(TOKEN_KEY);

export const getTokenName = () => sessionStorage.getItem(TOKEN_FIRST);

export const getTokenSurname = () => sessionStorage.getItem(TOKEN_SUR);

export const getTokenRole = () => sessionStorage.getItem(TOKEN_ROLE);

export const login = (token, firstName, surname, role) => {
  // localStorage.setItem(TOKEN_KEY, token);
  sessionStorage.setItem(TOKEN_KEY, token);
  sessionStorage.setItem(TOKEN_FIRST, firstName);
  sessionStorage.setItem(TOKEN_SUR, surname);
  sessionStorage.setItem(TOKEN_ROLE, role);
};


export const logout = () => {
  sessionStorage.removeItem(TOKEN_KEY);
  sessionStorage.removeItem(TOKEN_FIRST);
  sessionStorage.removeItem(TOKEN_SUR);
  sessionStorage.removeItem(TOKEN_ROLE);
};