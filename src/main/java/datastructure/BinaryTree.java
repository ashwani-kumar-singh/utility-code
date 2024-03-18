package datastructure;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://medium.com/techie-delight/binary-tree-interview-questions-and-practice-problems-439df7e5ea1f
 *
 */
public class BinaryTree {

  static class Node {
    int data;
    Node left, right;

    Node(int data) {
      this.data = data;
      this.left = null;
      this.right = null;
    }
  }

  /**
   * Two trees are identical
   * @param x
   * @param y
   * @return
   */
  private static boolean isIdentical(Node x, Node y) {
    if (x == null && y == null) {
      return true;
    }
    return x != null && y != null && (x.data == y.data) && isIdentical(x.left, y.left)
        && isIdentical(x.right, y.right);
  }

  private static int heightOrDepthRecursion(Node x){
    if(x == null){
      return 0;
    }
    return 1 + Math.max(heightOrDepthRecursion(x.left), heightOrDepthRecursion(x.right));
  }

  private static void preOrderTraversal(Node x){
    if(x == null){
      return;
    }
    System.out.println(x.data);
    preOrderTraversal(x.left);
    preOrderTraversal(x.right);
  }

  private static void inOrderTraversal(Node x){
    if(x == null){
      return;
    }
    inOrderTraversal(x.left);
    System.out.println(x.data);
    inOrderTraversal(x.right);
  }

  private static void postOrderTraversal(Node x){
    if(x == null)
      return;
    postOrderTraversal(x.left);
    postOrderTraversal(x.right);
    System.out.println(x.data);
  }

  private static void levelOrderTraversal(Node x){
    Queue<Node> queue = new ArrayDeque<>();
    queue.add(x);
    while(!queue.isEmpty()){
      Node temp = queue.poll();
      System.out.println(temp.data);
      if(temp.left != null){
        queue.add(temp.left);
      }
      if(temp.right !=null){
        queue.add(temp.right);
      }
    }
  }

  public static void main(String[] args) {
    Node x = new Node(15);
    x.left = new Node(10);
    x.right = new Node(20);
    x.left.left = new Node(8);
    x.left.right = new Node(12);
    x.right.left = new Node(16);
    x.right.right = new Node(25);
    // construct second tree
    Node y = new Node(15);
    y.left = new Node(10);
    y.right = new Node(20);
    y.left.left = new Node(8);
    y.left.right = new Node(12);
    y.right.left = new Node(16);
    y.right.right = new Node(25);

    if (isIdentical(x, y)) {
      System.out.println("Given binary Trees are identical");
    } else {
      System.out.println("Given binary Trees are not identical");
    }
    System.out.println("height/depth of tree: " + heightOrDepthRecursion(x));
    System.out.println("preOrderTraversal:");
    preOrderTraversal(x);
    System.out.println("inOrderTraversal:");
    inOrderTraversal(x);
    System.out.println("postOrderTraversal:");
    postOrderTraversal(x);
    System.out.println("levelOrderTraversal:");
    levelOrderTraversal(x);
  }
}
