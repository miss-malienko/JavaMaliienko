package ua.maliienko.lab5.service;
import ua.maliienko.lab5.exception.DBException;
import ua.maliienko.lab5.DBManager;
import ua.maliienko.lab5.student.Student;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/** Класс-сервіс для роботи з даними про студентів*/
public class StudentService {

    /** Менеджер для роботи з базою даних */
    private final DBManager dbManager = new DBManager();

    /**
     * Метод шукає всіх студентів наявних в базі даних
     *
     * @return список всіх студентів
     * @throws DBException помилка з'єднання з базою даних
     */
    public List<Student> getAllStudent() throws DBException {
        return dbManager.findAll();
    }

    /**Метод шукає всіх студентів наявних в базі даних по заданому прізвищу*/
    public List<Student> getByLastName(String lastName) throws DBException {
        List<Student> students = dbManager.findAll();
        return students.stream()
                .filter(student -> student.getLastName().equals(lastName))
                .toList();
    }

    /**Метод шукає студента у базі по заданому номеру залікової книжки*/
    public Optional<Student> getByGrabebookNumber(String gradebookNumber) throws DBException {
        List<Student> students = dbManager.findAll();
        return students.stream()
                .filter(student -> student.getGradebookNumber().equals(gradebookNumber))
                .findAny();
    }

    /**Метод шукає всіх студентів, які мають дату народження в заданий період (з-по певні дати)*/
    public List<Student> getByBirthDate(Date fromDate, Date toDate) throws DBException {
        List<Student> students = dbManager.findAll();
        return students.stream()
                .filter(student -> student.getBirthDate().after(fromDate)
                        && student.getBirthDate().before(toDate))
                .toList();
    }

    /**Метод додає нового студента до бази даних*/
    public void addStudent(Student student) throws DBException {
        dbManager.insertStudent(student);
    }
}
