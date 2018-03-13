package be.mielnoelanders.bazinga.restcontroller;

import be.mielnoelanders.bazinga.BazingaApplication;
import be.mielnoelanders.bazinga.domain.Supplier;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BazingaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class SupplierEndPointIntegrationTestIT {

    private static final String BASE_URI = "/api/supplier";

    private TestRestTemplate testRestTemplate;

    private HttpHeaders httpHeaders;

    @LocalServerPort
    private int port;

    @Before
    public void init() {
        testRestTemplate = new TestRestTemplate();
        httpHeaders = new HttpHeaders();
    }

    @Test
    public void testAddOne() {
        Supplier newSupplier = new Supplier();
        newSupplier.setName("New Supplier");
        newSupplier.setPhoneNumber("012-3456789");
        HttpEntity<Supplier> entity = new HttpEntity<>(newSupplier, httpHeaders);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        ResponseEntity<Supplier> responseEntity = testRestTemplate.postForEntity(createURLWithPort(BASE_URI + "/"), entity, Supplier.class);
        System.out.println("responseEntity.getBody = " + responseEntity.getBody());
        System.out.println("HttpStatus = " + responseEntity.getStatusCode().toString());

        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody().getName()).isEqualToIgnoringCase("new Supplier");
    }

    @Test
    public void testGetAll() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> response = testRestTemplate.exchange(createURLWithPort(BASE_URI + "/getall"),
                HttpMethod.GET, entity, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        System.out.println("response = " + response.getBody());
        String expected = "[{\"id\":10,\"name\":\"suppl1_name\",\"email\":null,\"phoneNumber\":\"suppl1_phonenumber\",\"website\":\"suppl1_website\",\"supplierGames\":[]},{\"id\":11,\"name\":\"suppl2_name\",\"email\":null,\"phoneNumber\":\"suppl2_phonenumber\",\"website\":\"suppl2_website\",\"supplierGames\":[]},{\"id\":12,\"name\":\"suppl3_name\",\"email\":null,\"phoneNumber\":\"suppl3_phonenumber\",\"website\":\"suppl3_website\",\"supplierGames\":[]}]";

        JSONAssert.assertEquals(expected, response.getBody(), false);

        ResponseEntity<Iterable> iterableResponseEntity = testRestTemplate.getForEntity(createURLWithPort(BASE_URI + "/getall"), Iterable.class);
        assertThat(iterableResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat((List) iterableResponseEntity.getBody()).size().isEqualTo(3);
    }

    @Test
    public void testFindById() {
        //find supplier with id=11 that exists
        ResponseEntity<Supplier> responseEntityOK = testRestTemplate.getForEntity(createURLWithPort(BASE_URI + "/11"), Supplier.class);
        assertThat(responseEntityOK.getBody()).isNotNull();
        assertThat(responseEntityOK.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntityOK.getBody().getName()).isEqualTo("suppl2_name");

        //find supplier with id=8 that not exists
        ResponseEntity<Supplier> responseEntityNOK = testRestTemplate.getForEntity(createURLWithPort(BASE_URI + "/8"), Supplier.class);
        assertThat(responseEntityNOK.getBody()).isNull();
        assertThat(responseEntityNOK.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testDeleteById() {
        //delete supplier with id=11 that exists
        HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> responseDelete = testRestTemplate.exchange(createURLWithPort(BASE_URI + "/11"),
                HttpMethod.DELETE, entity, String.class);
        assertThat(responseDelete.getStatusCode()).isEqualTo(HttpStatus.OK);
        //try to lookup supplier with id=11 that is deleted
        ResponseEntity<Supplier> responseEntityFind = testRestTemplate.getForEntity(createURLWithPort(BASE_URI + "/11"), Supplier.class);
        assertThat(responseEntityFind.getBody()).isNull();
        assertThat(responseEntityFind.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private String createURLWithPort(String uri) {
        String uriString = "http://localhost:" + port + uri;
        System.out.println(uriString);
        return uriString;
    }

}
