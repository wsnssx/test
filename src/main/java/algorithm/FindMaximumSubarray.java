package algorithm;

import java.util.Arrays;

/**
 * Created by zhangmn on 2018/9/18.
 */
public class FindMaximumSubarray {

    public int[] findMaximumSubarray(int[] A, int low, int high) {
        if (low == high) {
            return new int[]{low, high, A[low]};
        }
        int mid = low + (high - low) / 2;
        int[] left = findMaximumSubarray(A, low, mid);
        int[] right = findMaximumSubarray(A, mid + 1, high);
        int[] cross = findMaxCrossingSubarray(A, low, mid, high);
        if (left[2] >= cross[2] && left[2] >= right[2]) {
            return left;
        } else if (cross[2] >= left[2] && cross[2] >= right[2]) {
            return cross;
        }
        return right;
    }

    private int[] findMaxCrossingSubarray(int[] A, int low, int mid, int high) {
        int sum = 0;
        int leftSum = Integer.MIN_VALUE, leftIndex = mid + 1;
        for (int i = mid; i >= low; i--) {
            sum += A[i];
            if (sum >= leftSum) {
                leftSum = sum;
                leftIndex = i;
            }
        }
        sum = 0;
        int rightSum = Integer.MIN_VALUE, rightIndex = mid;
        for (int i = mid + 1; i <= high; i++) {
            sum += A[i];
            if (sum > rightSum) {
                rightSum = sum;
                rightIndex = i;
            }
        }
        return new int[]{leftIndex, rightIndex, leftSum + rightSum};
    }

    public static void main(String[] args) {
        int[] A = new int[]{13, -2, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        int[] res = new FindMaximumSubarray().findMaximumSubarray(A, 0, A.length - 1);
        System.out.println(Arrays.toString(res));
    }
}
