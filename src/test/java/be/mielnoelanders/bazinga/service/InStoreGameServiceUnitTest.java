package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.transferitems.PurchaseReceipt;
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
public class InStoreGameServiceUnitTest {

    @InjectMocks
    private InStoreItemServiceImpl gameService;

    @Mock
    private InStoreItemRepository repository;

    private PurchaseReceipt purchaseReceipt1;
    private Optional<PurchaseReceipt> supplierGamesOptional1;

    private Iterable<PurchaseReceipt> supplierGamesIterable;
    private List<PurchaseReceipt> purchaseReceiptList;

    @Before
    public void init() {

        purchaseReceipt1 = new PurchaseReceipt();
        purchaseReceipt1.setDate("23/01/2018");
        supplierGamesOptional1 = Optional.of(purchaseReceipt1);

        purchaseReceiptList = new ArrayList<>();
        purchaseReceiptList.addAll(Arrays.asList(purchaseReceipt1));
        supplierGamesIterable = purchaseReceiptList;
    }

    // CREATE
    @Test
    public void insertGameTest() {
        Mockito.when(this.repository.save(purchaseReceipt1)).thenReturn(purchaseReceipt1);
        PurchaseReceipt resultFromService = this.gameService.addOne(purchaseReceipt1);
        assertThat(resultFromService.getDate()).isEqualToIgnoringCase("23/01/2018");
        Mockito.verify(this.repository, Mockito.times(1)).save(purchaseReceipt1);
    }

    // READ ONE AND READ ALL
    @Test
    public void getOneTest() {
        Mockito.when(this.repository.findById(3L)).thenReturn(supplierGamesOptional1);
        PurchaseReceipt resultFromService = this.gameService.findOneById(3L);
        assertEquals("23/01/2018", resultFromService.getDate());
        Mockito.verify(this.repository, Mockito.times(1)).findById(3L);
    }

    @Test
    public void getAllTest() {
        Mockito.when(this.repository.findAll()).thenReturn(purchaseReceiptList);
        Iterable<PurchaseReceipt> resultFromService = this.gameService.findAll();
        PurchaseReceipt resultFromIterator = resultFromService.iterator().next();
        assertThat(resultFromIterator.getDate()).isEqualToIgnoringCase("23/01/2018");
        Mockito.verify(this.repository, Mockito.times(1)).findAll();
    }

    // UPDATE
    @Test
    public void changeGameTest() {

        Mockito.when(this.repository.findById(1L)).thenReturn(supplierGamesOptional1);
        Mockito.when(this.repository.save(purchaseReceipt1)).thenReturn(purchaseReceipt1);
        PurchaseReceipt resultFromService = this.gameService.updateOneById(1L, purchaseReceipt1);
        assertEquals("23/01/2018", resultFromService.getDate());
        Mockito.verify(this.repository, Mockito.times(1)).findById(1L);
        Mockito.verify(this.repository, Mockito.times(1)).save(purchaseReceipt1);
    }

    // DELETE
    @Test
    public void deleteByIdMetSpelTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(supplierGamesOptional1);
        PurchaseReceipt resultFromService = this.gameService.deleteOneById(1L);
        assertEquals("23/01/2018", resultFromService.getDate());
        Mockito.verify(this.repository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void deleteByIdSpelNietAanwezigTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(Optional.ofNullable(null));
        PurchaseReceipt resultFromService = this.gameService.deleteOneById(1L);
        assertEquals(null, resultFromService);
        Mockito.verify(this.repository, Mockito.times(0)).deleteById(1L);
    }

}