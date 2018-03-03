package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

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


    @Before
    public void init(){
        game.setTitle("Testgame");
        game.setExpansions(null);

    }



}
