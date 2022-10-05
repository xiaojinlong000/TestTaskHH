package task01;

import java.util.HashMap;
import java.util.Scanner;

public class Main1 {
    private static Scanner in = new Scanner(System.in);
    private static int s;
    private static int[] n;
    private static int[] m;

    public static void main(String[] args) {
        readData();
        System.out.println(calculate(n, m, s));
    }

    private static void readData() {
        String[] initData = in.nextLine().split(" ");

        n = new int[Integer.parseInt(initData[0])];
        m = new int[Integer.parseInt(initData[1])];
        s = Integer.parseInt(initData[2]);

        int length = Math.max(n.length, m.length);

        for (int i = 0; i < length; i++) {
            String[] data = in.nextLine().split(" ");

            if (i < n.length) {
                n[i] = Integer.parseInt(data[0]);
            }
            if (i < m.length) {
                m[i] = Integer.parseInt(data[1]);
            }
        }
    }

    private static int calculate(int[] fArray, int[] sArray, int maxValue) {
        int result = 0;

        int fSum = 0;
        int [] fResult = new int[fArray.length + 1];
        fResult[0] = 0;

        int sSum = 0;
        int [] sResult = new int[sArray.length + 1];
        sResult[0] = 0;

        int length = Math.max(fArray.length, sArray.length);

        for (int index = 0; index < length; index++) {
            if (index < fArray.length) {
                fSum += fArray[index];
                fResult[index + 1] = fSum;
            }
            if (index < sArray.length) {
                sSum += sArray[index];
                sResult[index + 1] = sSum;
            }
        }

        for (int fIndex = 0; fIndex < fResult.length; fIndex++) {
            for (int sIndex = 0; sIndex < sResult.length; sIndex++) {
                if (fResult[fIndex] + sResult[sIndex] <= maxValue) {
                    if (result < fIndex + sIndex) {
                        result = fIndex + sIndex;
                    }
                }

            }
        }

        return result;
    }
}
