# Не получается запустить приложение.
##Что сделал
1. Создал gradle проект
2. Скачал aqa-shop.jar в папку artifacts
3. Скачал application.properties в главный каталог и в папку папку artifacts(не знаю где должен лежать)
4. Добавил настройки в docker-compose.yml
   version: '3.7'
services:
  mysqldb:
    image: mysql:8.0.31
    ports:
      - '3306:3306'
    environment:
      - MYSQL_RANDOM_ROOT_PASSWORD=yes
      - MYSQL_DATABASE=app
      - MYSQL_USER=app
      - MYSQL_PASSWORD=pass
  postgresdb:
    image: postgres:14-alpine
    ports:
      - '5432:5432'
    environment:
      - POSTGRES_DB=app
      - POSTGRES_USER=app
      - POSTGRES_PASSWORD=pass
  node-app:
    build: ./gate-simulator
    ports:
      - '9999:9999'
5. Запустил Docker desktop
6. Скачал папку gate-simulator
7. Ввел команду в терминал docker-compose up(похоже запустилось)
8. Ввел команду в терминал java -jar .\artifacts\aqa-shop.jar
9. Ввел браузере http://localhost:8080/ Не заходит
## Скажите пожалуйста что я делаю не так?
