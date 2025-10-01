import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

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
        Map<String, Integer> tallyMap = new TreeMap<>();
        int occur = 1;

        for(String topic : topics)
        {
            if(tallyMap.containsKey(topic) == false)
            {
                tallyMap.put(topic, occur);
            }
            else
            {
                occur = tallyMap.get(topic);
                tallyMap.replace(topic, occur, occur + 1);
                occur = 1;
            }
        }

        return tallyMap;
    }

    /**
     * Tally the occurrences of valid votes for each topic from the provided lists of IDs and topics.
     * 
     * The lists are of equal length and are aligned: the id at index zero cast a vote for
     * the topic at index 0, the id at index 1 cast a vote for the topic at index 1, etc.
     * It returns a map where each topic is associated with the number of times it appears in the input.
     * However, any user who did not enter exactly 2 topics should not have their votes counted.
     *
     * @param ids a list of strings representing IDs associated with each topic
     * @param topics a list of strings representing the topics to be tallied
     * @return a map containing topics as keys and their occurrence counts as values
     */
    public static Map<String, Integer> tallyTopicsFiltered(List<String> ids, List<String> topics) {
        // WAVE 2
        Map<String, Integer> filterIDMap = new TreeMap<>();
        Map<String, Integer> filterTopics = new TreeMap<>();
        List<String> filterIDList = new ArrayList<>();
        int occur = 1;

        for(String id : ids)
        {
            if(!filterIDMap.containsKey(id))
            {
                filterIDMap.put(id, 1);
            }
            else
            {
                occur = filterIDMap.get(id);
                filterIDMap.put(id, occur + 1);
                occur = 1;
            }
        }

        for(String theID : filterIDMap.keySet())
        {
            if(filterIDMap.get(theID) > 2)
            {
                filterIDList.add(theID);
            }
        }

        for(String filterID : filterIDList)
        {
            for(int i = 0; i < ids.size(); i++)
            {   
                if(filterID.equals(ids.get(i)))
                {
                    ids.remove(i);
                    topics.remove(i);
                }
            }
        }

        occur = 1;

        for(String topic : topics)
            {
                if(filterTopics.containsKey(topic) == false)
                {
                    filterTopics.put(topic, 1);
                }
                else if(filterTopics.containsKey(topic) == true)
                {
                    occur = filterTopics.get(topic);
                    filterTopics.put(topic, occur + 1);
                    occur = 1;
                }
            }

        return filterTopics;
  }
}