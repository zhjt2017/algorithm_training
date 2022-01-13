package com.homework.week2;

import java.util.*;

/**
 * 算法实现：子域名访问计数
 * leetcode-811 链接：https://leetcode-cn.com/problems/subdomain-visit-count/
 * <p>
 * 网站域名 "discuss.leetcode.com" 由多个子域名组成。顶级域名为 "com" ，二级域名为 "leetcode.com" ，最低一级为 "discuss.leetcode.com" 。
 * 当访问域名 "discuss.leetcode.com" 时，同时也会隐式访问其父域名 "leetcode.com" 以及 "com" 。
 * <p>
 * 计数配对域名 是遵循 "rep d1.d2.d3" 或 "rep d1.d2" 格式的一个域名表示，其中 rep 表示访问域名的次数，d1.d2.d3 为域名本身。
 * <p>
 * 例如，"9001 discuss.leetcode.com" 就是一个 计数配对域名 ，表示 discuss.leetcode.com 被访问了 9001 次。
 * 给你一个 计数配对域名 组成的数组 cpdomains ，解析得到输入中每个子域名对应的 计数配对域名 ，并以数组形式返回。可以按 任意顺序 返回答案。
 * <p>
 * 输入：cpdomains = ["9001 discuss.leetcode.com"]
 * 输出：["9001 leetcode.com","9001 discuss.leetcode.com","9001 com"]
 * 解释：例子中仅包含一个网站域名："discuss.leetcode.com"。
 * 按照前文描述，子域名 "leetcode.com" 和 "com" 都会被访问，所以它们都被访问了 9001 次。
 * <p>
 * 输入：cpdomains = ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
 * 输出：["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
 * 解释：按照前文描述，会访问 "google.mail.com" 900 次，"yahoo.com" 50 次，"intel.mail.com" 1 次，"wiki.org" 5 次。
 * 而对于父域名，会访问 "mail.com" 900 + 1 = 901 次，"com" 900 + 50 + 1 = 951 次，和 "org" 5 次。
 * <p>
 * 1 <= cpdomain.length <= 100
 * 1 <= cpdomain[i].length <= 100
 * cpdomain[i] 会遵循 "repi d1i.d2i.d3i" 或 "repi d1i.d2i" 格式
 * repi 是范围 [1, 10^4] 内的一个整数
 * d1i、d2i 和 d3i 由小写英文字母组成
 * <p>
 * 设计思想：split出来，HashMap进行key-value进行统计，无序输出即可，每个输出元素是Join后的结果
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @since 2022-01-09 08:37:39
 */
public class SubDomainVisitsSolution {

    private static final char BLANK = ' ';
    private static final char DOT = '.';

    public static void main(String[] args) {
        String[] cpdomains = new String[]{"9001 discuss.leetcode.com"};
        System.out.println("input : " + Arrays.toString(cpdomains));
        System.out.println("output : " + subDomainVisits(cpdomains));

        cpdomains = new String[]{"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        System.out.println("input : " + Arrays.toString(cpdomains));
        System.out.println("output : " + subDomainVisits(cpdomains));
    }

    private static List<String> subDomainVisits(final String[] cpdomains) {
        final Map<String, Integer> countMap = new HashMap<>(cpdomains.length);

        for (final String source : cpdomains) {
            singleDoStatistics(source, countMap);
        }

        final List<String> result = new ArrayList<>(countMap.size());
        final StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            sb.append(entry.getValue());
            sb.append(BLANK);
            sb.append(entry.getKey());
            result.add(sb.toString());
            sb.delete(0, sb.length());
        }
        return result;
    }

    private static void singleDoStatistics(final String cpDomain, final Map<String, Integer> countMap) {
        final int length = cpDomain.length();
        int domainStartIndex = 0;
        // 我们假设给定的数据中是没有不符合格式的，故以下都直接处理，不做判断
        while (cpDomain.charAt(domainStartIndex) != BLANK) {
            domainStartIndex++;
        }
        final int count = Integer.parseInt(cpDomain.substring(0, domainStartIndex));
        domainStartIndex++;

        // 标识是否是最后一个点号，如果不是，则其后乃是上一级域名
        String key;
        for (int i = length - 1; i > domainStartIndex; i--) {
            if (cpDomain.charAt(i) != DOT) {
                continue;
            }
            key = cpDomain.substring(i + 1);
            countMap.put(key, countMap.getOrDefault(key, 0) + count);
        }
        // 这里是最低一级域名
        key = cpDomain.substring(domainStartIndex);
        countMap.put(key, countMap.getOrDefault(key, 0) + count);
    }
}
