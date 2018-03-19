package be.mielnoelanders.bazinga.restcontroller;

import be.mielnoelanders.bazinga.BazingaApplication;
import be.mielnoelanders.bazinga.domain.basicitems.Accessory;
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
public class AccessoryEndPointIT {

    //FIELDS
    private static final String BASE_URI = "/api/accessory";
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
        //create Accessory object

        Publisher publisher = new Publisher();
        publisher.setName("AccessoryWebTest");
        publisher.setWebsite("AccessoryWebTest");

        Address address = new Address();
        address.setPostalCode("AccessoryWebTest");
        address.setNumber("AccessoryWebTest");
        address.setStreet("AccessoryWebTest");
        address.setCity("AccessoryWebTest");
        address.setCountry("AccessoryWebTest");

        Customer customer = new Customer();
        customer.setFirstName("AccessoryWebTest");
        customer.setName("AccessoryWebTest");
        customer.setPhoneNumber("AccessoryWebTest");
        customer.setGoodCustomer(true);
        customer.setEmail("AccessoryWebTest");
        customer.setAddress(address);

        Supplier supplier = new Supplier();
        supplier.setName("AccessoryWebTest");
        supplier.setPhoneNumber("AccessoryWebTest");
        supplier.setEmail("AccessoryWebTest");
        supplier.setWebsite("AccessoryWebTest");

        Accessory newAccessory = new Accessory();
        newAccessory.setName("AccessoryWebTest");

        SalesReceipt salesReceipt = new SalesReceipt();
        salesReceipt.setDate("AccessoryWebTest");
        salesReceipt.setItem(newAccessory);
        salesReceipt.setSellingPrice(39.99);
        salesReceipt.setCustomer(customer);

        PurchaseReceipt purchaseReceipt = new PurchaseReceipt();
        purchaseReceipt.setSupplier(supplier);
        purchaseReceipt.setDate("AccessoryWebTest");
        purchaseReceipt.setPurchasePrice(15.59);
        purchaseReceipt.setItem(newAccessory);

        //testAddOne()
        HttpEntity<Accessory> entityAddOne = new HttpEntity<>(newAccessory, httpHeaders);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        ResponseEntity<Accessory> responseEntityAddOne = testRestTemplate.postForEntity(createURLWithPort(BASE_URI + "/"), entityAddOne, Accessory.class);
        checkBodyAndHttpStatusResponseEntity(responseEntityAddOne, 1, HttpStatus.CREATED);
        assertThat(responseEntityAddOne.getBody().getName()).isEqualToIgnoringCase("AccessoryWebTest");
        Long newId = responseEntityAddOne.getBody().getId();

        //test updateOneById()
        newAccessory.setName("Accessory gewijzigd");
        ResponseEntity<Accessory> responseEntityUpdateOne = testRestTemplate.exchange(createURLWithPort(BASE_URI + "/" + newId), HttpMethod.PUT, entityAddOne, Accessory.class);
        checkBodyAndHttpStatusResponseEntity(responseEntityUpdateOne, 1, HttpStatus.OK);
        assertThat(responseEntityUpdateOne.getBody().getName()).isEqualTo("Accessory gewijzigd");
        assertThat(responseEntityUpdateOne.getBody().getId()).isEqualTo(newId);

        //test findAll() : list must contain minimal 1 game
        ResponseEntity<Iterable> iterableResponseEntity = testRestTemplate.getForEntity(createURLWithPort(BASE_URI + "/findall"), Iterable.class);
        checkBodyAndHttpStatusResponseEntity(iterableResponseEntity, 1, HttpStatus.OK);
        assertThat((List) iterableResponseEntity.getBody()).size().isGreaterThanOrEqualTo(1);

        //testFindById()
        ResponseEntity<Accessory> responseEntityFindById = testRestTemplate.getForEntity(createURLWithPort(BASE_URI + "/" + newId), Accessory.class);
        assertThat(responseEntityFindById.getBody().getName()).isEqualTo("Accessory gewijzigd");
        checkBodyAndHttpStatusResponseEntity(responseEntityFindById, 1, HttpStatus.OK);

        //testDeleteById()
        HttpEntity<Accessory> entityDeleteById = new HttpEntity<>(httpHeaders);
        ResponseEntity<Accessory> responseDelete = testRestTemplate.exchange(createURLWithPort(BASE_URI + "/" + newId),
                HttpMethod.DELETE, entityDeleteById, Accessory.class);
        checkBodyAndHttpStatusResponseEntity(responseDelete, 1, HttpStatus.OK);

        //try to lookup new game with id=newId that is deleted
        ResponseEntity<Accessory> responseEntityFind = testRestTemplate.getForEntity(createURLWithPort(BASE_URI + "/" + newId), Accessory.class);
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

