package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.transferitems.SalesReceipt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SoldGameServiceIT {

    @Autowired
    private SoldItemService gameService;

    @Test
    public void createReadUpdateDeleteTestIT(){//De IT toevoeging zorgt ervoor dat dat de test later in de buildfase gerund wordt.

        // BUILD the game
        SalesReceipt salesReceipt1 = new SalesReceipt();
        salesReceipt1.setDate("23/01/2018");

        // CREATE TEST
        SalesReceipt inserted = this.gameService.addOne(salesReceipt1);
        assertTrue(inserted.getDate().equals("23/01/2018"));
        assertFalse(inserted.getId()==0);
        Long id = inserted.getId();

        // READ TEST
        SalesReceipt foundWithGetOne = this.gameService.findOneById(id);
        assertEquals("Id komt niet overeen",inserted.getDate(), foundWithGetOne.getDate());

        // UPDATE TEST
        foundWithGetOne.setDate("09/03/2018");
        SalesReceipt foundAfterSave = this.gameService.updateOneById(id,foundWithGetOne);
        assertEquals("Melding komt niet overeen","09/03/2018",foundAfterSave.getDate());

        // DELETE TEST
        this.gameService.deleteOneById(id);
        assertNull(this.gameService.findOneById(id));
    }

}