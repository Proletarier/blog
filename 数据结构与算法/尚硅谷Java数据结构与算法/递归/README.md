## 递归

简单的说: 递归就是方法自己调用自己,每次调用时传入不同的变量.递归有助于编程者解决复杂的问题,同时 可以让代码变得简洁。


### 递归能解决什么样的问题
1) 各种数学问题如: 8皇后问题 , 汉诺塔, 阶乘问题, 迷宫问题, 球和篮子的问题(google编程大赛)
2) 各种算法中也会使用到递归，比如快排，归并排序，二分查找，分治算法等.
3) 将用栈解决的问题-->第归代码比较简洁

### 递归需要遵守的重要规则

1) 执行一个方法时，就创建一个新的受保护的独立空间(栈空间)
2) 方法的局部变量是独立的，不会相互影响, 比如n变量
3) 如果方法中使用的是引用类型变量(比如数组)，就会共享该引用类型的数据.
4) 递归必须向退出递归的条件逼近，否则就是无限递归,出现StackOverflowError，死龟了:)
5) 当一个方法执行完毕，或者遇到return，就会返回，遵守谁调用，就将结果返回给谁，同时当方法执行完毕或者返回时，该方法也就执行完毕。


### 递归-八皇后问题(回溯算法)

八皇后问题，是一个古老而著名的问题，是回溯算法的典型案例。该问题是国际西洋棋棋手马克斯·贝瑟尔于1848年提出：在8×8格的国际象棋上摆放八个皇后，使其不能互相攻击，即：任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。

 ![image-1](images/1.png) 

### 八皇后问题算法思路分析
1) 第一个皇后先放第一行第一列 
2) 第二个皇后放在第二行第一列、然后判断是否 OK， 如果不 OK，继续放在第二列、第三列、依次把所有列都 放完，找到一个合适 
3) 继续第三个皇后，还是第一列、第二列……直到第 8 个皇后也能放在一个不冲突的位置，算是找到了一个正确 解 
4) 当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后，放到第一列的所有正确解， 全部得到. 
5) 然后回头继续第一个皇后放第二列，后面继续循环执行 1,2,3,4 的步骤

 #### 代码实现
 ````java
 package com.atguigu.recursion;

import sun.awt.windows.WPrinterJob;

import java.util.Arrays;

/**
 * 八皇后
 */
public class Queen {


    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array, 保存皇后放置位置的结果,比如 arr = {0 , 4, 7, 5, 2, 6, 1, 3}
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;
    public static void main(String[] args) {
        //测试一把 ， 8皇后是否正确
        Queen queue8 = new Queen();
        queue8.check(0);
        System.out.printf("一共有%d解法", count);
        System.out.printf("一共判断冲突的次数%d次", judgeCount); // 1.5w
        System.out.println();

    }

    public void  check(int n) {
        if (n == max){
            count++;
            print();
            return;
        }

        for (int i = 0; i < max; i++){
            array[n] = i;
            if(judge(n)){
                check(n +1 );
            }
        }
    }

    public  boolean  judge(int n){
        for (int i = 0; i < n; i ++){
            if(array[i] == array[n] || Math.abs(n - i) ==  Math.abs(array[n] - array[i]) ){
                return false;
            }
        }
        return  true;
    }


    private  void  print(){
        System.out.println(Arrays.toString(array));
    }


}

 ````
