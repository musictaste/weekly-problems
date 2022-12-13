package class_2022_12_4_week;

import java.util.Arrays;
import java.util.PriorityQueue;

// 测试链接 : https://leetcode.cn/problems/minimum-cost-to-hire-k-workers/
public class Code02_MinimumCostToHireKWorkers {

	public static class Employee {
		public double loserDegree;
		public int quality;

		public Employee(int w, int q) {
			loserDegree = (double) w / (double) q;
			quality = q;
		}
	}

	public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
		int n = quality.length;
		Employee[] employees = new Employee[n];
		for (int i = 0; i < n; i++) {
			employees[i] = new Employee(wage[i], quality[i]);
		}
		Arrays.sort(employees, (a, b) -> a.loserDegree <= b.loserDegree ? -1 : 1);
		PriorityQueue<Integer> minTops = new PriorityQueue<Integer>((a, b) -> b - a);
		double ans = Double.MAX_VALUE;
		for (int i = 0, qualitySum = 0; i < n; i++) {
			int curQuality = employees[i].quality;
			if (minTops.size() < k) {
				qualitySum += curQuality;
				minTops.add(curQuality);
				if (minTops.size() == k) {
					ans = Math.min(ans, qualitySum * employees[i].loserDegree);
				}
			} else {
				if (minTops.peek() > curQuality) {
					qualitySum += curQuality - minTops.poll();
					minTops.add(curQuality);
				}
				ans = Math.min(ans, qualitySum * employees[i].loserDegree);
			}
		}
		return ans;
	}

}
