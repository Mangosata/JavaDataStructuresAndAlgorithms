package com.sata.queue;

import java.util.Scanner;

/**
 * @author Bin Jia
 * @date 2020/7/11 20:52
 */
public class ArrayQueueDemo {

  public static void main(String[] args) {
    ArrayQueue arrayQueue = new ArrayQueue(3);
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

// 使用数组模拟队列-编写一个ArrayQueue类
class ArrayQueue {

  private int maxSize; //表示数组最大容量
  private int front; //队列头
  private int rear; // 队列尾
  private int[] arr; //存放数据的数组

  // 创建队列的构造器
  ArrayQueue(int arrMaxSize) {
    maxSize = arrMaxSize;
    arr = new int[maxSize];
    front = -1; //指向队列头的前一个位置
    rear = -1; //指向队列尾的数据（队列中最后一个数据）
  }

  // 判断队列是否满
  private boolean isFull() {
    return rear == maxSize - 1;
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
    rear++; //让rear后移
    arr[rear] = n;
  }

  // 获取队列的数据，出队列
  int getQueue() {
    // 判断队列是否为空
    if (isEmpty()) {
      throw new RuntimeException("队列空，不能取数据");
    }
    front++; // front后移
    return arr[front];
  }

  // 显示队列的所有数据
  void showQueue() {
    // 遍历
    if (isEmpty()) {
      System.out.println("队列为空，没有数据");
      return;
    }
    for (int i = 0; i < arr.length; i++) {
      System.out.printf("arr[%d]=%d\n", i, arr[i]);
    }
  }

  // 显示队列的头数据
  int headQueue() {
    if (isEmpty()) {
      throw new RuntimeException("队列为空，没有头数据");
    }
    return arr[front + 1];
  }
}
