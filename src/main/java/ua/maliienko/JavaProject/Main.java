package ua.maliienko.lab5;

import ua.maliienko.lab5.student.Student;
import ua.maliienko.lab5.exception.DBException;
import ua.maliienko.lab5.service.StudentService;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    /**Сканер для отримання введених даних з консолі*/
    private static final Scanner SCANNER = new Scanner(System.in);

    /**Сервіс для роботи з даними про студентів*/
    private static final StudentService studentService = new StudentService();

    public static void main(String[] args) throws DBException {

        int userChose;
        do {
            printMenu(); // виводимо меню
            userChose = SCANNER.nextInt(); // зчитуємо вибір користувача

            switch (userChose) {
                case 0 -> System.out.println("\nThank you for using our App. Bye!");
                case 1 -> printAllStudents();
                case 2 -> searchLastName();
                case 3 -> searchGradebookNumber();
                case 4 -> searchBirthDate();
                case 5 -> addNewStudent();
                default -> System.out.println("There is no such option.Please try again!");
            }
        } while (userChose != 0);
    }

    /**Метод виводіть у консоль меню додатку*/
    private static void printMenu() {
        System.out.println("\nPlease chose possible options:\n" +
                "1. See all students\n" +
                "2. Find students by last name\n" +
                "3. Find student by gradebook number\n" +
                "4. Find students by birth date(from-to)\n" +
                "5. Add new student\n" +
                "0. Exit");
    }

    /**Метод виводіть дані про всіх наявних у базі студентів*/
    private static void printAllStudents() throws DBException {
        List<Student> result = studentService.getAllStudent();
        System.out.println("All students from database: ");
        result.forEach(System.out::println);
    }

    /**Метод виконує пошук у базі по заданному прізвищу*/
    private static void searchLastName() throws DBException {
        System.out.println("Write last name for search: ");
        String lastName = SCANNER.nextLine();
        if (lastName.isBlank()) {
            System.out.println("Last name field cannot be empty. Please try again");
            searchLastName();
        } else {
            List<Student> result = studentService.getByLastName(lastName);

            if (result.isEmpty()) {
                System.out.println("There are no studentswith such last name in database");
            } else {
                System.out.println("Result of search:");
                result.forEach(System.out::println);
            }
        }
    }

    /**Метод виконує пошук у базі по номер залікової книжки*/
    private static void searchGradebookNumber() throws DBException {
        System.out.println("Write gradebook number for search: ");
        String gradebookNumber = SCANNER.nextLine();
        if (gradebookNumber.isBlank()) {
            System.out.println("Gradebook number field cannot be empty. Please try again");
            searchGradebookNumber();
        } else {
            Optional<Student> result = studentService.getByGrabebookNumber(gradebookNumber);
            result.ifPresentOrElse(System.out::println, () -> System.out.println("There is no student with such gradebook number"));
        }
    }

    /**Метод виконує пошук у базі по даті народження з-по заданим датам*/
    private static void searchBirthDate() throws DBException {
        SCANNER.nextLine(); // перехід на новий рядок, щоб запобігти збою в зчитуванні даних з консолі

        System.out.println("Write start date to search in format yyyy-mm-dd: ");
        String fromDate = SCANNER.nextLine();
        System.out.println("Write end date to search in format yyyy-mm-dd: ");
        String toDate = SCANNER.nextLine();
        if (fromDate.isBlank() || toDate.isBlank()) {
            System.out.println("Birth date field cannot be empty. Please try again");
            searchBirthDate();
        } else {
            List<Student> result = studentService.getByBirthDate(Date.valueOf(fromDate), Date.valueOf(toDate));

            if (result.isEmpty()) {
                System.out.println("There are no students with birth dates from this period in database");
            } else {
                System.out.println("Result of search:");
                result.forEach(System.out::println);
            }
        }
    }

    /**Метод додає нового студента у базу*/
    private static void addNewStudent() throws DBException {
        System.out.println("You need to fill next information about new student: ");
        SCANNER.nextLine(); // перехід на новий рядок, щоб запобігти збою в зчитуванні даних з консолі

        // питаємо дані про нового студента
        System.out.println("First name: ");
        String firstName = SCANNER.nextLine();
        System.out.println("Last name: ");
        String lastName = SCANNER.nextLine();
        System.out.println("Middle name: ");
        String middleName = SCANNER.nextLine();
        System.out.println("Birth date (yyyy-mm-dd): ");
        String birthDate = SCANNER.nextLine();
        System.out.println("Gradebooknumber: ");
        String gradebookNumber = SCANNER.nextLine();

        // валідація даних
        if (firstName.isBlank() || lastName.isBlank() || middleName.isBlank()
                || birthDate.isBlank() || gradebookNumber.isBlank()) {
            System.out.println("Any field cannot be empty. Please try again");
            addNewStudent();
        } else if (studentService.getByGrabebookNumber(gradebookNumber).isPresent()) {
            System.out.println("Student with such gradebook number is already in database. Check your data");
        } else {
            Student student = Student.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .middleName(middleName)
                    .birthDate(Date.valueOf(birthDate))
                    .gradebookNumber(gradebookNumber)
                    .build();
            studentService.addStudent(student);
            System.out.println("Student was added");
        }
    }
}
