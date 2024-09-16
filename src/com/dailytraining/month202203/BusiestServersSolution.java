package com.dailytraining.month202203;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 算法训练(2022-03-30) 找到处理最多请求的服务器
 * - https://leetcode-cn.com/problems/find-servers-that-handled-most-number-of-requests/ (1606题)
 * <p>
 * 你有 k 个服务器，编号为 0 到 k-1 ，它们可以同时处理多个请求组。
 * 每个服务器有无穷的计算能力但是 不能同时处理超过一个请求 。请求分配到服务器的规则如下：
 * 第 i （序号从 0 开始）个请求到达。
 * 如果所有服务器都已被占据，那么该请求被舍弃（完全不处理）。
 * 如果第 (i % k) 个服务器空闲，那么对应服务器会处理该请求。
 * 否则，将请求安排给下一个空闲的服务器（服务器构成一个环，必要的话可能从第 0 个服务器开始继续找下一个空闲的服务器）。
 * 比方说，如果第 i 个服务器在忙，那么会查看第 (i+1) 个服务器，第 (i+2) 个服务器等等。
 * 给你一个 严格递增 的正整数数组 arrival ，表示第 i 个任务的到达时间，和另一个数组 load ，
 * 其中 load[i] 表示第 i 个请求的工作量（也就是服务器完成它所需要的时间）。
 * 你的任务是找到 最繁忙的服务器 。最繁忙定义为一个服务器处理的请求数是所有服务器里最多的。
 * <p>
 * 请你返回包含所有 最繁忙服务器 序号的列表，你可以以任意顺序返回这个列表。
 * <p>
 * - 输入：k = 3, arrival = [1,2,3,4,5], load = [5,2,3,3,3]
 * 输出：[1]
 * 解释：
 * 所有服务器一开始都是空闲的。
 * 前 3 个请求分别由前 3 台服务器依次处理。
 * 请求 3 进来的时候，服务器 0 被占据，所以它呗安排到下一台空闲的服务器，也就是服务器 1 。
 * 请求 4 进来的时候，由于所有服务器都被占据，该请求被舍弃。
 * 服务器 0 和 2 分别都处理了一个请求，服务器 1 处理了两个请求。所以服务器 1 是最忙的服务器。
 * <p>
 * - 输入：k = 3, arrival = [1,2,3,4], load = [1,2,1,2]
 * 输出：[0]
 * 解释：
 * 前 3 个请求分别被前 3 个服务器处理。
 * 请求 3 进来，由于服务器 0 空闲，它被服务器 0 处理。
 * 服务器 0 处理了两个请求，服务器 1 和 2 分别处理了一个请求。所以服务器 0 是最忙的服务器。
 * <p>
 * - 输入：k = 3, arrival = [1,2,3], load = [10,12,11]
 * 输出：[0,1,2]
 * 解释：每个服务器分别处理了一个请求，所以它们都是最忙的服务器。
 * <p>
 * - 输入：k = 3, arrival = [1,2,3,4,8,9,10], load = [5,2,10,3,1,2,2]
 * 输出：[1]
 * <p>
 * - 输入：k = 1, arrival = [1], load = [1]
 * 输出：[0]
 * <p>
 * 1 <= k <= 10^5
 * 1 <= arrival.length, load.length <= 10^5
 * arrival.length == load.length
 * 1 <= arrival[i], load[i] <= 10^9
 * arrival 保证 严格递增 。
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-30 09:31:40
 */
public class BusiestServersSolution {
    public static void main(String[] args) {
        final BusiestServersSolution solution = new BusiestServersSolution();

        int k = 3;
        int[] arrival = new int[]{1, 2, 3, 4, 5};
        int[] load = new int[]{5, 2, 3, 3, 3};
        System.out.println("Input k : " + k + ", arrival : " + Arrays.toString(arrival) + ", load : " + Arrays.toString(load));
        System.out.println("Output busiest servers : " + solution.busiestServers(k, arrival, load));
        System.out.println("Output busiest servers (Priority Queue) : " + solution.busiestServersOpti(k, arrival, load));
        System.out.println();

        k = 3;
        arrival = new int[]{1, 2, 3, 4};
        load = new int[]{1, 2, 1, 2};
        System.out.println("Input k : " + k + ", arrival : " + Arrays.toString(arrival) + ", load : " + Arrays.toString(load));
        System.out.println("Output busiest servers : " + solution.busiestServers(k, arrival, load));
        System.out.println("Output busiest servers (Priority Queue) : " + solution.busiestServersOpti(k, arrival, load));
        System.out.println();

        k = 3;
        arrival = new int[]{1, 2, 3};
        load = new int[]{10, 12, 11};
        System.out.println("Input k : " + k + ", arrival : " + Arrays.toString(arrival) + ", load : " + Arrays.toString(load));
        System.out.println("Output busiest servers : " + solution.busiestServers(k, arrival, load));
        System.out.println("Output busiest servers (Priority Queue) : " + solution.busiestServersOpti(k, arrival, load));
        System.out.println();

        k = 3;
        arrival = new int[]{1, 2, 3, 4, 8, 9, 10};
        load = new int[]{5, 2, 10, 3, 1, 2, 2};
        System.out.println("Input k : " + k + ", arrival : " + Arrays.toString(arrival) + ", load : " + Arrays.toString(load));
        System.out.println("Output busiest servers : " + solution.busiestServers(k, arrival, load));
        System.out.println("Output busiest servers (Priority Queue) : " + solution.busiestServersOpti(k, arrival, load));
        System.out.println();

        k = 1;
        arrival = new int[]{1};
        load = new int[]{1};
        System.out.println("Input k : " + k + ", arrival : " + Arrays.toString(arrival) + ", load : " + Arrays.toString(load));
        System.out.println("Output busiest servers : " + solution.busiestServers(k, arrival, load));
        System.out.println("Output busiest servers (Priority Queue) : " + solution.busiestServersOpti(k, arrival, load));
        System.out.println();

        k = 3;
        arrival = new int[]{1};
        load = new int[]{1};
        System.out.println("Input k : " + k + ", arrival : " + Arrays.toString(arrival) + ", load : " + Arrays.toString(load));
        System.out.println("Output busiest servers : " + solution.busiestServers(k, arrival, load));
        System.out.println("Output busiest servers (Priority Queue) : " + solution.busiestServersOpti(k, arrival, load));
        System.out.println();

        k = 7;
        arrival = new int[]{1, 3, 4, 5, 6, 11, 12, 13, 15, 19, 20, 21, 23, 25, 31, 32};
        load = new int[]{9, 16, 14, 1, 5, 15, 6, 10, 1, 1, 7, 5, 11, 4, 4, 6};
        System.out.println("Input k : " + k + ", arrival : " + Arrays.toString(arrival) + ", load : " + Arrays.toString(load));
        System.out.println("Output busiest servers : " + solution.busiestServers(k, arrival, load));
        System.out.println("Output busiest servers (Priority Queue) : " + solution.busiestServersOpti(k, arrival, load));
        System.out.println();
    }

    /**
     * 我的初始题解：
     * (int[] load表示同一个请求，无论是哪个服务器来处理，处理完成的时间都是一致的，为load[i])
     * 直接模拟，使用ttl标识每个服务器当前还要多久才能空闲以执行新任务
     * - 时间复杂度 O(nk) (但是有个问题, 10^9 * 10^5, 超出时间限制了：10^9无法避免，故需要优化为O(n)或O(nlogk)，即优化寻找start与刷新ttl的部分)
     * - 空间复杂度 O(k)
     *
     * @param k
     * @param arrival
     * @param load
     * @return
     */
    List<Integer> busiestServers(final int k, final int[] arrival, final int[] load) {
        final int n = arrival.length;
        final int[] ttl = new int[k];
        final int[] count = new int[k];
        int last = 0;
        for (int i = 0; i < n; i++) {
            int time = arrival[i] - last;
            for (int x = 0; x < k; x++) {
                ttl[x] = ttl[x] <= time ? 0 : ttl[x] - time;
            }
            last = arrival[i];

            int start = findIdleServer(ttl, i % k, k);
            if (start < 0) {
                continue;
            }
            ttl[start] += load[i];
            count[start]++;
            System.out.println("ttl : " + Arrays.toString(ttl) + ", count : " + Arrays.toString(count));
        }
        int max = 0;
        for (int i = 0; i < k; i++) {
            if (count[i] > max) {
                max = count[i];
            }
        }
        final List<Integer> ans = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                ans.add(i);
            }
        }
        return ans;
    }

    private int findIdleServer(final int[] ttl, final int start, final int k) {
        for (int i = start; i < k; i++) {
            if (ttl[i] == 0) {
                return i;
            }
        }
        for (int i = 0; i < start; i++) {
            if (ttl[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 优化
     * 1、ttl计算优化为保存end，与arrival[i]进行比较
     * 2、维护busy集合与idle集合(使用优先队列进行维护)，可以使得busy与idle均最多执行n次放入与移除操作
     * - 2.1、优先队列，可以保证每次放入与移除操作的时间复杂度为O(logk)
     * - 2.2、busy的优先规则：按endTime小根堆 (value存储Server的index)
     * - 2.3、idle的优先规则：从busy中释放出的server的index, 按其小根堆 (>=(i%k)的直接取index, 否则取index+k)
     * <p>
     * - 放入到idle的值是：i + ((busy.poll()[1] - i) % k + k) % k (其实就是要保证>=i, 最大值i + k + k - 1, 在int范围内)
     * - 或者表达为：
     * int diff = (busy.poll()[1] - i) % k;
     * idle.offer(diff >= 0 ? i + diff : i + diff + k);
     *
     * @param k
     * @param arrival
     * @param load
     * @return
     */
    List<Integer> busiestServersOpti(final int k, final int[] arrival, final int[] load) {
        final int n = arrival.length;
        if (n < k) {
            return IntStream.range(0, n).boxed().collect(Collectors.toList());
        }

        final int[] count = new int[k];
        final PriorityQueue<Integer> idle = new PriorityQueue<>();
        final PriorityQueue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < k; i++) {
            busy.offer(new int[]{arrival[i] + load[i], i});
            count[i]++;
        }
        for (int i = k; i < n; i++) {
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                int diff = (busy.poll()[1] - i) % k;
                idle.offer(diff >= 0 ? i + diff : i + diff + k);
            }
            if (idle.isEmpty()) {
                continue;
            }
            int busyIndex = idle.poll() % k;
            busy.offer(new int[]{arrival[i] + load[i], busyIndex});
            count[busyIndex]++;
        }

        int max = 0;
        for (int i = 0; i < k; i++) {
            if (count[i] > max) {
                max = count[i];
            }
        }
        final List<Integer> ans = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                ans.add(i);
            }
        }
        return ans;
    }
}
