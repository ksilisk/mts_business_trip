import './Profile.css';
import {Link, useLocation, useNavigate} from 'react-router-dom';
import useFormValidation from '../../hooks/useFormValidation';
import {sendAppForm} from "../../utils/MainApi";
import {useState} from "react";


function Profile() {
  return (
    <section className='application register'>
    <h1 className='register__title'>Профиль</h1>
      <div>
        <div className='title__name'>
          <strong>Имя:</strong> {localStorage.name}
        </div>
        <div className='title__name'>
          <strong>Фамилия:</strong> {localStorage.surname}
        </div>
        <div className='title__name'>
          <strong>Отчество:</strong> {localStorage.patronymic}
        </div>
        <div className='title__name'>
          <strong>Номер телефона:</strong> {localStorage.phone}
        </div>
        <div className='title__name'>
          <strong>Почта:</strong> {localStorage.email}
        </div>
        <div className='title__name'>
          <strong>Должность:</strong> {localStorage.position}
        </div>
      </div>
  </section>
  )
}

export default Profile;