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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        assertEquals(30, resultFromService.getPercentage());
    }

    // DELETE
    @Test
    public void deleteByIdMetParameterTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(optionalParameter);
        Parameter resultFromService = this.parameterService.deleteOneById(1L);
        assertEquals(ParameterEnum.PROFITMARGIN, resultFromService.getType());
        Mockito.verify(this.repository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void deleteByIdPNietAanwezigTest() {
        Mockito.when(this.repository.findById(1L)).thenReturn(Optional.ofNullable(null));
        Parameter resultFromService = this.parameterService.deleteOneById(1L);
        assertEquals(null, resultFromService);
        Mockito.verify(this.repository, Mockito.times(0)).deleteById(1L);
    }
}
