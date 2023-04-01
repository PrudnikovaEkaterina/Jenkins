package tests;

import dataForTest.Student;
import dataForTest.StudentGenerationWithFaker;
import org.junit.jupiter.api.Test;
import pages.StudentRegistrationFormPage;

import java.io.File;


public class StudentRegistrationFormTest extends TestBase {

    StudentRegistrationFormPage registrationFormPage = new StudentRegistrationFormPage();


    @Test
    public void registrationFormTest() {
        Student student = StudentGenerationWithFaker.generationNewStudentOnlyFaker();
        File file = new File("src/test/resources/ljv.png");

        registrationFormPage.openPage()
                .removeTheBanner()
                .setFirstName(student.getFirstName())
                .setLastName(student.getLastName())
                .setEmail(student.getEmail())
                .chooseGender(student.getGender())
                .setPhone(student.getPhoneNumber())
                .setDateOfBirth(student.getDayOfBirth(), student.getMonthOfBirth(), student.getYearOfBirth())
                .chooseSubjects(student.getSubject())
                .chooseHobbies(student.getHobby())
                .uploadPicture(file)
                .setCurrentAdress(student.getAddress())
                .selectState(student.getState())
                .selectCity(student.getCity())
                .submitButtonClick();

        registrationFormPage.verifyModalAppears()
                .verifyResult("Student Name", student.getFirstName() + " " + student.getLastName())
                .verifyResult("Student Email", student.getEmail())
                .verifyResult("Gender", student.getGender())
                .verifyResult("Mobile", student.getPhoneNumber())
                .verifyResult("Date of Birth", student.getDayOfBirth() + " " + student.getMonthOfBirth() + "," + student.getYearOfBirth())
                .verifyResult("Subjects", student.getSubject())
                .verifyResult("Hobbies", student.getHobby())
                .verifyResult("Picture", "ljv.png")
                .verifyResult("Address", student.getAddress())
                .verifyResult("State and City", student.getState() + " " + student.getCity());



    }
}
