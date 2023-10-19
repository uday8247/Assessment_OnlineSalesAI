import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.*;

public class BiasedEventSimulationTest {

    @Test
    public void testSimulateEvents() {
        List<Map.Entry<Object, Integer>> probabilities = new ArrayList<>();
        probabilities.add(new AbstractMap.SimpleEntry<>(1, 35));
        probabilities.add(new AbstractMap.SimpleEntry<>(2, 65));

        BiasedEventSimulation event = new BiasedEventSimulation(probabilities);
        Map<Object, Integer> occurrences = event.simulateEvents(1000);

        assertEquals(1000, occurrences.values().stream().mapToInt(Integer::intValue).sum());

        // Check if probabilities are roughly followed
        int headCount = occurrences.get(1);
        int tailCount = occurrences.get(2);
        double headPercentage = (double) headCount / 1000 * 100;
        double tailPercentage = (double) tailCount / 1000 * 100;

        assertEquals(35, (int) headPercentage, 5);
        assertEquals(65, (int) tailPercentage, 5);
    }
}
