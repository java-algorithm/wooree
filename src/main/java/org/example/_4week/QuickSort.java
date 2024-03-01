package main.java.org.example._4week;

// 참고: https://hello-capo.netlify.app/algorithm-quicksort/
// 퀵 정렬의 개념
// 임의의 피봇(pivot)을 기준으로 해당 피봇 값보다 작은 데이터는 피봇의 왼쪽에,
// 큰 데이터는 피봇의 오른쪽에 배치한 뒤, 왼쪽 부분과 오른쪽 부분을 다시 퀵 정렬 방법으로 정렬하는 알고리즘
//
// 전체 데이터를 2개의 부분으로 분할한 뒤, 각각의 부분을 다시 퀵정렬하는 전형적인 '분할-정복' 알고리즘

public class QuickSort {
    // 배열 arr의 i번째 요소와 j번째 요소를 '교환'하는 메소드
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 파티션(분할) 과정을 수행하고, 피벗의 최종 위치를 반환하는 메소드
    private static int partition(int[] arr, int low, int high) {
        // 피벗을 배열의 첫 번째 요소로 선택
        int pivot = arr[low];
        int i = low;
        int j = high;

        while (i < j) {
            // 왼쪽에서부터 피벗보다 큰 요소를 찾기
            while (i <= high && arr[i] <= pivot) {
                i++;
            }
            // 오른쪽에서부터 피벗보다 작은 요소를 찾기
            while (j >= low && arr[j] > pivot) {
                j--;
            }
            // 두 요소를 교환
            if (i < j) {
                swap(arr, i, j);
            }
        }
        // 피벗을 최종 위치로 이동
        swap(arr, low, j);
        return j; // 피벗의 위치 반환
    }


    // 퀵 정렬을 수행하는 메소드
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // 파티션 과정을 수행하고, 피벗의 위치를 얻습니다.
            int pivotIndex = partition(arr, low, high);

            // 피벗을 기준으로 왼쪽 부분 배열 정렬
            quickSort(arr, low, pivotIndex - 1);
            // 피벗을 기준으로 오른쪽 부분 배열 정렬
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    // 배열을 출력하는 메소드
    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    // 메인 메소드
    public static void main(String[] args) {
        int[] arr = {8, 7, 6, 1, 0, 9, 2};
        System.out.println("Original array:");
        printArray(arr);

        // 정렬
        quickSort(arr, 0, arr.length - 1);

        System.out.println("Sorted array:");
        printArray(arr);
    }
}

