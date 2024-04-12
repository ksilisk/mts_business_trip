import './Report.css';
import {Link, useNavigate} from 'react-router-dom';
import {useEffect, useState} from "react";
import {getAllWaitReportApp, getApplicationById} from "../../utils/MainApi";


function Report() {
    const [apps, setApps] = useState([])

    useEffect(() => {
        getAllWaitReportApp(localStorage.getItem("username"), setApps)
            .catch((error) => console.log("error: " + error))
    }, []);

    return (
        <section className='application register'>
            <h1 className='register__title'>Список заявок ожидающих финансовый отчет</h1>
            <div className='title__name'>
                <ul>
                    {apps.map(request => (
                        <li key={request.id} className='title__name'>
                            <div>Цель: {request.tripGoal}</div>
                            <div>Обоснование: {request.tripArgument}</div>
                            <div>Дата начала: {request.startDate}</div>
                            <div>Дата конца: {request.endDate}</div>
                            <Link to={`/report/${request.id}`}>Прикрепить отчет</Link>
                        </li>
                    ))}
                </ul>
            </div>
        </section>
    )
}

export default Report;