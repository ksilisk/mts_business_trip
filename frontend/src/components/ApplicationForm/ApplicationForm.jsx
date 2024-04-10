import './ApplicationForm.css';
import { Link,  useLocation } from 'react-router-dom';
import useFormValidation from '../../hooks/useFormValidation';


function ApplicationForm({onLogin, isLoading, isError }) {
  const { pathname } = useLocation();


  const {handleChange, values, errors, isValid} = useFormValidation();

  function handleSubmit(evt) {

    evt.preventDefault();
    onLogin(values.email, values.password);
  }

  return (
    <section className='application register'>
    <h1 className='register__title'>Создать заявку</h1>
    <form  className='register__form' name='profile-edit' isValid={isValid} noValidate>
    <label className='register__label'>
      <span className='register__span'>Имя</span>
      <input 
        className='register__input'
        id='email'
        name='email'
        placeholder='Введите имя'
        required
        onChange={handleChange}
      />
      <span className='register__error'>{errors.email}</span>  
    </label>
    <label className='register__label'>
      <span className='register__span'>Фамилия</span>
      <input 
        className='register__input'
        minLength='6'
        maxLength='20'
        id='password'
        name='password'
        type='password'
        placeholder='Введите фамилию'
        value={values.password ? values.password : ''}
        required
        onChange={handleChange}
      />
      <span className='register__error'>{errors.password}</span>  
    </label>
    <label className='register__label'>
      <span className='register__span'>Отчество</span>
      <input 
        className='register__input'
        minLength='6'
        maxLength='20'
        id='password'
        name='password'
        type='password'
        placeholder='Введите отчество'
        value={values.password ? values.password : ''}
        required
        onChange={handleChange}
      />
      <span className='register__error'>{errors.password}</span>  
    </label>
    <label className='register__label'>
      <span className='register__span'>Телефон</span>
      <input 
        className='register__input'
        minLength='6'
        maxLength='20'
        id='password'
        name='password'
        type='password'
        placeholder='Введите телефон'
        value={values.password ? values.password : ''}
        required
        onChange={handleChange}
      />
      <span className='register__error'>{errors.password}</span>  
    </label>
    <label className='register__label'>
      <span className='register__span'>Email</span>
      <input 
        className='register__input'
        id='email'
        name='email'
        type='email'
        placeholder='Введите телефон'
        required
        onChange={handleChange}
      />
      <span className='register__error'>{errors.password}</span>  
    </label>
    <label className='register__label'>
      <span className='register__span'>Позиция</span>
      <input 
        className='register__input'
        id='email'
        name='email'
        type='email'
        placeholder='Введите позицию'
        required
        onChange={handleChange}
      />
      <span className='register__error'>{errors.password}</span>  
    </label>
    <label className='register__label'>
      <span className='register__span'>Город въезда</span>
      <input 
        className='register__input'
        id='email'
        name='email'
        type='email'
        placeholder='Введите город выезда'
        required
        onChange={handleChange}
      /> 
      <span className='register__error'></span>
    </label>
    <label className='register__label'>
      <span className='register__span'>Страна въезда</span>
      <input 
        className='register__input'
        id='email'
        name='email'
        type='email'
        placeholder='Введите страну выезда'
        required
        onChange={handleChange}
      /> 
      <span className='register__error'></span>
    </label>
    <label className='register__label'>
      <span className='register__span'>Основание поездки</span>
      <input 
        className='register__input'
        id='email'
        name='email'
        type='email'
        placeholder='Введите основание поездки'
        required
        onChange={handleChange}
      /> 
      <span className='register__error'></span>
    </label>
    <label className='register__label'>
      <span className='register__span'>Статус</span>
      <input 
        className='register__input'
        id='email'
        name='email'
        type='email'
        placeholder='Введите статус'
        required
        onChange={handleChange}
      /> 
      <span className='register__error'></span>
    </label>
    <label className='register__label'>
      <span className='register__span'>Тип аванса</span>
      <input 
        className='register__input'
        id='email'
        name='email'
        type='email'
        placeholder='Введите тип аванса'
        required
        onChange={handleChange}
      /> 
      <span className='register__error'></span>
    </label>
    <label className='register__label'>
      <span className='register__span'>Тип проживания</span>
      <input 
        className='register__input'
        id='email'
        name='email'
        type='email'
        placeholder='Введите тип проживания'
        required
        onChange={handleChange}
      /> 
      <span className='register__error'></span>
    </label>
    <label className='register__label'>
      <span className='register__span'>Тип транспорта</span>
      <input 
        className='register__input'
        id='email'
        name='email'
        type='email'
        placeholder='Введите тип транспорта'
        required
        onChange={handleChange}
      /> 
      <span className='register__error'></span>
    </label>
    <label className='register__label'>
      <span className='register__span'>Дата окончания</span>
      <input 
        className='register__input'
        name='email'
        type='date'
        required
        onChange={handleChange}
      /> 
      <span className='register__error'></span>
    </label>
    <label className='register__label'>
      <span className='register__span'>Дата начала</span>
      <input 
        className='register__input'
        id='email'
        name='email'
        type='date'
        required
        onChange={handleChange}
      /> 
      <span className='register__error'></span>
    </label>
    {isError && <div className='profile__succes'>Ошибка</div>}
    <button 
      className={`login__submit ${isValid ? '' : 'register__submit_disabled'}`}
      type='submit'
      disabled={!isValid}>
        Отправить
    </button>
    </form>
  </section>
  )
}

export default ApplicationForm;