package tree;

import java.util.*;

// Define TreeNode
class TreeNode {
    // A single tree node has three fields.
    int val; // The value of the node.
    TreeNode left; // The reference to the left child node.
    TreeNode right; // The reference to the right child node.

    // Default constructor to initialize an empty node.
    TreeNode() {
    }

    // Constructor to initialize a node with a specific value.
    TreeNode(int val) {
        this.val = val;
    }

    // Constructor to initialize a node
    // with a specific value and references to the left and right child nodes.
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

// Define BinaryTree.
class BinaryTree {
    // A binary tree has one field.
    TreeNode root; // The reference to the topmost node of the tree.

    // Default constructor to initialize an empty binary tree.
    BinaryTree() {
        root = null;
    }

    // Method to build a binary tree from an array of values
    // using level-order traversal (Breadth-First Search).
    public void buildTreeLevelOrder(int[] vals) {
        // Early exit check: If the input array is empty, there's nothing to build.
        if (vals == null || vals.length == 0) {
            return;
        }

        // Create a queue for level-order traversal.
        Queue<TreeNode> queue = new LinkedList<>();
        // Set the first value of the array to the root node's value.
        root = new TreeNode(vals[0]);
        // Add the root node to the queue.
        queue.offer(root);

        // Initialize an index to traverse the array from the second element.
        int i = 1;

        // Loop through the array to build the binary tree using level-order traversal.
        while (!queue.isEmpty() && i < vals.length) {
            // Get the front node from the queue.
            TreeNode curr = queue.poll();

            // If there are more values in the array
            // and the current value is not null (-1)
            if (i < vals.length && vals[i] != -1) {
                // Set the current value to the left child.
                curr.left = new TreeNode(vals[i]);
                // Add the left child to the queue.
                queue.offer(curr.left);
            }
            // Move to the next value in the array.
            i++;

            // If there are more values in the array
            // and the current value is not null (-1)
            if (i < vals.length && vals[i] != -1) {
                // Set the current value to the right child.
                curr.right = new TreeNode(vals[i]);
                // Add the right child to the queue.
                queue.offer(curr.right);
            }
            // Move to the next value in the array.
            i++;
        }
    }

    // Method to print the binary tree up to a specific level
    // using level-order traversal (Breadth-First Search).
    public void printLevelOrder(int level) {
        // If the root is null, print an empty array and exit.
        if (root == null) {
            System.out.println("[]");
            return;
        }

        // Create a queue for level-order traversal,
        // using LinkedList instead of ArrayDeque to add a `null` node to a queue.
        Queue<TreeNode> queue = new LinkedList<>();
        // Add the root node to the queue.
        queue.offer(root);

        // Create a StringBuilder to construct the result string.
        StringBuilder result = new StringBuilder();
        result.append("[");

        // Set a variable to track the current level of the tree traversal.
        int currentLevel = 0;

        // Continue traversal while there are nodes in the queue
        // and the current level does not exceed the specified level.
        while (!queue.isEmpty() && currentLevel < level) {
            // Get the number of nodes at the current level.
            int levelSize = queue.size();

            // Iterate through all nodes at the current level.
            for (int i = 0; i < levelSize; i++) {
                // Remove the front node from the queue.
                TreeNode curr = queue.poll();

                // Append "null" if the node is null.
                if (curr == null) {
                    result.append("null, ");
                } else {
                    // Else, append the node's value.
                    result.append(curr.val).append(", ");
                    // Add the left and right children to the queue for the next level.
                    queue.offer(curr.left);
                    queue.offer(curr.right);
                }
            }
            // Increment the current level counter.
            currentLevel++;
        }

        // Remove trailing comma and space.
        if (result.length() > 1) {
            result.setLength(result.length() - 2);
        }

        result.append("]");
        System.out.println(result.toString());
    }
}

public class MaximumDepthOfBinaryTree {
    /**
     * Given the root of a binary tree, return its maximum depth.
     * A binary tree's maximum depth is the number of nodes along the longest path
     * from the root node down to the farthest leaf node.
     */

    /**
     * Approach 1: Recursion (Depth-First Search)
     * Time Complexity: O(n), where n is the number of nodes in the tree.
     * - We visit each node exactly once.
     * Space Complexity: O(n)
     * - It is determined by the maximum depth of the recursion stack.
     * - In the worst case (a skewed binary tree or linked list),
     * the height of the binary tree would be equal to the number of nodes, n.
     * - In the best case (a perfectly balanced binary tree),
     * the height would be approximately logn.
     *
     * @param root The root node of a binary tree.
     * @return The maximum depth of the binary tree.
     */

    public static int solutionOne(TreeNode root) {
        // Base case: If the tree is empty, the depth is zero.
        if (root == null) {
            return 0;
        }
        // Recursive case: Calculate the maximum depth of the tree.
        else {
            // Recursively calculate depths of left and right subtrees
            int leftDepth = solutionOne(root.left);
            int rightDepth = solutionOne(root.right);
            // Return the larger depth plus one for the current node.
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }

    /**
     * Approach 2: Iteration (Breadth-First Search)
     * Time Complexity: O(n), where n is the number of nodes in the tree.
     * - We visit each node exactly once to add to and remove from the queue.
     * Space Complexity: O(n)
     * - It is determined by the maximum number of nodes at any level of the tree.
     * - The queue may hold up to n nodes in an unbalanced tree.
     *
     * @param root The root node of a binary tree.
     * @return The maximum depth of the binary tree.
     */

    public static int solutionTwo(TreeNode root) {
        // Early exit check: If the tree is empty, the depth is zero.
        if (root == null) {
            return 0;
        }

        // Create a queue for level-order traversal.
        Queue<TreeNode> queue = new LinkedList<>();
        // Start traversal from the root node.
        queue.offer(root);

        // Initialize a variable to track the depth of the tree.
        int depth = 0;

        // Perform level-order traversal to calculate the maximum depth.
        while (!queue.isEmpty()) {
            // Increment the depth at the start of each level
            depth++;

            // Get the number of nodes at the current level.
            int levelSize = queue.size();

            // Iterate through all nodes at the current level.
            for (int i = 0; i < levelSize; i++) {
                // Remove the front node from the queue.
                TreeNode curr = queue.poll();

                // Add left child to the queue if it exists.
                if (curr.left != null) {
                    queue.offer(curr.left);
                }

                // Add right child to the queue if it exists.
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
        }

        // Return the maximum depth of the tree.
        return depth;
    }

    public static void main(String[] args) {
        // Test case 1: A three-level tree
        BinaryTree tree1 = new BinaryTree();
        tree1.buildTreeLevelOrder(new int[] { 3, 9, 20, -1, -1, 15, 7 });
        int expectedOutput1 = 3;

        // Test case 2: A two-level tree
        BinaryTree tree2 = new BinaryTree();
        tree2.buildTreeLevelOrder(new int[] { 1, -1, 2 });
        int expectedOutput2 = 2;

        // Test case 3: An empty tree
        BinaryTree tree3 = new BinaryTree();
        int expectedOutput3 = 0;

        // Test solutionOne
        System.out.println("*****Testing solutionOne*****");
        System.out.println("Test Case 1:");
        System.out.print("Input Tree: ");
        tree1.printLevelOrder(expectedOutput1);
        System.out.println("Expected Output: " + expectedOutput1);
        System.out.println("Actual Output: " + MaximumDepthOfBinaryTree.solutionOne(tree1.root));
        System.out.println();
        System.out.println("Test Case 2:");
        System.out.print("Input Tree: ");
        tree2.printLevelOrder(expectedOutput2);
        System.out.println("Expected Output: " + expectedOutput2);
        System.out.println("Actual Output: " + MaximumDepthOfBinaryTree.solutionOne(tree2.root));
        System.out.println();
        System.out.println("Test Case 3:");
        System.out.print("Input Tree: ");
        tree3.printLevelOrder(expectedOutput3);
        System.out.println("Expected Output: " + expectedOutput3);
        System.out.println("Actual Output: " + MaximumDepthOfBinaryTree.solutionOne(tree3.root));
        System.out.println();

        // Test solutionTwo
        System.out.println("*****Testing solutionTwo*****");
        System.out.println("Test Case 1:");
        System.out.print("Input Tree: ");
        tree1.printLevelOrder(expectedOutput1);
        System.out.println("Expected Output: " + expectedOutput1);
        System.out.println("Actual Output: " + MaximumDepthOfBinaryTree.solutionTwo(tree1.root));
        System.out.println();
        System.out.println("Test Case 2:");
        System.out.print("Input Tree: ");
        tree2.printLevelOrder(expectedOutput2);
        System.out.println("Expected Output: " + expectedOutput2);
        System.out.println("Actual Output: " + MaximumDepthOfBinaryTree.solutionTwo(tree2.root));
        System.out.println();
        System.out.println("Test Case 3:");
        System.out.print("Input Tree: ");
        tree3.printLevelOrder(expectedOutput3);
        System.out.println("Expected Output: " + expectedOutput3);
        System.out.println("Actual Output: " + MaximumDepthOfBinaryTree.solutionTwo(tree3.root));
        System.out.println();
    }
}
