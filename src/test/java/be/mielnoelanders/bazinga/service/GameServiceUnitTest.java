package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Game;
import be.mielnoelanders.bazinga.repository.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceUnitTest {

    @InjectMocks
    private GameServiceImpl gameService;

    @Mock
    private GameRepository repository;

    private Game testGame;
    private Optional<Game> testOptionalGame;

    @Before
    public void init(){
        Game.Builder game = new Game.Builder();
        game.title("testgame").edition(1);
        testGame = game.build();
        testOptionalGame = Optional.of(testGame);
    }

    @Test
    public void getOneTest(){

        // Hier zeg je wat de mock moet geven als je de findById oproept op de repository.
        Mockito.when(this.repository.findById(3L)).thenReturn(testOptionalGame);

        // Hier roep je de findById op de repository aan VIA de service getOne
        Game resultFromService = this.gameService.getOne(3L);

//        System.out.println(testOptionalGame);
//        System.out.println(resultFromService);
//        System.out.println(resultFromService.getTitle());

        assertEquals("testgame", resultFromService.getTitle());

        //Mockito.verify(this.repository).getOne(3L);
    }
}
