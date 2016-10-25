package boj.solution;

public class BstSelect {

  interface TreeNode {

    /**
     * Gets key for this node.
     */
    int getKey();

    /**
     * Gets left subtree.
     */
    TreeNode getLeft();

    /**
     * Gets right subtree.
     */
    TreeNode getRight();

    /**
     * Get size of the (sub)tree rooted at this node.
     */
    int getSize();
  }

  /**
   * Given a BST, select kth smallest element in the tree.
   * k = 0 means the smallest element in the tree.
   * Returns null if no such element exists.
   */
  Integer solve(TreeNode root, int k) {
    while (root != null) {
      int leftSize = getSize(root.getLeft());
      if (k == leftSize) {
        return root.getKey();
      } else if (k < leftSize) {
        root = root.getLeft();
      } else {
        k -= leftSize + 1;
        root = root.getRight();
      }
    }
    return null;
  }

  private int getSize(TreeNode root) {
    return root == null ? 0 : root.getSize();
  }
}

