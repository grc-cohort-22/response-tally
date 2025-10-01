import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * The Tallyer class provides functionality for reading ID and topic pairs from
 * user input,
 * and tallying the number of occurrences of each topic.
 */
public class Tallyer {

    /**
     * The main method serves as the entry point for the program. It reads pairs of
     * IDs and topics
     * from standard input, stores them in lists, and then calculates the number of
     * occurrences
     * of each topic. The IDs and topics are guaranteed to not include internal
     * whitespace.
     *
     * @param args command-line arguments (not used in this implementation)
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        List<String> ids = new ArrayList<>();
        List<String> topics = new ArrayList<>();

        // Reading input for IDs and topics
        // Assumes file is well formed into pairs
        while (input.hasNext()) {
            ids.add(input.next());
            topics.add(input.next());
        }
        input.close();

        // Wave 1
        Map<String, Integer> topicCounts = tallyTopics(topics);
        System.out.println("Here are how many times each topic appears (unfiltered):");
        System.out.println(topicCounts);

        // Wave 2
        Map<String, Integer> topicCountsFiltered = tallyTopicsFiltered(ids, topics);
        System.out.println("Here are how many times each topic appears (filtered):");
        System.out.println(topicCountsFiltered);
    }

    /**
     * Tally the occurrences of each topic from the provided list of topics.
     * This method takes a list of topics. It returns a Map where the keys are
     * topics
     * and the values are how many times they appear in the input.
     *
     * @param topics a list of strings representing the topics to be tallied
     * @return a map containing topics as keys and their occurrence counts as values
     */
    public static Map<String, Integer> tallyTopics(List<String> topics) {
        // WAVE 1
        Map<String, Integer> map = new HashMap<>();

        // TODO: Implement this method
        for (String topic : topics) {
            // Check if the topic is already in the map
            if (!map.containsKey(topic)) {
                map.put(topic, 1);
            } else {
                map.put(topic, map.get(topic) + 1);
            }
        }

        // Return the map
        return map;
    }

    /**
     * Tally the occurrences of valid votes for each topic from the provided lists
     * of IDs and topics.
     * 
     * The lists are of equal length and are aligned: the id at index zero cast a
     * vote for
     * the topic at endex 0, the id at index 1 cast a vote for the topic at index 1,
     * etc.
     * It returns a map where each topic is associated with the number of times it
     * appears in the input.
     * However, any user who did not enter exactly 2 topics should not have their
     * votes counted.
     *
     * @param ids    a list of strings representing IDs associated with each topic
     * @param topics a list of strings representing the topics to be tallied
     * @return a map containing topics as keys and their occurrence counts as values
     */
    public static Map<String, Integer> tallyTopicsFiltered(List<String> ids, List<String> topics) {
        // WAVE 2
        // TODO: Implement this method
        Map<String, Integer> validUsers = new HashMap<>();

        // For every student id in ids array
        for (String id : ids) {
            // Check if the id is already in the map
            if (validUsers.containsKey(id)) {
                // id has been found, tally votes
                validUsers.put(id, validUsers.get(id) + 1);
            } else {
                // id was not found, create one
                validUsers.put(id, 1);
            }
        }

        // Filter all invalid votes
        // Iterator to safely iterate
        Iterator<Map.Entry<String, Integer>> iterator = validUsers.entrySet().iterator();

        // Loop through the map with an iterator
        while (iterator.hasNext()) {
            // Store current entry
            Map.Entry<String, Integer> entry = iterator.next();

            // Remove the entry if it does not have two votes
            if (entry.getValue() != 2) {
                iterator.remove();
            }
        }

        // Only valid users remain in the map from this point
        // Iterate over topics and make a new map
        Map<String, Integer> topicVotes = new HashMap<>();
        System.out.println(validUsers);

        for (int i = 0; i < topics.size(); i++) {
            // Check if the student is in our valid vote map
            if (validUsers.containsKey(ids.get(i))) {
                // Vote is valid, check if the topic
                if (topicVotes.containsKey(topics.get(i))) {
                    topicVotes.put(topics.get(i), topicVotes.get(topics.get(i)) + 1);
                } else {
                    topicVotes.put(topics.get(i), 1);
                }
            }
        }

        // Return
        return topicVotes;
    }
}
