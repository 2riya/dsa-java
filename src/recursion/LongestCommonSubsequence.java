package recursion;

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        String s1 = "abcd";
        String s2 = "agcfd";
        System.out.println(lcs(s1, s2));
        System.out.println(lcsWithOffset(s1, 0, s2, 0));
    }

    private static int lcs(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        if (s1.charAt(0) == s2.charAt(0)) {
            return 1 + lcs(s1.substring(1), s2.substring(1));
        }
        return Math.max(lcs(s1, s2.substring(1)), lcs(s1.substring(1), s2));
    }

    private static int lcsWithOffset(String s1, int offset1, String s2, int offset2) {
        if (s1.length() == offset1 || s2.length() == offset2) {
            return 0;
        }
        if (s1.charAt(offset1) == s2.charAt(offset2)) {
            return 1 + lcsWithOffset(s1, offset1 + 1, s2, offset2 + 1);
        }
        return Math.max(lcsWithOffset(s1, offset1, s2, offset2 + 1),
            lcsWithOffset(s1, offset1 + 1, s2, offset2));
    }
}
