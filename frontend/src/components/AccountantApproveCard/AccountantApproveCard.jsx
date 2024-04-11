import './AccountantApproveCard.css';
import {useLocation} from 'react-router-dom';
import {useEffect, useState} from "react";
import {getAccountantApplicationById, getApplicationById, getStatus} from "../../utils/MainApi";

function AccountantApproveCard() {
    const {pathname} = useLocation();
    const [app, setApp] = useState({});

    useEffect(() => {
        console.log("dsafjhasdhfasklghdksafhja")
        getAccountantApplicationById(pathname.split("/")[2], setApp)
            .catch((error) => console.log("error: " + error))
    }, []);
// TODO добавить валидаюцию по роли пользователя
    return (
        <section className='application register'>
            <div key={app.application.id}>
                <h2 className='register__title'>Информация по заявке</h2>
                <div className='title__name'>
                    <strong>Цель:</strong> {app.application.tripGoal}
                </div>
                <div className='title__name'>
                    <strong>Статус:</strong> {getStatus(app.application.status)}
                </div>
                {/*<div className='title__name'>*/}
                {/*    <strong>Обоснование:</strong> {app.application.tripArgument}*/}
                {/*</div>*/}
                {/*<div className='title__name'>*/}
                {/*    <strong>Город вьезда:</strong> {app.application.incomeCity}*/}
                {/*</div>*/}
                {/*<div className='title__name'>*/}
                {/*    <strong>Страна вьезда:</strong> {app.application.incomeCountry}*/}
                {/*</div>*/}
                {/*<div className='title__name'>*/}
                {/*    <strong>Дата начала:</strong> {app.application.startDate}*/}
                {/*</div>*/}
                {/*<div className='title__name'>*/}
                {/*    <strong>Дата конца:</strong> {app.application.endDate}*/}
                {/*</div>*/}
                {/*<div className='title__name'>*/}
                {/*    <strong>Тип аванса:</strong> {app.prepaymentType}*/}
                {/*</div>*/}
                {/*<div className='title__name'>*/}
                {/*    <strong>Сумма аванса (руб):</strong> {app.application.prepaymentAmount}*/}
                {/*</div>*/}
                {/*<div className='title__name' hidden={app.application.prepaymentType !== 'карта'}>*/}
                {/*    <strong>Номер карты:</strong> {app.application.cardNumber}*/}
                {/*</div>*/}
            </div>
        </section>
    );
}

export default AccountantApproveCard;