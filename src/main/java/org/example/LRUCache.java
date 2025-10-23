package org.example;

import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LRUCache {

    public static Map<String, Integer> CACHE = new HashMap<>();
    public static PriorityQueue<KEY> QUEUE = new PriorityQueue<>(Comparator.comparing(KEY::getTimestamp));

    public static String TOKEN_1 = "1111";
    public static String TOKEN_2 = "2222";
    public static String TOKEN_3 = "3333";
    public static int MAX_SIZE = 2;

    public static void main(String[] args) {
        printChecksum(TOKEN_1);
        printChecksum(TOKEN_2);
        printChecksum(TOKEN_3);
        printChecksum(TOKEN_1);
    }

    private static void printChecksum(String token) {
        System.out.println("Checksum for token '" + token + "' :" + getChecksum(token));
    }

    public static int getChecksum(String key) {
        if (CACHE.containsKey(key)) {
            QUEUE.remove(getByKey(key));
            QUEUE.add(new KEY(key, Instant.now()));
            return CACHE.get(key);
        }

        int checksum = generateChecksum(key);

        if (CACHE.size() == MAX_SIZE) {
            KEY min = QUEUE.poll();
            assert min != null;
            CACHE.remove(min.getKey());
        }

        QUEUE.add(new KEY(key, Instant.now()));
        CACHE.put(key, checksum);

        return checksum;
    }

    public static int generateChecksum(String key) {
        int sum = 0;
        for (char c : key.toCharArray()) {
            sum += c;
        }
        return sum % 10;
    }

    public static KEY getByKey(String key) {
        return QUEUE.stream().filter(k -> k.getKey().equals(key)).findFirst().orElse(null);
    }

    static class KEY {
        public String key;
        public Instant timestamp;

        public KEY(String key, Instant timestamp) {
            this.key = key;
            this.timestamp = timestamp;
        }

        public String getKey() {
            return key;
        }

        public Instant getTimestamp() {
            return timestamp;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public void setTimestamp(Instant timestamp) {
            this.timestamp = timestamp;
        }

    }

}
