import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("2"));
        assertFalse(palindrome.isPalindrome("abcdefg"));
        assertFalse(palindrome.isPalindrome("abcdefg"));
        assertFalse(palindrome.isPalindrome("abcbcbc"));
        assertTrue(palindrome.isPalindrome("abba"));
        assertFalse(palindrome.isPalindrome("ABCcbA"));

        CharacterComparator offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("", offByOne));
        assertFalse(palindrome.isPalindrome("aa", offByOne));
        assertFalse(palindrome.isPalindrome("aA", offByOne));
        assertFalse(palindrome.isPalindrome("abB", offByOne));
        assertTrue(palindrome.isPalindrome("abb", offByOne));
        assertTrue(palindrome.isPalindrome("bcbcbcbc", offByOne));
    }
}
