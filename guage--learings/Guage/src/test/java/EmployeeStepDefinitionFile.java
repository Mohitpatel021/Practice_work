import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EmployeeStepDefinitionFile {

    Response response;
    private final String url = "http://localhost:9090/api/v1/employee";

    @Step("Create employees with data <table>")
    public void implementation2(Table table) throws InterruptedException {
        List<TableRow> rows = table.getTableRows();
        for (TableRow r : rows) {
            Map<String, Object> data = new HashMap<>();
            data.put("name", r.getCell("Name"));
            data.put("email", r.getCell("Email"));
            data.put("phoneNumber", r.getCell("phoneNumber"));
            data.put("city", r.getCell("city"));
            data.put("department", r.getCell("department"));
            data.put("salary", r.getCell("salary"));
            data.put("experience", r.getCell("experience"));
            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(data)
                    .post(url + "/save");
            System.out.println("Response status code is printing over here : " + response.statusCode());
            Thread.sleep(2000);
            assertEquals(500, response.statusCode());

        }
    }

    @Step("Verify all employees are created successfully <table>")
    public void implementation1(Table table) {
        List<TableRow> rows = table.getTableRows();
        for (TableRow r : rows) {
            Map<String, Object> data = new HashMap<>();
            data.put("name", r.getCell("Name"));
            data.put("email", r.getCell("Email"));
            response = RestAssured.given().contentType(ContentType.JSON).queryParam("email", (String) data.get("email")).get(url + "/get");
            assertEquals((String) data.get("email"), (String) response.jsonPath().get("empEmail"));

        }
    }

    @Step("Delete all employees <table>")
    public void implementation3(Table table) {
        List<TableRow> tableRows = table.getTableRows();
        for (TableRow r : tableRows) {
            response = RestAssured.given().contentType(ContentType.JSON).queryParam("email", r.getCell("Email")).delete(url + "/");
            assertEquals(200, response.statusCode());
        }
    }

    @Step("update employees with data <table>")
    public void implementation4(Table table) {
        List<TableRow> rows = table.getTableRows();
        for (TableRow r : rows) {
            Map<String, Object> data = new HashMap<>();
            data.put("name", r.getCell("Name"));
            data.put("email", r.getCell("Email"));
            data.put("phoneNumber", r.getCell("phoneNumber"));
            data.put("city", r.getCell("city"));
            data.put("department", r.getCell("department"));
            data.put("salary", r.getCell("salary"));
            data.put("experience", r.getCell("experience"));
            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(data)
                    .patch(url + "/");
            System.out.println("Response status code is printing over here in update : " + response.statusCode());
            assertEquals(200, response.statusCode());
        }
    }
}
