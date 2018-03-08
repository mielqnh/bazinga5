package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Game;
import be.mielnoelanders.bazinga.repository.GameRepository;
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

    @Test
    public void getOneTest(){
        Game.Builder game = new Game.Builder();
        game.title("testgame").edition(1);
        Game testgame = game.build();
        System.out.println(testgame.toString());
        Optional<Game> resultFromRepository = Optional.of(testgame);
        System.out.println(resultFromRepository);
        // instruct the mock of the repo what to do
        Mockito.when(this.repository.findById(3L)).thenReturn(resultFromRepository);
        // mocking done

        Game resultFromService = this.gameService.getOne(3L);

        System.out.println(resultFromRepository);
        System.out.println(resultFromService);
        System.out.println(resultFromService.getTitle());

        assertEquals("testgame", resultFromService.getTitle());

        Mockito.verify(this.repository).getOne(3L);


    }
}
