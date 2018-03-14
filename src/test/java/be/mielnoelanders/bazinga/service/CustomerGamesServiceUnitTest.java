package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.CustomerGames;
import be.mielnoelanders.bazinga.repository.CustomerGamesRepository;
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
public class CustomerGamesServiceUnitTest {

    @InjectMocks
    private CustomerGamesServiceImpl gameService;

    @Mock
    private CustomerGamesRepository repository;

    private CustomerGames customerGames1;
    private Optional<CustomerGames> customerGamesOptional1;

    private Iterable<CustomerGames> customerGamesIterable;
    private List<CustomerGames> customerGamesList;

    @Before
    public void init() {

        customerGames1 = new CustomerGames();
        customerGames1.setDate("23/01/2018");
        customerGamesOptional1 = Optional.of(customerGames1);

        customerGamesList = new ArrayList<>();
        customerGamesList.addAll(Arrays.asList(customerGames1));
        customerGamesIterable = customerGamesList;
    }

    // CREATE
    @Test
    public void insertGameTest() {
        Mockito.when(this.repository.save(customerGames1)).thenReturn(customerGames1);
        CustomerGames resultFromService = this.gameService.insertCustomerGames(customerGames1);
        assertThat(resultFromService.getDate()).isEqualToIgnoringCase("23/01/2018");
        Mockito.verify(this.repository, Mockito.times(1)).save(customerGames1);
    }

    // READ ONE AND READ ALL
    @Test
    public void getOneTest() {
        Mockito.when(this.repository.findById(3L)).thenReturn(customerGamesOptional1);
        CustomerGames resultFromService = this.gameService.getOne(3L);
        assertEquals("23/01/2018", resultFromService.getDate());
        Mockito.verify(this.repository, Mockito.times(1)).findById(3L);
    }

    @Test
    public void getAllTest() {
        Mockito.when(this.repository.findAll()).thenReturn(customerGamesList);
        Iterable<CustomerGames> resultFromService = this.gameService.getAll();
        CustomerGames resultFromIterator = resultFromService.iterator().next();
        assertThat(resultFromIterator.getDate()).isEqualToIgnoringCase("23/01/2018");
        Mockito.verify(this.repository, Mockito.times(1)).findAll();
    }

    // UPDATE
    @Test
    public void changeGameTest() {

        Mockito.when(this.repository.findById(1L)).thenReturn(customerGamesOptional1);
        Mockito.when(this.repository.save(customerGames1)).thenReturn(customerGames1);
        CustomerGames resultFromService = this.gameService.changeCustomerGames(1L,customerGames1);
        assertEquals("23/01/2018", resultFromService.getDate());
        Mockito.verify(this.repository, Mockito.times(1)).findById(1L);
        Mockito.verify(this.repository, Mockito.times(1)).save(customerGames1);
    }

    // DELETE
    @Test
    public void deleteByIdMetSpelTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(customerGamesOptional1);
        CustomerGames resultFromService = this.gameService.deleteById(1L);
        assertEquals("23/01/2018", resultFromService.getDate());
        Mockito.verify(this.repository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void deleteByIdSpelNietAanwezigTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(Optional.ofNullable(null));
        CustomerGames resultFromService = this.gameService.deleteById(1L);
        assertEquals(null, resultFromService);
        Mockito.verify(this.repository, Mockito.times(0)).deleteById(1L);
    }

}