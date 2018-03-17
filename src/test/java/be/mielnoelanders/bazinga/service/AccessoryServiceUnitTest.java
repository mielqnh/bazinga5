package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.basicitems.Accessory;
import be.mielnoelanders.bazinga.repository.AccessoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class AccessoryServiceUnitTest {

    @InjectMocks
    private AccessoryServiceImpl service;

    @Mock
    private AccessoryRepository repository;

    private Accessory testAccessory1;
    private Iterable<Accessory> accessoryIterable;
    private List<Accessory> accessoryList;

    @Before
    public void init() {
        testAccessory1 = new Accessory();
        testAccessory1.setName("testaccessory1");
        accessoryList = new ArrayList<>();
        accessoryList.add(testAccessory1);
        accessoryIterable = accessoryList;
    }

    // CREATE
    @Test
    public void addOneTest() {
        Mockito.when(this.repository.save(testAccessory1)).thenReturn(testAccessory1);
        Accessory resultFromService = this.service.addOne(testAccessory1);
        assertThat(resultFromService.getName()).isEqualToIgnoringCase("testaccessory1");
        Mockito.verify(this.repository, Mockito.times(1)).save(testAccessory1);
    }

    // READ ONE AND READ ALL
    @Test
    public void findOneByIdTest() {
        Mockito.when(this.repository.findById(3L)).thenReturn(Optional.of(testAccessory1));
        Accessory resultFromService = this.service.findOneById(3L);
        assertEquals("testaccessory1", resultFromService.getName());
        Mockito.verify(this.repository, Mockito.times(1)).findById(3L);
    }
    @Test
    public void findAllTest() {
        Mockito.when(this.repository.findAll()).thenReturn(accessoryList);
        Iterable<Accessory> resultFromService = this.service.findAll();
        Accessory resultFromIterator = resultFromService.iterator().next();
        assertThat(resultFromIterator.getName()).isEqualToIgnoringCase("testaccessory1");
        Mockito.verify(this.repository, Mockito.times(1)).findAll();
    }

    // UPDATE
    @Test
    public void updateOneByIdTest() {

        Mockito.when(this.repository.findById(1L)).thenReturn(Optional.of(testAccessory1));
        Mockito.when(this.repository.save(testAccessory1)).thenReturn(testAccessory1);
        Accessory resultFromService = this.service.updateOneById(1L,testAccessory1);
        assertEquals("testaccessory1", resultFromService.getName());
        Mockito.verify(this.repository, Mockito.times(1)).findById(1L);
        Mockito.verify(this.repository, Mockito.times(1)).save(testAccessory1);
    }

    // DELETE
    @Test
    public void deleteOneByIdAccessoryPresentTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(Optional.of(testAccessory1));
        Accessory resultFromService = this.service.deleteOneById(1L);
        assertEquals("testaccessory1", resultFromService.getName());
        Mockito.verify(this.repository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void deleteOneByIdAccessoryNotPresentTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(Optional.ofNullable(null));
        Accessory resultFromService = this.service.deleteOneById(1L);
        assertEquals(null, resultFromService);
        Mockito.verify(this.repository, Mockito.times(0)).deleteById(1L);
    }

    // OTHER METHODS
    @Test
    public void findOneByNameTest() {
        Mockito.when(this.repository.findOneByName("BAM")).thenReturn(accessoryIterable);
        Iterable<Accessory> resultFromService = this.service.findOneByName("BAM");
        Accessory resultFromIterator = resultFromService.iterator().next();
        assertThat(resultFromIterator.getName()).isEqualToIgnoringCase("testaccessory1");
        Mockito.verify(this.repository, Mockito.times(1)).findOneByName("BAM");
    }
}