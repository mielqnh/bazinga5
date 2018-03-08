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

    private Game testgame1;

    @Before
    public void init(){
        Game.Builder game = new Game.Builder();
        game.title("testgame").edition(1);
        testgame1 = game.build();
    }

    @Test
    public void getOneTest(){

        Optional<Game> initGame = Optional.of(testgame1);
        Mockito.when(this.repository.findById(3L)).thenReturn(initGame);
        // mocking done

        Game resultFromService = this.gameService.getOne(3L);

        System.out.println(resultFromRepository);
        System.out.println(resultFromService);
        System.out.println(resultFromService.getTitle());

        assertEquals("testgame", resultFromService.getTitle());

        Mockito.verify(this.repository).getOne(3L);
    }
}
