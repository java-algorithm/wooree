package main.java.org.example._5week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 이진 탐색은 정렬된 리스트에서 검색 범위를 줄여 나가면서 검색 값을 찾는 알고리즘
// 이진 탐색은 정렬된 리스트에만 사용할 수 있다는 단점이 있지만,
// 검색이 반복될 때마다 검색 범위가 절반으로 줄기 때문에 속도가 빠르다는 장점이 있다.

public class BinarySearch {
    // 이진 탐색 메소드
    public static int binarySearch(int[] arr, int target) {
        int left = 0; // 검색 범위의 시작 인덱스
        int right = arr.length - 1; // 검색 범위의 끝 인덱스

        while (left <= right) {
            int mid = (left + right) / 2; // 중간 인덱스

            if (arr[mid] == target) {
                // 중간값이 타겟과 일치하는 경우, 타겟의 인덱스 반환
                return mid;
            } else if (arr[mid] < target) {
                // 중간값이 타겟보다 작은 경우, 왼쪽 검색 범위를 버리고 오른쪽 검색 범위 조정
                left = mid + 1;
            } else {
                // 중간값이 타겟보다 큰 경우, 오른쪽 검색 범위를 버리고 왼쪽 검색 범위 조정
                right = mid - 1;
            }
        }

        // 타겟을 찾지 못한 경우, -1 반환
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 8, 10, 12, 14, 16, 18}; // 정렬된 배열
        int target = 10; // 찾고자 하는 값

        int result = binarySearch(arr, target); // 이진 탐색 실행

        if (result != -1) {
            System.out.println("찾고자 하는 인덱스: " + result);
        } else {
            System.out.println("찾고자 하는 값이 없음");
        }
    }
}

