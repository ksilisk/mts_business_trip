import './ApplicationList.css';
import {Link} from 'react-router-dom';
import {useEffect, useState} from "react";
import {getAllApplicationsByUser, getStatus} from "../../utils/MainApi";

function ApplicationList() {
    const [requests, setRequests] = useState([]);

    // Эмуляция данных заявок
    useEffect(() => {
        // Ваша логика для получения списка заявок с бэкэнда
        getAllApplicationsByUser(localStorage.getItem("username"), setRequests)
            .catch((error) => console.log("error: " + error))
    }, []);

    return (
        <section className='application register'>
            <div className=''>
                <h2 className='register__title'>Список заявок</h2>
                <ul>
                    {requests.map(request => (
                        <li key={request.id} className='title__name'>
                            <div>Цель: {request.tripGoal}</div>
                            <div>Статус: {getStatus(request.status)}</div>
                            <Link to={`/list/${request.id}`}>Подробнее</Link>
                        </li>
                    ))}
                </ul>
            </div>
        </section>
    );
}

export default ApplicationList;