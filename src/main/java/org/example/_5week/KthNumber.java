package main.java.org.example._5week;

// https://www.acmicpc.net/problem/1300
//
// K번째 수
// 시간 제한	메모리 제한
// 2초 	    128MB
//
// 문제
// 세준이는 크기가 N×N인 배열 A를 만들었다. 배열에 들어있는 수 A[i][j] = i×j 이다.
// 이 수를 일차원 배열 B에 넣으면 B의 크기는 N×N이 된다. B를 오름차순 정렬했을 때, B[k]를 구해보자.
// 배열 A와 B의 인덱스는 1부터 시작한다.
//
// 입력
// 첫째 줄에 배열의 크기 N이 주어진다. N은 105보다 작거나 같은 자연수이다. 둘째 줄에 k가 주어진다. k는 min(109, N2)보다 작거나 같은 자연수이다.
//
// 출력
// B[k]를 출력한다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 문제 해결 방법
// 배열 A의 특성상 직접 배열을 생성하지 않고도 K번째 수를 찾을 수 있다.
//
// 이진 탐색 범위 설정: 최소값 min을 1로, 최대값 max를 K로 설정합니다. K번째 수는 K보다 클 수 없기 때문.
// 이진 탐색 실행: min과 max의 중간값 mid를 구하고, mid보다 작거나 같은 수가 배열 A에서 몇 개 있는지 계산.
// K번째 수 판별:
// mid보다 작거나 같은 수의 개수가 K보다 작다면, min을 mid + 1로 조정.
// mid보다 작거나 같은 수의 개수가 K보다 크거나 같다면, max를 mid - 1로 조정.
// 결과 도출: min과 max가 만나는 지점이 K번째 수이다.

public class KthNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 배열의 크기 N
        int K = Integer.parseInt(br.readLine()); // 찾고자 하는 K번째 수

        long min = 1, max = K; // 이진 탐색을 위한 초기 설정
        long answer = 0; // K번째 수를 저장할 변수

        while (min <= max) {
            long mid = (min + max) / 2; // 중간값
            long count = 0; // mid보다 작거나 같은 수의 개수

            // mid보다 작거나 같은 수의 개수를 계산
            for (int i = 1; i <= N; i++) {
                count += Math.min(mid / i, N); // i번째 행에서 mid보다 작거나 같은 수의 개수
            }

            // 이진 탐색 조건에 따라 min, max 조정
            if (count < K) {
                min = mid + 1;
            } else {
                answer = mid; // 가능한 K번째 수 후보 갱신
                max = mid - 1;
            }
        }

        System.out.println(answer); // K번째 수 출력
    }
}

