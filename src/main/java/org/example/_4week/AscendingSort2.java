package main.java.org.example._4week;

// https://www.acmicpc.net/problem/2751
//
// 수 정렬하기 2
// 시간 제한	메모리 제한
// 2초 	    256MB
//
// 문제
// N개의 수가 주어졌을 때, 이를 오름차순으로 정렬하는 프로그램을 작성하시오.
//
// 입력
// 첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)이 주어진다. 둘째 줄부터 N개의 줄에는 수가 주어진다. 이 수는 절댓값이 1,000,000보다 작거나 같은 정수이다. 수는 중복되지 않는다.
//
// 출력
// 첫째 줄부터 N개의 줄에 오름차순으로 정렬한 결과를 한 줄에 하나씩 출력한다.


import java.io.*;
import java.util.Arrays;

public class AscendingSort2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 입력받을 숫자의 개수
        int[] numbers = new int[N]; // 숫자를 저장할 배열

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(br.readLine()); // 숫자 입력받기
        }

        // Arrays.sort()는 Dual-Pivot Quicksort 알고리즘을 사용
        Arrays.sort(numbers); // 배열 정렬

        for (int number : numbers) {
            bw.write(number + "\n"); // 정렬된 숫자 출력
        }

        bw.flush(); // 남아있는 데이터를 모두 출력
        bw.close(); // BufferedWriter 스트림 닫기
        br.close(); // BufferedReader 스트림 닫기
    }
}

