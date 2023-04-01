package dataForTest;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class StudentGenerationWithFaker {
    private final static String[] GENDERS = {"Male", "Female", "Other"};
    private final static String[] SUBJECTS = {"English", "Maths", "Biology"};
    private final static String[] HOBBIES = {"Sports", "Reading", "Music"};
    private final static String[] STATES = {"NCR", "Uttar Pradesh", "Haryana"};
    private final static Map<String, String[]> stateCityMap = Map.of(
            "NCR", new String[]{"Delhi", "Gurgaon", "Noida"},
            "Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"},
            "Haryana", new String[]{"Karnal", "Panipat"},
            "Rajasthan", new String[]{"Jaipur", "Jaiselmer"});

    public static Student generationNewStudent() {
        Student student = new Student();
        Faker ruFaker = new Faker(new Locale("ru"));
        Faker enFaker = new Faker(new Locale("en"));
        student.setFirstName(ruFaker.name().firstName());
        student.setLastName(ruFaker.name().lastName());
        student.setEmail(enFaker.internet().emailAddress());
        student.setGender(pickingRandomValue(GENDERS));
        student.setPhoneNumber(8 + ruFaker.phoneNumber().subscriberNumber(9));

        LocalDate birthday = ruFaker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Эта строка кода выполняет преобразование объекта типа java.util.Date в объект типа java.time.LocalDate
        //
        //Более подробно, эта строка кода выполняет следующие действия:
        //
        //Вызов метода toInstant() объекта Date преобразует его в объект типа Instant, который представляет момент времени на временной шкале UTC (координированное всемирное время).
        //
        //Вызов метода atZone(ZoneId.systemDefault()) создает объект типа ZonedDateTime, который представляет тот же момент времени, но с учетом часового пояса,
        // соответствующего текущей системной настройке.
        //
        //Вызов метода toLocalDate() преобразует объект ZonedDateTime в объект типа LocalDate, который представляет дату без учета времени и часового пояса.
        //
        //Таким образом, данная строка кода выполняет преобразование объекта Date в объект LocalDate, который удобно использовать для работы с датами без учета времени и часовых поясов.

        student.setDayOfBirth(birthday.format(DateTimeFormatter.ofPattern("dd")));

        student.setMonthOfBirth(StringUtils.capitalize(birthday.getMonth().name().toLowerCase()));
        // получаю имя месяца (все большими буквами),
        // затем toLowerCase() все буквы делает маленькими, а StringUtils.capitalize делает первую букву слова заглавной.

        student.setYearOfBirth(String.valueOf(birthday.getYear()));

        student.setSubject(pickingRandomValue(SUBJECTS));
        student.setHobby(pickingRandomValue(HOBBIES));
        student.setAddress(ruFaker.address().fullAddress());
        String randomState = pickingRandomValue(STATES);
        student.setState(randomState);
        String randomCity = pickingRandomValue(generateRandomCities(randomState));
        student.setCity(randomCity);
        return student;
    }

    public static String pickingRandomValue(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    public static String[] generateRandomCities(String state) {
        switch (state) {
            case "NCR":
                return new String[]{"Delhi", "Gurgaon", "Noida"};
            case "Uttar Pradesh":
                return new String[]{"Agra", "Lucknow", "Merrut"};
            case "Haryana":
                return new String[]{"Karnal", "Panipat"};
            default:
                return new String[]{""};
        }
    }

    public static Student generationNewStudentOnlyFaker() {
        Student student = new Student();
        Faker faker = new Faker(new Locale("en"));
        student.setFirstName(faker.name().firstName());
        student.setLastName(faker.name().lastName());
        student.setEmail(faker.internet().emailAddress());

        // faker.options().nextElement(Массив/Список) - Возвращает случайный элемент из массива/Списка.
        // faker.options().option() - Возвращает случайный элемент из varargs/Enum. varargs = это "список переменной длины, в которой лежат аргументы"

        student.setGender(faker.options().nextElement(GENDERS));
        student.setPhoneNumber(8 + faker.phoneNumber().subscriberNumber(9));
        Date fakerDate = faker.date().birthday();

//      SimpleDateFormat. Это класс, который нужен для приведения даты в определяемый вами формат.
//      Создаем форматы для дня, месяца, года

        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

        student.setDayOfBirth(dayFormat.format(fakerDate));
        student.setMonthOfBirth(StringUtils.capitalize(monthFormat.format(fakerDate))); // StringUtils.capitalize - чтобы название месяца было с большой буквы
        student.setYearOfBirth(yearFormat.format(fakerDate));

        student.setSubject(faker.options().option(SUBJECTS));
        student.setHobby(faker.options().option(HOBBIES));
        student.setAddress(faker.address().fullAddress());

        //Person person = new Person();
        //Вместо явного указания типа можно использовать var: var person = new Person(); var может использоваться при объявлении локальных переменных,
        // включая индексные переменные цикла for и ресурсные переменные оператора try-with-resources.
        ////var нельзя использовать для полей, параметров методов и возвращаемых типов методов. Причина заключается в том, что типы в этих местах
        // явно присутствуют в class-файлах и в спецификациях Javadoc.

        var  stateCityMap1 = Map.of(
                "NCR", new String[]{"Delhi", "Gurgaon", "Noida"},
                "Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"},
                "Haryana", new String[]{"Karnal", "Panipat"},
                "Rajasthan", new String[]{"Jaipur", "Jaiselmer"});

        String state = faker.options().nextElement(stateCityMap.keySet().toArray(new String[0]));
        // stateCityMap.keySet() - собираем ключи мапы, toArray - преобразуем в стринговый массив,
        // new String[0] - предаем пустой массива без предварительно заданного размера;
        // faker.options().nextElement - из массива выбираем случайный элемент

        student.setState(state);

        student.setCity(faker.options().nextElement(stateCityMap.get(state))); //получаем по ранее выбранному штату список городов и выбираем случайный элемент;

        return student;
    }

}
