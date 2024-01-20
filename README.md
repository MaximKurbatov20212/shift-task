# Версии
    Java 17
    Используется система сборки Maven. 
    Версию и версии зависимотей можно посмотреть в файле pom.xml.

# Сборка проекта
    mvn compile

# Запуск
    mvn exec:java -Dexec.mainClass=nsu.maxwell.Main -Dexec.args=-s -a -f -p simple- in1.txt in2.txt