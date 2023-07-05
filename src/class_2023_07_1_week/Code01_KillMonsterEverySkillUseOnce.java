package class_2023_07_1_week;

// 先来一个最近国外同学考的题目
// 已知一些供应点的位置，一共n个供应点
// 其中有n-1个供应点一定都在x轴上，比如(15,0)位置，(2,0)位置等
// 只有1个供应点不在x轴上，比如(23,17)位置
// 给出每个供应点的位置，并且给定第k号供应点是出发点
// 要求每个供应点最多走过2次，返回从k点出发，走完所有供应点的最少距离
// 上面这个题没有代码实现
// 因为这个题就是彻底的业务分析，只有一系列的贪心设计，代码也不难写
// 以下是这节课的正式题，来自学员问题
// 现在有一个打怪类型的游戏，这个游戏是这样的，你有n个技能
// 每一个技能会有一个伤害，
// 同时若怪物小于等于一定的血量，则该技能可能造成双倍伤害
// 每一个技能最多只能释放一次，已知怪物有m点血量
// 现在想问你最少用几个技能能消灭掉他(血量小于等于0)
// 测试链接 : https://www.nowcoder.com/questionTerminal/d88ef50f8dab4850be8cd4b95514bbbd
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的所有代码，并把主类名改成"Main"
// 可以直接通过

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Code01_KillMonsterEverySkillUseOnce {

	public static int MAXN = 11;

	public static int[] kill = new int[MAXN];

	public static int[] blood = new int[MAXN];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			int t = (int) in.nval;
			for (int i = 0; i < t; i++) {
				in.nextToken();
				int n = (int) in.nval;
				in.nextToken();
				int m = (int) in.nval;
				for (int j = 0; j < n; j++) {
					in.nextToken();
					kill[j] = (int) in.nval;
					in.nextToken();
					blood[j] = (int) in.nval;
				}
				int ans = f(n, 0, m);
				out.println(ans == Integer.MAX_VALUE ? -1 : ans);
				out.flush();
			}
		}
	}

	public static int f(int n, int i, int rest) {
		if (rest <= 0) {
			return i;
		}
		if (i == n) {
			return Integer.MAX_VALUE;
		}
		int ans = Integer.MAX_VALUE;
		for (int j = i; j < n; j++) {
			swap(i, j);
			if (rest > blood[i]) {
				ans = Math.min(ans, f(n, i + 1, rest - kill[i]));
			} else {
				ans = Math.min(ans, f(n, i + 1, rest - kill[i] * 2));
			}
			swap(i, j);
		}
		return ans;
	}

	public static void swap(int i, int j) {
		int a = kill[i];
		int b = blood[i];
		kill[i] = kill[j];
		blood[i] = blood[j];
		kill[j] = a;
		blood[j] = b;
	}

}