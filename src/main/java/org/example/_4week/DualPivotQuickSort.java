package main.java.org.example._4week;

// 피봇 1개를 기준으로 삼아 정렬하는 퀵 정렬에서 나아가 피봇 2개를 기준으로 정렬하는 알고리즘
// 임의의 피봇 2개를 기준으로 pivot1 보다 작은 부분, pivot1 ~ pivot2 사이의 부분, pivot2 보다 큰 부분으로 나눈 뒤 각 부분을 다시 듀얼 피봇 퀵 정렬 방법으로 정렬한다.
// pivot2는 항상 pivot1보다 크도록 설정해야함을 주의한다.


public class DualPivotQuickSort {
    // Dual Pivot Quick Sort 함수
    public static void dualPivotQuickSort(int[] arr, int low, int high) {
        // 배열(arr), 시작 인덱스(low), 끝 인덱스(high)
        // 배열의 첫 번째 요소와 마지막 요소를 피벗으로 사용
        if (low < high) {
            // 피벗들을 정렬하고, 위치를 조정
            if (arr[low] > arr[high])
                swap(arr, low, high); // swap()은 배열 내의 두 요소의 위치를 교환.
            int pivot1 = arr[low];
            int pivot2 = arr[high];

            int lt = low + 1;
            int gt = high - 1;
            int i = low + 1;
            while (i <= gt) {
                if (arr[i] < pivot1) {
                    swap(arr, i++, lt++);
                } else if (arr[i] > pivot2) {
                    swap(arr, i, gt--);
                } else {
                    i++;
                }
            }

            // 피벗을 올바른 위치로 이동
            swap(arr, low, --lt);
            swap(arr, high, ++gt);

            // 재귀적으로 각 세그먼트를 정렬
            dualPivotQuickSort(arr, low, lt - 1);
            dualPivotQuickSort(arr, lt + 1, gt - 1);
            dualPivotQuickSort(arr, gt + 1, high);
        }
    }

    // 배열의 두 요소를 교환하는 함수
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // 메인 함수
    public static void main(String[] args) {
        int[] arr = {24, 8, 42, 75, 29, 77, 38, 57};
        System.out.println("Original array:");
        printArray(arr);

        dualPivotQuickSort(arr, 0, arr.length - 1);

        System.out.println("Sorted array:");
        printArray(arr);
    }
}

