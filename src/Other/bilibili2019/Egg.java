package Other.bilibili2019;

import java.util.Scanner;

/**
 * 22娘和33娘接到了小电视君的扭蛋任务：
 * 一共有两台扭蛋机，编号分别为扭蛋机2号和扭蛋机3号，22娘使用扭蛋机2号，33娘使用扭蛋机3号。
 * 扭蛋机都不需要投币，但有一项特殊能力：
 * 扭蛋机2号：如果塞x（x范围为>=0正整数）个扭蛋进去，然后就可以扭到2x+1个
 * 扭蛋机3号：如果塞x（x范围为>=0正整数）个扭蛋进去，然后就可以扭到2x+2个
 * 22娘和33娘手中没有扭蛋，需要你帮她们设计一个方案，两人“轮流扭”
 * （谁先开始不限，扭到的蛋可以交给对方使用），用“最少”的次数，使她们能够最后恰好扭到N个交给小电视君。
 */
public class Egg {

    // 本题第一个想法是dp，dp[i]表示i的最少组成，但是仔细读题发现
    // 双方每次交给对方多少个，自己下次扭时要放多少个多是可以的，一切只要达成“最少”即可
    // 那么每次我只要全压一定比部分压要“少”，n 为偶则将 (n-2)/2全压进3，n 为奇则将 (n-1)/2 全压，
    // 从 n 往前不断选择最“少”到0即可
    private static void egg(int n, StringBuilder builder) {
        if (n <= 0) return;
        if ((n&1) == 1) {
            builder.append("2");
            egg((n-1)/2, builder);
        } else {
            builder.append("3");
            egg((n-2)/2, builder);
        }
    }

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        egg(4357, builder);
        System.out.println(builder.reverse().toString());
    }
}
