package main.java.org.example._5week;

// https://www.acmicpc.net/problem/2512
//
// 예산
// 시간 제한	메모리 제한
// 1초 	    128MB
//
// 문제
// 국가의 역할 중 하나는 여러 지방의 예산요청을 심사하여 국가의 예산을 분배하는 것이다.
// 국가예산의 총액은 미리 정해져 있어서 모든 예산요청을 배정해 주기는 어려울 수도 있다.
// 그래서 정해진 총액 이하에서 가능한 한 최대의 총 예산을 다음과 같은 방법으로 배정한다.
//
// 모든 요청이 배정될 수 있는 경우에는 요청한 금액을 그대로 배정한다.
// 모든 요청이 배정될 수 없는 경우에는 특정한 정수 상한액을 계산하여 그 이상인 예산요청에는 모두 상한액을 배정한다.
// 상한액 이하의 예산요청에 대해서는 요청한 금액을 그대로 배정한다.
// 예를 들어, 전체 국가예산이 485이고 4개 지방의 예산요청이 각각 120, 110, 140, 150이라고 하자.
// 이 경우, 상한액을 127로 잡으면, 위의 요청들에 대해서 각각 120, 110, 127, 127을 배정하고 그 합이 484로 가능한 최대가 된다.
//
// 여러 지방의 예산요청과 국가예산의 총액이 주어졌을 때, 위의 조건을 모두 만족하도록 예산을 배정하는 프로그램을 작성하시오.
//
// 입력
// 첫째 줄에는 지방의 수를 의미하는 정수 N이 주어진다. N은 3 이상 10,000 이하이다.
// 다음 줄에는 각 지방의 예산요청을 표현하는 N개의 정수가 빈칸을 사이에 두고 주어진다.
// 이 값들은 모두 1 이상 100,000 이하이다. 그 다음 줄에는 총 예산을 나타내는 정수 M이 주어진다.
// M은 N 이상 1,000,000,000 이하이다.
//
// 출력
// 첫째 줄에는 배정된 예산들 중 최댓값인 정수를 출력한다.

import java.io.*;
import java.util.*;

// 문제 해결 방법
// 예산 요청의 상한액을 이진 탐색을 통해 찾아나간다.
// 이진 탐색의 범위 설정: 상한액의 최소값을 0, 최대값을 예산 요청 중 최대값으로 설정
// 이진 탐색 실행: 상한액을 이진 탐색으로 조절하면서, 설정된 상한액으로 모든 지방의 예산 요청을 충족시키거나
// 국가 예산 내에서 최대한 많은 예산을 배분할 수 있는지 확인
// 조건 확인 및 범위 조정:
// 설정된 상한액으로 모든 지방의 예산 요청을 충족시키고 예산이 남는 경우, 상한액을 높일 수 있는 여지가 있으므로, 검색 범위의 최소값을 조정
// 설정된 상한액으로 국가 예산을 초과하는 경우, 상한액을 낮춰야 하므로, 검색 범위의 최대값을 조정
// 최적의 상한액 찾기: 위의 과정을 반복하면서, 국가 예산을 초과하지 않는 범위 내에서 가능한 최대의 상한액을 찾는다.

public class Budget {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 지방의 수
        int[] requests = new int[N]; // 각 지방의 예산 요청

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            requests[i] = Integer.parseInt(st.nextToken()); // 예산 요청 입력
        }

        int totalBudget = Integer.parseInt(br.readLine()); // 총 예산

        // 이진 탐색을 위한 초기 설정
        int left = 0; // 최소 예산 상한액
        int right = Arrays.stream(requests).max().getAsInt(); // 최대 예산 요청액
        int result = 0; // 최적의 예산 상한액

        while (left <= right) {
            int mid = (left + right) / 2; // 현재 예산 상한액 가정
            long sum = 0; // 현재 상한액으로 배정될 총 예산 계산

            for (int request : requests) {
                if (request > mid) {
                    sum += mid; // 상한액보다 큰 요청에는 상한액만큼만 배정
                } else {
                    sum += request; // 그 외에는 요청액 그대로 배정
                }
            }

            if (sum > totalBudget) {
                // 총 예산을 초과하는 경우, 상한액을 낮춰야 함
                right = mid - 1;
            } else {
                // 총 예산 내에서 가능한 경우, 상한액을 높일 수 있음
                left = mid + 1;
                result = mid; // 현재 상한액을 결과로 저장
            }
        }

        System.out.println(result); // 최적의 예산 상한액 출력
    }
}

