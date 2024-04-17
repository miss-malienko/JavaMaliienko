package ua.maliienko.lab5.db;
/**Запит до БД*/
public class SQLQueries {

    public static final String SELECT_ALL_STUDENTS = "SELECT * FROM student";

    public static final String INSERT_STUDENT = "INSERT INTO student VALUES (null, ?, ?, ?, ?, ?)";
}
