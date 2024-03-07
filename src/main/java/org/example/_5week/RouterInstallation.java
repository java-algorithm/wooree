package main.java.org.example._5week;

// https://www.acmicpc.net/problem/2110
//
// 공유기 설치
// 시간 제한	메모리 제한
// 2초 	    128MB
//
// 문제
// 도현이의 집 N개가 수직선 위에 있다. 각각의 집의 좌표는 x1, ..., xN이고, 집 여러개가 같은 좌표를 가지는 일은 없다.
// 도현이는 언제 어디서나 와이파이를 즐기기 위해서 집에 공유기 C개를 설치하려고 한다.
// 최대한 많은 곳에서 와이파이를 사용하려고 하기 때문에, 한 집에는 공유기를 하나만 설치할 수 있고,
// 가장 인접한 두 공유기 사이의 거리를 가능한 크게 하여 설치하려고 한다.
// C개의 공유기를 N개의 집에 적당히 설치해서, 가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램을 작성하시오.
//
// 입력
// 첫째 줄에 집의 개수 N (2 ≤ N ≤ 200,000)과 공유기의 개수 C (2 ≤ C ≤ N)이 하나 이상의 빈 칸을 사이에 두고 주어진다.
// 둘째 줄부터 N개의 줄에는 집의 좌표를 나타내는 xi (0 ≤ xi ≤ 1,000,000,000)가 한 줄에 하나씩 주어진다.
//
// 출력
// 첫째 줄에 가장 인접한 두 공유기 사이의 최대 거리를 출력한다.

import java.io.*;
import java.util.*;

// 문제 해결 방법
// 공유기 사이의 최대 거리를 이진 탐색을 통해 찾아낸다.
//
// 이진 탐색 범위 설정: 최소 거리 min을 1로, 최대 거리 max를 가장 먼 두 집의 거리로 설정
// 이진 탐색 실행: min과 max의 중간값 mid를 공유기 사이의 거리로 가정하고, 이 거리를 유지하면서 C개의 공유기를 설치할 수 있는지 확인
// 조건 확인 및 범위 조정:
// C개 이상의 공유기를 설치할 수 있다면, 거리를 늘릴 여지가 있으므로 min을 조정
// C개를 설치할 수 없다면, 거리를 줄여야 하므로 max를 조정
// 결과 도출: min과 max가 만나는 지점이 공유기 사이의 최대 거리

public class RouterInstallation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 집의 개수
        int C = Integer.parseInt(st.nextToken()); // 공유기의 개수
        int[] houses = new int[N]; // 각 집의 위치

        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(houses); // 집의 위치를 오름차순으로 정렬

        int left = 1; // 가능한 최소 거리
        int right = houses[N - 1] - houses[0]; // 가능한 최대 거리
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2; // 가정한 공유기 사이의 거리
            int installed = 1; // 설치된 공유기 수 (첫 번째 집에 설치한다고 가정)
            int lastInstalled = houses[0]; // 마지막으로 설치된 공유기의 위치

            for (int i = 1; i < N; i++) {
                if (houses[i] - lastInstalled >= mid) {
                    installed++;
                    lastInstalled = houses[i];
                }
            }

            if (installed >= C) { // C개 이상의 공유기를 설치할 수 있는 경우
                left = mid + 1;
                result = mid; // 공유기 사이의 거리를 갱신
            } else { // C개를 설치할 수 없는 경우
                right = mid - 1;
            }
        }

        System.out.println(result); // 공유기 사이의 최대 거리 출력
    }
}

