package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.transferitems.SoldItem;
import be.mielnoelanders.bazinga.repository.SoldItemRepository;
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
public class SoldGameServiceUnitTest {

    @InjectMocks
    private SoldItemServiceImpl gameService;

    @Mock
    private SoldItemRepository repository;

    private SoldItem soldItem1;
    private Optional<SoldItem> customerGamesOptional1;

    private Iterable<SoldItem> customerGamesIterable;
    private List<SoldItem> soldItemList;

    @Before
    public void init() {

        soldItem1 = new SoldItem();
        soldItem1.setDate("23/01/2018");
        customerGamesOptional1 = Optional.of(soldItem1);

        soldItemList = new ArrayList<>();
        soldItemList.addAll(Arrays.asList(soldItem1));
        customerGamesIterable = soldItemList;
    }

    // CREATE
    @Test
    public void insertGameTest() {
        Mockito.when(this.repository.save(soldItem1)).thenReturn(soldItem1);
        SoldItem resultFromService = this.gameService.addOne(soldItem1);
        assertThat(resultFromService.getDate()).isEqualToIgnoringCase("23/01/2018");
        Mockito.verify(this.repository, Mockito.times(1)).save(soldItem1);
    }

    // READ ONE AND READ ALL
    @Test
    public void getOneTest() {
        Mockito.when(this.repository.findById(3L)).thenReturn(customerGamesOptional1);
        SoldItem resultFromService = this.gameService.findOneById(3L);
        assertEquals("23/01/2018", resultFromService.getDate());
        Mockito.verify(this.repository, Mockito.times(1)).findById(3L);
    }

    @Test
    public void getAllTest() {
        Mockito.when(this.repository.findAll()).thenReturn(soldItemList);
        Iterable<SoldItem> resultFromService = this.gameService.findAll();
        SoldItem resultFromIterator = resultFromService.iterator().next();
        assertThat(resultFromIterator.getDate()).isEqualToIgnoringCase("23/01/2018");
        Mockito.verify(this.repository, Mockito.times(1)).findAll();
    }

    // UPDATE
    @Test
    public void changeGameTest() {

        Mockito.when(this.repository.findById(1L)).thenReturn(customerGamesOptional1);
        Mockito.when(this.repository.save(soldItem1)).thenReturn(soldItem1);
        SoldItem resultFromService = this.gameService.updateOneById(1L, soldItem1);
        assertEquals("23/01/2018", resultFromService.getDate());
        Mockito.verify(this.repository, Mockito.times(1)).findById(1L);
        Mockito.verify(this.repository, Mockito.times(1)).save(soldItem1);
    }

    // DELETE
    @Test
    public void deleteByIdMetSpelTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(customerGamesOptional1);
        SoldItem resultFromService = this.gameService.deleteOneById(1L);
        assertEquals("23/01/2018", resultFromService.getDate());
        Mockito.verify(this.repository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void deleteByIdSpelNietAanwezigTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(Optional.ofNullable(null));
        SoldItem resultFromService = this.gameService.deleteOneById(1L);
        assertEquals(null, resultFromService);
        Mockito.verify(this.repository, Mockito.times(0)).deleteById(1L);
    }

}