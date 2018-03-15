package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Supplier;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplierServiceIntegrationTestIT {// de naam van een integratietest klasse het best laten eindigen op 'IT' zodat Maven deze test
                                               // pas als laatste uitvoert waardoor de maven-build sneller verloopt !!

    @Autowired
    private SupplierService supplierService;

    @Test
    public void createReadUpdateDeleteSupplierTest() {
        Supplier newSupplier = new Supplier();
        newSupplier.setName("New Supplier to insert");

        //test create supplier
        Supplier insertedSupplier = supplierService.addOne(newSupplier);
        long newId = insertedSupplier.getId();
        Assert.assertFalse(newId == 0);

        //test read supplier
        Supplier readSupplier = supplierService.findOneById(newId);
        Assert.assertEquals(newSupplier.getName(), readSupplier.getName());

        //test update supplier
        readSupplier.setName("Supplier updated");
        Supplier updatedSupplier = supplierService.updateOneById(newId, readSupplier);
        Assert.assertEquals("Supplier updated", updatedSupplier.getName());

        //test delete supplier
        supplierService.deleteOneById(newId);
        Assert.assertNull(supplierService.findOneById(newId));

    }
}