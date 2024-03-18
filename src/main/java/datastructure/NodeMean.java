package datastructure;

/* Write an implementation to a method that calculate Mean( Average) of current node value and it's
all children node value at each node of binary tree.

If required, Feel free to add new fields in Node class.

Example:           (A,44) (m= (44+9+4+5+13+6+7)/7)
                       /\
                      /  \
                     /    \
                    /      \
(m= (9+4+5)/3)  (B, 9)      (C, 13) (m = (13+6+7)/3)
                   / \         / \
                  /   \       /   \
                 /     \     /     \
           (D, 4)    (E, 5)  (F, 6) (G, 7)
           (m=4)     (m=5)   (m=6)   (m=7)


Output :
- Mean at Each Node -
Mean at Node: A is -> 12.571428571428571
Mean at Node: B is -> 6.0
Mean at Node: C is -> 8.666666666666666
Mean at Node: D is -> 4.0
Mean at Node: E is -> 5.0
Mean at Node: F is -> 6.0
Mean at Node: G is -> 7.0

*/

import java.util.*;

class NodeMean {
    private static void calculateMeanAtEachNodeOfBinaryTree(Node node) {
        if (node != null) {
            node.dataList.add(node.data);
            calculateMeanAtEachNodeOfBinaryTree(node.left);
            calculateMeanAtEachNodeOfBinaryTree(node.right);
            if (node.left != null)
                node.dataList.addAll(node.left.dataList);
            if (node.right != null)
                node.dataList.addAll(node.right.dataList);
            node.mean = node.dataList.stream().mapToDouble(Integer::doubleValue).average().getAsDouble();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of nodes to be created in a Binary Tree: ");
        int n = sc.nextInt();

        if (n == 0) {
            System.out.println("At least a node is required to calculate mean");
            return;
        }

        List<String> nodeNameList = new ArrayList<>();
        List<Integer> dataList = new ArrayList<>();
        System.out.println("Note: Insert Binary Nodes in Level Order Traversal");
        for (int i = 0; i < n; i++) {
            System.out.print("Enter the Node Name: ");
            nodeNameList.add(sc.next());
            System.out.print("Enter the Node Value: ");
            dataList.add(sc.nextInt());
        }

        Node root = new Node();
        root = createBinaryTree(nodeNameList, dataList, root, 0);

        calculateMeanAtEachNodeOfBinaryTree(root);

        printMean(root);
    }

    private static void printMean(Node node) {
        {
            Queue<Node> queue = new ArrayDeque<>();
            queue.add(node);
            while (!queue.isEmpty()) {
                Node tempNode = queue.poll();
                System.out.println("mean at node: " + tempNode.nodeName + " is -> " + tempNode.mean);
                if (tempNode.left != null) {
                    queue.add(tempNode.left);
                }
                if (tempNode.right != null) {
                    queue.add(tempNode.right);
                }
            }
        }
    }

    private static Node createBinaryTree(List<String> nodeNameList, List<Integer> dataList, Node root, int i) {
        if (i < dataList.size() && i < nodeNameList.size()) {
            root = new Node(nodeNameList.get(i), dataList.get(i));
            root.left = createBinaryTree(nodeNameList, dataList, root.left, 2 * i + 1);
            root.right = createBinaryTree(nodeNameList, dataList, root.right, 2 * i + 2);
        }
        return root;
    }

    static class Node {
        Integer data;
        String nodeName;
        Node left = null, right = null;
        double mean;
        List<Integer> dataList = new ArrayList<>();

        Node() {
        }

        Node(String nodeName, Integer data) {
            this.nodeName = nodeName;
            this.data = data;
        }
    }
}