package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Parameter;
import be.mielnoelanders.bazinga.domain.ParameterEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParameterServiceIT {

    @Autowired
    private ParameterService parameterService;

    @Test
    public void crudParameterTest() {
        Parameter newParm = new Parameter();
        newParm.setType(ParameterEnum.PROFITMARGIN);

        //test create Parameter
        Parameter insertedParm = parameterService.addOne(newParm);
        long newId = insertedParm.getId();
        // id is auto incremented dus kan niet 0 zijn !
        Assert.assertFalse(newId == 0);

/*        //test read Parameter
        Parameter readParm = parameterService.findOneById(insertedParm.getType());
        Assert.assertEquals(newParm.getType(), readParm.getType());

        //test update Parameter
        readParm.setPercentage(15);
        boolean updatedParm = parameterService.updateOneById(readParm);
        Assert.assertTrue(updatedParm);*/

        //test delete Parameter
        boolean deletedParm = parameterService.deleteOneById(newId);
        Assert.assertTrue(deletedParm);
    }
}
