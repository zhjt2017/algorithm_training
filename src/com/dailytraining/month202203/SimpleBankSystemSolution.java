package com.dailytraining.month202203;

import java.util.Arrays;

/**
 * 算法训练(2022-03-18) 简易银行系统
 * - https://leetcode-cn.com/problems/simple-bank-system/ (2043题)
 * <p>
 * 你的任务是为一个很受欢迎的银行设计一款程序，以自动化执行所有传入的交易（转账，存款和取款）。
 * 银行共有 n 个账户，编号从 1 到 n 。每个账号的初始余额存储在一个下标从 0 开始的整数数组 balance 中，其中第 (i + 1) 个账户的初始余额是 balance[i] 。
 * 请你执行所有 有效的 交易。如果满足下面全部条件，则交易 有效 ：
 * 指定的账户数量在 1 和 n 之间，且
 * 取款或者转账需要的钱的总数 小于或者等于 账户余额。
 * <p>
 * 实现 Bank 类：
 * <p>
 * Bank(long[] balance) 使用下标从 0 开始的整数数组 balance 初始化该对象。
 * boolean transfer(int account1, int account2, long money) 从编号为 account1 的账户向编号为 account2 的账户转帐 money 美元。如果交易成功，返回 true ，否则，返回 false 。
 * boolean deposit(int account, long money) 向编号为 account 的账户存款 money 美元。如果交易成功，返回 true ；否则，返回 false 。
 * boolean withdraw(int account, long money) 从编号为 account 的账户取款 money 美元。如果交易成功，返回 true ；否则，返回 false 。
 * <p>
 * - 输入：
 * ["Bank", "withdraw", "transfer", "deposit", "transfer", "withdraw"]
 * [[[10, 100, 20, 50, 30]], [3, 10], [5, 1, 20], [5, 20], [3, 4, 15], [10, 50]]
 * 输出：
 * [null, true, true, true, false, false]
 * 解释：
 * Bank bank = new Bank([10, 100, 20, 50, 30]);
 * bank.withdraw(3, 10);    // 返回 true ，账户 3 的余额是 $20 ，所以可以取款 $10 。
 * // 账户 3 余额为 $20 - $10 = $10 。
 * bank.transfer(5, 1, 20); // 返回 true ，账户 5 的余额是 $30 ，所以可以转账 $20 。
 * // 账户 5 的余额为 $30 - $20 = $10 ，账户 1 的余额为 $10 + $20 = $30 。
 * bank.deposit(5, 20);     // 返回 true ，可以向账户 5 存款 $20 。
 * // 账户 5 的余额为 $10 + $20 = $30 。
 * bank.transfer(3, 4, 15); // 返回 false ，账户 3 的当前余额是 $10 。
 * // 所以无法转账 $15 。
 * bank.withdraw(10, 50);   // 返回 false ，交易无效，因为账户 10 并不存在。
 * <p>
 * n == balance.length
 * 1 <= n, account, account1, account2 <= 10^5
 * 0 <= balance[i], money <= 1012
 * transfer, deposit, withdraw 三个函数，每个 最多调用 10^4 次
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-18 08:41:18
 */
public class SimpleBankSystemSolution {
    public static void main(String[] args) {
        long[] balance = new long[]{10, 100, 20, 50, 30};
        Bank bank = new Bank(balance);
        System.out.println("Initialization bank with balance : " + Arrays.toString(balance));
        System.out.println(String.format("(account = %d) withdraw (money = %d), result : %b", 3, 10, bank.withdraw(3, 10)));
        System.out.println(String.format("(account1 = %d) transfer (to account2 = %d) (money = %d), result : %b", 5, 1, 20, bank.transfer(5, 1, 20)));
        System.out.println(String.format("(account = %d) deposit (money = %d), result : %b", 5, 20, bank.deposit(5, 20)));
        System.out.println(String.format("(account1 = %d) transfer (to account2 = %d) (money = %d), result : %b", 3, 4, 15, bank.transfer(3, 4, 15)));
        System.out.println(String.format("(account = %d) withdraw (money = %d), result : %b", 10, 50, bank.withdraw(10, 50)));
    }

    /**
     * 分析：这就是一个简单模拟器，主要是设计，需要能够维持过来(当然，这里先不考虑并发问题)，转账、存款、取款3种业务的单笔交易都要支持O(1)时间复杂度
     */
    static class Bank {

        private int n;
        private long[] balance;

        Bank(final long[] balance) {
            this.balance = balance;
            this.n = balance.length;
        }

        boolean transfer(final int account1, final int account2, final long money) {
            if (!accountExists(account1)) {
                return false;
            }
            if (!accountExists(account2)) {
                return false;
            }
            if (balance[account1 - 1] >= money) {
                balance[account1 - 1] -= money;
                balance[account2 - 1] += money;
                return true;
            }
            return false;
        }

        boolean deposit(final int account, final long money) {
            if (accountExists(account)) {
                balance[account - 1] += money;
                return true;
            }
            return false;
        }

        boolean withdraw(final int account, final long money) {
            if (accountExists(account) && balance[account - 1] >= money) {
                balance[account - 1] -= money;
                return true;
            }
            return false;
        }

        private boolean accountExists(final int account) {
            return account >= 1 && account <= n;
        }
    }
}
