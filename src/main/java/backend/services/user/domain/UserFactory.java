package backend.services.user.domain;

import backend.exception.InvalidParameterException;

import java.time.LocalDate;
import java.util.UUID;
import java.util.regex.Pattern;

import static java.lang.Character.isDigit;

public class UserFactory {

    private static final Pattern EMAIL_PATTERN = Pattern.compile
            ("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z\\d-]+\\.)+[a-zA-Z]{2,6}$");
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{10}$");

    public User createStudent(String firstName, String lastName,
                              String stringBirthDate, String email, String phone, String address) throws Exception {

        validateBirthDate(stringBirthDate);
        validateEmail(email);
        validatePhone(phone);
        validateFirstNameAndLastName(firstName, lastName);

        String id = UUID.randomUUID().toString();
        LocalDate birthDate = LocalDate.parse(stringBirthDate);

        return new User(id, firstName, lastName, birthDate, email, phone, address);
    }

    private void validateBirthDate(String stringBirthDate) throws Exception {
        if (!DATE_PATTERN.matcher(stringBirthDate).matches()){
            throw new InvalidParameterException("Date format is invalid; Must be (yyyy-mm-dd)");
        }
    }

    private void validateEmail(String email) throws Exception {
        if(email.isEmpty()) throw new InvalidParameterException("Student email shouldn't be empty");
        if (!EMAIL_PATTERN.matcher(email).matches()){
            throw new InvalidParameterException("Email format is invalid; Must be example@something.com ");
        }
    }

    private void validatePhone(String phone) throws Exception {
        if(phone.isEmpty()) throw new InvalidParameterException("Student phone shouldn't be empty");
        if (!PHONE_PATTERN.matcher(phone).matches()){
            throw new InvalidParameterException("Phone format is invalid; Must be 10 digit without space of dot");
        }
    }

    private void validateFirstNameAndLastName(String firstName, String lastName) throws Exception {
        if(firstName.isEmpty() || lastName.isEmpty()) {
            throw new InvalidParameterException("Student firstName and lastName shouldn't be empty");
        }
        var arr = firstName.toCharArray();
        for (char value : arr) {
            if (isDigit(value)) throw new InvalidParameterException("FirsName should only contains non numeric values");
        }
        arr = lastName.toCharArray();
        for (char c : arr) {
            if (isDigit(c)) throw new InvalidParameterException("LastName should only contains non numeric values");
        }

    }
}
