package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.transferitems.SalesReceipt;
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

    private SalesReceipt salesReceipt1;
    private Optional<SalesReceipt> customerGamesOptional1;

    private Iterable<SalesReceipt> customerGamesIterable;
    private List<SalesReceipt> salesReceiptList;

    @Before
    public void init() {

        salesReceipt1 = new SalesReceipt();
        salesReceipt1.setDate("23/01/2018");
        customerGamesOptional1 = Optional.of(salesReceipt1);

        salesReceiptList = new ArrayList<>();
        salesReceiptList.addAll(Arrays.asList(salesReceipt1));
        customerGamesIterable = salesReceiptList;
    }

    // CREATE
    @Test
    public void insertGameTest() {
        Mockito.when(this.repository.save(salesReceipt1)).thenReturn(salesReceipt1);
        SalesReceipt resultFromService = this.gameService.addOne(salesReceipt1);
        assertThat(resultFromService.getDate()).isEqualToIgnoringCase("23/01/2018");
        Mockito.verify(this.repository, Mockito.times(1)).save(salesReceipt1);
    }

    // READ ONE AND READ ALL
    @Test
    public void getOneTest() {
        Mockito.when(this.repository.findById(3L)).thenReturn(customerGamesOptional1);
        SalesReceipt resultFromService = this.gameService.findOneById(3L);
        assertEquals("23/01/2018", resultFromService.getDate());
        Mockito.verify(this.repository, Mockito.times(1)).findById(3L);
    }

    @Test
    public void getAllTest() {
        Mockito.when(this.repository.findAll()).thenReturn(salesReceiptList);
        Iterable<SalesReceipt> resultFromService = this.gameService.findAll();
        SalesReceipt resultFromIterator = resultFromService.iterator().next();
        assertThat(resultFromIterator.getDate()).isEqualToIgnoringCase("23/01/2018");
        Mockito.verify(this.repository, Mockito.times(1)).findAll();
    }

    // UPDATE
    @Test
    public void changeGameTest() {

        Mockito.when(this.repository.findById(1L)).thenReturn(customerGamesOptional1);
        Mockito.when(this.repository.save(salesReceipt1)).thenReturn(salesReceipt1);
        SalesReceipt resultFromService = this.gameService.updateOneById(1L, salesReceipt1);
        assertEquals("23/01/2018", resultFromService.getDate());
        Mockito.verify(this.repository, Mockito.times(1)).findById(1L);
        Mockito.verify(this.repository, Mockito.times(1)).save(salesReceipt1);
    }

    // DELETE
    @Test
    public void deleteByIdMetSpelTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(customerGamesOptional1);
        SalesReceipt resultFromService = this.gameService.deleteOneById(1L);
        assertEquals("23/01/2018", resultFromService.getDate());
        Mockito.verify(this.repository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void deleteByIdSpelNietAanwezigTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(Optional.ofNullable(null));
        SalesReceipt resultFromService = this.gameService.deleteOneById(1L);
        assertEquals(null, resultFromService);
        Mockito.verify(this.repository, Mockito.times(0)).deleteById(1L);
    }

}