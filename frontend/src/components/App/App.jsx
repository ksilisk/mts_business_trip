import './App.css';
import Header from '../Header/Header';
import Main from '../Main/Main';
import {Navigate, Route, Routes, useLocation, useNavigate} from "react-router-dom";
import Error from '../Error/Error';
import Login from '../Login/Login';
import {useEffect, useState} from "react";
import CurrentUserContext from '../../context/CurrentUserContext';
import {authorize, checkToken, getToken, getUserInfo} from "../../utils/MainApi";
import Logout from "../Logout/Logout";
import ApplicationForm from '../ApplicationForm/ApplicationForm';
import ApplicationList from "../ApplicationList/ApplicationList";
import ApplicationCard from "../ApplicationCard/ApplicationCard";
import Profile from "../Profile/Profile";
import Report from "../Report/Report";
import ReportCard from "../ReportCard/ReportCard";

function App() {

    const [loggedIn, setLoggedIn] = useState(false);
    const [currentUser, setCurrentUser] = useState({});
    const [isLoading, setIsLoading] = useState(false);
    const [isError, setError] = useState(false);
    const [isCheckToken, setIsCheckToken] = useState(true)

    const navigate = useNavigate();
    const {pathname} = useLocation();

    const header =
        pathname === "/" ||
        pathname === "/application" ||
        pathname.startsWith("/list") ||
        pathname.startsWith("/accountant-approve") ||
        pathname.startsWith("/lead-approve") ||
        pathname.startsWith("/report") ||
        pathname === "/profile";

    useEffect(() => {
        if (!localStorage.getItem("token")) {
            getToken()
                .then(([]) => {
                    setLoggedIn(true)
                })
                .catch((error => console.log(`Ошибка ${error}`)))
        }
    })

    // useEffect(() => {
    //   if (localStorage.token) {
    //     Promise.all([getInfo(localStorage.token)])
    //     .then(([ ]) => {
    //       setLoggedIn(true);
    //     })
    //     .catch((error => console.log(`Ошибка ${error}`)))
    //   }
    // }, [loggedIn])

    // Проверка токена при загрузке страницы
    useEffect(() => {
        const token = localStorage.getItem('token');
        // если у пользователя есть токен в localStorage,
        // функция проверит, действующий он или нет
        if (token) {
            checkToken(token)
                .then((res) => {
                    if (res) {
                        setLoggedIn(true);
                        setIsCheckToken(false)
                    }
                })
                .catch((error => {
                    console.log(`Ошибка проверки токена ${error}`)
                    localStorage.clear()
                    setLoggedIn(false)
                }))
        } else {
            setLoggedIn(false)
            setIsCheckToken(false)
            localStorage.clear()
        }
    }, []);

    // //авторизация
    function handleLogin(username, password) {
        authorize(username, password)
            .then(res => {
                setLoggedIn(true);
                getUserInfo(username)
                    .then(() => navigate('/profile', {replace: true}))
            })
            .catch((error) => {
                setLoggedIn(false);
                console.log(`Ошибка авторизации ${error}`);
                setError(true)
            })
            .finally(() => setIsLoading(false))
        setIsLoading(true)
    }

    //выход
    function handleLogout() {
        localStorage.clear();
        navigate('/', {replace: true});
        setLoggedIn(false)
        setError(false)
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
                    <Route path='/report/*' element={<ReportCard/>}/>
                    <Route path='/report' element={<Report/>}/>
                    <Route path='/profile' element={<Profile/>}/>
                    <Route path='/list/*' element={<ApplicationCard/>}/>
                    <Route path='/application' element={<ApplicationForm/>}/>
                    <Route path='/list' element={<ApplicationList/>}/>
                    <Route path='/signin' element={
                        loggedIn ? <Navigate to='/' replace/> :
                            <Login
                                onLogin={handleLogin}
                                isLoading={isLoading}
                                isError={isError}
                            />
                    }/>
                    <Route path='/logout' element={<Logout setLoggedIn={setLoggedIn}/>}/>
                    <Route path='*' element={<Error/>}/>
                </Routes>
            </CurrentUserContext.Provider>
        </div>
    );
}

export default App;