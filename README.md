# alfa_test

Для запуска приложения в docker на Linux необходимо:
1. Перейти в терминале в корень приложения
2. gradle clean build  
3. docker build -t currency_app:1.0 .   точка в конце команды обязательна
4. docker run --name currency_app -d -p $PORT:8080 -t currency_app:1.0
5. После запуска контейнера в браузере набираем localhost:$PORT/currency/{symbols}

$PORT - порт на котором мы разворачиваем наше приложение.
{symbols} - трех значный код валюты. 
Пример localhost:8080/currency/RUB
