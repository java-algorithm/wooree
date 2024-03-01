package main.java.org.example._4week;

// https://www.acmicpc.net/problem/10989
//
// 수 정렬하기 3
// 시간 제한	메모리 제한
// 5초 	    8MB
//
// 문제
//N개의 수가 주어졌을 때, 이를 오름차순으로 정렬하는 프로그램을 작성하시오.
//
// 입력
//첫째 줄에 수의 개수 N(1 ≤ N ≤ 10,000,000)이 주어진다. 둘째 줄부터 N개의 줄에는 수가 주어진다. 이 수는 10,000보다 작거나 같은 자연수이다.
//
// 출력
//첫째 줄부터 N개의 줄에 오름차순으로 정렬한 결과를 한 줄에 하나씩 출력한다.


import java.io.*;
import java.util.*;

public class AscendingSort3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 입력받을 숫자의 개수
        int[] counts = new int[10001]; // 숫자의 범위가 1~10000이므로

        for (int i = 0; i < N; i++) {
            counts[Integer.parseInt(br.readLine())]++; // 입력받은 숫자의 출현 횟수를 증가
        }

        for (int i = 1; i < counts.length; i++) {
            if (counts[i] > 0) {
                for (int j = 0; j < counts[i]; j++) {
                    bw.write(i + "\n");
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    //    public static void main(String[] args) {
//        int[] inputArray = { 10, 5, 2, 3, 1, 4, 2, 3, 5, 1, 7 };
//
//        for (int i = 0; i < inputArray.length; i++) {
//            for (int j = i + 1; j < inputArray.length; j++) {
//                if (inputArray[i] > inputArray[j]){
//                    int temp = inputArray[i];
//                    inputArray[i] = inputArray[j];
//                    inputArray[j] = temp;
//                }
//            }
//        }
//        System.out.println(Arrays.toString(inputArray));
//    }
}

