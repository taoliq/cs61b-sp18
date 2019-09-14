import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private static final int R = 256;
    public boolean val;
    private Map<Character, TrieNode> next;

    public TrieNode() {
        this.val = false;
        next = new HashMap<>();
    }

    public static TrieNode put(TrieNode x, String key, int d) {
        if (x == null) x = new TrieNode();
        if (d == key.length()) {
            x.val = true;
            return x;
        }
        char c = key.charAt(d);
        TrieNode trieNode = put(x.next.get(c), key, d + 1);
        x.next.put(c, trieNode);
        return x;
    }

    public static TrieNode get(TrieNode x, char c) {
        if (x == null) return null;
        return x.next.get(c);
    }
}
