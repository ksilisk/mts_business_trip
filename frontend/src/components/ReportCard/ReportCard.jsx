import './ReportCard.css';
import {useLocation} from 'react-router-dom';
import {useEffect, useState} from "react";
import {getApplicationById} from "../../utils/MainApi";
import useFormValidation from "../../hooks/useFormValidation";


function ReportCard() {
    const {pathname} = useLocation();
    const [app, setApp] = useState({});
    const [isError, setError] = useState(false)

    useEffect(() => {
        getApplicationById(pathname.split("/")[2], setApp)
            .catch((error) => console.log("error: " + error))
    }, []);

    const [reportData, setReportData] = useState({
        description: '',
        pdfFile: null
    });

    const handleChange = (e) => {
        const {name, value} = e.target;
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

    const isValid = () => {
        return reportData.pdfFile != null && reportData.description.length !== 0
    }

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
            <form className='register__form' name='profile-edit' onSubmit={handleSubmit} noValidate>
                <label className='register__label'>
                    <span className='register__span'>Описание отчета</span>
                    <input
                        className='register__input'
                        name='description'
                        type='text'
                        placeholder='Напишите отчет'
                        value={reportData.description ? reportData.description : ''}
                        required
                        onChange={handleChange}
                    />
                    <span className='register__error'>{}</span>
                </label>
                <label className='register__label'>
                    <span className='register__span'>PDF-документ, подтверждающий расходы</span>
                    <input
                        className='register__input'
                        type='file'
                        accept='.pdf'
                        required
                        onChange={handlePdfChange}
                    />
                    <span className='register__error'>{}</span>
                </label>
                {reportData.pdfFile == null && reportData.description.length === 0 ? <div className='profile__error'>Необходимо заполнить все поля</div>:''}
                <button type="submit"
                        className={`login__submit`}
                        disabled={reportData.pdfFile == null && reportData.description.length === 0}>Отправить отчет</button>
            </form>
        </section>
    )
}

export default ReportCard;