package com.example.kotlinfirstdemo.util;

import android.util.Log;

import java.util.Random;
import java.util.Scanner;

public class DFSTest {

    public static int mMax = 0;
    /**
     *
     * @param arr 二维数组
     * @param i 当前坐标的x 轴
     * @param j 当前坐标的y 轴
     * @param arrRow 数组的行
     * @param arrColumn 数组的列
     */
    public static void DFS(int[][] arr , int i, int j, int arrRow, int arrColumn,int max){
        arr[i][j] = 0;
//        int lenRow = flag.length;
//        int lenCol = flag[0].length;
        // 左上
        if((i - 1) >= 0 && (j - 1) >= 0 && arr[i-1][j-1] == 1){
            if(mMax <= max){
                mMax = mMax + 1;
            }
            DFS(arr, i-1, j-1, arrRow, arrColumn,max + 1);
            Log.i("TestUtil2", "df左上"+ max);

        }
        // 上
        if((i - 1) >= 0 && arr[i-1][j] == 1){
            if(mMax <= max){
                mMax = mMax + 1;
            }
            DFS(arr, i-1, j, arrRow, arrColumn,max + 1);
            Log.i("TestUtil2", "dfs上"+ max);

        }

        // 右上
        if((i - 1) >= 0 && (j + 1) < arrColumn && arr[i-1][j+1] == 1){
            if(mMax <= max){
                mMax = mMax + 1;
            }
            DFS(arr, i-1, j+1, arrRow , arrColumn,max + 1);
            Log.i("TestUtil2", "dfs右上" + max);

        }

        // 左
        if((j - 1) >= 0 && arr[i][j-1] == 1){
            if(mMax <= max){
                mMax = mMax + 1;
            }
            DFS(arr, i, j-1,arrRow, arrColumn,max + 1);
            Log.i("TestUtil2", "dfs左"+ max);

        }

        // 右
        if( (j + 1) < arrColumn && arr[i][j+1] == 1){
            if(mMax <= max){
                mMax = mMax + 1;
            }
            DFS(arr, i, j+1,arrRow, arrColumn,max + 1);
            Log.i("TestUtil2", "dfs右"+ max);

        }

        // 左下
        if((i + 1) < arrRow && (j - 1) >= 0 && arr[i+1][j-1] == 1){
            if(mMax <= max){
                mMax = mMax + 1;
            }
            DFS(arr, i+1, j-1,arrRow, arrColumn,max + 1);
            Log.i("TestUtil2", "dfs左下"+ max);

        }

        // 下
        if((i + 1) < arrRow && arr[i+1][j] == 1){
            if(mMax <= max){
                mMax = mMax + 1;
            }
            DFS(arr, i+1, j,arrRow, arrColumn,max + 1);
            Log.i("TestUtil2", "dfs下"+ max);

        }

        // 右下
        if((i + 1) < arrRow && (j + 1) < arrColumn && arr[i+1][j+1] == 1){
            if(mMax <= max){
                mMax = mMax + 1;
            }
            DFS(arr, i+1, j+1,arrRow, arrColumn,max + 1);
            Log.i("TestUtil2", "dfs右下"+ max);

        }
    }

    public static void testDFS(){
        testInitArray();
//        testRandom();
    }

    //用随机数产生二维数组
    public static void testRandom(){
        StringBuilder stringBuilder = new StringBuilder();
        Random in = new Random();
        int arrayRow = 4;
        int arrColumn = 5;
        int[][] flag = new int[arrayRow][arrColumn];
        for(int i = 0; i < arrayRow; i++){
            stringBuilder.setLength(0);
            for(int j = 0; j < arrColumn; j ++){
                flag[i][j] = in.nextInt(2);
                int temp = flag[i][j];
                String tempString = temp + "   ";
                stringBuilder.append(tempString);
            }
            Log.i("TestUtil2", stringBuilder.toString());
        }
        int count = 0;
        for(int i = 0; i < arrayRow; i++){
            for(int j = 0; j < arrColumn; j ++){
                if(flag[i][j] == 1){
                    count ++;
                    DFS(flag, i, j, arrayRow, arrColumn,1);
                }
            }
        }

        Log.i("TestUtil22", String.valueOf(count));
        Log.i("TestUtil22","最多几个："+ String.valueOf(mMax));
    }

    //二维数组已经被初始化
    public static void testInitArray(){
        StringBuilder stringBuilder = new StringBuilder();
        int[][] arr = iniArray();
        int arrRow = arr.length;// 行
        int arrColumn = arr[0].length;//列

        Log.i("TestUtil23", arrRow  + ":" + arrColumn);
        arr = iniArray();
        for(int i = 0; i < arrRow; i++){
            stringBuilder.setLength(0);
            for(int j = 0; j < arrColumn; j ++){
                int temp = arr[i][j];
                String tempString = temp + "   ";
                stringBuilder.append(tempString);
            }
            Log.i("TestUtil2", stringBuilder.toString());
        }
        int count = 0;
        arrColumn = 3;
        arrRow = 3;
        for(int i = 0; i < arrRow; i++){
            for(int j = 0; j < arrColumn; j ++){
                if(arr[i][j] == 1){
                    count ++;
                    Log.i("TestUtil22", String.valueOf(count) + "row:" + arrRow + "col:" + arrColumn);
//                    DFS(arr, i, j, arrRow, arrColumn,1);
                }
            }
        }


        Log.i("TestUtil22", "最多几次："+String.valueOf(mMax));
    }

    //初始化二维数组
    public static int[][] iniArray(){
        int[][] arr1 = {{1,0,1}, {1,1,0}, {1,0,1}};
        return arr1;
    }
}
