package test;

import dao.impl.DaoPositions;
import entity.Position;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DaoTestPosition {

    private DaoPositions daoPosition;

    @BeforeAll
    public void setUp() {
        daoPosition = new DaoPositions();
    }

    @Test
    public void testListPositions() {
        daoPosition.listPositions("A", 10000, 15000).forEach(System.out::println);
    }


    @AfterAll
    public void tearDown() {
        daoPosition = null;
    }

}
