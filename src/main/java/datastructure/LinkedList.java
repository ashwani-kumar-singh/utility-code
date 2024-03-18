package datastructure;

public class LinkedList {
  private Node head;
  static class Node{
    Node next;
    int data;
    Node(int data){
      this.data = data;
      this.next = null;
    }
  }

  private static LinkedList insert(LinkedList list, int data)
  {
    Node new_node = new Node(data);
    if (list.head == null) {
      list.head = new_node;
    }
    else {
      Node currNode = list.head;
      while (currNode.next != null) {
        currNode = currNode.next;
      }
      currNode.next = new_node;
    }
    return list;
  }

  private static void printList(LinkedList list)
  {
    Node currNode = list.head;
    System.out.print("\nLinkedList: \n");
    while (currNode != null) {
      System.out.println(currNode.data + " ");
      currNode = currNode.next;
    }
  }

  private static LinkedList deleteByKey(LinkedList list, int key)
  {
    Node currNode = list.head, prev = null;
    // CASE 1: If head node itself holds the key to be deleted
    if (currNode != null && currNode.data == key) {
      list.head = currNode.next; // Changed head
      System.out.println(key + " found and deleted");
      return list;
    }

    // CASE 2: If the key is somewhere other than at head Search for the key to be deleted,
    // keep track of the previous node as it is needed to change currNode.next
    while (currNode != null && currNode.data != key) {
      // If currNode does not hold key continue to next node
      prev = currNode;
      currNode = currNode.next;
    }

    // If the key was present, it should be at currNode, Therefore the currNode shall not be null
    if (currNode != null) {
      // Since the key is at currNode
      // Unlink currNode from linked list
      prev.next = currNode.next;
      System.out.println(key + " found and deleted");
    }

    // CASE 3: The key is not present, If key was not present in linked list currNode should be null
    if (currNode == null) {
      System.out.println(key + " not found");
    }
    return list;
  }

  public static LinkedList deleteAtPosition(LinkedList list, int index)
  {
    Node currNode = list.head, prev = null;
    if (index == 0 && currNode != null) {
      list.head = currNode.next; // Changed head
      System.out.println(index + " position element deleted");
      return list;
    }

    // CASE 2: If the index is greater than 0 but less than the size of LinkedList
    int counter = 0;
    // Count for the index to be deleted,  keep track of the previous node
    // as it is needed to change currNode.next
    while (currNode != null) {
      if (counter == index) {
        // Since the currNode is the required position  Unlink currNode from linked list
        prev.next = currNode.next;
        System.out.println(index + " position element deleted");
        break;
      }
      else {
        prev = currNode;
        currNode = currNode.next;
        counter++;
      }
    }
    // CASE 3: The index is greater than the size of the LinkedList. In this case, the currNode
    // should be null
    if (currNode == null) {
      // Display the message
      System.out.println(index + " position element not found");
    }
    return list;
  }

  public static void deleteNthNodeFromEnd(LinkedList.Node root, int k){
    LinkedList.Node p1= root, p2= root;
    while(k > 0) {
      p1= p1.next;
      k--;
    }
    while(p1.next!= null){
      p1 = p1.next;
      p2 = p2.next;
    }

    p2.next = p2.next.next;
  }

  public static LinkedList.Node pairWiseSwap(LinkedList.Node node)
  {

    // If linked list is empty or there is only one node in list
    if (node == null || node.next == null) {
      return node;
    }

    // Initialize previous and current pointers
    LinkedList.Node prev = node;
    LinkedList.Node curr = node.next;

    node = curr; // Change head before proceeding

    // Traverse the list
    while (true) {
      LinkedList.Node next = curr.next;
      curr.next = prev; // Change next of current as previous node

      // If next NULL or next is the last node
      if (next == null || next.next == null) {
        prev.next = next;
        break;
      }

      // Change next of previous to next next
      prev.next = next.next;

      // Update previous and curr
      prev = next;
      curr = prev.next;
    }
    return node;
  }

  public static void main(String[] args){
    LinkedList list = new LinkedList();
    list = insert(list, 1);
    list = insert(list, 2);
    list = insert(list, 3);
    list = insert(list, 4);
    list = insert(list, 5);
    list = insert(list, 6);
    list = insert(list, 7);
    list = insert(list, 8);
    printList(list);
    LinkedList.Node n = pairWiseSwap(list.head);
    //deleteNthNodeFromEnd(list.head, 2);
    //deleteByKey(list, 1);
    printList(list);
    /*deleteAtPosition(list, 5);
    printList(list);*/
  }
}