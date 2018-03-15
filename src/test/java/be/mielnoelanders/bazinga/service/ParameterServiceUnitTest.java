package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Parameter;
import be.mielnoelanders.bazinga.domain.ParameterEnum;
import be.mielnoelanders.bazinga.repository.ParameterRepository;
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
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ParameterServiceUnitTest {

    @InjectMocks
    private ParameterServiceImpl parameterService;

    @Mock
    private ParameterRepository repository;

    private Parameter testParameter1;
    private Parameter testParameter2;
    private Parameter testParameter3;
    Optional<Parameter> optionalParameter;

    private List<Parameter> parmList;

    @Before
    public void init() {
        testParameter1 = new Parameter();
        testParameter1.setType(ParameterEnum.PROFITMARGIN);
        testParameter1.setPercentage(30);
        testParameter2 = new Parameter();
        testParameter2.setType(ParameterEnum.PREMIUMCUSTOMER);
        testParameter2.setPercentage(10);
        testParameter3 = new Parameter();
        testParameter3.setType(ParameterEnum.DAMAGEDISCOUNT);
        testParameter3.setPercentage(20);

        optionalParameter = Optional.of(testParameter1);
        parmList = new ArrayList<>();
        parmList.addAll(Arrays.asList(testParameter1, testParameter2, testParameter3));
    }

    // Insert new Parameter //
    @Test
    public void addParameterTest() {
        Mockito.when(this.repository.save(testParameter1)).thenReturn(testParameter1);
        Parameter resultFromService = this.parameterService.addOne(testParameter1);
        assertTrue(resultFromService.getPercentage() == 30);
    }

    // Get all parameters //
    @Test
    public void getAllTest() {
        Mockito.when(this.repository.findAll()).thenReturn(parmList);
        Iterable<Parameter> resultFromService = this.parameterService.findAll();
        Parameter resultFromIterator = resultFromService.iterator().next();
        assertThat(resultFromIterator.getType()).isEqualTo(ParameterEnum.PROFITMARGIN);
        Mockito.verify(this.repository, Mockito.times(1)).findAll();
    }

    // Find unique by parameter type //
    @Test
    public void findOneByIdTest() {
        Mockito.when(this.repository.findById(3L)).thenReturn(optionalParameter);
        Parameter resultFromService = this.parameterService.findOneById(3L);
        assertEquals(20, resultFromService.getPercentage());
    }

    // Delete parameter by id TRUE //
    @Test
    public void deleteParameterTrueTest() {
        Mockito.when(this.repository.existsById(1L)).thenReturn(true);
        boolean resultFromService = this.parameterService.deleteOneById(1L);
        assertTrue(resultFromService);
    }

    // Delete parameter by id FALSE //
    @Test
    public void deleteParameterFalseTest() {
        Mockito.when(this.repository.existsById(100001L)).thenReturn(false);
        boolean resultFromService = this.parameterService.deleteOneById(100001L);
        assertFalse(resultFromService);
    }
}



/*    // Update parameter by type TRUE //
    @Test
    public void updateParameterByTypeTrueTest() {
        Mockito.when(this.repository.findByType(ParameterEnum.PREMIUMCUSTOMER)).thenReturn(testParameter2);
        Mockito.when(this.repository.save(testParameter2)).thenReturn(testParameter2);
        boolean resultFromService = this.parameterService.updateOneById(testParameter2);
        assertTrue(resultFromService);
    }

    // Update parameter by type FALSE //
    @Test
    public void updateParameterByTypeFalseTest() {
        Mockito.when(this.repository.findByType(ParameterEnum.PREMIUMCUSTOMER)).thenReturn(null);
        boolean resultFromService = this.parameterService.updateOneById(testParameter2);
        assertFalse(resultFromService);
    }*/