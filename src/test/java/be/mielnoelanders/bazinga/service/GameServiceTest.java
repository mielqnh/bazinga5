package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class GameServiceTest{

    @Autowired
    private GameService gameService;
    @Autowired
    private Game game;
    @Autowired
    private Expansion expansion;
    @Autowired
    private Supplier supplier;
    @Autowired
    private Publisher publisher;
    @Autowired
    private Address address;
    private List<Expansion> expansionList;
    private List<Game> gameList;

    @Before
    public void init(){
        address.setCountry("Belgium");
        address.setCity("Hasselt");
        address.setStreet("Sint-Jansstraat");
        address.setNumber("21");
        address.setPostalCode("3500");

        publisher.setName("publisherName");
        publisher.setWebsite("www.publisherWebsite.com");

        supplier.setAddress(address);
        supplier.setEmail("info@game.com");
        supplier.setName("supplyStore");
        supplier.setPhoneNumber("911");

        gameList = new ArrayList<>();

        expansion.setActualPrice(10.00);
        expansion.setEdition(2);
        expansion.setNormalPrice(20.00);
        expansion.setPromotionPercentage(10);
        expansion.setPublisher(publisher);
        expansion.setTitle("TestGameExpansion");
        expansion.setStock(5);
        expansion.setExpansionNumber("20");
        expansion.setSupplier(supplier);
        expansion.setExpandedGame(game);

        expansionList = new ArrayList<>();
        expansionList.add(expansion);

        game.setTitle("Testgame");
        game.setExpansions(expansionList);
        game.setSupplier(supplier);
        game.setPublisher(publisher);
        game.setActualPrice(100.00);
        game.setPromotionPercentage(10);
        game.setNormalPrice(99.99);
        game.setEdition(1);

        gameList.add(game);
        publisher.setGames(gameList);
        supplier.setGames(gameList);

        System.out.println(game);
        System.out.println(expansion);
        System.out.println(publisher);
        System.out.println(supplier);
        System.out.println(address);
        System.out.println(gameList);
    }

    @Test
    public void soutTest(){
        System.out.println("hey");
    }


}
