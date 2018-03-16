package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.basicitems.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameServiceIT {

    @Autowired
    private GameService service;

    @Test
    public void createReadUpdateDeleteTestIT(){//De IT toevoeging zorgt ervoor dat dat de test later in de buildfase gerund wordt.

        // BUILD the game

        Game testGame1 = new Game();
        testGame1.setEdition(1);
        testGame1.setName("testgame1");

        // CREATE TEST
        Game inserted = this.service.addOne(testGame1);
        assertTrue(inserted.getName().equals("testgame1"));
        assertFalse(inserted.getId()==0);
        Long id = inserted.getId();

        // READ TEST
        Game foundWithGetOne = this.service.findOneById(id);
        assertEquals("Id komt niet overeen",inserted.getName(), foundWithGetOne.getName());

        // UPDATE TEST
        foundWithGetOne.setName("Change");
        Game foundAfterSave = this.service.updateOneById(id,foundWithGetOne);
        assertEquals("Melding komt niet overeen","Change",foundAfterSave.getName());

        // DELETE TEST
        this.service.deleteOneById(id);
        assertNull(this.service.findOneById(id));
    }

}