package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.basicitems.Game;
import be.mielnoelanders.bazinga.repository.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
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
    private Iterable<Game> gameIterable;
    private List<Game> gameList;

    @Before
    public void init() {
        testGame1 = new Game();
        testGame1.setName("testgame1");
        gameList = new ArrayList<>();
        gameList.add(testGame1);
        gameIterable = gameList;
    }

    // CREATE
    @Test
    public void addOneTest() {
        Mockito.when(this.repository.save(testGame1)).thenReturn(testGame1);
        Game resultFromService = this.gameService.addOne(testGame1);
        assertThat(resultFromService.getName()).isEqualToIgnoringCase("testgame1");
        Mockito.verify(this.repository, Mockito.times(1)).save(testGame1);
    }

    // READ ONE AND READ ALL
    @Test
    public void findOneByIdTest() {
        Mockito.when(this.repository.findById(3L)).thenReturn(Optional.of(testGame1));
        Game resultFromService = this.gameService.findOneById(3L);
        assertEquals("testgame1", resultFromService.getName());
        Mockito.verify(this.repository, Mockito.times(1)).findById(3L);
    }
    @Test
    public void findAllTest() {
        Mockito.when(this.repository.findAll()).thenReturn(gameList);
        Iterable<Game> resultFromService = this.gameService.findAll();
        Game resultFromIterator = resultFromService.iterator().next();
        assertThat(resultFromIterator.getName()).isEqualToIgnoringCase("testgame1");
        Mockito.verify(this.repository, Mockito.times(1)).findAll();
    }

    // UPDATE
    @Test
    public void updateOneByIdTest() {

        Mockito.when(this.repository.findById(1L)).thenReturn(Optional.of(testGame1));
        Mockito.when(this.repository.save(testGame1)).thenReturn(testGame1);
        Game resultFromService = this.gameService.updateOneById(1L,testGame1);
        assertEquals("testgame1", resultFromService.getName());
        Mockito.verify(this.repository, Mockito.times(1)).findById(1L);
        Mockito.verify(this.repository, Mockito.times(1)).save(testGame1);
    }

    // DELETE
    @Test
    public void deleteOneByIdGamePresentTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(Optional.of(testGame1));
        Game resultFromService = this.gameService.deleteOneById(1L);
        assertEquals("testgame1", resultFromService.getName());
        Mockito.verify(this.repository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void deleteOneByIdGameNotPresentTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(Optional.ofNullable(null));
        Game resultFromService = this.gameService.deleteOneById(1L);
        assertEquals(null, resultFromService);
        Mockito.verify(this.repository, Mockito.times(0)).deleteById(1L);
    }

    // OTHER METHODS
    @Test
    public void findOneByNameTest() {
        Mockito.when(this.repository.findOneByName("BAM")).thenReturn(gameIterable);
        Iterable<Game> resultFromService = this.gameService.findOneByName("BAM");
        Game resultFromIterator = resultFromService.iterator().next();
        assertThat(resultFromIterator.getName()).isEqualToIgnoringCase("testgame1");
        Mockito.verify(this.repository, Mockito.times(1)).findOneByName("BAM");
    }
}