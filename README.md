# Процедура запуска автотестов
1. Запустить Docker Desktop
2. Открыть проект в IntelliJ IDEA
3. В терминале в корне проекта запустить контейнеры:
   * docker-compose -p reportportal up -d --force-recreate
4. В файле docker-compose1.yml надать на двойную стрелочку для запуска
5. Запустить приложение: 
   * java -jar .\artifacts\aqa-shop\aqa-shop.jar --spring.datasource.url=jdbc:mysql://localhost:3306/app
6. Запустить тесты:
   * .\gradlew clean test -DdbUrl=jdbc:mysql://localhost:3306/app
7. Запустить отчёт Allure:
   * .\gradlew allureServe и открыть в браузере:
8. В браузере для запуска ReportPortal ввести адрес: 
   * http://localhost:8181/ui/#login
   * Логин superadmin
   * Пароль erebus


## Документация


1. [План автоматизации](docs/Plan.md)

2. [Отчётные документы по итогам тестирования](docs/Report.md)

3. [Отчётные документы по итогам автоматизации](docs/Summary.md)
