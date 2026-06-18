Olá. Para rodar o sistema, basta rodar o App.java em: SistemaControleEstoque\src\main\java\sdedr

Aparentemente o NetBeans não aceita rodar o javaFx por conta de alguma coisa do mave, então se você usa o NetBeans, vá até a pasta do App e execute no terminal (necessário o maven):

mvn javafx:run <- windows

mvn clean compile javafx:run <- linux

Existe a possibilidade de que o banco de dados não seja reconhecido, para isso, vá no arquivo \SistemaControleEstoque\src\main\java\sdedr\dao\dbcon\AcessoSQLite.java e mude o caminho do banco de dados, se ele reconhecer, perfeito!