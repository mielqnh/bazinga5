package be.mielnoelanders.bazinga.restcontroller;

import be.mielnoelanders.bazinga.BazingaApplication;
import be.mielnoelanders.bazinga.domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BazingaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerEndPointIT {

    private static final String BASE_URI = "/api/customer";

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
    public void testCreateFindAllReadUpdateDelete() {
        //create customer object
        Customer newCustomer = new Customer();
        newCustomer.setName("New Customer");
        newCustomer.setFirstName("New Customer Firstname");

        //testAddOne()
        HttpEntity<Customer> entityAddOne = new HttpEntity<>(newCustomer, httpHeaders);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        ResponseEntity<Customer> responseEntityAddOne = testRestTemplate.postForEntity(createURLWithPort(BASE_URI + "/"), entityAddOne, Customer.class);
        checkBodyAndHttpStatusResponseEntity(responseEntityAddOne, 1, HttpStatus.CREATED);
        assertThat(responseEntityAddOne.getBody().getName()).isEqualToIgnoringCase("new Customer");
        Long newId = responseEntityAddOne.getBody().getId();

        //test findAll() : list must contain miminmal 1 customer
        ResponseEntity<Iterable> iterableResponseEntity = testRestTemplate.getForEntity(createURLWithPort(BASE_URI + "/findall"), Iterable.class);
        checkBodyAndHttpStatusResponseEntity(iterableResponseEntity, 1, HttpStatus.OK);
        assertThat((List) iterableResponseEntity.getBody()).size().isGreaterThanOrEqualTo(1);

        //testFindById()
        ResponseEntity<Customer> responseEntityFindById = testRestTemplate.getForEntity(createURLWithPort(BASE_URI + "/" + newId), Customer.class);
        assertThat(responseEntityFindById.getBody().getName()).isEqualTo("New Customer");
        checkBodyAndHttpStatusResponseEntity(responseEntityFindById, 1, HttpStatus.OK);

        //testDeleteById()
        HttpEntity<String> entityDeleteById = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseDelete = testRestTemplate.exchange(createURLWithPort(BASE_URI + "/" + newId),
                HttpMethod.DELETE, entityDeleteById, String.class);
        checkBodyAndHttpStatusResponseEntity(responseDelete, 1, HttpStatus.OK);
        //try to lookup new cutomer with id=newId that is deleted
        ResponseEntity<Customer> responseEntityFind = testRestTemplate.getForEntity(createURLWithPort(BASE_URI + "/" + newId), Customer.class);
        checkBodyAndHttpStatusResponseEntity(responseEntityFind, 0, HttpStatus.NOT_FOUND);

    }

    private void checkBodyAndHttpStatusResponseEntity(ResponseEntity responseEntity, int responseBodyValue, HttpStatus httpStatus) {
        System.out.println("responseEntity.getBody()) = " + responseEntity.getBody());
        System.out.println("responseEntity.getStatusCode()) = " + responseEntity.getStatusCode());
        if (responseBodyValue == 0) {
            assertThat(responseEntity.getBody()).isNull();
        } else {
            assertThat(responseEntity.getBody()).isNotNull();
        }
        assertThat(responseEntity.getStatusCode()).isEqualTo(httpStatus);
    }

    private String createURLWithPort(String uri) {
        String uriString = "http://localhost:" + port + uri;
        System.out.println(uriString);
        return uriString;
    }
}
