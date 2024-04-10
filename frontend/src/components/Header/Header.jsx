import './Header.css';
import {Link, Navigate, useLocation} from 'react-router-dom';

function Header({ loggedIn }) {
  const { pathname } = useLocation();


  return (
    <header className={pathname === '/' ? 'header header__home' : 'header'}>
      <Link to={'/'}><img className='header__logo' src="//static.mts.ru/mts_rf/images/logo-eco.svg"></img></Link>
      <div className='header__container-nolog'>
      <Link to={'/application'}>
          <button className='header__signin header__application'>Заявка</button>
        </Link>
        <Link to={'/signin'}>
          <button className='header__signin'>Войти</button>
        </Link>
          <Link to={'/logout'}>
              <button className='header__signin'>Выйти</button>
          </Link>
      </div>
    </header>
  )
}

export default Header;