# mts_business_trip
MTS Bank Hackathon

* [Описание задания](#task)
* [Решение](solution#)
* [Технологический стек](#tech_stack)
  * [Fronend](#front_stack)
  * [Backend](#back_stack)
* [Инструкции](#instruction)
    * [Запуск Backend'а](#start_backend)
    * [Запуск Frontend'а](#start_front)
* [Тесты](#test_cases)


## <a name="task"></a> Описание задачи
<div style="text-align:justify">
*Создание сервиса автоматической организации командировок.
В рамках этого кейса необходимо разработать инновационный веб-сервис, который позволит компаниям 
автоматизировать и оптимизировать процесс организации командировок для сотрудников. Основной 
целью кейсаявляется упрощение и ускорение процесса планирования,бронирования и оформления поездок,
 а также повышение эффективности управления корпоративными командировками.*
</div>

## <a name="solution"></a> Описание решения
<div style="text-align:justify">
В рамках этого MVP был разработал Frontend и Backend. Для их связи был использован OpenAPI 3.0.1.
Для пользователей были определены 3 основные роли: "сотрудник", "руководитель" и"бухгалтер". Для каждой роли был создан отдельный интерфейс с различным функционалом. Для всех ролей предусмотрен функционал создания заявки на командировку, просмотра статуса заявок,
а также формирование авансового отчёта. Для Руководителя доступен функционал принятия заявки. Для Бухгалтера доступны функции одобрения заявки и просмотра авансового отчёта.
Для удобства развёртывания всегор проекта был использован Docker compose.
</div>


## <a name="tech_stack"></a> Технологический стек
### <a name="front_stack"></a> Frontend
* React
### <a name="back_stack"></a> Backend
* Java 17 или выше
* Spring-Boot
* Spring-Cloud
* springdoc-openApi
* Maven
* Docker

## <a name="instruction"></a> Инструкции
### <a name="start_backend"></a> Запуск Backend'а
Для запуска необходимо иметь установленный `Docker` версии `2.25` и выше. 
#### Собираем `docker-image` для каждого сервиса
    $ bash mvnw clean install spring-boot:build-image
#### Предоставляем права для запуска скриптов
    $ chmod +x ./assets/*
#### Запускаем Backend-сервисы
    $ docker-compose up

### <a name="start_front"></a> Запуск Frontend'а
*not implemented yet (description in progress)*

## <a name="test_cases"></a> Тест-кейсы
[Test_plan.pdf](tech_documentation%2FTest_plan.pdf)