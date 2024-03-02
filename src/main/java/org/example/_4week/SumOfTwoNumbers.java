package main.java.org.example._4week;

// https://www.acmicpc.net/problem/3273
//
// 수 정렬하기 3
// 시간 제한	메모리 제한
// 1초 	    128MB
//
// 문제
// n개의 서로 다른 양의 정수 a1, a2, ..., an으로 이루어진 수열이 있다. ai의 값은 1보다 크거나 같고, 1000000보다 작거나 같은 자연수이다. 자연수 x가 주어졌을 때, ai + aj = x (1 ≤ i < j ≤ n)을 만족하는 (ai, aj)쌍의 수를 구하는 프로그램을 작성하시오.
//
// 입력
// 첫째 줄에 수열의 크기 n이 주어진다. 다음 줄에는 수열에 포함되는 수가 주어진다. 셋째 줄에는 x가 주어진다. (1 ≤ n ≤ 100000, 1 ≤ x ≤ 2000000)
//
// 출력
// 문제의 조건을 만족하는 쌍의 개수를 출력한다.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

// 문제 해결 방법
// 1. 주어진 수열을 오름차순으로 정렬
// 2. 수열의 시작 부분을 가리키는 (start), 수열의 끝 부분을 가리키는 (end) 초기화.
// 3. start, end 가 가리키는 두 수의 합을 계산하고, 그 합(sum)이 주어진 값 x와 같은지, 더 큰지, 더 작은지에 따라 start end 값 조정.
// 3-1. 합이 x보다 작으면 start 를 오른쪽으로 이동시켜 합을 증가시켜서 비교.
// 3-2. 합이 x보다 크면 end 를 왼쪽으로 이동시켜 합을 감소시켜 비교.
// 3-3. 합이 x와 같으면 카운트 증가, start++ end-- 조정.

// 예제
// 9
// 5 12 7 10 9 1 2 3 11
// 13

public class SumOfTwoNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 수열의 크기
        int[] arr = new int[n]; // 수열을 저장할 배열

        String[] numberStrings = br.readLine().split(" "); // 공백을 기준으로 문자열을 나눔
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(numberStrings[i]);
        }

        int x = Integer.parseInt(br.readLine()); // 찾고자 하는 합

        Arrays.sort(arr); // 수열 오름차순 정렬, 기본 함수 사용

        int count = 0; // 조건을 만족하는 쌍의 수
        int start = 0, end = n - 1; // 투 포인터 초기화
        // { 5, 12, 7, 10, 9, 1, 2, 3, 11 }
        // start ->        |         <- end

        while (start < end) {
            int sum = arr[start] + arr[end];

            if (sum == x) {
                count++;
                start++;
                end--;
            } else if (sum < x) {
                start++;
            } else {
                end--;
            }
        }

        System.out.println(count);
    }
}

