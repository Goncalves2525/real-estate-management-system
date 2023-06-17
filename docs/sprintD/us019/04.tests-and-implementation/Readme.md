# US 019 -  I want to divide the set of all stores into two subsets

# 4. Tests 

**Test 1:** Test of Tuple constructor

    @Test
    void Tuple() {
    Tuple<String, Integer> tuple = new Tuple<>("3", 16);
    assertEquals("3", tuple.getFirst());
    assertEquals(Integer.valueOf(16), tuple.getSecond());
    }

**Test 2:** Test of add method

    @Test
    void add() {
        Tuple<String, Integer> tuple1 = new Tuple<>("2", 3);
        Tuple<String, Integer> tuple2 = new Tuple<>("4", 5);
        Tuple<String, Integer> sum = tuple1.add(tuple2);
        assertEquals(Integer.valueOf(4), Integer.valueOf(Integer.parseInt(sum.getFirst())));
        assertEquals(Integer.valueOf(5), sum.getSecond());
    }



# 5. Construction (Implementation)

## Class Tuple

```java
package pt.ipp.isep.dei.esoft.project.domain;

public class Tuple<T1, T2> {
    private T1 first;
    private T2 second;

    public Tuple(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    public void setFirst(T1 first) {
        this.first = first;
    }

    public void setSecond(T2 second) {
        this.second = second;
    }

    //method to add a new tuple
    public Tuple<T1, T2> add(Tuple<T1, T2> tuple) {
        T1 first = tuple.getFirst();
        T2 second = tuple.getSecond();
        return new Tuple<>(first, second);
    }


}


```     



# 6. Integration and Demo 

* A new option on the Network Manager menu options was added.


# 7. Observations






