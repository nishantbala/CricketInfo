package com.stackroute.favouriteservice.repository;


        import static org.junit.Assert.assertEquals;

        import java.util.List;

        import javax.transaction.Transactional;

        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
        import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
        import org.springframework.test.context.junit4.SpringRunner;

        import com.stackroute.favouriteservice.domain.FavList;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class FavListRepositoryTest {
    @Autowired
    private FavListRepository favListRepository;

    @Test
    public void findByUserId() throws Exception {
        long matchID = 1144512;
        long favListID = 1144512;
        FavList watchList = new FavList(favListID, "poovi", matchID);
        FavList watchData = favListRepository.save(watchList);
        List<FavList> list = favListRepository.findByUserId("poovi");
        assertEquals("poovi", list.get(0).getUserId().toString());
    }
}

