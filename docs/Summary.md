# Отчёт о проведённой автоматизации
## Запланировано:

1. Планирование автоматизации тестирования.
    
2. Автоматизация:
   * Автоматизация позитивных и негативных UI сценариев, тестирования сервисов покупки туров ("Оплата по карте" и "Кредит по данным карты");
   * Автоматизация API сценариев;
   * Интеграция с системой репортов (Gradle, Allure, Report Portal).
   
3. Подготовке отчётных документов по итогам автоматизированного тестирования.
   
## Сделано:

1. В соответствии с планом автоматизации ([Plan.md](Plan.md)), в проекте была реализована автоматизация тестирования запланированных сценариев:
   * Позитивные сценарии покупки тура по карте;
   * Позитивные сценарии покупки тура по карте в кредит;
   * Сценарии позитивного и негативного тестирования всех полей формы (Номер карты, Месяц, Год, Владелец, CVC/CVV).
2. Подключено и настроено для интеграции с системой репортов Gradle, Allure, Report Portal.
   
3. В результате проведения тестирования сформирован отчет по итогам тестирования ([Report.md](Report.md)):
   * В отчете указано количество тест кейсов;
   * Статистика в % успешных/неуспешных кейсов;
   * Общие рекомендации по устранению багов.
   
## Сработавшие риски
* Потрачено много времени при настройке запуска симулятора банковских карт;
* Возникли сложности с поиском селекторов;
* Возникли сложности с запуском Report Portal

## Общий итог сдачи работы по срокам:
1. Планирование автоматизации тестирования: 
    * Запланировано до 04.08.2023, выполнено 15.08.2023.
2. Автоматизация тестирования: 
    * Запланировано до 14.09.2023, выполнено 20.08.2023.
3. Отчёт по результатам автоматизации: 
    * Запланировано до 18.09.2023, выполнено 01.09.2023.