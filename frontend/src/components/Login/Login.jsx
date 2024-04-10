import './Login.css';
import { Link } from 'react-router-dom'
import useFormValidation from '../../hooks/useFormValidation';
import { useEffect } from "react";

function Login({ onLogin, isLoading, isError }) {

  const {handleChange, values, errors, isValid} = useFormValidation();

  function handleSubmit(evt) {
    evt.preventDefault();
    onLogin(values.username, values.password);
  }

  return (
    <section className='register'>
      <Link to={'/'}>
        <img className='header__logo header__logo-min' src="//static.mts.ru/mts_rf/images/logo-eco.svg"></img>
      </Link>
      <h1 className='register__title'>Рады видеть!</h1>
      <form  className='register__form' name='profile-edit' onSubmit={handleSubmit} isValid={isValid} noValidate>
      <label className='register__label'>
        <span className='register__span'>Login</span>
        <input 
          className='register__input'
          id='username'
          name='username'
          type='username'
          placeholder='Введите login'
          value={values.username ? values.username : ''}
          required
          onChange={handleChange}
        />
        <span className='register__error'>{errors.username}</span>
      </label>
      <label className='register__label'>
        <span className='register__span'>Пароль</span>
        <input 
          className='register__input'
          minLength='6'
          maxLength='20'
          id='password'
          name='password'
          type='password'
          placeholder='Введите пароль'
          value={values.password ? values.password : ''}
          required
          onChange={handleChange}
        />
        <span className='register__error'>{errors.password}</span>  
      </label>
      {isError && <div className='profile__error'>Неверный логин или пароль!</div>}
      <button 
        className={`login__submit ${isValid ? '' : 'register__submit_disabled'}`}
        type='submit'
        disabled={!isValid}>
          {isLoading ? "Вход..." : "Войти"}
      </button>
      </form>
    </section>
  );
}

export default Login;