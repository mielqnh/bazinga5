package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.basicitems.Accessory;
import be.mielnoelanders.bazinga.domain.enums.AccessoryType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccessoryServiceIT {

    @Autowired
    private AccessoryService service;

    @Test
    public void createReadUpdateDeleteTestIT(){

        // BUILD the game
        Accessory testAccessory1 = new Accessory();
        testAccessory1.setType(AccessoryType.DICE);
        testAccessory1.setName("testaccessory1");

        // CREATE TEST
        Accessory inserted = this.service.addOne(testAccessory1);
        assertTrue(inserted.getName().equals("testaccessory1"));
        assertFalse(inserted.getId()==0);
        Long id = inserted.getId();

        // READ TEST
        Accessory foundWithGetOne = this.service.findOneById(id);
        assertEquals("Id komt niet overeen",inserted.getName(), foundWithGetOne.getName());

        // UPDATE TEST
        foundWithGetOne.setName("Change");
        Accessory foundAfterSave = this.service.updateOneById(id,foundWithGetOne);
        assertEquals("Melding komt niet overeen","Change",foundAfterSave.getName());

        // DELETE TEST
        this.service.deleteOneById(id);
        assertNull(this.service.findOneById(id));
    }
}