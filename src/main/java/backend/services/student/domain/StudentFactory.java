package backend.services.student.domain;

import backend.exception.InvalidParameterException;
import backend.services.student.domain.Student;

import java.time.LocalDate;
import java.util.UUID;
import java.util.regex.Pattern;

public class StudentFactory {

    private static Pattern Email_Pattern = Pattern.compile("");
    private static Pattern DATE_PATTERN = Pattern.compile(
            "^\\d{4}-\\d{2}-\\d{2}$");

    public Student createStudent(String firstName, String lastName,
                                 String stringBirthDate, String email, String phone, String address) throws Exception {

        validateBirthDate(stringBirthDate);
        validateEmail(email);
        validatePhone(phone);

        String id = UUID.randomUUID().toString();
        LocalDate birthDate = LocalDate.parse(stringBirthDate);

        Student newStudent = new Student(id, firstName, lastName, birthDate, email, phone, address);
        return newStudent;
    }

    private void validateBirthDate(String stringBirthDate) throws Exception {
        if (!DATE_PATTERN.matcher(stringBirthDate).matches()){
            throw new InvalidParameterException("Date format is invalid; Must be (yyyy-mm-dd)");
        }
    }

    private void validateEmail(String email) throws Exception {

    }

    private void validatePhone(String phone) throws Exception {
        // Unimplemented
    }
}
