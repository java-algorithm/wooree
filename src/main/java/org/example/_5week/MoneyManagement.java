package main.java.org.example._5week;

// https://www.acmicpc.net/problem/6236
//
// 용돈 관리
// 시간 제한	메모리 제한
// 1초 	    128MB
//
// 문제
// 현우는 용돈을 효율적으로 활용하기 위해 계획을 짜기로 하였다. 현우는 앞으로 N일 동안 자신이 사용할 금액을 계산하였고,
// 돈을 펑펑 쓰지 않기 위해 정확히 M번만 통장에서 돈을 빼서 쓰기로 하였다. 현우는 통장에서 K원을 인출하며,
// 통장에서 뺀 돈으로 하루를 보낼 수 있으면 그대로 사용하고, 모자라게 되면 남은 금액은 통장에 집어넣고 다시 K원을 인출한다.
// 다만 현우는 M이라는 숫자를 좋아하기 때문에, 정확히 M번을 맞추기 위해서 남은 금액이 그날 사용할 금액보다 많더라도
// 남은 금액은 통장에 집어넣고 다시 K원을 인출할 수 있다. 현우는 돈을 아끼기 위해 인출 금액 K를 최소화하기로 하였다.
// 현우가 필요한 최소 금액 K를 계산하는 프로그램을 작성하시오.
//
// 입력
// 1번째 줄에는 N과 M이 공백으로 주어진다. (1 ≤ N ≤ 100,000, 1 ≤ M ≤ N)
//
// 2번째 줄부터 총 N개의 줄에는 현우가 i번째 날에 이용할 금액이 주어진다. (1 ≤ 금액 ≤ 10000)
//
// 출력
// 첫 번째 줄에 현우가 통장에서 인출해야 할 최소 금액 K를 출력한다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 문제 해결 방법
// 이진 탐색 범위 설정: 최소 금액 K의 최솟값을 하루 최대 사용 금액으로, 최댓값을 모든 일자의 사용 금액 합으로 설정
// 이진 탐색 실행: 최솟값과 최댓값의 중간값(mid)을 K로 가정하고, 이 금액으로 N일 동안 생활할 수 있는지 확인
// 여기서 K는 한 번 인출할 때마다 인출하는 금액을 의미
// 조건 확인 및 범위 조정:
// 만약 해당 금액으로 N일 동안 M번 이내의 인출로 생활이 가능하다면, K를 줄일 수 있는 가능성이 있으므로 최댓값을 mid로 조정
// 만약 불가능하다면, K를 늘려야 하므로 최솟값을 mid + 1로 조정
// 최적의 K 찾기: 위의 과정을 반복하며 최적의 K 값을 찾는다.

public class MoneyManagement {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 총 일수
        int M = Integer.parseInt(st.nextToken()); // 인출 횟수
        int[] expenses = new int[N]; // 각 일자별 사용 금액

        int left = 0; // 이진 탐색의 최소값
        int right = 0; // 이진 탐색의 최대값

        for (int i = 0; i < N; i++) {
            expenses[i] = Integer.parseInt(br.readLine());
            right += expenses[i]; // 모든 금액의 합을 최대값으로 설정
            left = Math.max(left, expenses[i]); // 가장 큰 지출을 최소값으로 설정
        }

        // 이진 탐색을 통해 최소 인출 금액 K 찾기
        while (left <= right) {
            int mid = (left + right) / 2; // 가정한 인출 금액
            if (isPossible(expenses, mid, M)) { // mid 금액으로 M번 이내에 N일 동안 생활 가능한지 확인
                right = mid - 1; // 가능하다면, 인출 금액을 줄일 수 있음
            } else {
                left = mid + 1; // 불가능하다면, 인출 금액을 늘려야 함
            }
        }

        System.out.println(left); // 최소 인출 금액 출력
    }

    // 주어진 인출 금액으로 M번 이내의 인출로 N일 동안 생활이 가능한지 확인하는 함수
    public static boolean isPossible(int[] expenses, int money, int M) {
        int count = 1; // 인출 횟수 (처음에 이미 인출했다고 가정)
        int currentMoney = money; // 현재 가지고 있는 돈

        for (int expense : expenses) {
            if (currentMoney < expense) { // 현재 가진 돈으로 지출할 수 없는 경우
                currentMoney = money; // 다시 인출
                count++; // 인출 횟수 증가
            }
            currentMoney -= expense; // 지출 처리
        }

        return count <= M; // 인출 횟수가 M번 이내인지 확인
    }
}

