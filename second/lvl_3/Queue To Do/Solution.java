public class Solution {
    public static int solution(int start, int add) {
        int skip = 0;
        int result = 0;
        while (add > 0){
            for (int i = 0; i < add; i++){
                result ^= start;
                start++;
            }
            start+=skip;
            skip++;
            add--;
        }
        return result;
    }
}