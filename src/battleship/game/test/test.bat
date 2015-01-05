mkdir classes
javac -d classes\ -sourcepath .;..\..\BattleShip\ PointTest.java
javac -d classes\ -sourcepath .;..\..\BattleShip\ BoardTest.java
java -ea -classpath classes\ PointTest
java -ea -classpath classes\ BoardTest
