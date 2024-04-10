import './Header.css';
import { Link,  useLocation } from 'react-router-dom';
import { useState } from 'react';

function Header({ loggedIn }) {
  const { pathname } = useLocation();


  return (
    <header className={pathname === '/' ? 'header header__home' : 'header'}>
      <Link to={'/'}><img className='header__logo' src="//static.mts.ru/mts_rf/images/logo-eco.svg"></img></Link>
      <div className='header__container-nolog'>
        <Link to={'/signin'}>
          <button className='header__signin'>Войти</button>
        </Link>
      </div>
    </header>
  )
}

export default Header;