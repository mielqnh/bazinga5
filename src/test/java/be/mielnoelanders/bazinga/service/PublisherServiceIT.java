package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Publisher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
// De naam van een integratietest klasse het best laten eindigen op 'IT' zodat Maven deze test
// pas als laatste uitvoert waardoor de maven-build sneller verloopt !
public class PublisherServiceIT {

    @Autowired
    private PublisherService publisherService;

    @Test
    public void createReadUpdateDeleteCustomerTest() {
        Publisher newPublisher = new Publisher();
        newPublisher.setName("New Publisher to insert");

        //test create Publisher
        Publisher insertedPublisher = publisherService.addPublisher(newPublisher);
        long newId = insertedPublisher.getId();
        Assert.assertFalse(newId == 0);

        //test read Publisher
        Publisher readPublisher = publisherService.findByName(insertedPublisher.getName());
        Assert.assertEquals(newPublisher.getName(), readPublisher.getName());

        //test update customer
        readPublisher.setName("Publisher updated");
        boolean updatedPublisher = publisherService.updatePublisherByName(readPublisher);
        Assert.assertTrue(updatedPublisher);

        //test delete customer
        publisherService.deletePublisher(newId);
        Assert.assertNull(publisherService.findByName(insertedPublisher.getName()));
    }
}
