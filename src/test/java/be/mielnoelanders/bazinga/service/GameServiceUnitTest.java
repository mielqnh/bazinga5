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
        gameList.addAll(Arrays.asList(testGame1, testGame2, testGame3));
        gameIterable = gameList;
    }

    // CREATE
    @Test
    public void insertGameTest() {
        Mockito.when(this.repository.save(testGame1)).thenReturn(testGame1);
        Game resultFromService = this.gameService.addOne(testGame1);
        assertThat(resultFromService.getTitle()).isEqualToIgnoringCase("testgame1");
        Mockito.verify(this.repository, Mockito.times(1)).save(testGame1);
    }

    // READ ONE AND READ ALL
    @Test
    public void getOneTest() {
        Mockito.when(this.repository.findById(3L)).thenReturn(testOptionalGame1);
        Game resultFromService = this.gameService.findOneById(3L);
        assertEquals("testgame1", resultFromService.getTitle());
        Mockito.verify(this.repository, Mockito.times(1)).findById(3L);
    }
    @Test
    public void getAllTest() {
        Mockito.when(this.repository.findAll()).thenReturn(gameList);
        Iterable<Game> resultFromService = this.gameService.findAll();
        Game resultFromIterator = resultFromService.iterator().next();
        assertThat(resultFromIterator.getTitle()).isEqualToIgnoringCase("testgame1");
        Mockito.verify(this.repository, Mockito.times(1)).findAll();
    }

    // UPDATE
    @Test
    public void changeGameTest() {

        Mockito.when(this.repository.findById(1L)).thenReturn(testOptionalGame1);
        Mockito.when(this.repository.save(testGame1)).thenReturn(testGame1);
        Game resultFromService = this.gameService.updateOneById(1L,testGame1);
        assertEquals("testgame1", resultFromService.getTitle());
        Mockito.verify(this.repository, Mockito.times(1)).findById(1L);
        Mockito.verify(this.repository, Mockito.times(1)).save(testGame1);
    }

    // DELETE
    @Test
    public void deleteByIdMetSpelTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(testOptionalGame1);
        Game resultFromService = this.gameService.deleteOneById(1L);
        assertEquals("testgame1", resultFromService.getTitle());
        Mockito.verify(this.repository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void deleteByIdSpelNietAanwezigTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(Optional.ofNullable(null));
        Game resultFromService = this.gameService.deleteOneById(1L);
        assertEquals(null, resultFromService);
        Mockito.verify(this.repository, Mockito.times(0)).deleteById(1L);
    }

    // OTHER METHODS
    @Test
    public void findByTitleTest() {
        Mockito.when(this.repository.findByTitle("BAM")).thenReturn(gameIterable);
        Iterable<Game> resultFromService = this.gameService.findOneByTitle("BAM");
        Game resultFromIterator = resultFromService.iterator().next();
        assertThat(resultFromIterator.getTitle()).isEqualToIgnoringCase("testgame1");
        Mockito.verify(this.repository, Mockito.times(1)).findByTitle("BAM");
    }
}