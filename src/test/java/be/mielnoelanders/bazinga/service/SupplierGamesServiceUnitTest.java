package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.SupplierGames;
import be.mielnoelanders.bazinga.repository.SupplierGamesRepository;
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
public class SupplierGamesServiceUnitTest {

    @InjectMocks
    private SupplierGamesServiceImpl gameService;

    @Mock
    private SupplierGamesRepository repository;

    private SupplierGames supplierGames1;
    private Optional<SupplierGames> supplierGamesOptional1;

    private Iterable<SupplierGames> supplierGamesIterable;
    private List<SupplierGames> supplierGamesList;

    @Before
    public void init() {

        supplierGames1 = new SupplierGames();
        supplierGames1.setDate("23/01/2018");
        supplierGamesOptional1 = Optional.of(supplierGames1);

        supplierGamesList = new ArrayList<>();
        supplierGamesList.addAll(Arrays.asList(supplierGames1));
        supplierGamesIterable = supplierGamesList;
    }

    // CREATE
    @Test
    public void insertGameTest() {
        Mockito.when(this.repository.save(supplierGames1)).thenReturn(supplierGames1);
        SupplierGames resultFromService = this.gameService.insertSupplierGames(supplierGames1);
        assertThat(resultFromService.getDate()).isEqualToIgnoringCase("23/01/2018");
        Mockito.verify(this.repository, Mockito.times(1)).save(supplierGames1);
    }

    // READ ONE AND READ ALL
    @Test
    public void getOneTest() {
        Mockito.when(this.repository.findById(3L)).thenReturn(supplierGamesOptional1);
        SupplierGames resultFromService = this.gameService.getOne(3L);
        assertEquals("23/01/2018", resultFromService.getDate());
        Mockito.verify(this.repository, Mockito.times(1)).findById(3L);
    }

    @Test
    public void getAllTest() {
        Mockito.when(this.repository.findAll()).thenReturn(supplierGamesList);
        Iterable<SupplierGames> resultFromService = this.gameService.getAll();
        SupplierGames resultFromIterator = resultFromService.iterator().next();
        assertThat(resultFromIterator.getDate()).isEqualToIgnoringCase("23/01/2018");
        Mockito.verify(this.repository, Mockito.times(1)).findAll();
    }

    // UPDATE
    @Test
    public void changeGameTest() {

        Mockito.when(this.repository.findById(1L)).thenReturn(supplierGamesOptional1);
        Mockito.when(this.repository.save(supplierGames1)).thenReturn(supplierGames1);
        SupplierGames resultFromService = this.gameService.changeSupplierGames(1L,supplierGames1);
        assertEquals("23/01/2018", resultFromService.getDate());
        Mockito.verify(this.repository, Mockito.times(1)).findById(1L);
        Mockito.verify(this.repository, Mockito.times(1)).save(supplierGames1);
    }

    // DELETE
    @Test
    public void deleteByIdMetSpelTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(supplierGamesOptional1);
        SupplierGames resultFromService = this.gameService.deleteById(1L);
        assertEquals("23/01/2018", resultFromService.getDate());
        Mockito.verify(this.repository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void deleteByIdSpelNietAanwezigTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(Optional.ofNullable(null));
        SupplierGames resultFromService = this.gameService.deleteById(1L);
        assertEquals(null, resultFromService);
        Mockito.verify(this.repository, Mockito.times(0)).deleteById(1L);
    }

}