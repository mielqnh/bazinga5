package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Publisher;
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

    // Insert new Parameter //
    @Test
    public void addPublisherTest() {
        Mockito.when(this.repo.save(testPublisher1)).thenReturn(testPublisher1);
        Publisher resultFromService = this.publisherService.addOne(testPublisher1);
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
        Mockito.when(this.repo.findByName("Where The Where")).thenReturn(testPublisher3);
        Publisher resultFromService = this.publisherService.findByName(testPublisher3.getName());
        assertEquals("www.wherethewhere.com", resultFromService.getWebsite());
    }

    // Delete publisher by id TRUE //
    @Test
    public void deleteParameterTrueTest() {
        Mockito.when(this.repo.existsById(1L)).thenReturn(true);
        boolean resultFromService = this.publisherService.deleteOneById(1L);
        assertTrue(resultFromService);
    }

    // Delete publisher by id FALSE //
    @Test
    public void deleteParameterFalseTest() {
        Mockito.when(this.repo.existsById(100001L)).thenReturn(false);
        boolean resultFromService = this.publisherService.deleteOneById(100001L);
        assertFalse(resultFromService);
    }
}
