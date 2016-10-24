package boj.solution;

public class CountLessThan {

  interface TreeNode {

    /**
     * Gets key for this node;
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
   * Returns the number of elements less than given key in the tree.
   * Assumption: given tree is a BST. There is no duplicated elements in the tree.
   */
  int solve(TreeNode root, int key) {
    int res = 0;
    while (root != null) {
      if (key < root.getKey()) {
        root = root.getLeft();
      } else if (key > root.getKey()) {
        res += 1 + getSize(root.getLeft());
        root = root.getRight();
      } else {
        res += getSize(root.getLeft());
        break;
      }
    }
    return res;
  }

  private int getSize(TreeNode root) {
    return root == null ? 0 : root.getSize();
  }
}
