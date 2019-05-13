
public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        ArrayDeque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    private boolean isPalindromeHelper(String word, int begin, int end) {
        if (begin >= end) {
            return true;
        }
        if (word.charAt(begin) != word.charAt(end)) {
            return false;
        }
        return isPalindromeHelper(word, begin + 1, end - 1);
    }

    public boolean isPalindrome(String word) {
        return isPalindromeHelper(word, 0, word.length() - 1);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        int begin = 0;
        int end = word.length() - 1;
        while (begin < end) {
            if (!cc.equalChars(word.charAt(begin), word.charAt(end))) {
                return false;
            }
            begin++;
            end--;
        }
        return true;
    }

}
