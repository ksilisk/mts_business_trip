import './ApplicationForm.css';
import {Link, useLocation, useNavigate} from 'react-router-dom';
import useFormValidation from '../../hooks/useFormValidation';
import {sendAppForm} from "../../utils/MainApi";
import {useState} from "react";


function ApplicationForm() {
  const navigate = useNavigate()
  const [isError, setIsError] = useState(false)
  const {handleChange, values, errors, isValid} = useFormValidation();

  function handleSubmit(evt) {
    evt.preventDefault();
    sendAppForm(JSON.stringify({
      "id": 0,
      "username": localStorage.getItem("username"),
      "incomeCity": values.city,
      "incomeCountry": values.country,
      "tripArgument": values.reason,
      "tripGoal": values.target,
      "startDate": values.date_start,
      "status": 'not approved',
      "endDate": values.date_stop,
      "prepaymentAmount": values.prepayment_amount,
      "prepaymentType": values.prepayment_type,
      "cardNumber": values.card_number
    }))
        .then( () => navigate('/'))
        .catch(() => setIsError(true))
    return navigate('/profile')
  }

  return (
    <section className='application register'>
    <h1 className='register__title'>Создать заявку</h1>
    <form onSubmit={handleSubmit}  className='register__form' name='profile-edit' isValid={isValid} noValidate>
    <label className='register__label'>
      <span className='register__span'>Имя</span>
      <input 
        className='register__input'
        type='text'
        placeholder='Введите имя'
        value={localStorage.getItem("name")}
        required
        readOnly={true}
        onChange={handleChange}
      />
      <span className='register__error'>{errors.text}</span>
    </label>
    <label className='register__label'>
      <span className='register__span'>Фамилия</span>
      <input 
        className='register__input'
        type='text'
        placeholder='Введите фамилию'
        value={localStorage.getItem("surname")}
        required
        readOnly={true}
        onChange={handleChange}
      />
      <span className='register__error'>{errors.text}</span>
    </label>
    <label className='register__label'>
      <span className='register__span'>Отчество</span>
      <input 
        className='register__input'
        type='text'
        placeholder='Введите отчество'
        value={localStorage.getItem("patronymic")}
        required
        readOnly={true}
        onChange={handleChange}
      />
      <span className='register__error'>{errors.password}</span>  
    </label>
    <label className='register__label'>
      <span className='register__span'>Телефон</span>
      <input
        className='register__input'
        type='text'
        placeholder='Введите телефон'
        value={localStorage.getItem("phone")}
        required
        readOnly={true}
        onChange={handleChange}
      />
      <span className='register__error'>{errors.password}</span>  
    </label>
    <label className='register__label'>
      <span className='register__span'>Email</span>
      <input 
        className='register__input'
        type='text'
        placeholder='Введите emal'
        value={localStorage.getItem("email")}
        required
        readOnly={true}
        onChange={handleChange}
      />
      <span className='register__error'>{errors.password}</span>  
    </label>
    <label className='register__label'>
      <span className='register__span'>Позиция</span>
      <input 
        className='register__input'
        type='text'
        placeholder='Введите позиция'
        value={localStorage.getItem("position")}
        required
        readOnly={true}
        onChange={handleChange}
      />
      <span className='register__error'>{errors.password}</span>  
    </label>
    <label className='register__label'>
      <span className='register__span'>Город въезда</span>
      <input 
        className='register__input'
        name='city'
        type='text'
        placeholder='Введите город выезда'
        value={values.city ? values.city : ''}
        required
        onChange={handleChange}
      /> 
      <span className='register__error'></span>
    </label>
    <label className='register__label'>
      <span className='register__span'>Страна въезда</span>
      <input 
        className='register__input'
        name='country'
        type='text'
        placeholder='Введите страну вьезда'
        value={values.country ? values.country : ''}
        required
        onChange={handleChange}
      /> 
      <span className='register__error'></span>
    </label>
    <label className='register__label'>
      <span className='register__span'>Основание поездки</span>
      <input 
        className='register__input'
        name='reason'
        type='text'
        placeholder='Введите основание поездки'
        value={values.reason ? values.reason : ''}
        required
        onChange={handleChange}
      /> 
      <span className='register__error'></span>
    </label>
      <label className='register__label'>
        <span className='register__span'>Цель поездки</span>
        <input
            className='register__input'
            name='target'
            type='text'
            placeholder='Введите основание поездки'
            value={values.target ? values.target : ''}
            required
            onChange={handleChange}
        />
        <span className='register__error'></span>
      </label>
    <label className='register__label' hidden={true}>
      <input
        className='register__input'
        id='text'
        name='text'
        type='text'
        value={"not approved"}
        required
        onChange={handleChange}
        hidden={true}
      /> 
    </label>
    <label className='register__label'>
      <span className='register__span'>Тип аванса</span>
      <input 
        className='register__input'
        name='prepayment_type'
        type='text'
        placeholder='Введите тип аванса (наличные, карта)'
        value={values.prepayment_type ? values.prepayment_type : ''}
        required
        onChange={handleChange}
      /> 
      <span className='register__error'></span>
    </label>
      <label className='register__label'>
        <span className='register__span' hidden={values.prepayment_type !== 'карта'}>Номер карты (опционально)</span>
        <input
            className='register__input'
            name='card_number'
            type='number'
            placeholder='Введите номер карты'
            value={values.card_number ? values.card_number : ''}
            required
            hidden={values.prepayment_type !== 'карта'}
            onChange={handleChange}
        />
        <span className='register__error' hidden={values.prepayment_type !== 'карта'}></span>
      </label>
      <label className='register__label'>
        <span className='register__span'>Сумма аванса</span>
        <input
            className='register__input'
            name='prepayment_amount'
            type='number'
            placeholder='Введите сумму аванса (руб)'
            value={values.prepayment_amount ? values.prepayment_amount : ''}
            required
            onChange={handleChange}
        />
        <span className='register__error'></span>
      </label>
    <label className='register__label'>
      <span className='register__span'>Дата окончания</span>
      <input 
        className='register__input'
        name='date_stop'
        type='date'
        value={values.date_stop}
        required
        onChange={handleChange}
      /> 
      <span className='register__error'></span>
    </label>
    <label className='register__label'>
      <span className='register__span'>Дата начала</span>
      <input 
        className='register__input'
        name='date_start'
        type='date'
        value={values.date_start}
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