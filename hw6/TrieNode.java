public class TrieNode {
    private static final int R = 256;
    public boolean val;
    private TrieNode[] next;

    public TrieNode() {
        this.val = false;
        next = new TrieNode[R];
    }

    public static TrieNode put(TrieNode x, String key, int d) {
        if (x == null) x = new TrieNode();
        if (d == key.length()) {
            x.val = true;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, d + 1);
        return x;
    }

    public static TrieNode get(TrieNode x, char c) {
        if (x == null) return null;
        return x.next[c];
    }
}
