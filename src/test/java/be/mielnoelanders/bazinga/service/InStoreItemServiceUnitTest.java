package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.InStoreItem;
import be.mielnoelanders.bazinga.repository.InStoreItemRepository;
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
public class InStoreItemServiceUnitTest {

    @InjectMocks
    private InStoreItemServiceImpl gameService;

    @Mock
    private InStoreItemRepository repository;

    private InStoreItem inStoreItem1;
    private Optional<InStoreItem> supplierGamesOptional1;

    private Iterable<InStoreItem> supplierGamesIterable;
    private List<InStoreItem> inStoreItemList;

    @Before
    public void init() {

        inStoreItem1 = new InStoreItem();
        inStoreItem1.setDate("23/01/2018");
        supplierGamesOptional1 = Optional.of(inStoreItem1);

        inStoreItemList = new ArrayList<>();
        inStoreItemList.addAll(Arrays.asList(inStoreItem1));
        supplierGamesIterable = inStoreItemList;
    }

    // CREATE
    @Test
    public void insertGameTest() {
        Mockito.when(this.repository.save(inStoreItem1)).thenReturn(inStoreItem1);
        InStoreItem resultFromService = this.gameService.addOne(inStoreItem1);
        assertThat(resultFromService.getDate()).isEqualToIgnoringCase("23/01/2018");
        Mockito.verify(this.repository, Mockito.times(1)).save(inStoreItem1);
    }

    // READ ONE AND READ ALL
    @Test
    public void getOneTest() {
        Mockito.when(this.repository.findById(3L)).thenReturn(supplierGamesOptional1);
        InStoreItem resultFromService = this.gameService.findOneById(3L);
        assertEquals("23/01/2018", resultFromService.getDate());
        Mockito.verify(this.repository, Mockito.times(1)).findById(3L);
    }

    @Test
    public void getAllTest() {
        Mockito.when(this.repository.findAll()).thenReturn(inStoreItemList);
        Iterable<InStoreItem> resultFromService = this.gameService.findAll();
        InStoreItem resultFromIterator = resultFromService.iterator().next();
        assertThat(resultFromIterator.getDate()).isEqualToIgnoringCase("23/01/2018");
        Mockito.verify(this.repository, Mockito.times(1)).findAll();
    }

    // UPDATE
    @Test
    public void changeGameTest() {

        Mockito.when(this.repository.findById(1L)).thenReturn(supplierGamesOptional1);
        Mockito.when(this.repository.save(inStoreItem1)).thenReturn(inStoreItem1);
        InStoreItem resultFromService = this.gameService.updateOneById(1L, inStoreItem1);
        assertEquals("23/01/2018", resultFromService.getDate());
        Mockito.verify(this.repository, Mockito.times(1)).findById(1L);
        Mockito.verify(this.repository, Mockito.times(1)).save(inStoreItem1);
    }

    // DELETE
    @Test
    public void deleteByIdMetSpelTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(supplierGamesOptional1);
        InStoreItem resultFromService = this.gameService.deleteOneById(1L);
        assertEquals("23/01/2018", resultFromService.getDate());
        Mockito.verify(this.repository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void deleteByIdSpelNietAanwezigTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(Optional.ofNullable(null));
        InStoreItem resultFromService = this.gameService.deleteOneById(1L);
        assertEquals(null, resultFromService);
        Mockito.verify(this.repository, Mockito.times(0)).deleteById(1L);
    }

}