package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Parameter;
import be.mielnoelanders.bazinga.domain.ParameterEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParameterServiceIT {

    @Autowired
    private ParameterService service;

    @Test
    public void crudParameterTest() {

        // MAKE new parameter
        Parameter newParm = new Parameter();
        newParm.setType(ParameterEnum.PROFITMARGIN);
        newParm.setPercentage(15);

        // CREATE TEST
        Parameter insertedParm = this.service.addOne(newParm);
        assertTrue(insertedParm.getType().equals(ParameterEnum.PROFITMARGIN));
        assertFalse(insertedParm.getId()==0);
        Long id = insertedParm.getId();

        // READ TEST
        Parameter readParm = this.service.findOneById(id);
        assertEquals(newParm.getType(), readParm.getType());

        // UPDATE TEST
        readParm.setPercentage(10);
        Parameter updatedParm = this.service.updateOneById(id, readParm);
        assertEquals(10, updatedParm.getPercentage());

        // DELETE
        this.service.deleteOneById(id);
        assertNull(this.service.findOneById(id));
    }
}
