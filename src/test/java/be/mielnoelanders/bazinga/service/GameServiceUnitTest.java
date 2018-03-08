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

    private Game testGame1;
    private Optional<Game> testOptionalGame1;

    private Game testGame2;
    private Optional<Game> testOptionalGame2;

    private Game testGame3;
    private Optional<Game> testOptionalGame3;

    @Before
    public void init(){
        Game.Builder game1 = new Game.Builder();
        game1.title("testgame1").edition(1);
        testGame1 = game1.build();
        testOptionalGame1 = Optional.of(testGame1);

        Game.Builder game2 = new Game.Builder();
        game2.title("testgame2").edition(2);
        testGame2 = game2.build();
        testOptionalGame2 = Optional.of(testGame2);

        Game.Builder game3 = new Game.Builder();
        game3.title("testgame3").edition(3);
        testGame3 = game3.build();
        testOptionalGame3 = Optional.of(testGame3);
    }

    @Test
    public void getOneTest(){

        // Hier zeg je wat de mock moet geven als je de findById oproept op de repository.
        Mockito.when(this.repository.findById(3L)).thenReturn(testOptionalGame1);

        // Hier roep je de findById op de repository aan VIA de service getOne
        Game resultFromService = this.gameService.getOne(3L);

        assertEquals("testgame1", resultFromService.getTitle());

        Mockito.verify(this.repository, Mockito.times(1)).findById(3L);
    }
}
