package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Supplier;
import be.mielnoelanders.bazinga.repository.SupplierRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class SupplierServiceUnitTest {

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Mock
    private SupplierRepository supplierRepository;

    private Supplier supplier1;
    private Supplier supplier2;
    private Supplier supplier3;
    private Optional<Supplier> optionalSupplier;

    @Before
    public void init() {
        supplier1 = new Supplier();
        supplier1.setName("testsupplier1");
        supplier2 = new Supplier();
        supplier2.setName("testsupplier2");
        supplier3 = new Supplier();
        supplier3.setName("testsupplier3");
        optionalSupplier = Optional.of(supplier2);
    }


    @Test
    public void testFindById() {
        //test supplierRepository.findById(id)
        Mockito.when(this.supplierRepository.findById(2L)).thenReturn(optionalSupplier);
        Supplier supplierFromService = supplierService.findById(2L);
        assertThat(supplierFromService.getName()).isEqualTo("testsupplier2");
    }
}
