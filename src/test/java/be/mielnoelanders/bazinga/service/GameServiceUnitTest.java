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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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

    private Iterable<Game> gameIterable;
    private List<Game> gameList;

    @Before
    public void init() {
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

        gameList = new ArrayList<>();
        gameList.addAll(Arrays.asList(testGame1,testGame2,testGame3));
        gameIterable = gameList;
    }

    @Test
    public void getOneTest() {
        // Hier zeg je wat de mock moet geven als je de findById oproept op de repository.
        Mockito.when(this.repository.findById(3L)).thenReturn(testOptionalGame1);
        // Hier roep je de findById op de repository aan VIA de service getOne
        Game resultFromService = this.gameService.getOne(3L);
        assertEquals("testgame1", resultFromService.getTitle());
        Mockito.verify(this.repository, Mockito.times(1)).findById(3L);
    }

    @Test
    public void deleteByIdMetSpelTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(testOptionalGame1);
        Game resultFromService = this.gameService.deleteById(1L);
        assertEquals("testgame1", resultFromService.getTitle());
        Mockito.verify(this.repository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void deleteByIdSpelNietAanwezigTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(Optional.ofNullable(null));
        Game resultFromService = this.gameService.deleteById(1L);
        assertEquals(null, resultFromService);
        Mockito.verify(this.repository, Mockito.times(0)).deleteById(1L);
    }
@Test
    public void insertGameTest() {

    }

    @Test
    public void getAllTest() {
        Mockito.when(this.repository.findAll()).thenReturn(gameList);
        Iterable<Game> resultFromService = this.gameService.getAll();
        Game resultFromIterator = resultFromService.iterator().next();
        assertThat(resultFromIterator.getTitle()).isEqualToIgnoringCase("testgame1");
        Mockito.verify(this.repository, Mockito.times(1)).findAll();
    }

    public void findByTitleTest() {
    }

    public void changeGameTest() {
    }


}
