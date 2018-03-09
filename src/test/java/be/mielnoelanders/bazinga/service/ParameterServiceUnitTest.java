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

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ParameterServiceUnitTest {

    @InjectMocks
    private ParameterServiceImpl parameterService;

    @Mock
    private ParameterRepository repo;

    private Parameter testParameter;

    @Before
    public void init(){
        Parameter parm1 = new Parameter();
            parm1.setType(ParameterEnum.PROFITMARGIN);
            parm1.setPercentage(50);

        testParameter = parm1;
    }

    // Insert new Parameter //
    @Test
    public void addParameterTest(Parameter parameter) {

    }

    // Get all parameters //
    @Test
    public void getAllTest() {

    }

    // Find unique by parameter type //
    @Test
    public void findByTypeTest(){

        // Hier zeg je wat de mock moet geven als je de findByType oproept op de repository.
        Mockito.when(this.repo.findByType(ParameterEnum.PROFITMARGIN))
               .thenReturn(testParameter);

        // Hier roep je de findByType op de repository aan VIA de service findByType.
        Parameter resultFromService = this.parameterService.findByType(ParameterEnum.PROFITMARGIN);

        assertEquals(50, resultFromService.getPercentage());
    }

    // Update parameter by type //
    public void updateParameterByType(Parameter parameter) {

    }

    // Delete parameter by id //
    public void deleteParameter(long id) {

    }

}