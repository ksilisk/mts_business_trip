import './App.css';
import Header from '../Header/Header';
import Main from '../Main/Main';
import { Routes, Route, useNavigate, Navigate, useLocation } from "react-router-dom";
import Error from '../Error/Error';
import Login from '../Login/Login';
import ProtectedRouteElement from "../ProtectedRoute/ProtectedRoute";
import { useEffect, useState } from "react";
import CurrentUserContext from '../../context/CurrentUserContext';
import Preloader from '../Preloader/Preloader';

function App() {

  const [loggedIn, setLoggedIn] = useState(false);
  const [currentUser, setCurrentUser] = useState({});
  const [isLoading, setIsLoading] = useState(false);
  const [isError, setError] = useState(false);
  const [isCheckToken, setIsCheckToken] = useState(true)
  
  const navigate = useNavigate();
  const { pathname } = useLocation();

  const footer =
    pathname === "/" || 
    pathname === "/movies" || 
    pathname === "/saved-movies";

  const header =
    pathname === "/" ||
    pathname === "/movies" ||
    pathname === "/saved-movies" ||
    pathname === "/profile";


  // useEffect(() => {
  //   if (localStorage.token) {
  //     Promise.all([getInfo(localStorage.token)])
  //     .then(([ ]) => {
  //       setLoggedIn(true);
  //     }) 
  //     .catch((error => console.log(`Ошибка ${error}`)))
  //   }
  // }, [loggedIn])

  // // Проверка токена при загрузке страницы
  // useEffect(() => {
  //   const token = localStorage.getItem('token');
  //   // если у пользователя есть токен в localStorage, 
  //   // функция проверит, действующий он или нет
  //   if (token){
  //     checkToken(token)
  //       .then((res) => {
  //         if (res) {
  //           setLoggedIn(true);
  //           setIsCheckToken(false)
  //         }
  //       })
  //       .catch((error => console.log(`Ошибка проверки токена ${error}`)))
  //   }
  //   else {
  //     setLoggedIn(false)
  //     setIsCheckToken(false)
  //     localStorage.clear()
  //   }
  // }, []);

  // //авторизация
  // function handleLogin(email, password) {
  //   authorize(email, password)
  //   .then(res => { 
  //       localStorage.setItem("token", res.token);
  //       setLoggedIn(true);
  //       navigate('/movies', {replace: true})
  //   })
  //   .catch((error) => {
  //     setLoggedIn(false);
  //     console.log(`Ошибка авторизации ${error}`);
  //     setError(true)
  //   })
  //   .finally(() => setIsLoading(false))
  //   setIsLoading(true)
  // }

  //выход
  function handleLogout() {
    localStorage.clear();
    navigate('/', {replace: true});
    setLoggedIn(false)
  }

  return (
    <div className="page">
    <CurrentUserContext.Provider value={currentUser}>
      {header && (
          <Header
            loggedIn={loggedIn}
          />
      )}
      <Routes>
        <Route path='/' element={
          <Main/>
        }/>
        <Route path='/signin' element={
          loggedIn ? <Navigate to='/' replace /> :
          <Login
            // onLogin={handleLogin}
            isLoading={isLoading}
          />
        }/>
        <Route path='*' element={<Error/>}/>
      </Routes>
    </CurrentUserContext.Provider>
    </div>
  );
}

export default App;