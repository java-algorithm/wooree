package main.java.org.example._4week;

// https://www.acmicpc.net/problem/7453
//
// 합이 0인 네 정수
// 시간 제한	메모리 제한
// 12초 	    1024MB
//
// 문제
// 정수로 이루어진 크기가 같은 배열 A, B, C, D가 있다.
//
// A[a], B[b], C[c], D[d]의 합이 0인 (a, b, c, d) 쌍의 개수를 구하는 프로그램을 작성하시오.
//
// 입력
// 첫째 줄에 배열의 크기 n (1 ≤ n ≤ 4000)이 주어진다. 다음 n개 줄에는 A, B, C, D에 포함되는 정수가 공백으로 구분되어져서 주어진다. 배열에 들어있는 정수의 절댓값은 최대 228이다.
//
// 출력
// 합이 0이 되는 쌍의 개수를 출력한다.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

// 문제 해결 방법
// 두 배열의 모든 가능한 합 계산: A와 B의 모든 요소의 합, C와 D의 모든 요소의 합을 각각 계산하여 두 개의 리스트에 저장.
//
// 리스트 정렬: 계산된 두 리스트를 각각 정렬.
//
// 투 포인터 알고리즘 사용: A와 B의 합을 저장한 리스트를 순회하면서, 해당 값의 반대 부호를 C와 D의 합 리스트에서 찾기. 이때, 투 포인터 알고리즘을 사용하여 효율적으로 탐색.
//
// 카운트: A와 B의 합에 대해, C와 D의 합 리스트에서 해당 합의 반대 부호를 가진 요소의 개수를 찾아 카운트.

public class SumToZero {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] A = new long[n];
        long[] B = new long[n];
        long[] C = new long[n];
        long[] D = new long[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Long.parseLong(st.nextToken());
            B[i] = Long.parseLong(st.nextToken());
            C[i] = Long.parseLong(st.nextToken());
            D[i] = Long.parseLong(st.nextToken());
        }

        long[] AB = new long[n * n];
        long[] CD = new long[n * n];

        // 네 개의 배열 A, B, C, D에서 모든 가능한 두 수의 합을 계산하여 AB와 CD 배열에 저장
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                AB[i * n + j] = A[i] + B[j];
                CD[i * n + j] = C[i] + D[j];
            }
        }

        // AB와 CD 배열을 정렬
        Arrays.sort(AB);
        Arrays.sort(CD);

        long count = 0;
        int ptA = 0;
        int ptC = CD.length - 1;

        // 투 포인터 알고리즘을 사용하여, AB 배열의 각 요소에 대해 CD 배열에서 합이 0이 되는 요소를 찾기
        while (ptA < AB.length && ptC >= 0) {
            long currentAB = AB[ptA];
            long currentCD = CD[ptC];
            long sum = currentAB + currentCD;

            if (sum == 0) {
                long countAB = 0;
                while (ptA < AB.length && AB[ptA] == currentAB) {
                    ptA++;
                    countAB++;
                }

                long countCD = 0;
                while (ptC >= 0 && CD[ptC] == currentCD) {
                    ptC--;
                    countCD++;
                }

                // 합이 0이 되는 경우, 해당 합을 구성하는 요소들의 개수를 곱하여 최종 카운트에 더함. 이는 중복된 값들을 처리하기 위함
                // 예시
                // A와 B의 합 리스트: [-2, -2, 1, 1, 1]
                // C와 D의 합 리스트: [-1, -1, 2, 2]
                //
                // A와 B의 합이 -2인 경우, C와 D의 합에서 2를 찾을 수 있고, 각각의 개수는 2와 2이므로, 이 경우의 수는 2 * 2 = 4
                // A와 B의 합이 1인 경우, C와 D의 합에서 -1을 찾을 수 있고, 각각의 개수는 3과 2이므로, 이 경우의 수는 3 * 2 = 6
                count += countAB * countCD;
            } else if (sum > 0) {
                ptC--;
            } else {
                ptA++;
            }
        }

        System.out.println(count);
    }
}

