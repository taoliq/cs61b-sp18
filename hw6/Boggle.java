import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.List;
import java.util.Queue;

public class Boggle {
    
    // File path of dictionary file
    static String dictPath = "words.txt";
//    static String dictPath = "trivial_words.txt";

    /**
     * Solves a Boggle puzzle.
     * 
     * @param k The maximum number of words to return.
     * @param boardFilePath The file path to Boggle board file.
     * @return a list of words found in given Boggle board.
     *         The Strings are sorted in descending order of length.
     *         If multiple words have the same length,
     *         have them in ascending alphabetical order.
     */
    public static List<String> solve(int k, String boardFilePath) {
        In boardIn = new In(boardFilePath);
        In dictIn = new In(dictPath);
        String[] board = boardIn.readAllStrings();
        String[] dict = dictIn.readAllStrings();
        TrieNode root = new TrieNode();

        checkErrorCases(k, board);

        PriorityQueue<String> words = new PriorityQueue<>(k, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (o1.length() != o2.length()) {
                        return o2.length() - o1.length();
                    }
                    return o1.compareTo(o2);
                }
            });
        boolean[][] visited = new boolean[board.length][board[0].length()];

        //Initialize trie
        for (String w : dict) {
            root = TrieNode.put(root, w, 0);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                char c = board[i].charAt(j);
                TrieNode trieNode = TrieNode.get(root, c);
                if (trieNode != null) {
                    dfs(c + "", i, j, trieNode, words, board, visited);
                }
            }
        }

        List<String> res = new ArrayList<>();
        for (int i = 0; i < k && !words.isEmpty(); i++) {
            res.add(words.poll());
        }
//        System.out.println(res);

        return res;
    }

    private static void dfs(String string, int x, int y, TrieNode trieNode,
                            Queue<String> words, String[] board, boolean[][] visited) {
        if (trieNode == null) {
            return;
        }

        visited[x][y] = true;
        if (trieNode.val && string.length() >= 3 && !words.contains(string)) {
            words.add(string);
        }

        List<int[]> points = getAdjacency(x, y, visited);
        for (int[] p: points) {
            int nx = p[0];
            int ny = p[1];
            char c = board[nx].charAt(ny);
            TrieNode next = TrieNode.get(trieNode, c);
            if (next != null) {
                dfs(string + c, nx, ny, next, words, board, visited);
            }
        }

        visited[x][y] = false;
    }

    private static List<int[]> getAdjacency(int x, int y, boolean[][] visited) {
        ArrayList<int[]> adjs = new ArrayList<>();
        for (int nx = x - 1; nx <= x + 1; nx++) {
            for (int ny = y - 1; ny <= y + 1; ny++) {
                if (nx < 0 || nx >= visited.length || ny < 0 || ny >= visited[0].length) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                adjs.add(new int[]{nx, ny});
            }
        }
        return adjs;
    }

    private static void checkErrorCases(int k, String[] board) {
        if (!isRectangular(board)) {
            throw new IllegalArgumentException("The input board is not rectangular.\n");
        }

        if (k <= 0) {
            throw new IllegalArgumentException("k is non-positive.\n");
        }
    }

    private static boolean isRectangular(String[] board) {
        int wid = board[0].length();
        for (String row : board) {
            if (row.length() != wid) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<String> res = solve(7, "exampleBoard.txt");
        System.out.println(res);
        solve(7, "exampleBoard2.txt");
    }
}
