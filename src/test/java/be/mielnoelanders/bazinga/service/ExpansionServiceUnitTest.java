package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.basicitems.Expansion;
import be.mielnoelanders.bazinga.repository.ExpansionRepository;
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
public class ExpansionServiceUnitTest {

    @InjectMocks
    private ExpansionServiceImpl service;

    @Mock
    private ExpansionRepository repository;

    private Expansion testExpansion1;
    private Iterable<Expansion> expansionIterable;
    private List<Expansion> expansionList;

    @Before
    public void init() {
        testExpansion1 = new Expansion();
        testExpansion1.setName("testexpansion1");
        expansionList = new ArrayList<>();
        expansionList.add(testExpansion1);
        expansionIterable = expansionList;
    }

    // CREATE
    @Test
    public void addOneTest() {
        Mockito.when(this.repository.save(testExpansion1)).thenReturn(testExpansion1);
        Expansion resultFromService = this.service.addOne(testExpansion1);
        assertThat(resultFromService.getName()).isEqualToIgnoringCase("testexpansion1");
        Mockito.verify(this.repository, Mockito.times(1)).save(testExpansion1);
    }

    // READ ONE AND READ ALL
    @Test
    public void findOneByIdTest() {
        Mockito.when(this.repository.findById(3L)).thenReturn(Optional.of(testExpansion1));
        Expansion resultFromService = this.service.findOneById(3L);
        assertEquals("testexpansion1", resultFromService.getName());
        Mockito.verify(this.repository, Mockito.times(1)).findById(3L);
    }
    @Test
    public void findAllTest() {
        Mockito.when(this.repository.findAll()).thenReturn(expansionList);
        Iterable<Expansion> resultFromService = this.service.findAll();
        Expansion resultFromIterator = resultFromService.iterator().next();
        assertThat(resultFromIterator.getName()).isEqualToIgnoringCase("testexpansion1");
        Mockito.verify(this.repository, Mockito.times(1)).findAll();
    }

    // UPDATE
    @Test
    public void updateOneByIdTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(Optional.of(testExpansion1));
        Mockito.when(this.repository.save(testExpansion1)).thenReturn(testExpansion1);
        Expansion resultFromService = this.service.updateOneById(1L,testExpansion1);
        assertEquals("testexpansion1", resultFromService.getName());
        Mockito.verify(this.repository, Mockito.times(1)).findById(1L);
        Mockito.verify(this.repository, Mockito.times(1)).save(testExpansion1);
    }

    // DELETE
    @Test
    public void deleteOneByIdExpansionPresentTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(Optional.of(testExpansion1));
        Expansion resultFromService = this.service.deleteOneById(1L);
        assertEquals("testexpansion1", resultFromService.getName());
        Mockito.verify(this.repository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void deleteOneByIdExpansionNotPresentTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(Optional.ofNullable(null));
        Expansion resultFromService = this.service.deleteOneById(1L);
        assertEquals(null, resultFromService);
        Mockito.verify(this.repository, Mockito.times(0)).deleteById(1L);
    }

    // OTHER METHODS
    @Test
    public void findOneByNameTest() {
        Mockito.when(this.repository.findOneByName("BAM")).thenReturn(expansionIterable);
        Iterable<Expansion> resultFromService = this.service.findOneByName("BAM");
        Expansion resultFromIterator = resultFromService.iterator().next();
        assertThat(resultFromIterator.getName()).isEqualToIgnoringCase("testexpansion1");
        Mockito.verify(this.repository, Mockito.times(1)).findOneByName("BAM");
    }
}