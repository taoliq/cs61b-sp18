package lab11.graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private boolean hasCycle = false;

    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        Stack<Integer> stk = new Stack<>();
        stk.push(-1);
        for (int i = 0; i < maze.V(); i++) {
            if (!marked[i]) {
                dfs(i, stk);
            }
        }
    }

    // Helper methods go here
    private void dfs(int v, Stack<Integer> stk) {
        if (hasCycle) {
            return;
        }
        marked[v] = true;
        int parent = stk.peek();
        stk.push(v);
        announce();

        for (int u : maze.adj(v)) {
            if (hasCycle) {
                return;
            }
            if (!marked[u]) {
                dfs(u, stk);
            } else if (marked[u] && u != parent) {
                int pre = stk.pop();
                int cur;
                edgeTo[u] = v;
                while (pre != u) {
                    cur = stk.pop();
                    edgeTo[pre] = cur;
                    pre = cur;
                }
                hasCycle = true;
                announce();
                return;
            }
        }
        if (!stk.isEmpty()) {
            stk.pop();
        }
    }
}

