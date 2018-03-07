package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Game;
import be.mielnoelanders.bazinga.domain.Publisher;
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

    @Mock
    private Publisher publisher;

    @Test
    public void getOneTest(){
        Game game = new Game();
        game.setTitle("title");

        Optional<Game> resultFromRepository = Optional.of(game);
        // instruct the mock of the repo what to do
        Mockito.when(this.repository.findById(3L)).thenReturn(resultFromRepository);
        // mocking done

        Game resultFromService = this.gameService.getOne(3L);

        assertEquals("title", resultFromService.getTitle());

        Mockito.verify(this.repository).getOne(3L);
        //Mockito.verify(this.repo,Mockito.times(3)).getOne(3L);

    }


}
