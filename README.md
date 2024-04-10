# mts_business_trip
MTS Bank Hackathon

* [Описание задания](#task)
* [Технологический стек](#tech_stack)
  * [Fronend](#front_stack)
  * [Backend](#back_stack)
* [Инструкции](#instruction)
    * [Запуск Backend'а](#start_backend)
    * [Запуск Frontend'а](#start_front)
## <a name="task"></a> Описание задачи
*description in progress*

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