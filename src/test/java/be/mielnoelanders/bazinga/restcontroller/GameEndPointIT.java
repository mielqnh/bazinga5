package be.mielnoelanders.bazinga.restcontroller;

import be.mielnoelanders.bazinga.BazingaApplication;
import be.mielnoelanders.bazinga.domain.basicitems.Game;
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
public class GameEndPointIT {

    //FIELDS
    private static final String BASE_URI = "/api/game";
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
        //create Game object

        Publisher publisher = new Publisher();
        publisher.setName("GameWebTest");
        publisher.setWebsite("GameWebTest");

        Address address = new Address();
        address.setPostalCode("GameWebTest");
        address.setNumber("GameWebTest");
        address.setStreet("GameWebTest");
        address.setCity("GameWebTest");
        address.setCountry("GameWebTest");

        Customer customer = new Customer();
        customer.setFirstName("GameWebTest");
        customer.setName("GameWebTest");
        customer.setPhoneNumber("GameWebTest");
        customer.setGoodCustomer(true);
        customer.setEmail("GameWebTest");
        customer.setAddress(address);

        Supplier supplier = new Supplier();
        supplier.setName("GameWebTest");
        supplier.setPhoneNumber("GameWebTest");
        supplier.setEmail("GameWebTest");
        supplier.setWebsite("GameWebTest");

        Game newGame = new Game();
        newGame.setName("GameWebTest");

        SalesReceipt salesReceipt = new SalesReceipt();
        salesReceipt.setDate("GameWebTest");
        salesReceipt.setItem(newGame);
        salesReceipt.setSellingPrice(39.99);
        salesReceipt.setCustomer(customer);

        PurchaseReceipt purchaseReceipt = new PurchaseReceipt();
        purchaseReceipt.setSupplier(supplier);
        purchaseReceipt.setDate("GameWebTest");
        purchaseReceipt.setPurchasePrice(15.59);
        purchaseReceipt.setItem(newGame);

        //testAddOne()
        HttpEntity<Game> entityAddOne = new HttpEntity<>(newGame, httpHeaders);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        ResponseEntity<Game> responseEntityAddOne = testRestTemplate.postForEntity(createURLWithPort(BASE_URI + "/"), entityAddOne, Game.class);
        checkBodyAndHttpStatusResponseEntity(responseEntityAddOne, 1, HttpStatus.CREATED);
        assertThat(responseEntityAddOne.getBody().getName()).isEqualToIgnoringCase("GameWebTest");
        Long newId = responseEntityAddOne.getBody().getId();

        //test updateOneById()
        newGame.setName("Game gewijzigd");
        ResponseEntity<Game> responseEntityUpdateOne = testRestTemplate.exchange(createURLWithPort(BASE_URI + "/" + newId), HttpMethod.PUT, entityAddOne, Game.class);
        checkBodyAndHttpStatusResponseEntity(responseEntityUpdateOne, 1, HttpStatus.OK);
        assertThat(responseEntityUpdateOne.getBody().getName()).isEqualTo("Game gewijzigd");
        assertThat(responseEntityUpdateOne.getBody().getId()).isEqualTo(newId);

        //test findAll() : list must contain minimal 1 game
        ResponseEntity<Iterable> iterableResponseEntity = testRestTemplate.getForEntity(createURLWithPort(BASE_URI + "/findall"), Iterable.class);
        checkBodyAndHttpStatusResponseEntity(iterableResponseEntity, 1, HttpStatus.OK);
        assertThat((List) iterableResponseEntity.getBody()).size().isGreaterThanOrEqualTo(1);

        //testFindById()
        ResponseEntity<Game> responseEntityFindById = testRestTemplate.getForEntity(createURLWithPort(BASE_URI + "/" + newId), Game.class);
        assertThat(responseEntityFindById.getBody().getName()).isEqualTo("Game gewijzigd");
        checkBodyAndHttpStatusResponseEntity(responseEntityFindById, 1, HttpStatus.OK);

        //testDeleteById()
        HttpEntity<Game> entityDeleteById = new HttpEntity<>(httpHeaders);
        ResponseEntity<Game> responseDelete = testRestTemplate.exchange(createURLWithPort(BASE_URI + "/" + newId),
                HttpMethod.DELETE, entityDeleteById, Game.class);
        checkBodyAndHttpStatusResponseEntity(responseDelete, 1, HttpStatus.OK);

        //try to lookup new game with id=newId that is deleted
        ResponseEntity<Game> responseEntityFind = testRestTemplate.getForEntity(createURLWithPort(BASE_URI + "/" + newId), Game.class);
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

