package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.basicitems.Expansion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpansionServiceIT {

    @Autowired
    private ExpansionService service;

    @Test
    public void createReadUpdateDeleteTestIT(){//De IT toevoeging zorgt ervoor dat dat de test later in de buildfase gerund wordt.

        // BUILD the game
        Expansion testExpansion1 = new Expansion();
        testExpansion1.setName("testexpansion1");

        // CREATE TEST
        Expansion inserted = this.service.addOne(testExpansion1);
        assertTrue(inserted.getName().equals("testexpansion1"));
        assertFalse(inserted.getId()==0);
        Long id = inserted.getId();

        // READ TEST
        Expansion foundWithGetOne = this.service.findOneById(id);
        assertEquals("Id komt niet overeen",inserted.getName(), foundWithGetOne.getName());

        // UPDATE TEST
        foundWithGetOne.setName("Change");
        Expansion foundAfterSave = this.service.updateOneById(id,foundWithGetOne);
        assertEquals("Melding komt niet overeen","Change",foundAfterSave.getName());

        // DELETE TEST
        this.service.deleteOneById(id);
        assertNull(this.service.findOneById(id));
    }
}