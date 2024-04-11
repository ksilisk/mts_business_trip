import './ReportCard.css';
import {Link, useLocation, useNavigate} from 'react-router-dom';
import {useEffect, useState} from "react";
import {getAllWaitReportApp, getApplicationById} from "../../utils/MainApi";


function ReportCard() {
    const {pathname} = useLocation();
    const [app, setApp] = useState({});

    useEffect(() => {
        getApplicationById(pathname.split("/")[2], setApp)
            .catch((error) => console.log("error: " + error))
    }, []);

    const [reportData, setReportData] = useState({
        description: '',
        expenses: [],
        pdfFile: null
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setReportData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handlePdfChange = (e) => {
        setReportData(prevState => ({
            ...prevState,
            pdfFile: e.target.files[0]
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Логика для отправки данных на сервер (может быть добавлена позже)
        console.log(reportData);
        // Очистка данных формы после отправки
        setReportData({
            description: '',
            expenses: [],
            pdfFile: null
        });
    };

    return (
        <section className='application register'>
            <h1 className='register__title'>Список заявок ожидающих финансовый отчет</h1>
            <div>
                <h2>Financial Report</h2>
                <form onSubmit={handleSubmit}>
                    <label>
                        Description:
                        <textarea name="description" value={reportData.description} onChange={handleChange} />
                    </label>
                    <label>
                        PDF File:
                        <input type="file" accept=".pdf" onChange={handlePdfChange} />
                    </label>
                    <button type="submit">Submit</button>
                </form>
            </div>
        </section>
    )
}

export default ReportCard;