import './AccountantApprove.css';
import {useEffect, useState} from "react";
import {approveByAccountant, getAllApplicationForAccountantApprove} from "../../utils/MainApi";
import {Link} from "react-router-dom";

function AccountantApprove() {
    const [requests, setRequests] = useState([]);
    const [approvedRequests, setApprovedRequests] = useState([]);

    useEffect(() => {
        console.log("lf ,kzn z yt ghjuwfbdajkvhzfjhk")
        getAllApplicationForAccountantApprove(setRequests)
            .catch((error) => console.log("error: " + error))

    }, []);

    function approve(id) {
        setApprovedRequests(prevApprovedRequests => [...prevApprovedRequests, id]);
        approveByAccountant(id)
            .catch((e) => console.log("Error while approve by accountant: " + e))
    }

    return (
        <section className='application register'>
            <div className='title__name'>
                <h2 className='register__title'>Список заявок на согласование бухгалтеру</h2>
                <ul>
                    {requests.map(request => (
                        <li key={request.application.id} className='title__name'
                            hidden={approvedRequests.includes(request.application.id)}>
                            <div><strong>Цель:</strong> {request.application.tripGoal}</div>
                            <div><strong>Обосонование:</strong> {request.application.tripArgument}</div>
                            <div><strong>Даты:</strong> {request.application.startDate} - {request.application.endDate}
                            </div>
                            <div>
                                <strong>Сотрудник:</strong> {request.employee.surname + ' ' + request.employee.name + ' ' + request.employee.patronymic}
                            </div>
                            <div><strong>Номер телефона сотрудника:</strong> {request.employee.phone}</div>
                            <div><strong>Email сотрудника:</strong> {request.employee.email}</div>
                            <div>
                                <strong>Руководитель:</strong> {request.lead.surname + ' ' + request.lead.name + ' ' + request.lead.patronymic}
                            </div>
                            <div><strong>Номер телефона руководителя:</strong>{request.lead.phone}</div>
                            <div><strong>Email руководителя:</strong>{request.lead.email}</div>
                            <button onClick={() => {
                                approve(request.application.id)
                            }}>Подтвердить
                            </button>
                            {/*TODO по хорошему бы сделать доп страничку с полной информацией, чет пока не осилил из-за ошибок*/}
                            {/*<div><Link to={`/accountant-approve/${request.application.id}`}>Ознакомиться подробнее</Link></div>*/}
                        </li>
                    ))}
                </ul>
            </div>
        </section>
    );
}

export default AccountantApprove;