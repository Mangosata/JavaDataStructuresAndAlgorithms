package com.sata.linkedList;

/**
 * @author Bin Jia
 * @date 2020/7/14 19:54
 */
public class SingleLinkedListDemo {

  public static void main(String[] args) {
    // 创建节点
    HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
    HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
    HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
    HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

    // 创建一个链表
    SingleLinkedList singleLinkedList = new SingleLinkedList();
//    singleLinkedList.add(hero1);
//    singleLinkedList.add(hero2);
//    singleLinkedList.add(hero3);
//    singleLinkedList.add(hero4);

    // 按照编号的顺序加入
    singleLinkedList.addByOrder(hero1);
    singleLinkedList.addByOrder(hero4);
    singleLinkedList.addByOrder(hero3);
    singleLinkedList.addByOrder(hero2);

    singleLinkedList.list();
  }
}

// 定义SingleLinkedList管理我们的英雄
class SingleLinkedList {

  // 先初始化一个头节点， 头节点不要动, 不存放具体的数据
  private HeroNode head = new HeroNode(0, "", "");

  // 添加节点到单向链表
  // 思路：当不考虑编号顺序时
  // 1. 找到当前链表的最后节点
  // 2. 将最后这个节点的next指向新节点
  public void add(HeroNode heroNode) {

    // 因为head节点不能动，所以需要一个辅助遍历temp
    HeroNode temp = head;
    // 遍历链表，找到最后
    while (temp.next != null) {
      // 找到链表的最后
      // 如果没有找到最后，就将temp后移
      temp = temp.next;
    }
    // 当退出while循环时，temp就指向了链表的最后
    // 将最后这个节点的next指向新的节点
    temp.next = heroNode;
  }

  // 第二种添加方式，根据排名将英雄插入到指定位置
  // 如果已存在，添加失败并给出提示
  public void addByOrder(HeroNode heroNode) {
    // 因为头节点不能动，因此通过一个辅助指针来找到指定位置
    // 因为单链表，所以找的temp是位于添加位置的前一个节点，否则无法添加
    HeroNode temp = head;
    boolean flag = false; // 标志添加的编号是否存在，默认为false
    while (temp.next != null) {
      // 说明temp已经在链表的最后

      if (temp.next.no > heroNode.no) {
        // 位置找到，就在temp和temp.next的之间
        break;
      } else if (temp.next.no == heroNode.no) {
        // 说明希望添加到heroNode的编号已存在
        flag = true; // 编号已存在
        break;
      }
      temp = temp.next; // 后移，遍历当前链表
    }

    // 判断flag的值
    if (flag) {
      // 不能添加，说明编号存在
      System.out.printf("准备插入的英雄的编号%d 已经存在\n", heroNode.no);
    } else {
      // 插入到链表中，temp的后面
      heroNode.next = temp.next;
      temp.next = heroNode;
    }
  }


  // 显示链表（遍历）
  public void list() {
    // 判断链表是否为空
    if (head.next == null) {
      System.out.println("链表为空");
      return;
    }
    // 因为头节点不能动，因此我们需要一个辅助变量
    HeroNode temp = head.next;
    while (temp != null) {
      // 判断是否到链表最后
      // 输出节点的信息
      System.out.println(temp);
      // 将temp后移
      temp = temp.next;
    }
  }

}

// 定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode {

  public int no;
  public String name;
  public String nickname;
  public HeroNode next; // 指向下一个节点

  public HeroNode(int no, String name, String nickname) {
    this.no = no;
    this.name = name;
    this.nickname = nickname;
  }

  // 为了显示方便，重写toString
  @Override
  public String toString() {
    return "HeroNode [no=" + no + ", name=" + name
        + ", nickname=" + nickname + "]";
  }
}
