# mts_business_trip
MTS Bank Hackathon

* [Описание задания](#task)
* [Решение](#solution)
  * [Frontend](#front)
  * [Backend](#back)
  * [Сервис авторизации](#auth)
  * [Справочник сотрудников](#employee)
  * [Сервис Booking'а](#booking)
* [Технологический стек](#tech_stack)
  * [Fronend](#front_stack)
  * [Backend](#back_stack)
* [Инструкции](#instruction)
    * [Запуск Backend'а](#start_backend)
    * [Запуск Frontend'а](#start_front)
* [Тесты](#test_cases)


## <a name="task"></a> Описание задачи
Создание сервиса автоматической организации командировок.

В рамках этого кейса необходимо разработать инновационный веб-сервис, который позволит компаниям 
автоматизировать и оптимизировать процесс организации командировок для сотрудников. 

Основной целью кейсаявляется упрощение и ускорение процесса планирования,бронирования и оформления поездок, а также повышение эффективности управления корпоративными командировками.

## <a name="solution"></a> Решение
В рамках этого MVP был разработал Frontend и Backend.

Для пользователей были определены 3 основные роли: `сотрудник`, `руководитель` и `бухгалтер`. 

Для каждой роли был создан отдельный интерфейс с различным функционалом. 

Для всех ролей предусмотрен функционал создания заявки на командировку, просмотра статуса заявок,
а также формирование авансового отчёта. 

Для `Руководителя` доступен функционал принятия заявки. 

Для `Бухгалтера` доступны функции одобрения заявки и просмотра авансового отчёта.

Для удобства развёртывания всегор проекта был использован `Docker Compose`.

Для каждого сервиса представлена `Swagger` документация.

Ниже представлена информация о всех релизованных компонентах системы.

### <a name="front"></a> Frontend
На стороне фронтанда были реализованы основные операции над заявками:

1. Создание заявки
2. Согласование руководителем
3. Согласование бухгалтером
   * *Операции перечисления авансовых средств в автоматическом режиме предусмотрены, но не реализованы*
5. Прикрепление финансового отчета
6. Перенос в архив
7. Отображение всех заявок пользователя и его профиля

### <a name="back"></a> Backend
Основной компонент системы, с которым Frontend обменивается информацией.

Подробнее с документацией по этому компоненту можно ознакомиться [тут](trip-resource-server#readme)

### <a name="auth"></a> Сервис авторизации
Mock-сервис авторизации, к которму обращается Frontend в момент аутентификации и авторизации сотрудника.

Подробнее с документацией по этому компоненту можно ознакомиться [тут](trip-auth-server#readme)

### <a name="employee"></a> Справочник сотрудников
Mock-сервис с данным о сотрудниках внутри компании, используется при работе Backend'а.

Подробнее с документацией по этому компоненту можно ознакомиться [тут](trip-employee-directory#readme)

### <a name="booking"></a> Сервис Booking'а
Mock-сервис, который позволяет выбрать рейс, отель и остальные необходимые для коммандировки услуги.

Подробнее с документацией по этому компоненту можно ознакомиться [тут](trip-booking-service#readme)

## <a name="tech_stack"></a> Технологический стек
### <a name="front_stack"></a> Frontend
* JavaScript
* React
### <a name="back_stack"></a> Backend
* Java 17
* Spring-Boot
* Spring-Cloud
* Swagger
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
Для запуска необходимо иметь установленный `npm` версии `10.5` и выше.
#### Переходим в директорию с Frontend'ом
    $ cd frontend
#### Устанавливаем зависимости
    $ npm install
#### Запускаем Frontend
    $ npm start
Сервис будет доступен по адресу `http://localhost:3000`
## <a name="test_cases"></a> Тест-кейсы
[Test_plan.pdf](tech_documentation%2FTest_plan.pdf)
