package be.mielnoelanders.bazinga.restcontroller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@WebAppConfiguration
public class GameEndPointTest /*extends AbstractTransactionalJUnit4SpringContextTests*/ {

    //SERVER MOET DRAAIEN
    private static final String URL = "http://localhost:8080/api/games";
    RestTemplate template;

    @Before
    public void init() {
        template = new RestTemplate();
    }

}







/*    @Test
    public void getAllTest() {
        ResponseEntity<String> jsonArray = template.getForEntity(URL, String.class);
        try {
            JSONArray array = new JSONArray(jsonArray.getBody());
            List<Game> allGames = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonGame = array.getJSONObject(i);
                Game javaGame = makeJavaGameObject(jsonGame);
                allGames.add(javaGame);
            }

            Game myFirstGame = allGames.get(0);
            assertThat(myFirstGame.getId()).isEqualTo(1);
            assertThat(myFirstGame.getTitle()).isEqualToIgnoringCase("Dit is game 1");
            assertThat(myFirstGame.getEdition()).isEqualTo(1);

            assertThat(allGames).isNotEmpty();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddBook() {
        String URL_ADD = “http:
//localhost:8085/books/add”;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String jsonBook = “
        {\“title\“:\“De stille kracht\“,\“author\“:\“Louis Couperus\“,\“publisher\“:\“Athenaeum - Polak & Van Gennep\“,\“
            isbn\“:\“9789064037023\“,\“numberOfPages\“:249,\“ebook\“:false,\“subject\“:\“In Laboewangi op het
            eiland Java vindt een aantal onverklaarbare gebeurtenissen plaats.De inwoners wijzen die toe aan ‘de stille
            kracht’,een Indisch mysterie dat de mensen in zijn greep houdt.\“,\“language\“:\“DUTCH\“,\“genre\“:\“
            THRILLER\“}”;
        HttpEntity<String> data = new HttpEntity<>(jsonBook, headers);

        ResponseEntity<Integer> responseEntity = template.postForEntity(URL_ADD, data, Integer.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testUpdateBook() {
        String URL_UPDATE = “http:
//localhost:8085/books/update”;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String jsonBook = “
        {\“id\“:16,\“title\“:\“De stille kracht\“,\“author\“:\“Louis Couperus\“,\“publisher\“:\“Athenaeum - Polak & Van
            Gennep\“,\“isbn\“:\“9789064037023\“,\“numberOfPages\“:249,\“ebook\“:false,\“subject\“:\“In Laboewangi op het
            eiland Java vindt een aantal onverklaarbare gebeurtenissen plaats.\“,\“language\“:\“DUTCH\“,\“genre\“:\“
            THRILLER\“}”;
        HttpEntity<String> data = new HttpEntity<>(jsonBook, headers);

        ResponseEntity<Integer> responseEntity = template.postForEntity(URL_UPDATE, data, Integer.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testDeleteBook() {
        String URL_DELETE = “http:
//localhost:8085/books/delete”;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String jsonBook = “
        {\“id\“:16,\“title\“:\“De stille kracht\“,\“author\“:\“Louis Couperus\“,\“publisher\“:\“Athenaeum - Polak & Van
            Gennep\“,\“isbn\“:\“9789064037023\“,\“numberOfPages\“:249,\“ebook\“:false,\“subject\“:\“In Laboewangi op het
            eiland Java vindt een aantal onverklaarbare gebeurtenissen plaats.\“,\“language\“:\“DUTCH\“,\“genre\“:\“
            THRILLER\“}”;
        HttpEntity<String> data = new HttpEntity<>(jsonBook, headers);

        ResponseEntity<Integer> responseEntity = template.exchange(URL_DELETE, HttpMethod.DELETE, data, Integer.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testFindByAuthor() {
        String URL_AUTHOR = “http:
//localhost:8085/books/author/Johan”;
        ResponseEntity<String> jsonArray = template.getForEntity(URL_AUTHOR, String.class);
        try {
            JSONArray array = new JSONArray(jsonArray.getBody());
            List<Book> allBooks = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonBook = array.getJSONObject(i);
                Book javaBook = makeJavaBookObject(jsonBook);
                allBooks.add(javaBook);
            }

            assertThat(allBooks).isNotEmpty();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByTitle() {
        String URL_TITLE = “http:
//localhost:8085/books/title/man”;
        ResponseEntity<String> jsonArray = template.getForEntity(URL_TITLE, String.class);
        try {
            JSONArray array = new JSONArray(jsonArray.getBody());
            List<Book> allBooks = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonBook = array.getJSONObject(i);
                Book javaBook = makeJavaBookObject(jsonBook);
                allBooks.add(javaBook);
            }

            assertThat(allBooks).isNotEmpty();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByGenre() {
        String URL_GENRE = “http:
//localhost:8085/books/genre/CLASSIC”;
        ResponseEntity<String> jsonArray = template.getForEntity(URL_GENRE, String.class);
        try {
            JSONArray array = new JSONArray(jsonArray.getBody());
            List<Book> allBooks = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonBook = array.getJSONObject(i);
                Book javaBook = makeJavaBookObject(jsonBook);
                allBooks.add(javaBook);
            }

            assertThat(allBooks).isNotEmpty();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByKeyword() {
        String URL_KEY = “http:
//localhost:8085/books/keyword/jo”;
        ResponseEntity<String> jsonArray = template.getForEntity(URL_KEY, String.class);
        try {
            JSONArray array = new JSONArray(jsonArray.getBody());
            List<Book> allBooks = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonBook = array.getJSONObject(i);
                Book javaBook = makeJavaBookObject(jsonBook);
                allBooks.add(javaBook);
            }

            assertThat(allBooks).isNotEmpty();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByEbook() {
        String URL_EBOOK = “http:
//localhost:8085/books/ebook/true”;
        ResponseEntity<String> jsonArray = template.getForEntity(URL_EBOOK, String.class);
        try {
            JSONArray array = new JSONArray(jsonArray.getBody());
            List<Book> allBooks = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonBook = array.getJSONObject(i);
                Book javaBook = makeJavaBookObject(jsonBook);
                allBooks.add(javaBook);
            }

            assertThat(allBooks).isNotEmpty();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByIsbn() {
        String URL_ISBN = “http:
//localhost:8085/books/isbn/9789028204652”;
        ResponseEntity<String> jsonArray = template.getForEntity(URL_ISBN, String.class);
        try {
            JSONArray array = new JSONArray(jsonArray.getBody());
            JSONObject jsonBook = array.getJSONObject(0);
            Book javaBook = makeJavaBookObject(jsonBook);

            assertThat(javaBook.getId()).isEqualTo(8);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static Game makeJavaGameObject(JSONObject jsonObject) {
        Game.Builder game1 = new Game.Builder();
        Game javaGame;

        try{
            game1.title(jsonObject.getString("title"));
            game1.edition(jsonObject.getInt("edition"));
            game1.publisher(makeJavaPublisher(jsonObject.getJSONObject(("publisher"))));
            //game1.supplierGames(makeJavaSupplierGames(jsonObject.getJSONArray("suppliergames"))); //IK WAS HIER BEGONNEN AAN HET MAKEN VAN DE ANDEREN MAAR DAT DRAAIDE IN DE SOEP

        } catch (JSONException e) {
            e.printStackTrace();
        }
        javaGame = game1.build();
        return javaGame;
    }

    private static Publisher makeJavaPublisher(JSONObject jsonObject) {
        Publisher publisher = new Publisher();

        try {
            publisher.setName(jsonObject.getString("name"));
            publisher.setWebsite(jsonObject.getString("website"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return publisher;
    }

//    private static List<SupplierGames> makeJavaSupplierGames(JSONArray jsonArray) {
//        List<SupplierGames> games = new ArrayList<>();
//        try {
//            for (int i = 0; i < jsonArray.length(); i++){
//                SupplierGames supplierGame = new SupplierGames();
//                JSONObject object = (JSONObject) jsonArray.get(i);
//                supplierGame.setPurchasePrice(object.getDouble("purchasePrice"));
//                supplierGame.setGame(makeJavaGameObject(object.getJSONObject("game")));
//                supplierGame.setDate(object.getString("date"));
//                supplierGame.setSupplier();
//
//                games.add(supplierGame);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return games;
//    }

}


       *//*




        try {
            javaBook.setId(jsonObject.getInt(“id”));
            javaBook.setAuthor(jsonObject.getString(“author”));
            javaBook.setEbook(jsonObject.getBoolean(“ebook”));
            javaBook.setIsbn(jsonObject.getString(“isbn”));
            javaBook.setNumberOfPages(jsonObject.getInt(“number_of_pages”));
            javaBook.setPublisher(jsonObject.getString(“publisher”));
            javaBook.setSubject(jsonObject.getString(“subject”));
            javaBook.setTitle(jsonObject.getString(“title”));
            //IF GENRE AND LANGUAGE ARE ‘NULL’
            String genre = jsonObject.getString(“genre”);
            if (genre != null) {
                javaBook.setGenre(EnumMapper.mapToGenre(Integer.parseInt(genre)));
            }
            String language = jsonObject.getString(“language”);
            if (language != null) {
                javaBook.setLanguage(EnumMapper.mapToLanguage(Integer.parseInt(language)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return javaBook;
    }
}*/

