import './LeadAppovalCard.css';
import {useLocation} from 'react-router-dom';
import {useEffect, useState} from "react";
import {getApplicationById, getLeadApplicationById, getStatus, getUserInfo} from "../../utils/MainApi";
import useFormValidation from "../../hooks/useFormValidation";
import {render} from "react-dom";


function LeadApproveCard() {
    const {pathname} = useLocation();
    const [app, setApp] = useState({});

    useEffect(() => {
        getLeadApplicationById(pathname.split("/")[2], setApp)
            .catch((error) => console.log("error: " + error))
    }, []);

    return (
        <section className='application register'>
            <div key={app?.id}>
                <h2 className='register__title'>Информация по заявке</h2>
                <div className='title__name'>
                    <strong>ФИО сотрудника:</strong> {app?.employee.surname + ' ' + app?.employee.name + ' ' + app?.employee.patronymic}
                </div>
                <div className='title__name'>
                    <strong>Номер телефона сотрудника:</strong> {app.phone}
                </div>
                <div className='title__name'>
                    <strong>Цель:</strong> {app.tripGoal}
                </div>
                <div className='title__name'>
                    <strong>Статус:</strong> {getStatus(app.status)}
                </div>
                <div className='title__name'>
                    <strong>Обоснование:</strong> {app.tripArgument}
                </div>
                <div className='title__name'>
                    <strong>Город вьезда:</strong> {app.incomeCity}
                </div>
                <div className='title__name'>
                    <strong>Страна вьезда:</strong> {app.incomeCountry}
                </div>
                <div className='title__name'>
                    <strong>Дата начала:</strong> {app.startDate}
                </div>
                <div className='title__name'>
                    <strong>Дата конца:</strong> {app.endDate}
                </div>
                <div className='title__name'>
                    <strong>Тип аванса:</strong> {app.prepaymentType}
                </div>
                <div className='title__name'>
                    <strong>Сумма аванса (руб):</strong> {app.prepaymentAmount}
                </div>
                <div className='title__name' hidden={app.prepaymentType !== 'карта'}>
                    <strong>Номер карты:</strong> {app.cardNumber}
                </div>
            </div>
        </section>
    );
}

export default LeadApproveCard;