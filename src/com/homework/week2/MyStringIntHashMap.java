package com.homework.week2;

/**
 * (使用hash表/开散列)实现一个无序映射map (不妨固定为：String类型的key, Integer类型的value)
 * - 支持constructor, containsKey、find、put、remove方法
 * <p>
 * - 数据结构：数组后面挂链表 (实际上，解决碰撞冲突可以有拉链法和线性探测法)
 * - 注意字符串的hash函数
 * - int类型的hash函数，如果针对所有int范围的数，不妨对99991取模，其为10万以内的最大质数
 * <p>
 * - 这里就不完全实现了，参考jdk的HashMap
 * - 1.7及以前，采用 位桶+链表 实现
 * - 1.8采用 位桶+链表+红黑树 实现
 * - 当数组的容量达到初始容量的0.75时，再散列将数组扩大2倍，扩容之后，将原来链表数组的每一个链表分成奇偶2个子链表分别挂在新链表数组的散列位置
 * - 默认：加载因子=0.75，最大容量=2^30
 * <p>
 * - get(key)方法时获取key的hash值，计算hash&(n-1)得到在链表数组中的位置first=tab[hash&(n-1)], (n=tab.length)
 * 先判断first的key是否与参数key相等，不等就遍历后面的链表找到相同的key值返回对应的Value值即可
 * <p>
 * - 下面简单说下添加键值对put(key,value)的过程：
 * 1，判断键值对数组tab[]是否为空或为null，否则以默认大小resize()；
 * 2，根据键值key计算hash值得到插入的数组索引i，如果tab[i]==null，直接新建节点添加，否则转入3
 * 3，判断当前数组中处理hash冲突的方式为链表还是红黑树(check第一个节点类型即可),分别处理
 * <p>
 * - 构造hash表时，如果不指明初始大小，默认大小为16（即Node数组大小16），如果Node[]数组中的元素达到（填充比*Node.length）重新调整HashMap大小 变为原来2倍大小,扩容很耗时
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-08 08:56:07
 */
public class MyStringIntHashMap {
    public static void main(String[] args) {
        MyStringIntHashMap map = new MyStringIntHashMap(20);
    }

    private int capacity;
    private Node[] table;

    /**
     * 实现constructor
     *
     * @param capacity
     */
    public MyStringIntHashMap(final int capacity) {
        this.capacity = capacity;
    }

    /**
     * 实现containsKey方法
     *
     * @param key
     * @return
     */
    public boolean containsKey(final String key) {
        return false;
    }

    /**
     * 实现find方法（找到key对应的value）
     *
     * @param key
     * @return
     */
    public Integer find(final String key) {
        return null;
    }

    /**
     * 实现put方法（放入新的键值对，如果之前已经有了该键，返回之前对应的值）
     *
     * @param key
     * @param value
     * @return 返回之前的value，如果之前没有key，返回null
     */
    public Integer put(final String key, final Integer value) {
        return null;
    }

    /**
     * 实现remove方法（按给定的键删除键值对）
     *
     * @param key
     * @return 返回之前的value，如果之前没有key，返回null
     */
    public Integer remove(final String key) {
        return null;
    }

    private class Node {
        private String key;
        private Integer value;
        private int hash;
        private Node next;

        Node(final String key, final Integer value) {
            this.key = key;
            this.value = value;
        }
    }
}
