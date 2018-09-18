package algorithm;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by zhangmn on 2018/8/17.
 */
public class BubbleSort {

    public void bubbleSort(int[] A) {
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = A.length - 1; j > i; j--) {
                if (A[j] < A[j - 1]) {
                    int a = A[j];
                    A[j] = A[j - 1];
                    A[j - 1] = a;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] A = new int[]{5, 2, 4, 6, 1, 3};
        new BubbleSort().bubbleSort(A);
        String s = Arrays.stream(A).boxed().map(String::valueOf).collect(Collectors.joining(", "));
        System.out.println(s);
    }
}
