package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.enums.ParameterEnum;
import be.mielnoelanders.bazinga.domain.other.Parameter;
import be.mielnoelanders.bazinga.domain.other.Publisher;
import be.mielnoelanders.bazinga.repository.PublisherRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PublisherServiceUnitTest {

    @InjectMocks
    private PublisherServiceImpl publisherService;

    @Mock
    private PublisherRepository repo;

    private Publisher testPublisher1;
    private Publisher testPublisher2;
    private Publisher testPublisher3;

    private List<Publisher> publisherList;

    @Before
    public void init() {
        Publisher publisher1 = new Publisher();
        publisher1.setName("What The What");
        publisher1.setWebsite("www.whatthewhat.com");
        Publisher publisher2 = new Publisher();
        publisher2.setName("Who The Who");
        publisher2.setWebsite("www.whothewho.com");
        Publisher publisher3 = new Publisher();
        publisher3.setName("Where The Where");
        publisher3.setWebsite("www.wherethewhere.com");

        testPublisher1 = publisher1;
        testPublisher2 = publisher2;
        testPublisher3 = publisher3;

        publisherList = new ArrayList<>();
        publisherList.addAll(Arrays.asList(publisher1, publisher2, publisher3));
    }

    // Insert new Publisher //
    @Test
    public void addPublisherTest() {
        // Hier zeg je wat de mock moet geven als je de save oproept op de repository.
        Mockito.when(this.repo.save(testPublisher1)).thenReturn(testPublisher1);

        // Hier roep je de save methode op de CrudRepository aan VIA de service addPublisher.
        Publisher resultFromService = this.publisherService.addOne(testPublisher1);

        // testParameter1 heeft als inhoud : {0, "What The What", "www.whatthewhat.com"}
        assertTrue(resultFromService.getWebsite().equals("www.whatthewhat.com"));
    }

    // Get all publishers //
    @Test
    public void getAllTest() {
        Mockito.when(this.repo.findAll()).thenReturn(publisherList);
        Iterable<Publisher> resultFromService = this.publisherService.findAll();
        Publisher resultFromIterator = resultFromService.iterator().next();
        assertThat(resultFromIterator.getName()).isEqualTo("What The What");
        Mockito.verify(this.repo, Mockito.times(1)).findAll();
    }

    // Find unique by publisher name //
    @Test
    public void findByNameTest() {
        // Hier zeg je wat de mock moet geven als je de findByName oproept op de repository.
        Mockito.when(this.repo.findByName("Where The Where")).thenReturn(testPublisher3);

        // Hier roep je de findByName op de repository aan VIA de service findByName.
        Publisher resultFromService = this.publisherService.findByName(testPublisher3.getName());

        assertEquals("www.wherethewhere.com", resultFromService.getWebsite());
    }

    // Delete publisher by id TRUE //
    @Test
    public void deletePublisherTrueTest() {
        // Hier zeg je wat de mock moet geven als je de findById oproept op de repository.
        Mockito.when(this.repo.findById(1L)).thenReturn(java.util.Optional.of(testPublisher1));

        // Hier roep je de deleteOneById op de repository aan VIA de service findByName.
        Publisher resultFromService = this.publisherService.deleteOneById(1L);

        assertEquals("www.whatthewhat.com", resultFromService.getWebsite());
    }

    // Delete publisher by id FALSE //
    @Test
    public void deletePublisherFalseTest() {
        // Hier roep je de deleteOneById op de repository aan VIA de service findByName.
        Publisher resultFromService = this.publisherService.deleteOneById(1000001L);

        assertNull(resultFromService);
    }
}