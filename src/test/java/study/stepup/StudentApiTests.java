package study.stepup;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class StudentApiTests {
    private int currentStudentId;
    private static final String BASE_URL = "http://localhost:8080";

    @SneakyThrows
    private void createStudentForTests(String name) {
        //ObjectMapper objectMapper = new ObjectMapper();
        //Student student = new Student(name);
        Response response = given().log().all().
                when().
                contentType(ContentType.JSON).
                body("{ \"name\":" + "\"" + name + "\"}").
                post(BASE_URL + "/student").
                then().
                assertThat().
                statusCode(201).
                header("Content-Type", equalTo("application/json")).
                extract().response();
        currentStudentId = response.getBody().as(Integer.class);
    }

    @SneakyThrows
    private void createStudentWithMarksForTests(String name, int[] marks) {
        JSONObject studentParams = new JSONObject();
        studentParams.put("name", name);
        studentParams.put("marks", marks);
        Response response = given().log().all().
                when().
                contentType(ContentType.JSON).
                body(studentParams.toString()).
                post(BASE_URL + "/student").
                then().
                assertThat().
                statusCode(201).
                header("Content-Type", equalTo("application/json")).
                extract().response();
        currentStudentId = response.getBody().as(Integer.class);
    }

    @AfterEach
    public void cleanStudents() {
        if (currentStudentId != 0) {
            given().log().all().
                    when().
                    delete(BASE_URL + "/student/" + currentStudentId).
                    then().
                    assertThat().
                    statusCode(200);
            System.out.println("Удален студент с id = " + currentStudentId);
        }
    }

    @Test
    @DisplayName("Получение существующего студента по id")
    public void getExistingStudent() {
        createStudentWithMarksForTests("Pasha1",null);
        //createStudentForTests("Pasha1");
        given().log().all().
                when().
                get(BASE_URL + "/student/" + currentStudentId).
                then().
                assertThat().
                statusCode(200).
                body("id", equalTo(currentStudentId));
    }

    @Test
    @DisplayName("Попытка получения несуществующего студента по id")
    public void getNonExistingStudent() {
        int nonExistingId = -1;
        given().log().all().
                when().
                get(BASE_URL + "/student/" + nonExistingId).
                then().
                assertThat().
                statusCode(404);
    }

    @Test
    @DisplayName("Обновление существующего студента")
    @SneakyThrows
    public void updateStudent() {
        createStudentWithMarksForTests("Pasha2",null);
        String newStudentName = "Pasha2Upd";
        Student newStudent = new Student(newStudentName);
        newStudent.setId(currentStudentId);
        ObjectMapper objectMapper = new ObjectMapper();
        given().log().all().
                when().
                contentType(ContentType.JSON).
                body(objectMapper.writeValueAsString(newStudent)).
                post(BASE_URL + "/student").
                then().
                assertThat().
                statusCode(201).
                extract().response();

        given().log().all().
                when().
                get(BASE_URL + "/student/" + currentStudentId).
                then().
                assertThat().
                statusCode(200).
                body("id", equalTo(currentStudentId)).
                body("name", equalTo(newStudentName));
    }

    @Test
    @DisplayName("Создание нового студента")
    @SneakyThrows
    public void createNewStudent() {
        createStudentWithMarksForTests("Pasha3",null);
    }

    @Test
    @DisplayName("Попытка создания нового студента без имени")
    @SneakyThrows
    public void createNewStudentWithoutName() {
        given().log().all().
                when().
                contentType(ContentType.JSON).
                body("{}").
                post(BASE_URL + "/student").
                then().
                assertThat().
                statusCode(400);
    }

    @Test
    @DisplayName("Удаление существующего студента")
    @SneakyThrows
    public void deleteExistingStudent() {
        createStudentWithMarksForTests("PashaForDelete",null);
        given().log().all().
                when().
                delete(BASE_URL + "/student/" + currentStudentId).
                then().
                assertThat().
                statusCode(200);
        currentStudentId = 0;
    }

    @Test
    @DisplayName("Попытка удаления несуществующего студента")
    @SneakyThrows
    public void deleteNonExistingStudent() {
        int nonExistingId = -1;
        given().log().all().
                when().
                delete(BASE_URL + "/student/" + nonExistingId).
                then().
                assertThat().
                statusCode(404);
    }

    @Test
    @DisplayName("Попытка получения топового студента, когда БД пустая")
    public void getTopStudentWithEmptyDb() {
        given().log().all().
                when().
                get(BASE_URL + "/topStudent").
                then().
                assertThat().
                statusCode(200).
                body(equalTo(""));
    }

    @Test
    @DisplayName("Попытка получения топового студента, когда есть только студенты без оценок")
    public void getTopStudentWithNoMarks() {
        createStudentWithMarksForTests("PashaWithoutMarks",null);
        given().log().all().
                when().
                get(BASE_URL + "/topStudent").
                then().
                assertThat().
                statusCode(200).
                body(equalTo(""));
    }

    @Test
    @DisplayName("Получение топового студента")
    public void getTopStudent() {
        createStudentWithMarksForTests("PashaTop", new int[]{5, 5, 5, 5, 5});
        given().log().all().
                when().
                get(BASE_URL + "/topStudent").
                then().
                assertThat().
                statusCode(200).
                body("size()", is(1)).
                body("id[0]", equalTo(currentStudentId)).
                extract().response();
        //JsonPath jsonPath = response.jsonPath();
        //Assertions.assertTrue(response.getBody().jsonPath().getInt("id[0]")==currentStudentId);
    }

    @Test
    @DisplayName("Получение нескольких топовых студентов")
    public void getSeveralTopStudents() {
        createStudentWithMarksForTests("PashaTop1", new int[]{5, 5, 5, 5, 5});
        createStudentWithMarksForTests("PashaTop2", new int[]{5, 5, 5, 5, 5});
        given().log().all().
                when().
                get(BASE_URL + "/topStudent").
                then().
                assertThat().
                statusCode(200).
                body("size()", is(2)).
                body("id[0]", equalTo(currentStudentId - 1)).
                body("id[1]", equalTo(currentStudentId)).
                extract().response();
        //удаление первого созданного студента
        given().log().all().
                when().
                delete(BASE_URL + "/student/" + (currentStudentId - 1)).
                then().
                assertThat().
                statusCode(200);
    }
}
