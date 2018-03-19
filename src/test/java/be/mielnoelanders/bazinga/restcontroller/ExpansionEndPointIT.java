package be.mielnoelanders.bazinga.restcontroller;

import be.mielnoelanders.bazinga.BazingaApplication;
import be.mielnoelanders.bazinga.domain.basicitems.Expansion;
import be.mielnoelanders.bazinga.domain.other.Address;
import be.mielnoelanders.bazinga.domain.other.Customer;
import be.mielnoelanders.bazinga.domain.other.Publisher;
import be.mielnoelanders.bazinga.domain.other.Supplier;
import be.mielnoelanders.bazinga.domain.transferitems.PurchaseReceipt;
import be.mielnoelanders.bazinga.domain.transferitems.SalesReceipt;
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
public class ExpansionEndPointIT {

    //FIELDS
    private static final String BASE_URI = "/api/expansion";
    private TestRestTemplate testRestTemplate;
    private HttpHeaders httpHeaders;

    // FIELDS WITH MAPPINGS
    @LocalServerPort
    private int port;

    // METHODS
    // --> init
    @Before
    public void init() {
        testRestTemplate = new TestRestTemplate();
        httpHeaders = new HttpHeaders();
    }

    // --> crud
    @Test
    public void testCreateFindAllReadUpdateDelete() {
        //create Expansion object

        Publisher publisher = new Publisher();
        publisher.setName("ExpansionWebTest");
        publisher.setWebsite("ExpansionWebTest");

        Address address = new Address();
        address.setPostalCode("ExpansionWebTest");
        address.setNumber("ExpansionWebTest");
        address.setStreet("ExpansionWebTest");
        address.setCity("ExpansionWebTest");
        address.setCountry("ExpansionWebTest");

        Customer customer = new Customer();
        customer.setFirstName("ExpansionWebTest");
        customer.setName("ExpansionWebTest");
        customer.setPhoneNumber("ExpansionWebTest");
        customer.setGoodCustomer(true);
        customer.setEmail("ExpansionWebTest");
        customer.setAddress(address);

        Supplier supplier = new Supplier();
        supplier.setName("ExpansionyWebTest");
        supplier.setPhoneNumber("ExpansionWebTest");
        supplier.setEmail("ExpansionWebTest");
        supplier.setWebsite("ExpansionWebTest");

        Expansion newExpansion = new Expansion();
        newExpansion.setName("ExpansionWebTest");

        SalesReceipt salesReceipt = new SalesReceipt();
        salesReceipt.setDate("ExpansionWebTest");
        salesReceipt.setItem(newExpansion);
        salesReceipt.setSellingPrice(39.99);
        salesReceipt.setCustomer(customer);

        PurchaseReceipt purchaseReceipt = new PurchaseReceipt();
        purchaseReceipt.setSupplier(supplier);
        purchaseReceipt.setDate("ExpansionWebTest");
        purchaseReceipt.setPurchasePrice(15.59);
        purchaseReceipt.setItem(newExpansion);

        //testAddOne()
        HttpEntity<Expansion> entityAddOne = new HttpEntity<>(newExpansion, httpHeaders);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        ResponseEntity<Expansion> responseEntityAddOne = testRestTemplate.postForEntity(createURLWithPort(BASE_URI + "/"), entityAddOne, Expansion.class);
        checkBodyAndHttpStatusResponseEntity(responseEntityAddOne, 1, HttpStatus.CREATED);
        assertThat(responseEntityAddOne.getBody().getName()).isEqualToIgnoringCase("ExpansionWebTest");
        Long newId = responseEntityAddOne.getBody().getId();

        //test updateOneById()
        newExpansion.setName("Expansion gewijzigd");
        ResponseEntity<Expansion> responseEntityUpdateOne = testRestTemplate.exchange(createURLWithPort(BASE_URI + "/" + newId), HttpMethod.PUT, entityAddOne, Expansion.class);
        checkBodyAndHttpStatusResponseEntity(responseEntityUpdateOne, 1, HttpStatus.OK);
        assertThat(responseEntityUpdateOne.getBody().getName()).isEqualTo("Expansion gewijzigd");
        assertThat(responseEntityUpdateOne.getBody().getId()).isEqualTo(newId);

        //test findAll() : list must contain minimal 1 game
        ResponseEntity<Iterable> iterableResponseEntity = testRestTemplate.getForEntity(createURLWithPort(BASE_URI + "/findall"), Iterable.class);
        checkBodyAndHttpStatusResponseEntity(iterableResponseEntity, 1, HttpStatus.OK);
        assertThat((List) iterableResponseEntity.getBody()).size().isGreaterThanOrEqualTo(1);

        //testFindById()
        ResponseEntity<Expansion> responseEntityFindById = testRestTemplate.getForEntity(createURLWithPort(BASE_URI + "/" + newId), Expansion.class);
        assertThat(responseEntityFindById.getBody().getName()).isEqualTo("Expansion gewijzigd");
        checkBodyAndHttpStatusResponseEntity(responseEntityFindById, 1, HttpStatus.OK);

        //testDeleteById()
        HttpEntity<Expansion> entityDeleteById = new HttpEntity<>(httpHeaders);
        ResponseEntity<Expansion> responseDelete = testRestTemplate.exchange(createURLWithPort(BASE_URI + "/" + newId),
                HttpMethod.DELETE, entityDeleteById, Expansion.class);
        checkBodyAndHttpStatusResponseEntity(responseDelete, 1, HttpStatus.OK);

        //try to lookup new game with id=newId that is deleted
        ResponseEntity<Expansion> responseEntityFind = testRestTemplate.getForEntity(createURLWithPort(BASE_URI + "/" + newId), Expansion.class);
        checkBodyAndHttpStatusResponseEntity(responseEntityFind, 0, HttpStatus.NOT_FOUND);
    }

    // --> helpermethods
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

