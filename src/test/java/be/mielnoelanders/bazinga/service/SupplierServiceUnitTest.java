package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Supplier;
import be.mielnoelanders.bazinga.repository.SupplierRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SupplierServiceUnitTest {

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Mock //is injected into supplierService
    private SupplierRepository supplierRepository;

    private Supplier supplier1;
    private Supplier supplier2;
    private Supplier supplier3;
    private Optional<Supplier> optionalSupplier;
    private List<Supplier> suppliers;

    @Before
    public void init() {
        supplier1 = new Supplier();
        supplier1.setName("testsupplier1");
        supplier2 = new Supplier();
        supplier2.setName("testsupplier2");
        supplier3 = new Supplier();
        supplier3.setName("testsupplier3");
        optionalSupplier = Optional.of(supplier2);
        suppliers = new ArrayList<>();
        suppliers.addAll(Arrays.asList(supplier1, supplier2, supplier3));
    }

    @Test
    public void testFindById() {
        when(this.supplierRepository.findById(2L)).thenReturn(optionalSupplier);
        Supplier supplierFromService = supplierService.findOneById(2L);
        assertThat(supplierFromService.getName()).isEqualTo("testsupplier2");
        verify(supplierRepository, times(1)).findById(2L);
    }

    @Test
    public void testFindAll() {
        when(this.supplierRepository.findAll()).thenReturn(suppliers);
        Iterable<Supplier> suppliersFromService = supplierService.findAll();
        //test op name van eerste supplier in de List suppliers
        assertThat(((List<Supplier>) suppliersFromService).get(0).getName()).isEqualTo("testsupplier1");
        //test of aantal suppliers in de List suppliers 3 is
        assertThat((List<Supplier>) suppliersFromService).size().isEqualTo(3);
        verify(supplierRepository, times(1)).findAll();
    }

    @Test
    public void testAddOne() {
        when(supplierRepository.save(supplier3)).thenReturn(supplier3);
        Supplier addedSupplier = supplierService.addOne(supplier3);
        assertThat(addedSupplier).isNotNull();
        assertThat(addedSupplier.getName()).isEqualTo("testsupplier3");
        verify(supplierRepository, times(1)).save(supplier3);
    }

    @Test
    public void testDeleteById() {
        when(supplierRepository.existsById(3L)).thenReturn(true);
        supplierService.deleteOneById(3L);
        verify(supplierRepository, times(1)).existsById(3L);
        verify(supplierRepository, times(1)).deleteById(3L);
    }

    @Test
    public void testUpdateOne() {
        when(supplierRepository.findById(2L)).thenReturn(optionalSupplier);
        when(supplierRepository.save(optionalSupplier.get())).thenReturn(supplier3);
        Supplier updatedSupplier = supplierService.updateOneById(2L, optionalSupplier.get());
        System.out.println("Updated supplier = " + updatedSupplier);
        assertThat(updatedSupplier.getName()).isEqualTo("testsupplier3");
        verify(supplierRepository, times(1)).findById(2L);
        verify(supplierRepository, times(1)).save(optionalSupplier.get());
    }

}