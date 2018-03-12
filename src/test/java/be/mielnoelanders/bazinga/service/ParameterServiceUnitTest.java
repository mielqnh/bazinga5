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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ParameterServiceUnitTest {

    @InjectMocks
    private ParameterServiceImpl parameterService;

    @Mock
    private ParameterRepository repo;

    private Parameter testParameter1;
    private Parameter testParameter2;
    private Parameter testParameter3;

    private List<Parameter> parmList;

    @Before
    public void init() {
        Parameter parm1 = new Parameter();
            parm1.setType(ParameterEnum.PROFITMARGIN);
            parm1.setPercentage(30);
        Parameter parm2 = new Parameter();
            parm2.setType(ParameterEnum.PREMIUMCUSTOMER);
            parm2.setPercentage(10);
        Parameter parm3 = new Parameter();
            parm3.setType(ParameterEnum.DAMAGEDISCOUNT);
            parm3.setPercentage(20);

        testParameter1 = parm1;
        testParameter2 = parm2;
        testParameter3 = parm3;

        parmList = new ArrayList<>();
        parmList.addAll(Arrays.asList(parm1, parm2, parm3));
    }

    // Insert new Parameter //
    @Test
    public void addParameterTest() {
        // Hier zeg je wat de mock moet geven als je de findByType oproept op de repository.
        Mockito.when(this.repo.save(testParameter1)).thenReturn(testParameter1);

        // Hier roep je de save methode op de CrudRepository aan VIA de service addParameter.
        Parameter resultFromService = this.parameterService.addParameter(testParameter1);

        // testParameter1 heeft als inhoud : {0, PROFITMARGIN, 30}
        assertTrue(resultFromService.getPercentage() == 30);
    }

    // Get all parameters //
    @Test
    public void getAllTest() {
        Mockito.when(this.repo.findAll()).thenReturn(parmList);
        Iterable<Parameter> resultFromService = this.parameterService.getAll();
        Parameter resultFromIterator = resultFromService.iterator().next();
        assertThat(resultFromIterator.getType()).isEqualTo(ParameterEnum.PROFITMARGIN);
        Mockito.verify(this.repo, Mockito.times(1)).findAll();
    }

    // Find unique by parameter type //
    @Test
    public void findByTypeTest() {
        // Hier zeg je wat de mock moet geven als je de findByType oproept op de repository.
        Mockito.when(this.repo.findByType(ParameterEnum.DAMAGEDISCOUNT)).thenReturn(testParameter3);

        // Hier roep je de findByType op de repository aan VIA de service findByType.
        Parameter resultFromService = this.parameterService.findByType(ParameterEnum.DAMAGEDISCOUNT);

        assertEquals(20, resultFromService.getPercentage());
    }

    // Update parameter by type TRUE //
    @Test
    public void updateParameterByTypeTrueTest() {
        // In de methode updateParameterByType gebeurd een findByType en een save :
        // Hier zeg je wat de mock moet geven als je de findByType oproept op de repository.
        Mockito.when(this.repo.findByType(ParameterEnum.PREMIUMCUSTOMER)).thenReturn(testParameter2);
        // Hier zeg je wat de mock moet geven als je de save oproept op de repository.
        Mockito.when(this.repo.save(testParameter2)).thenReturn(testParameter2);

        // Hier roep je de findByType op de Repository en de save op de CrudTRepository aan VIA de service updateParameterByType.
        boolean resultFromService = this.parameterService.updateParameterByType(testParameter2);

        assertTrue(resultFromService);
    }

    // Update parameter by type FALSE //
    @Test
    public void updateParameterByTypeFalseTest() {
        // In de methode updateParameterByType gebeurd een findByType en een save :
        // Hier zeg je wat de mock moet geven als je de findByType oproept op de repository.
        Mockito.when(this.repo.findByType(ParameterEnum.PREMIUMCUSTOMER)).thenReturn(null);
        // Hier zeg je wat de mock moet geven als je de save oproept op de repository. In dit geval overbodig.
        // Mockito.when(this.repo.save(testParameter2)).thenReturn(testParameter2);

        // Hier roep je de findByType op de Repository en de save op de CrudTRepository aan VIA de service updateParameterByType.
        boolean resultFromService = this.parameterService.updateParameterByType(testParameter2);

        assertFalse(resultFromService);
    }

    // Delete parameter by id TRUE //
    @Test
    public void deleteParameterTrueTest() {
        // In de methode deleteParameter gebeurd een existsById en een deleteById :
        // Hier zeg je wat de mock moet geven als je de existsById oproept op de repository.
        Mockito.when(this.repo.existsById(1L)).thenReturn(true);
        // De mock geeft niets terug als je de deleteById oproept op de repository, dus kan je niets opgeven.

        // Hier roep je de deleteById op de Repository en de save op de CrudTRepository aan VIA de service updateParameterByType.
        boolean resultFromService = this.parameterService.deleteParameter(1L);

        assertTrue(resultFromService);

        // Tel hoeveel maal deleteById werd opgeroepen
        //  Mockito.verify(this.repo, Mockito.times(1)).deleteById(1L);
    }

    // Delete parameter by id FALSE //
    @Test
    public void deleteParameterFalseTest() {
        // In de methode deleteParameter gebeurd een existsById en een deleteById :
        // Hier zeg je wat de mock moet geven als je de existsById oproept op de repository.
        Mockito.when(this.repo.existsById(100001L)).thenReturn(false);
        // De mock geeft niets terug als je de deleteById oproept op de repository, dus kan je niets opgeven.

        // Hier roep je de deleteById op de Repository en de save op de CrudTRepository aan VIA de service updateParameterByType.
        boolean resultFromService = this.parameterService.deleteParameter(100001L);

        assertFalse(resultFromService);

        // Tel hoeveel maal deleteById werd opgeroepen
        //  Mockito.verify(this.repo, Mockito.times(0)).deleteById(1L);
    }
}
