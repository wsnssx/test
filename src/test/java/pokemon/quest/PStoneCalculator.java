package pokemon.quest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangmn
 */
public class PStoneCalculator {

    //  0率  1量  2HP 3A  4K  5R- 6R+ 7D  8U  9S
    private static final int[] ATTACK_DEMAND = new int[]{4, 4, 0, 2, 0, 0, 0, 4, 4, 3};
    private static final int[] ASSIST_DEMAND = new int[]{4, 4, 0, 4, 0, 0, 0, 3, 4, 3};

    private class Stone {
        private String label;
        private int value;
        private int[] effects;

        public Stone(String label, int value, int... effects) {
            this.label = label;
            this.value = value;
            this.effects = effects;
        }

        @Override
        public String toString() {
            return "(" + label + ", " + value + ", " + effects[0] + effects[1] + effects[2] + ")";
        }
    }

    private List<Stone> stones;

    public void init() {
        stones = new ArrayList<>();
        add("A", 998, 0, 1, 9); // hx
        add("A", 998, 2, 7, 8); // hx
        add("H", 997, 2, 6, 9);
        add("H", 995, 0, 7, 8); // hx
        add("A", 993, 0, 7, 3); // hx
        add("H", 992, 9, 0, 1); // hx
        add("A", 991, 8, 2, 3); // hx
        add("A", 989, 9, 7, 6); // sb
        add("H", 989, 3, 4, 2);
        add("H", 989, 0, 1, 2); // hx
        add("H", 989, 4, 3, 7); // wl
        add("A", 989, 9, 0, 1); // hx
        add("H", 988, 0, 1, 4); // sb
        add("H", 988, 9, 8, 2); // wl
        add("A", 985, 6, 8, 4); // sb
        add("H", 985, 2, 1, 4);
        add("A", 985, 8, 4, 7); // hx
        add("A", 980, 7, 2, 4);
        add("H", 979, 8, 3, 5); // wl
        add("H", 978, 7, 0, 4);
        add("H", 978, 3, 1, 2);
        add("A", 976, 0, 1, 9); // sb
        add("H", 976, 0, 1, 9);
        add("A", 975, 4, 8, 3); // wl
        add("A", 974, 0, 1, 2); // sb
        add("A", 974, 0, 1, 9); // sb
        add("H", 973, 0, 1, 3); // wl
        add("H", 972, 3, 7, 8); // sb
        add("A", 971, 4, 7, 9);
        add("A", 971, 1, 4, 5);
        add("A", 970, 4, 8, 7); // sb
        add("A", 969, 1, 2, 6);
        add("H", 969, 7, 8, 2);
        add("A", 968, 6, 7, 8);
        add("A", 966, 0, 1, 9); // wl
        add("H", 965, 4, 7, 8);
        add("A", 965, 0, 1, 9); // wl
        add("A", 965, 4, 0, 7); // wl
        add("A", 963, 4, 0, 3);
        add("A", 962, 7, 3, 4);
        add("H", 962, 3, 1, 6);
        add("H", 962, 3, 7, 8); // sb
        add("A", 960, 4, 7, 8);
        add("A", 959, 4, 2, 8);
        add("A", 958, 7, 3, 2);
        add("A", 958, 2, 7, 8);
        add("A", 954, 0, 1, 9);
        add("A", 946, 6, 0, 1);
        add("A", 938, 1, 7, 8); // wl
    }

    private void add(String label, int value, int... effects) {
        stones.add(new Stone(label, value, effects));
    }

    public void calc(int[] target, int a, int h, int start, List<Stone> curStones, int[] curValues, int curA, int curH, List<Stone> answer, String label) {
        if (curA == a && curH == h) {
            if (valid(target, curValues) && bigger(curStones, answer, label)) {
                answer.clear();
                answer.addAll(curStones);
            }
            return;
        }
        for (int i = start; i < stones.size(); i++) {
            Stone stone = stones.get(i);
            int changeA = 0, changeH = 0;
            if ("A".equals(stone.label) && curA < a) {
                changeA++;
            } else if ("H".equals(stone.label) && curH < h) {
                changeH++;
            } else {
                continue;
            }
            curStones.add(stone);
            for (int j : stone.effects) {
                curValues[j]++;
            }
            calc(target, a, h, i + 1, curStones, curValues, curA + changeA, curH + changeH, answer, label);
            for (int j : stone.effects) {
                curValues[j]--;
            }
            curStones.remove(stone);
        }
    }

    private boolean valid(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] > b[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean bigger(List<Stone> a, List<Stone> b, String label) {
        int numA = a.stream().filter(s -> label.equals(s.label)).mapToInt(s -> s.value).sum();
        int numB = b.stream().filter(s -> label.equals(s.label)).mapToInt(s -> s.value).sum();
        return numA > numB;
    }

    public static void main(String[] args) {
        PStoneCalculator calculator = new PStoneCalculator();
        calculator.init();
        List<Stone> answer = new ArrayList<>();
        calculator.calc(ATTACK_DEMAND, 6, 3, 0, new ArrayList<>(), new int[ATTACK_DEMAND.length], 0, 0, answer, "A");
//        calculator.calc(ASSIST_DEMAND, 5, 4, 0, new ArrayList<>(), new int[ASSIST_DEMAND.length], 0, 0, answer, "H");
        for (Stone stone : answer) {
            System.out.println(stone.toString());
        }
    }
}
