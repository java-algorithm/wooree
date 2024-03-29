package main.java.org.example._5week;

// https://www.acmicpc.net/problem/2805
//
// 나무 자르기
// 시간 제한	메모리 제한
// 1초 	    256MB
//
// 문제
// 상근이는 나무 M미터가 필요하다. 근처에 나무를 구입할 곳이 모두 망해버렸기 때문에, 정부에 벌목 허가를 요청했다.
// 정부는 상근이네 집 근처의 나무 한 줄에 대한 벌목 허가를 내주었고, 상근이는 새로 구입한 목재절단기를 이용해서 나무를 구할것이다.
//
// 목재절단기는 다음과 같이 동작한다. 먼저, 상근이는 절단기에 높이 H를 지정해야 한다.
// 높이를 지정하면 톱날이 땅으로부터 H미터 위로 올라간다. 그 다음, 한 줄에 연속해있는 나무를 모두 절단해버린다.
// 따라서, 높이가 H보다 큰 나무는 H 위의 부분이 잘릴 것이고, 낮은 나무는 잘리지 않을 것이다.
// 예를 들어, 한 줄에 연속해있는 나무의 높이가 20, 15, 10, 17이라고 하자.
// 상근이가 높이를 15로 지정했다면, 나무를 자른 뒤의 높이는 15, 15, 10, 15가 될 것이고,
// 상근이는 길이가 5인 나무와 2인 나무를 들고 집에 갈 것이다.
// (총 7미터를 집에 들고 간다) 절단기에 설정할 수 있는 높이는 양의 정수 또는 0이다.
//
// 상근이는 환경에 매우 관심이 많기 때문에, 나무를 필요한 만큼만 집으로 가져가려고 한다.
// 이때, 적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값을 구하는 프로그램을 작성하시오.
//
// 입력
// 첫째 줄에 나무의 수 N과 상근이가 집으로 가져가려고 하는 나무의 길이 M이 주어진다. (1 ≤ N ≤ 1,000,000, 1 ≤ M ≤ 2,000,000,000)
// 둘째 줄에는 나무의 높이가 주어진다. 나무의 높이의 합은 항상 M보다 크거나 같기 때문에, 상근이는 집에 필요한 나무를 항상 가져갈 수 있다.
// 높이는 1,000,000,000보다 작거나 같은 양의 정수 또는 0이다.
//
// 출력
// 적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값을 출력한다.

import java.io.*;
import java.util.*;

// 문제 해결 방법
// 이진 탐색을 위한 범위 설정: 나무를 자를 수 있는 높이(H)의 최솟값을 0, 최댓값을 가장 높은 나무의 높이로 설정.
// 이진 탐색 실행: 최솟값과 최댓값의 중간값(mid)을 H로 설정하고, 모든 나무를 이 높이로 잘랐을 때 얻을 수 있는 나무의 길이의 합을 계산.
// 목표 길이(M)와 비교: 잘린 나무의 총 길이가 M보다 크거나 같다면, H를 더 높일 수 있는 가능성이 있으므로, 최솟값을 mid + 1로 조정.
// 만약 M보다 작다면, 최댓값을 mid - 1로 조정합니다.
// 최적의 H 찾기: 위의 과정을 반복하면서 최적의 H 값을 찾기.

public class CuttingTrees {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 나무의 수 N과 필요한 나무의 길이 M을 입력받음
        int N = Integer.parseInt(st.nextToken()); // 나무의 수
        long M = Long.parseLong(st.nextToken()); // 필요한 나무의 길이

        long[] trees = new long[N]; // 나무의 높이를 저장할 배열
        st = new StringTokenizer(br.readLine());
        long max = 0; // 가장 높은 나무의 높이를 저장할 변수

        // 나무의 높이를 입력받고, 가장 높은 나무의 높이를 찾음
        for (int i = 0; i < N; i++) {
            trees[i] = Long.parseLong(st.nextToken());
            if (trees[i] > max) {
                max = trees[i]; // 가장 높은 나무의 높이 갱신
            }
        }

        long min = 0; // 이진 탐색을 위한 최소 높이
        long result = 0; // 최종 결과값(절단기의 최대 높이)을 저장할 변수

        // 이진 탐색 시작
        while (min <= max) {
            long mid = (min + max) / 2; // 현재 시도하는 절단기의 높이
            long sum = 0; // 잘린 나무의 총 길이를 저장할 변수

            // 모든 나무를 현재 절단기 높이(mid)로 잘라보고, 잘린 나무의 총 길이를 계산
            for (long tree : trees) {
                if (tree > mid) {
                    sum += tree - mid; // 나무가 절단기 높이보다 높을 경우, 차이만큼을 sum에 더함
                }
            }

            // 잘린 나무의 총 길이가 필요한 길이 M 이상인 경우
            if (sum >= M) {
                min = mid + 1; // 절단기의 높이를 더 높일 수 있음을 의미, min을 조정
                result = mid; // 현재 mid 값이 조건을 만족하므로, 결과값 갱신
            } else { // 잘린 나무의 길이가 M에 미치지 못하는 경우
                max = mid - 1; // 절단기의 높이를 낮춰야 함, max를 조정
            }
        }

        System.out.println(result); // 최종 계산된 절단기의 최대 높이를 출력
    }
}

