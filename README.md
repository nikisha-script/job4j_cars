# Сервис по продаже машин

Данное приложение иллюстрирует сайт по продаже машин.
На сайте есть объявления. В объявлении: описание, марка машины, тип кузова, фото. Объявление имеет статус продано или нет.

Стек технологий: 

![java](https://img.shields.io/badge/Java--17-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot--2.7.3-F2F4F9?style=for-the-badge&logo=spring-boot)
![Bootstrap](https://img.shields.io/badge/Bootstrap--5.2.2-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white)
![PostgresSQL](https://img.shields.io/badge/PostgreSQL--42.3.6-316192?style=for-the-badge&logo=postgresql&logoColor=white)
[![Hibernate](https://img.shields.io/badge/Hibernate--5.6.11-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)](https://hibernate.org/)

![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.0.15-blue)
![Liquibase](https://img.shields.io/badge/Liquibase-4.9.1-red)
![Junit](https://img.shields.io/badge/JUNIT-4.13.2-orange)
![Mockito](https://img.shields.io/badge/MOCKITO-4.6.1-red)
![H2](https://img.shields.io/badge/hsqldb-2.7.0-yellowgreen)

## ТЗ:
1. Основная страница. таблица со всеми объявлениям машин на продажу.
2. На странице должна быть кнопка. добавить новое объявление.
3. Переходить на страницу добавления.
4. Должны быть категории машины, марка, тип кузова и тд. Пример с сайта auto.ru.
5. Можно добавлять фото.
6. объявление имеет статус продано. или нет.
7. Должны существовать пользователи. кто подал заявление. только он может менять статус.

Перед запуском установите:

- Java 17
- Apache Maven 3.x
- PostgreSQL 14

## Запуск приложения

1. Создать бд:
```sql
create database car_sales;
```

2. Запуск приложения с maven. Перейдите в корень проекта через командную строку и выполните команды:
```
    mvn clean install
    mvn spring-boot:run
```


### Основная страница со всеми объявлениями и функционалом:
![](img/gn.png)

### Страницы с авторизацией и аутентификацией пользователя: 
![](img/reg.png)
![](img/auth.png)

### Добавление нового объявления:
![](img/add.png)

## Контакты
email: danya.nikisha@mail.ru



