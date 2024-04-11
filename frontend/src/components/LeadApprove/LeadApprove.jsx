import './LeadApprove.css';
import {useEffect, useState} from "react";
import {approveByLead, getAllAppsForLeadApprove} from "../../utils/MainApi";
import {Link, useNavigate} from "react-router-dom";

function LeadApprove() {
    const [requests, setRequests] = useState([]);
    const [approvedRequests, setApprovedRequests] = useState([]);

    // Эмуляция данных заявок
    useEffect(() => {
        // Ваша логика для получения списка заявок с бэкэнда
        getAllAppsForLeadApprove(localStorage.getItem("username"), setRequests)
            .catch((error) => console.log("error: " + error))

    }, []);

    function approve(id) {
        setApprovedRequests(prevApprovedRequests => [...prevApprovedRequests, id]);
        approveByLead(id)
            .catch((e) => console.log("Error while approve by lead: " + e))
    }

    return (
        <section className='application register'>
            <div className='title__name'>
                <h2 className='register__title'>Список заявок на согласование</h2>
                <ul>
                    {requests.map(request => (
                        <li key={request.application.id} className='title__name' hidden={approvedRequests.includes(request.application.id)}>
                            <div><strong>Цель:</strong> {request.application.tripGoal}</div>
                            <div><strong>Обосонование:</strong> {request.application.tripArgument}</div>
                            <div><strong>Даты:</strong> {request.application.startDate} - {request.application.endDate}</div>
                            <div><strong>Сотрудник:</strong> {request.employee.surname + ' ' + request.employee.name + ' ' + request.employee.patronymic}</div>
                            <div><strong>Номер телефона сотрудника:</strong> {request.employee.phone}</div>
                            <div><strong>Email сотрудника:</strong> {request.employee.email}</div>
                            <button onClick={() => {approve(request.application.id)}}>Подтвердить</button>
                            {/*TODO по хорошему бы сделать доп страничку с полной информацией, чет пока не осилил из-за ошибок*/}
                            {/*<div><Link to={`/lead-approve/${request.application.id}`}>Ознакомиться подробнее</Link></div>*/}
                        </li>
                    ))}
                </ul>
            </div>
        </section>
    );
}

export default LeadApprove;