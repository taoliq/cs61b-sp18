package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayDeque;

public class Solver {
    private int moves;
    private ArrayDeque<WorldState> solution;

    private class SearchNode implements Comparable<SearchNode> {
        private WorldState worldState;
        private int moves;
        private SearchNode preNode;
        private int estimatedMoves;

        SearchNode(WorldState worldState, int moves, SearchNode preNode) {
            this.worldState = worldState;
            this.moves = moves;
            this.preNode = preNode;
            this.estimatedMoves = worldState.estimatedDistanceToGoal();
        }

        @Override
        public int compareTo(SearchNode node) {
            int p1 = this.moves + this.estimatedMoves;
            int p2 = node.moves + node.estimatedMoves;
            return p1 - p2;
        }
    }

    public Solver(WorldState initial) {
        SearchNode initialNode = new SearchNode(initial, 0, null);
        SearchNode goalNode = initialNode;
        MinPQ<SearchNode> minPQ = new MinPQ<>();
        minPQ.insert(initialNode);

        while (!minPQ.isEmpty()) {
            SearchNode curNode = minPQ.delMin();
            if (curNode.worldState.isGoal()) {
                goalNode = curNode;
                moves = goalNode.moves;
                break;
            }
            for (WorldState state : curNode.worldState.neighbors()) {
                if (curNode.preNode != null && state.equals(curNode.preNode.worldState)) {
                    continue;
                }
                minPQ.insert(new SearchNode(state, curNode.moves + 1, curNode));
            }
        }

        solution = new ArrayDeque<WorldState>();
        solution.addFirst(goalNode.worldState);
        while (goalNode.preNode != null) {
            goalNode = goalNode.preNode;
            solution.addFirst(goalNode.worldState);
        }
    }

    public int moves() {
        return moves;
    }

    public Iterable<WorldState> solution() {
        return solution;
    }

}
