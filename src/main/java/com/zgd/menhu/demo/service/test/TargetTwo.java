package com.zgd.menhu.demo.service.test;

import java.util.concurrent.ThreadPoolExecutor;

public class TargetTwo implements TargetInterface{


    @Override
    public void test(String test) {
        //ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1);
        System.out.println(test + "two");
    }

    /**
     * 获取倒数第三个节点
     * @param head
     * @return
     */
    public Node handle(Node head) {
        if (head == null) {
            return null;
        }
        Node preNode = head;
        Node next = head.getNext();
        if (next == null) {
            return null;
        }
        Node currentNode = next.getNext();
        if (currentNode == null) {
            return null;
        }

        while (currentNode.getNext() != null) {
            preNode = preNode.getNext();
            currentNode = currentNode.getNext();
        }
        return preNode;
    }
}
