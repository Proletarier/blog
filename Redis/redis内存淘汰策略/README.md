# redis内存淘汰策略


## 定时删除

Redis不可能时时刻刻遍历所有被设置了生存时间的key，来检测数据是否已经到达过期时间，然后对它进行删除。

立即删除能保证内存中数据的最大新鲜度，因为它保证过期键值会在过期后马上被删除，其所占用的内存也会随之释放。但是立即删除对cpu是最不友好的。因为删除操作会占用cpu的时间，如果刚好碰上了cpu很忙的时候，比如正在做交集或排序等计算的时候，就会给cpu造成额外的压力，让CPU心累，时时需要删除，忙死。

这会产生大量的性能消耗，同时也会影响数据的读取操作。

## 惰性删除

数据到达过期时间，不做处理。等下次访问该数据时，

如果未过期，返回数据；

发现已过期，删除，返回不存在。

惰性删除策略的缺点是，它对内存是最不友好的。

如果一个键已经过期，而这个键又仍然保留在数据库中，那么只要这个过期键不被删除，它所占用的内存就不会释放。

在使用惰性删除策略时，如果数据库中有非常多的过期键，而这些过期键又恰好没有被访问到的话，那么它们也许永远也不会被删除（除非用户手动执行FLUSHDB），我们甚至可以将这种情况看作是一种内存泄漏 – 无用的垃圾数据占用了大量的内存，而服务器却不会自己去释放它们，这对于运行状态非常依赖于内存的Redis服务器来说，肯定不是一个好消息。

## 定期删除
定期删除策略是前两种策略的折中：

定期删除策略每隔一段时间执行一次删除过期键操作，并通过限制删除操作执行的时长和频率来减少删除操作对CPU时间的影响。

周期性轮询Redis库中的时效性数据，来用随机抽取的策略，利用过期数据占比的方式控制删除频度

特点1：CPU性能占用设置有峰值，检测频度可自定义设置

特点2：内存压力不是很大，长期占用内存的冷数据会被持续清理

总结：周期性抽查存储空间（随机抽查，重点抽查）

举例：

redis默认每个100ms检查，是否有过期的key，有过期key则删除。注意：redis不是每隔100ms将所有的key检查一次而是随机抽取进行检查(如果每隔100ms，全部key进行检查，redis直接进去ICU)。因此，如果只采用定期删除策略，会导致很多key到时间没有删除。

定期删除策略的难点是确定删除操作执行的时长和频率:如果删除操作执行得太频繁，或者执行的时间太长，定期删除策略就会退化成定时删除策略，以至于将CPU时间过多地消耗在删除过期键上面。如果删除操作执行得太少，或者执行的时间太短，定期删除策略又会和惰性删除束略一样，出现浪费内存的情况。因此，如果采用定期删除策略的话，服务器必须根据情况，合理地设置删除操作的执行时长和执行频率。

## 总结
1. 定时删除 - 总结：对CPU不友好，用处理器性能换取存储空间（拿时间换空间）
2. 惰性删除 - 总结：对memory不友好，用存储空间换取处理器性能（拿空间换时间）
3. 上面两种方案都走极端 - 定期删除 - 定期抽样key，判断是否过期（存在漏网之鱼）

## redis内存淘汰策略
* noeviction：不会驱逐任何key
* volatile-lfu：对所有设置了过期时间的key使用LFU算法进行删除
* volatile-Iru：对所有设置了过期时间的key使用LRU算法进行删除
* volatile-random：对所有设置了过期时间的key随机删除
* volatile-ttl：删除马上要过期的key
* allkeys-lfu：对所有key使用LFU算法进行删除
* allkeys-Iru：对所有key使用LRU算法进行删除
* allkeys-random：对所有key随机删除

## lru算法简介

````java
class LRUCache2{
	class Node<K, V>{//双向链表节点
		K key;
		V value;
		Node<K, V> prev;
		Node<K, V> next;
		
		public Node() {
			this.prev = this.next = null;
		}
		public Node(K key, V value) {
			super();
			this.key = key;
			this.value = value;
		}
	}
	
    //新的插入头部，旧的从尾部移除
	class DoublyLinkedList<K, V>{
		Node<K, V> head;
		Node<K, V> tail;
		
		public DoublyLinkedList() {
            //头尾哨兵节点
			this.head = new Node<K, V>();
			this.tail = new Node<K, V>();
			this.head.next = this.tail;
			this.tail.prev = this.head;
		}
		
		public void addHead(Node<K, V> node) {
			node.next = this.head.next;
			node.prev = this.head;
			this.head.next.prev = node;
			this.head.next = node;
		}
		
		public void removeNode(Node<K, V> node) {
			node.prev.next = node.next;
			node.next.prev = node.prev;
			node.prev = null;
			node.next = null;

		}
		
		public Node<K, V> getLast() {
			if(this.tail.prev == this.head)
				return null;
			return this.tail.prev;
		}

	}
	
	private int cacheSize;
	private Map<Integer, Node<Integer, Integer>> map;
	private DoublyLinkedList<Integer, Integer> doublyLinkedList;
	
	
	public LRUCache2(int cacheSize) {
		this.cacheSize = cacheSize;
		map = new HashMap<>();
		doublyLinkedList = new DoublyLinkedList<>();
	}

	public int get(int key) {
		if(!map.containsKey(key)) {
			return -1;
		}
		
		Node<Integer, Integer> node = map.get(key);
        
        //更新节点位置，将节点移置链表头
		doublyLinkedList.removeNode(node);
		doublyLinkedList.addHead(node);
		
		return node.value;
	}
	
	public void put(int key, int value) {
		
		if(map.containsKey(key)) {
			
			Node<Integer, Integer> node = map.get(key);
			node.value = value;
			map.put(key, node);
			
            
			doublyLinkedList.removeNode(node);
			doublyLinkedList.addHead(node);
		}else {
			
			if(map.size() == cacheSize) {//已达到最大容量了，把旧的移除，让新的进来
				Node<Integer, Integer> lastNode = doublyLinkedList.getLast();
				map.remove(lastNode.key);//node.key主要用处，反向连接map
				doublyLinkedList.removeNode(lastNode);
			}
			
			Node<Integer, Integer> newNode = new Node<>(key, value);
			map.put(key, newNode);
			doublyLinkedList.addHead(newNode);
		}
	}	
}

````