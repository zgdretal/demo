package com.zgd.menhu.demo.service.test;

public class TestzgdLRU {

    Node head;
    Node tail;
    //找到节点后将其放到头部
    public Node getNode(Node searchNode) {
        if (head == null) {
            return null;
        }
        //建立pre节点和current节点
        boolean flag = false;
        Node pre = new Node();
        pre.setNext(head);
        Node current = head;
        while (!flag && current != null) {
            if (current.getValue() == searchNode.getValue()) {
                flag = true;
            } else {
                current = current.getNext();
                pre = pre.getNext();
            }
        }
        if (flag) {
            //找到后将节点放到链表头部
            pre.setNext(current.getNext());
            current.setNext(head);
            head = current;
            return head;
        } else {
            return null;
        }

    }

    public void setNode(Node addNode) {
        if (head == null) {
            head = addNode;
            return;
        }
        addNode.setNext(head);
        head = addNode;
    }

    public void deleteNode(Node node) {

    }

    public void updateNode() {

    }


}
