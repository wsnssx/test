package algorithm;

/**
 * Created by zhangmn on 2018/8/17.
 */
public class MergeSort {

    public void mergeSort(int[] A) {
        mergeSort(A, 0, A.length - 1);
    }

    /**
     * 申请两个子数组时，长度都设置成多出1个空位的，最后一个数赋值为最大值，可以作为哨兵，减少到达结尾的判断
     * @param A
     * @param begin
     * @param end
     */
    private void mergeSort(int[] A, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int mid = begin + (end - begin) / 2;
        mergeSort(A, begin, mid);
        mergeSort(A, mid + 1, end);
        int[] left = new int[mid - begin + 2];
        System.arraycopy(A, begin, left, 0, left.length - 1);
        left[left.length - 1] = Integer.MAX_VALUE;
        int[] right = new int[end - mid + 1];
        System.arraycopy(A, mid + 1, right, 0, right.length - 1);
        right[right.length - 1] = Integer.MAX_VALUE;
        int l = 0, r = 0;
        for (int i = begin; i <= end; i++) {
            if (left[l] <= right[r]) {
                A[i] = left[l];
                l++;
            } else {
                A[i] = right[r];
                r++;
            }
        }
    }

    public int countReversePairs(int[] A) {
        return countReversePairs(A, 0, A.length - 1);
    }

    private int countReversePairs(int[] A, int begin, int end) {
        if (begin >= end) {
            return 0;
        }
        int mid = begin + (end - begin) / 2;
        int res = countReversePairs(A, begin, mid);
        res += countReversePairs(A, mid + 1, end);

        int[] left = new int[mid - begin + 2];
        left[left.length - 1] = Integer.MAX_VALUE;
        System.arraycopy(A, begin, left, 0, left.length - 1);
        int[] right = new int[end - mid + 1];
        right[right.length - 1] = Integer.MAX_VALUE;
        System.arraycopy(A, mid + 1, right, 0, right.length - 1);

        int l = 0, r = 0;
        for (int i = begin; i <= end; i++) {
            if (left[l] <= right[r]) {
                A[i] = left[l];
                l++;
            } else {
                A[i] = right[r];
                r++;
                res += left.length - l - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] A = new int[]{5, 2, 4, 7, 1, 3, 2, 6};
//        new MergeSort().mergeSort(A);
//        String s = Arrays.stream(A).boxed().map(String::valueOf).collect(Collectors.joining(", "));
//        System.out.println(s);

        int count = new MergeSort().countReversePairs(A);
        System.out.println(count);
    }
}
