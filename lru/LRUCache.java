import java.util.HashMap;

public class LRUCache {
	public static void main(String[] args) {
		System.out.println("LRUCache Implementation");
	}

	private int cacheSizeMax;
	private HashMap<Integer, LinkedListNode> map = new HashMap<Integer, LinkedListNode>();
	private LinkedListNode head = null;
	private LinkedListNode tail = null;

	public LRUCache(int maxSize) {
		cacheSizeMax = maxSize;
	}
	
	/**  
	 * Get value for the given key and mark as most recently used
	 * If a value is found in the map, it must exist in the list
	 * remove value from list and insert at head to mark as mru 
	 * 
	 * @param key the key of the hashmap
	 * @return	  the corresponding value to the key
	 */
	public String getValue(int key) {
		LinkedListNode item = map.get(key);
		if (item == null) return null;
		if (item != head) {
			removeFromList(item);
			insertAtFrontOfList(item);
		}

		return item.value;
	}
	
	/**
	 * Remove the given node from the list
	 * From the node, if the prev pointer is not null, set the previous node's next pointer to next node
	 * From the node, if the next pointer is not null, set the next node's prev pointer to prev node
	 * If node is the tail, tail becomes the prev node
	 * if node if the head, head becomes the next node
	 * 
	 * @param node a node in the doubly linked list
	 */
	private void removeFromList(LinkedListNode node) {
		if (node == null) return;
		
		if (node.prev != null) node.prev.next = node.next;
		if (node.next != null) node.next.prev = node.prev;
		if (node == tail) tail = node.prev;
		if (node == head) head = node.next;
	}
	
	/**
	 * Insert the given node to the front of the list
	 * If the head of list is null, node is the head and tail of list
	 * Else set head prev pointer to node, then node next pointer to head then
	 * set the head to be the given node 
	 *
	 * @param node a node in the doubly linked list
	 */
	private void insertAtFrontOfList(LinkedListNode node) {
		// Empty List Case
		if (head == null) {
			head = node;
			tail = node;
		} else {
			head.prev = node;
			node.next = head;
			head = node;
		}
	}
	
	/**
	 * Remove the given key and its value pair from cache, deleting from both the map and the list
	 *
	 * @param key the key of the hashmap
	 * @return    must always return true  
	 */
	public boolean removeKey(int key) {
		LinkedListNode node = map.get(key);
		removeFromList(node);
		map.remove(key);
		return true;
	}

	/**
	 * Put the given key/value pair in cache
	 * Remove old value for key if needed
	 * Insert pair into list and map
	 *
	 * @param key   the key of the hashmap
	 * @param value the value for the key of the hashmap 
	 */
	public void setKeyValue(int key, String value) {
		// Remove if key exists to rewrite possible old value of key
		removeKey(key);
		
		// If cache if full, remove the least recently used (LRU) item from cache
		if (map.size() >= cacheSizeMax && tail != null) {
			removeKey(tail.key);
		}

		// Safely insert new node with given key/value
		LinkedListNode node = new LinkedListNode(key, value);
		insertAtFrontOfList(node);
		map.put(key, node);
	}

	private static class LinkedListNode {
		private LinkedListNode next, prev;
		public int key;
		public String value;
		public LinkedListNode(int key, String value) {
			key = key;
			value = value;
		}
	}
}
