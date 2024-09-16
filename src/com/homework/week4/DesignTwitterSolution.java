package com.homework.week4;

import java.util.*;

/**
 * 算法实现：设计推特
 * - https://leetcode-cn.com/problems/design-twitter/ (355题)
 * <p>
 * - 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近 10 条推文。
 * <p>
 * 实现 Twitter 类：
 * <p>
 * Twitter()
 * - 初始化简易版推特对象
 * void postTweet(int userId, int tweetId)
 * - 根据给定的 tweetId 和 userId 创建一条新推文。每次调用此函数都会使用一个不同的 tweetId 。
 * List<Integer> getNewsFeed(int userId)
 * - 检索当前用户新闻推送中最近  10 条推文的 ID 。新闻推送中的每一项都必须是由用户关注的人或者是用户自己发布的推文。推文必须 按照时间顺序由最近到最远排序 。
 * void follow(int followerId, int followeeId)
 * - ID 为 followerId 的用户开始关注 ID 为 followeeId 的用户。
 * void unfollow(int followerId, int followeeId) ID
 * - 为 followerId 的用户不再关注 ID 为 followeeId 的用户。
 *  
 * - 输入
 * ["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
 * [[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
 * 输出
 * [null, null, [5], null, null, [6, 5], null, [5]]
 * <p>
 * 解释
 * Twitter twitter = new Twitter();
 * twitter.postTweet(1, 5); // 用户 1 发送了一条新推文 (用户 id = 1, 推文 id = 5)
 * twitter.getNewsFeed(1);  // 用户 1 的获取推文应当返回一个列表，其中包含一个 id 为 5 的推文
 * twitter.follow(1, 2);    // 用户 1 关注了用户 2
 * twitter.postTweet(2, 6); // 用户 2 发送了一个新推文 (推文 id = 6)
 * twitter.getNewsFeed(1);  // 用户 1 的获取推文应当返回一个列表，其中包含两个推文，id 分别为 -> [6, 5] 。推文 id 6 应当在推文 id 5 之前，因为它是在 5 之后发送的
 * twitter.unfollow(1, 2);  // 用户 1 取消关注了用户 2
 * twitter.getNewsFeed(1);  // 用户 1 获取推文应当返回一个列表，其中包含一个 id 为 5 的推文。因为用户 1 已经不再关注用户 2
 * <p>
 * 1 <= userId, followerId, followeeId <= 500
 * 0 <= tweetId <= 10^4
 * 所有推特的 ID 都互不相同
 * postTweet、getNewsFeed、follow 和 unfollow 方法最多调用 3 * 10^4 次
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-23 01:52:28
 */
public class DesignTwitterSolution {
    public static void main(String[] args) {
        final Twitter twitter = new Twitter();
        int userId = 1;
        int tweetId = 5;
        twitter.postTweet(userId, tweetId);
        System.out.println("用户1发表了一条新推文, 推文id=5");
        System.out.println("postTweet success, userId = " + userId + ", tweetId = " + tweetId);

        System.out.println("用户1获取推文, 返回一个列表, 其中包含id为5的推文");
        System.out.println("getNewsFeed success, result = " + twitter.getNewsFeed(userId) + ", userId = " + userId);

        int followeeId = 2;
        twitter.follow(userId, followeeId);
        System.out.println("用户1关注了用户2");
        System.out.println("follow success, followerId = " + userId + ", followeeId = " + followeeId);

        tweetId = 6;
        twitter.postTweet(followeeId, tweetId);
        System.out.println("用户2发表了一条新推文, 推文id=6");
        System.out.println("postTweet success, userId = followeeId = " + followeeId + ", tweetId = " + tweetId);

        System.out.println("用户1再次获取推文, 返回一个列表, 其中包含id=5,6的推文, 推文6在推文5之前, 因为其晚发表(发表时间更新)");
        System.out.println("getNewsFeed success, result = " + twitter.getNewsFeed(userId) + ", userId = " + userId);

        twitter.unfollow(userId, followeeId);
        System.out.println("用户1取消关注用户2");
        System.out.println("unfollow success, followerId = " + userId + ", followeeId = " + followeeId);

        System.out.println("用户1再次获取推文, 返回一个列表, 其中包含id=5的推文 (id=6的推文由于其作者不再被关注, 故不会获取到)");
        System.out.println("getNewsFeed success, result = " + twitter.getNewsFeed(userId) + ", userId = " + userId);
    }

    /**
     * 设计类问题: 一个简易推特 (软件应用对象)
     */
    public static class Twitter {

        /**
         * 推文取最新10条
         */
        private static final int LATEST_COUNT = 10;

        /**
         * 推特(账号)集, 自带标识没有推特账号发表的最新推文 (按时间倒序, hash中只标识时间最新的那篇推文, 即推文链表的root节点)
         */
        private Map<Integer, Tweet> twitterHash;

        /**
         * 维护推特账号与其关注用户的关系 (一个推特账号的关注用户集使用Set存储followeeId, 即可快速增删防重复, 另外关注用户没有顺序要求)
         */
        private Map<Integer, Set<Integer>> followingHash;

        /**
         * 模拟全局时间戳字段, 维护方式: 用户每发布一条推文 +1
         */
        private long timestamp;

        /**
         * 注册一个推特对象 (比如注册了一个推特账号, 就可以以此账号来操作推特功能了)
         */
        public Twitter() {
            this.timestamp = 0;
            this.twitterHash = new HashMap<>();
            this.followingHash = new HashMap<>();
        }

        /**
         * 用户发布一条推文
         * - 时间戳标识+1
         * - 用户发表推文hash维护
         * 时间复杂度：O(1)
         *
         * @param userId
         * @param tweetId
         */
        public void postTweet(final int userId, final int tweetId) {
            this.timestamp++;
            if (this.twitterHash.containsKey(userId)) {
                this.twitterHash.put(userId, this.twitterHash.get(userId).addFirst(tweetId, this.timestamp));
            } else {
                this.twitterHash.put(userId, new Tweet(tweetId, this.timestamp));
            }
        }

        /**
         * 获取(可读)最新推文列表 (属于多路归并问题)
         * 时间复杂度：n * log(k) (n表示number of followee)
         * <p>
         * - 总结:
         * - 如果需要维护数据的时间有序性, 链表在特殊场景下可以胜任. (时间天然有序)
         * - 如果需要动态维护数据有序性, 优先队列 (堆) 可以胜任.
         * - 设计类的算法与数据结构问题, 有助于了解一些数据结构的大致思想与细节 (拓宽视野) , 建模, 抽象思维能力, 总结归纳能力
         *
         * @param userId
         * @return
         */
        public List<Integer> getNewsFeed(final int userId) {
            // jdk 优先队列按照从小到大的顺序, 故comparator取反, 使得从优先队列中的取出的推文是按照时间倒序的
            final PriorityQueue<Tweet> pq = new PriorityQueue<>((t1, t2) -> Long.compare(t2.timestamp, t1.timestamp));
            // 先算上自己的推文(最新发布的一篇), 其他篇推文待后续在组装10条记录时实时给出
            if (this.twitterHash.containsKey(userId)) {
                pq.offer(this.twitterHash.get(userId));
            }
            // 再算上所有关注的followeeId发表的文章
            final Set<Integer> followeeIds = this.followingHash.get(userId);
            if (followeeIds != null && !followeeIds.isEmpty()) {
                for (final int followee : followeeIds) {
                    // 这里现在优先队列中放入每个关注对象的根节点(最新发布的一篇), 其他篇推文待后续在组装10条记录时实时给出
                    if (this.twitterHash.containsKey(followee)) {
                        pq.offer(this.twitterHash.get(followee));
                    }
                }
            }

            // 最终取最新10条
            int count = 1;
            final List<Integer> result = new ArrayList<>(LATEST_COUNT);
            Tweet t;
            while (!pq.isEmpty() && count <= LATEST_COUNT) {
                t = pq.poll();
                result.add(t.tweetId);
                count++;
                // 当一个最新发布时间满足前10的推文, 其作者发布的前一篇推文也有可能在发布时间满足前10, 此时逐个按需给出
                if (t.next != null) {
                    pq.offer(t.next);
                }
            }
            return result;
        }

        /**
         * 用户followerId关注用户followeeId
         * - 维护用户followerId的关注关系集合 (增加一个关注)
         * - 被关注人不能是自己
         * 时间复杂度：O(1)
         *
         * @param followerId
         * @param followeeId
         */
        public void follow(final int followerId, final int followeeId) {
            if (followeeId == followerId) {
                return;
            }

            if (!this.followingHash.containsKey(followerId)) {
                this.followingHash.put(followerId, new HashSet<>());
            }
            this.followingHash.get(followerId).add(followeeId);
        }

        /**
         * 用户followerId取消关注用户followeeId
         * - 维护用户followerId的关注关系集合 (去除一个关注)
         * 时间复杂度：O(1)
         *
         * @param followerId
         * @param followeeId
         */
        public void unfollow(int followerId, int followeeId) {
            if (this.followingHash.containsKey(followerId)) {
                this.followingHash.get(followerId).remove(followeeId);
            }
        }

        /**
         * 推文对象结构定义
         */
        static class Tweet {
            private int tweetId;
            private long timestamp;
            private Tweet next;

            Tweet(final int tweetId, final long timestamp) {
                this.tweetId = tweetId;
                this.timestamp = timestamp;
            }

            Tweet addFirst(final int tweetId, final long timestamp) {
                final Tweet root = new Tweet(tweetId, timestamp);
                root.next = this;
                return root;
            }
        }
    }
}
