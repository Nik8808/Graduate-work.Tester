# Процедура запуска автотестов
1. Запустить Docker Desktop
2. Открыть проект в IntelliJ IDEA
3. В терминале в корне проекта запустить контейнеры:
   * docker-compose up -d
4. Запустить приложение:
   * MySQL: java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar
   * PostgreSQL: java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar
5. Запустить тесты:
   * MySQL: ./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"
   * PostgreSQL: ./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"
6. Запустить отчёт Allure:
   * ./gradlew allureServe и открыть в браузере:



## Документация


1. [План автоматизации](docs/Plan.md)

2. [Отчётные документы по итогам тестирования](docs/Report.md)

3. [Отчётные документы по итогам автоматизации](docs/Summary.md)