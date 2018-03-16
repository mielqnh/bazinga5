package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.transferitems.SoldItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SoldItemServiceIT {

    @Autowired
    private SoldItemService gameService;

    @Test
    public void createReadUpdateDeleteTestIT(){//De IT toevoeging zorgt ervoor dat dat de test later in de buildfase gerund wordt.

        // BUILD the game
        SoldItem soldItem1 = new SoldItem();
        soldItem1.setDate("23/01/2018");

        // CREATE TEST
        SoldItem inserted = this.gameService.addOne(soldItem1);
        assertTrue(inserted.getDate().equals("23/01/2018"));
        assertFalse(inserted.getId()==0);
        Long id = inserted.getId();

        // READ TEST
        SoldItem foundWithGetOne = this.gameService.findOneById(id);
        assertEquals("Id komt niet overeen",inserted.getDate(), foundWithGetOne.getDate());

        // UPDATE TEST
        foundWithGetOne.setDate("09/03/2018");
        SoldItem foundAfterSave = this.gameService.updateOneById(id,foundWithGetOne);
        assertEquals("Melding komt niet overeen","09/03/2018",foundAfterSave.getDate());

        // DELETE TEST
        this.gameService.deleteOneById(id);
        assertNull(this.gameService.findOneById(id));
    }

}