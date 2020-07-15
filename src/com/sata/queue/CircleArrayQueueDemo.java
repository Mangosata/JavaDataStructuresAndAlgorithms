package com.sata.queue;

import java.util.Scanner;

/**
 * @author Bin Jia
 * @date 2020/7/14 15:48
 */
public class CircleArrayQueueDemo {

  public static void main(String[] args) {
    System.out.println("测试环形数组队列");
    CircleArray arrayQueue = new CircleArray(4); // 有效数据最大为3
    char key = ' '; // 用户输入
    Scanner scanner = new Scanner(System.in);
    boolean loop = true;
    while (loop) {
      System.out.println("s(show): 显示队列");
      System.out.println("e(exit): 退出");
      System.out.println("a(add): 添加数据");
      System.out.println("g(get): 取出数据");
      System.out.println("h(head): 查看头数据");
      key = scanner.next().charAt(0); // 接受一个字符
      switch (key) {
        case 's':
          arrayQueue.showQueue();
          break;
        case 'a':
          System.out.println("输入一个数字");
          int value = scanner.nextInt();
          arrayQueue.addQueue(value);
          break;
        case 'g':
          try {
            int res = arrayQueue.getQueue();
            System.out.printf("取出的数据是%d\n", res);
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
          break;
        case 'h':
          try {
            int res = arrayQueue.headQueue();
            System.out.printf("队列头数据是%d\n", res);
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
          break;
        case 'e':
          scanner.close();
          loop = false;
          break;
        default:
          break;
      }
    }
    System.out.println("程序退出");
  }
}


class CircleArray {

  private int maxSize; //表示数组最大容量
  private int front; //队列头，指向第一个元素，初始值为0
  private int rear; // 队列尾，指向最后一个元素的后一个位置，初始值为0
  private int[] arr; //存放数据的数组

  public CircleArray(int arrMaxSize) {
    maxSize = arrMaxSize;
    arr = new int[maxSize];
  }

  // 判断队列是否满
  private boolean isFull() {
    return (rear + 1) % maxSize == front;
  }

  // 判断队列是否为空
  private boolean isEmpty() {
    return rear == front;
  }

  // 添加数据到队列
  void addQueue(int n) {
    // 判断队列是否满
    if (isFull()) {
      System.out.println("队列已满，不能加入数据");
      return;
    }
    // 直接将数据加入
    arr[rear] = n;
    // 将rear后移， 这里考虑取模
    rear = (rear + 1) % maxSize;
  }

  // 获取队列的数据，出队列
  int getQueue() {
    // 判断队列是否为空
    if (isEmpty()) {
      throw new RuntimeException("队列空，不能取数据");
    }
    // front指向队列的第一个元素
    // 先把front的值保存到一个临时变量
    // 将front后移，考虑取模
    // 将临时保存的变量返回
    int value = arr[front];
    front = (front + 1) % maxSize;
    return value;
  }

  // 显示队列的所有数据
  void showQueue() {
    // 遍历
    if (isEmpty()) {
      System.out.println("队列为空，没有数据");
      return;
    }
    // 从front开始遍历
    for (int i = front; i < front + size(); i++) {
      System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
    }
  }

  // 求出当前队列有效数据的个数
  public int size() {
    return (rear + maxSize - front) % maxSize;
  }

  // 显示队列的头数据
  int headQueue() {
    if (isEmpty()) {
      throw new RuntimeException("队列为空，没有头数据");
    }
    return arr[front];
  }

}