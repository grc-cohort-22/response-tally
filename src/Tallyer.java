import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The Tallyer class provides functionality for reading ID and topic pairs from user input,
 * and tallying the number of occurrences of each topic.
 */
public class Tallyer {

    /**
     * The main method serves as the entry point for the program. It reads pairs of IDs and topics
     * from standard input, stores them in lists, and then calculates the number of occurrences
     * of each topic. The IDs and topics are guaranteed to not include internal whitespace.
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
     * This method takes a list of topics. It returns a Map where the keys are topics
     * and the values are how many times they appear in the input.
     *
     * @param topics a list of strings representing the topics to be tallied
     * @return a map containing topics as keys and their occurrence counts as values
     */

    public static Map<String, Integer> tallyTopics(List<String> topics) {
        // WAVE 1
        // TODO: Implement this method
        Map<String, Integer> topicMap = new Hashtable();
        // String topicID
        // int topicValue
        for (String topicID : topics) {
            if (topicMap.containsKey(topicID)){
                topicMap.replace(topicID, (topicMap.get(topicID) + 1));
            }
            else {
                topicMap.put(topicID, 1);
            }
        }
        System.out.println(topicMap);
        return topicMap;
    }

    /**
     * Tally the occurrences of valid votes for each topic from the provided lists of IDs and topics.
     * 
     * The lists are of equal length and are aligned: the id at index zero cast a vote for
     * the topic at endex 0, the id at index 1 cast a vote for the topic at index 1, etc.
     * It returns a map where each topic is associated with the number of times it appears in the input.
     * However, any user who did not enter exactly 2 topics should not have their votes counted.
     *
     * @param ids a list of strings representing IDs associated with each topic
     * @param topics a list of strings representing the topics to be tallied
     * @return a map containing topics as keys and their occurrence counts as values
     */
    public static Map<String, Integer> tallyTopicsFiltered(List<String> ids, List<String> topics) {
      // WAVE 2 
      // TODO: Implement this method
        Map<String, Integer> userOccurances = new HashMap<>();
        Map<String, Integer> topicMap = new HashMap<>();
        List<String> bannedIDs = new ArrayList<>();
        for (String n : ids) {
            if (userOccurances.containsKey(n)){
                userOccurances.replace(n, userOccurances.get(n) + 1);
            }
            else{
                userOccurances.put(n, 1);
            }
        }
        for (Map.Entry<String, Integer> Thing : userOccurances.entrySet()) {
            if (Thing != null) {
                if (Thing.getValue() != 2){
                    System.out.println(Thing.getKey() + " was banned for votes not being equal to 2 (" + Thing.getValue() + ")");
                    bannedIDs.add(Thing.getKey());
                    userOccurances.remove(Thing.getKey());
                }
            }
        }
        for (String topicIndividual : topics) {   
            if (!topicMap.containsKey(topicIndividual)){
                topicMap.put(topicIndividual, 1);
            }
        }
        for (String n : ids) {
            if (!bannedIDs.contains(n)) {
                String topicFound = topics.get(ids.indexOf(n));
                System.out.println(topicFound + " is the value of " + n);
                //topic gets vote
                List<String> secondpartOfList = new ArrayList<>();
                //Does not already exist
                if (!topicMap.containsKey(topicFound)) {
                    topicMap.put(topicFound, 1);
                    System.out.println("Topic found, adding to topics: " + topicFound);
                }
                else {
                    // id exists and is not banned, add to topicMap which has topic found
                    topicMap.replace(topicFound, (topicMap.get(topicFound) + 1));
                }
            }
        }


      return topicMap;
  }
}
