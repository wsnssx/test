package algorithm;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by zhangmn on 2018/8/7.
 */
public class InsertionSort {

    public void sort(int[] A) {
        for (int i = 1; i < A.length; i++) {
            int k = A[i];
            int j = i - 1;
            while (j >= 0 && A[j] > k) {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = k;
        }
    }

    public static void main(String[] args) {
        int[] A = new int[]{5, 2, 4, 6, 1, 3};
        new InsertionSort().sort(A);
        String s = Arrays.stream(A).boxed().map(String::valueOf).collect(Collectors.joining(", "));
        System.out.println(s);
    }
}