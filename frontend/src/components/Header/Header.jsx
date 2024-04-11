import './Header.css';
import {Link, useLocation} from 'react-router-dom';

function Header({loggedIn}) {
    const {pathname} = useLocation();

    return (
        <header className={pathname === '/' ? 'header header__home' : 'header'}>
            <Link to={'/'}><img className='header__logo' src="//static.mts.ru/mts_rf/images/logo-eco.svg"></img></Link>
            {loggedIn ? <div
                className='header__container-nolog'>{localStorage.getItem("name") + ' ' + localStorage.getItem("surname")}</div> : ""}
            <div className='header__container-nolog'>
                <Link to={'/accountant-approve'} hidden={!loggedIn || localStorage.getItem("role") !== "ACCOUNTANT"}>
                    <button className='header__signin header__application'>Заявки на одобрение</button>
                </Link>
                <Link to={'/lead-approve'} hidden={!loggedIn || localStorage.getItem("role") !== "LEAD"} >
                    <button className='header__signin header__application'>Заявки на одобрение</button>
                </Link>
                <Link to={'/report'} hidden={!loggedIn}>
                    <button className='header__signin header__application'>Отчет</button>
                </Link>
                <Link to={'/application'} hidden={!loggedIn}>
                    <button className='header__signin header__application'>Заявка</button>
                </Link>
                <Link to={'/list'} hidden={!loggedIn}>
                    <button className='header__signin header__application'>Мои заявки</button>
                </Link>
                <Link to={'/profile'} hidden={!loggedIn}>
                    <button className='header__signin header__application'>Профиль</button>
                </Link>
                <Link to={'/signin'} hidden={loggedIn}>
                    <button className='header__signin header__application'>Войти</button>
                </Link>
                <Link to={'/logout'} hidden={!loggedIn}>
                    <button className='header__signin header__application'>Выйти</button>
                </Link>
            </div>
        </header>
    )
}

export default Header;