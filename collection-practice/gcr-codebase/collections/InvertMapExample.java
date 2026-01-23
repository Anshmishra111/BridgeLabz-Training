package collections;

import java.util.*;

public class InvertMapExample {

    public static void main(String[] args) {

        Map<String, Integer> originalMap = new HashMap<>();
        originalMap.put("A", 1);
        originalMap.put("B", 2);
        originalMap.put("C", 1);

        Map<Integer, List<String>> invertedMap = invertMap(originalMap);

        System.out.println(invertedMap);
    }

    public static <K, V> Map<V, List<K>> invertMap(Map<K, V> map) {

        Map<V, List<K>> result = new HashMap<>();

        for (Map.Entry<K, V> entry : map.entrySet()) {

            K key = entry.getKey();
            V value = entry.getValue();

            result.computeIfAbsent(value, v -> new ArrayList<>())
                  .add(key);
        }

        return result;
    }
}
