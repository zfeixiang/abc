package EightQueens;

import java.util.Arrays;

public class EightQueens {

	static int[] b = new int[8];
	static int sum = 0;
	
	//检查对角能否 j为行
	static boolean check(int j) {
		//i为行
		for (int i = 0; i < j; i++) {
			if(b[i] == b[j] || Math.abs(b[i] - b[j]) == Math.abs(i - j)) {
				return false;
			}
		}
		return true;
	}
	
	public static void put(int l) {
		
		for(int i = 1 ; i < 9 ; i++) {
			b[l] = i;
			if(check(l)) {
				if(l == 8 - 1){
					sum++;
					System.out.println(sum + Arrays.toString(b));
					/* 如果全部摆好，则输出所有皇后的坐标 */
	            }else{
	                put(l + 1); /* 否则继续摆放下一个皇后 */
	            }
			}
		}
	}

	public static void main(String[] args) {
		put(0);
	}

}
