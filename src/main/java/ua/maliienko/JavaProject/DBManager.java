package ua.maliienko.lab5;

import ua.maliienko.lab5.config.ConfigApp;
import ua.maliienko.lab5.student.Student;
import ua.maliienko.lab5.exception.DBException;
import ua.maliienko.lab5.db.SQLQueries;
import ua.maliienko.lab5.db.StudentFields;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**Клас-менеджер для роботи напряму з базою даних*/
public class DBManager {

    /**Метод який створює та повертає з'єднання з базою даних, яка задана в конфігурації*/
    private Connection getConnection() {
        String CONNECTION_URL = ConfigApp.getInstance().getProperty("connection.url");
        Connection con = null;
        try {
            con = DriverManager.getConnection(CONNECTION_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    /**Метод повертає список студентів з бази даних*/
    public List<Student> findAll() throws DBException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQLQueries.SELECT_ALL_STUDENTS)) {
            return getStudents(stmt);
        } catch (SQLException e) {
            throw new DBException("Cannot get all students from database", e);
        }
    }

    /**Метод отримує дані з бази даних за допомогою запиту SELECT та конвертує їх в студентів*/
    private List<Student> getStudents(PreparedStatement stmt) throws SQLException {
        List<Student> students = new ArrayList<>();
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Student student = Student.builder()
                    .id(rs.getInt(StudentFields.ID))
                    .firstName(rs.getString(StudentFields.FIRST_NAME))
                    .lastName(rs.getString(StudentFields.LAST_NAME))
                    .middleName(rs.getString(StudentFields.MIDDLE_NAME))
                    .birthDate(Date.valueOf(rs.getString(StudentFields.BIRTH_DATE)))
                    .gradebookNumber(rs.getString(StudentFields.GRADEBOOK_NUMBER))
                    .build();

            students.add(student);
        }
        return students;
    }

    /**Метод надсилає INSERT з новим студентом до бази даних*/
    public void insertStudent(Student student) throws DBException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement(SQLQueries.INSERT_STUDENT, Statement.RETURN_GENERATED_KEYS);
            execute(stmt, student);
        } catch (SQLException e) {
            throw new DBException("Cannot insert user", e);
        } finally {
            closeResource(stmt);
            closeResource(con);
        }
    }

    /**Метод виконує заповнення запиту до бази даних та виконує цей запит*/
    private boolean execute(PreparedStatement stmt, Student student) throws SQLException {
        int i = 0;
        stmt.setString(++i, student.getFirstName());
        stmt.setString(++i, student.getLastName());
        stmt.setString(++i, student.getMiddleName());
        stmt.setString(++i, student.getBirthDate().toString());
        stmt.setString(++i, student.getGradebookNumber());

        int count = stmt.executeUpdate();
        return count > 0;
    }

    /**Метод закриває отриманий ресурс*/
    private void closeResource(AutoCloseable obj) {
        if (obj != null) {
            try {
                obj.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
