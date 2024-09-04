import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.klingenberg.portlib.IndexCalculator;

class IndexCalculatorTest {

    IndexCalculator indexCalculator = new IndexCalculator();

    @Test
    void stringsToArray() {
        String string1 = "1-3,7,9";
        String string2 = "1-3,5,10";
        Assertions.assertArrayEquals(
                indexCalculator.stringArrayToIntArray(new String[]{string1, string2}),
                new Integer[][]{{1,2,3,7,9},{1,2,3,5,10}});
    }

    @Test
    void wrongDataFormat(){
        String badString = "3-1,4,48";
        Assertions.assertThrows(RuntimeException.class,
                () -> indexCalculator.stringToIntArray(badString), "Wrong data format");
    }

    @Test
    void allPossibleCombinations(){
        String[] strings = new String[]{ "1,3-5", "2", "3-4" };
        Integer[][] result = new Integer[][]{{1, 2, 3}, {1, 2, 4}, {3, 2, 3}, {3, 2, 4}, {4, 2, 3}, {4, 2, 4}, {5, 2, 3}, {5, 2, 4}};
        Assertions.assertArrayEquals(indexCalculator.allPossibleCombinations(strings), result);
    }
}