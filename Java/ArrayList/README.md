# ArrayList

Arraylist 线程不安全的，底层使用的是Object数组，他在插入和删除的时候，都会通过System.arraycopy把集合中的元素向后或者向前移一位，

移动的位数是新增是size-index，删除是size-index-1，操作的数据越多性能越慢

ArrayList JDK1.7以前是在实例化ArrayList 的时候创建数组，1.8后改成了在add的时候创建数组，默认长度为10

ArrayList 的扩容

每次在新增的时候也会检查数组的长度是否足够，不够的话就会进行扩容，默认是当前长度的1.5倍，然后会他调了一个Arrays.copyof将原有数组内容复制到新数组中去，比较浪费内存空间

# LinkedList 

LinkedList 也不是同步的，也就是不保证线程安全； 底层使用的是双向链表数据结构；LinkedList内部有两个Node，firstNode，lastNode，分别保存的是第一个节点和最后一个节点，每个节点又保存next和prev，就是他的上一个节点和下一个几点，所以说他在末尾添加和在首部添加的时候直接拿到第一个或者最后一个节点设置他的next和prev就行了，就不会像ArryList那样浪费空间， 但是呢他缺点也很明显，查找的比较慢他是根据你的index，然后用size去-index，然后判断从头还是从尾开始扫描，扫描的时候要一个一个的next或者prev，不像ArrayList那样直接给坐标就可以了

