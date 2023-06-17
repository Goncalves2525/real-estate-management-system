package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TupleTest {

    /**
     * Test of Tuple constructor
     */
    @Test
    void Tuple() {
        Tuple<String, Integer> tuple = new Tuple<>("3", 16);
        assertEquals("3", tuple.getFirst());
        assertEquals(Integer.valueOf(16), tuple.getSecond());
    }

    /**
     * Test of add method
     */
    @Test
    void add() {
        Tuple<String, Integer> tuple1 = new Tuple<>("2", 3);
        Tuple<String, Integer> tuple2 = new Tuple<>("4", 5);
        Tuple<String, Integer> sum = tuple1.add(tuple2);
        assertEquals(Integer.valueOf(4), Integer.valueOf(Integer.parseInt(sum.getFirst())));
        assertEquals(Integer.valueOf(5), sum.getSecond());
    }
}