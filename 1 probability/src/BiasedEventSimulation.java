import java.util.*;

public class BiasedEventSimulation {
    private List<Map.Entry<Object, Integer>> outcomes;

    public BiasedEventSimulation(List<Map.Entry<Object, Integer>> outcomes) {
        this.outcomes = outcomes;
    }

    // Simulate one occurrence of the event
    public Object simulateEvent() {
        int totalWeight = outcomes.stream().mapToInt(Map.Entry::getValue).sum();
        int randomNum = new Random().nextInt(totalWeight);
        int cumulativeWeight = 0;

        for (Map.Entry<Object, Integer> entry : outcomes) {
            cumulativeWeight += entry.getValue();
            if (randomNum < cumulativeWeight) {
                return entry.getKey();
            }
        }

        // This should not happen if probabilities are valid
        return null;
    }

    // Simulate multiple occurrences of the event
    public Map<Object, Integer> simulateEvents(int numOccurrences) {
        Map<Object, Integer> occurrenceCount = new HashMap<>();

        for (int i = 0; i < numOccurrences; i++) {
            Object outcome = simulateEvent();
            occurrenceCount.put(outcome, occurrenceCount.getOrDefault(outcome, 0) + 1);
        }

        return occurrenceCount;
    }

    public static void main(String[] args) {
        // Example Usage
        List<Map.Entry<Object, Integer>> probabilities = new ArrayList<>();
        probabilities.add(new AbstractMap.SimpleEntry<>(1, 35));
        probabilities.add(new AbstractMap.SimpleEntry<>(2, 65));

        BiasedEventSimulation event = new BiasedEventSimulation(probabilities);
        Map<Object, Integer> occurrences = event.simulateEvents(1000);

        // Print the results
        System.out.println("Outcome Counts:");
        for (Map.Entry<Object, Integer> entry : occurrences.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
