import {Link, Navigate, useNavigate} from "react-router-dom";

function Logout({setLoggedIn}) {
    localStorage.clear()
    setLoggedIn(false)
    return <Navigate to='/'/>
}

export default Logout;