package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Game;
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
    private GameService gameService;

    @Test
    public void createReadUpdateDeleteTestIT(){//De IT toevoeging zorgt ervoor dat dat de test later in de buildfase gerund wordt.

        // BUILD the game
        Game.Builder game1 = new Game.Builder();
        game1.title("testgame1").edition(1);
        Game testGame1 = game1.build();

        // CREATE TEST
        Game inserted = this.gameService.insertGame(testGame1);
        assertTrue(inserted.getTitle().equals("testgame1"));
        assertFalse(inserted.getId()==0);
        Long id = inserted.getId();

        // READ TEST
        Game foundWithGetOne = this.gameService.getOne(id);
        assertEquals("Id komt niet overeen",inserted.getTitle(), foundWithGetOne.getTitle());

        // UPDATE TEST
        foundWithGetOne.setTitle("Change");
        Game foundAfterSave = this.gameService.changeGame(id,foundWithGetOne);
        assertEquals("Melding komt niet overeen","Change",foundAfterSave.getTitle());

        // DELETE TEST
        this.gameService.deleteById(id);
        assertNull(this.gameService.getOne(id));
    }

}
