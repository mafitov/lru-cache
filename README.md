## LRU Cache

This project implements a simple **Least Recently Used (LRU) Cache** mechanism in Java using a `HashMap` and a `PriorityQueue` to manage cache entries based on access time.  
The cache automatically removes the least recently used item when it reaches its maximum capacity.

### Overview

An **LRU Cache** is a caching strategy that discards the least recently accessed items first when the cache reaches its limit.  
This implementation provides a basic demonstration of LRU logic by storing token checksums.

### Implementation Details

**Key Components:**
- **`CACHE`**: A `HashMap<String, Integer>` that stores cached items (token → checksum).
- **`QUEUE`**: A `PriorityQueue<KEY>` ordered by timestamp to track access order.
- **`MAX_SIZE`**: Defines the maximum number of cache entries (default: `2`).
- **`KEY` class**: Represents a cache entry with `key` and `timestamp` fields.

When an item is accessed:
1. If the key exists:
    - It is updated in the queue to refresh its timestamp.
2. If the key does not exist:
    - A checksum is generated.
    - If the cache is full, the least recently used item is evicted.
    - The new item is added to the cache and queue.

### How It Works

`getChecksum(String key)`

Retrieves or computes a checksum for the given key:
- Updates the access timestamp if the key is already cached.
- Removes the least recently used entry if the cache is full.

`generateChecksum(String key)`

Computes a simple checksum by summing ASCII values of characters and taking a modulo 10 result.

`getByKey(String key)`

Finds and returns the `KEY` object corresponding to a given token in the priority queue.

### Technologies Used

- Java 17+
- `HashMap` for constant-time lookups.
- `PriorityQueue` for ordering entries by access time.
