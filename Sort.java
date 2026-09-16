import java.util.*;

public class Sort {

    // ===================== Helper Methods =====================

    public static void printArray(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + (i < arr.length - 1 ? " " : ""));
        }
        System.out.println();
    }

    // Merge Sort (Time Complexity = O(nlog(n)), Space Complexity = O(n))
    public static void mergeSort(int arr[], int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(int arr[], int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int leftArr[] = new int[n1];
        int rightArr[] = new int[n2];
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int i = 0; i < n2; i++) {
            rightArr[i] = arr[mid + 1 + i];
        }
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    // Quick Sort (Time Complexity = O(nlog(n)), Space Complexity = O(log(n)))
    public static void quickSort(int arr[], int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Heap Sort (Time Complexity = O(nlog(n)), Space Complexity = O(1))
    public static void heapSort(int arr[]) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    public static void heapify(int arr[], int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    // Bubble Sort (Time Complexity = O(n^2), Space Complexity = O(1))
    public static void bubbleSort(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Selection Sort (Time Complexity = O(n^2), Space Complexity = O(1))
    public static void selectionSort(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    // Insertion Sort (Time Complexity = O(n^2), Space Complexity = O(1))
    public static void insertionSort(int arr[]) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    // Shell Sort (Time Complexity = O(nlog(n)), Space Complexity = O(1))
    public static void shellSort(int arr[]) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    // Counting Sort (Time Complexity = O(n+k), Space Complexity = O(k))
    public static void countingSort(int arr[]) {
        int n = arr.length;
        if (n <= 1) return;
        int max = arr[0], min = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) max = arr[i];
            if (arr[i] < min) min = arr[i];
        }
        int range = max - min + 1;
        int[] count = new int[range];
        for (int i = 0; i < n; i++) {
            count[arr[i] - min]++;
        }
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }
        int[] output = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    // Radix Sort (Time Complexity = O(nk), Space Complexity = O(n+k))
    public static void radixSort(int arr[]) {
        int n = arr.length;
        if (n <= 1) return;
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    public static void countingSort(int arr[], int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];
        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    // Bucket Sort (Time Complexity = O(n+k), Space Complexity = O(n+k))
    public static void bucketSort(int arr[]) {
        int n = arr.length;
        if (n <= 1) return;
        int max = arr[0], min = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) max = arr[i];
            if (arr[i] < min) min = arr[i];
        }
        int bucketCount = (int) Math.sqrt(n);
        if (bucketCount == 0) bucketCount = 1;
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }
        double range = (double) (max - min + 1) / bucketCount;
        for (int i = 0; i < n; i++) {
            int bucketIdx = (int) ((arr[i] - min) / range);
            if (bucketIdx >= bucketCount) bucketIdx = bucketCount - 1;
            buckets.get(bucketIdx).add(arr[i]);
        }
        int idx = 0;
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
            for (int val : bucket) {
                arr[idx++] = val;
            }
        }
    }

    // Timsort (Time Complexity = O(nlog(n)), Space Complexity = O(n))
    public static void timsort(int arr[]) {
        int n = arr.length;
        int run = 32;
        for (int i = 0; i < n; i += run) {
            insertionSort(arr, i, Math.min((i + run - 1), (n - 1)));
        }
        for (int size = run; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));
                if (mid < right) {
                    merge(arr, left, mid, right);
                }
            }
        }
    }

    public static void insertionSort(int arr[], int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    // Comb Sort (Time Complexity = O(n^2), Space Complexity = O(1))
    public static void combSort(int arr[]) {
        int n = arr.length;
        int gap = n;
        boolean swapped = true;
        while (gap != 1 || swapped) {
            gap = (gap * 10) / 13;
            if (gap < 1) {
                gap = 1;
            }
            swapped = false;
            for (int i = 0; i < n - gap; i++) {
                if (arr[i] > arr[i + gap]) {
                    int temp = arr[i];
                    arr[i] = arr[i + gap];
                    arr[i + gap] = temp;
                    swapped = true;
                }
            }
        }
    }

    // Gnome Sort (Time Complexity = O(n^2), Space Complexity = O(1))
    public static void gnomeSort(int arr[]) {
        int n = arr.length;
        int index = 0;
        while (index < n) {
            if (index == 0) {
                index++;
            }
            if (arr[index] >= arr[index - 1]) {
                index++;
            } else {
                int temp = arr[index];
                arr[index] = arr[index - 1];
                arr[index - 1] = temp;
                index--;
            }
        }
    }

    // Binary Insertion Sort (Time Complexity = O(n^2), Space Complexity = O(1))
    public static void binaryInsertionSort(int arr[]) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int low = 0, high = i - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (key < arr[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            for (int j = i - 1; j >= low; j--) {
                arr[j + 1] = arr[j];
            }
            arr[low] = key;
        }
    }

    // ===================== Main =====================

    public static void main(String[] args) {
        int arr[] = {10, 4, 1, 6, 8, 32, 96, 50, 36, 78, 88, 57, 68, 34};
        System.out.print("Original Array: ");
        printArray(arr);
        System.out.println();

        // Merge Sort (Time Complexity = O(nlog(n)), Space Complexity = O(n))
        int a1[] = arr.clone();
        mergeSort(a1, 0, a1.length - 1);
        System.out.print("Merge Sort: ");
        printArray(a1);
        System.out.println("Merge Sort Successfull");
        System.out.println();

        // Quick Sort (Time Complexity = O(nlog(n)), Space Complexity = O(log(n)))
        int a2[] = arr.clone();
        quickSort(a2, 0, a2.length - 1);
        System.out.print("Quick Sort: ");
        printArray(a2);
        System.out.println("Quick Sort Successfull");
        System.out.println();

        // Heap Sort (Time Complexity = O(nlog(n)), Space Complexity = O(1))
        int a3[] = arr.clone();
        heapSort(a3);
        System.out.print("Heap Sort: ");
        printArray(a3);
        System.out.println("Heap Sort Successfull");
        System.out.println();

        // Bubble Sort (Time Complexity = O(n^2), Space Complexity = O(1))
        int a4[] = arr.clone();
        bubbleSort(a4);
        System.out.print("Bubble Sort: ");
        printArray(a4);
        System.out.println("Bubble Sort Successfull");
        System.out.println();

        // Selection Sort (Time Complexity = O(n^2), Space Complexity = O(1))
        int a5[] = arr.clone();
        selectionSort(a5);
        System.out.print("Selection Sort: ");
        printArray(a5);
        System.out.println("Selection Sort Successfull");
        System.out.println();

        // Insertion Sort (Time Complexity = O(n^2), Space Complexity = O(1))
        int a6[] = arr.clone();
        insertionSort(a6);
        System.out.print("Insertion Sort: ");
        printArray(a6);
        System.out.println("Insertion Sort Successfull");
        System.out.println();

        // Shell Sort (Time Complexity = O(nlog(n)), Space Complexity = O(1))
        int a7[] = arr.clone();
        shellSort(a7);
        System.out.print("Shell Sort: ");
        printArray(a7);
        System.out.println("Shell Sort Successfull");
        System.out.println();

        // Counting Sort (Time Complexity = O(n+k), Space Complexity = O(k))
        int a8[] = arr.clone();
        countingSort(a8);
        System.out.print("Counting Sort: ");
        printArray(a8);
        System.out.println("Counting Sort Successfull");
        System.out.println();

        // Radix Sort (Time Complexity = O(nk), Space Complexity = O(n+k))
        int a9[] = arr.clone();
        radixSort(a9);
        System.out.print("Radix Sort: ");
        printArray(a9);
        System.out.println("Radix Sort Successfull");
        System.out.println();

        // Bucket Sort (Time Complexity = O(n+k), Space Complexity = O(n+k))
        int a10[] = arr.clone();
        bucketSort(a10);
        System.out.print("Bucket Sort: ");
        printArray(a10);
        System.out.println("Bucket Sort Successfull");
        System.out.println();

        // Timsort (Time Complexity = O(nlog(n)), Space Complexity = O(n))
        int a11[] = arr.clone();
        timsort(a11);
        System.out.print("Timsort: ");
        printArray(a11);
        System.out.println("Timsort Successfull");
        System.out.println();

        // Comb Sort (Time Complexity = O(n^2), Space Complexity = O(1))
        int a12[] = arr.clone();
        combSort(a12);
        System.out.print("Comb Sort: ");
        printArray(a12);
        System.out.println("Comb Sort Successfull");
        System.out.println();

        // Gnome Sort (Time Complexity = O(n^2), Space Complexity = O(1))
        int a13[] = arr.clone();
        gnomeSort(a13);
        System.out.print("Gnome Sort: ");
        printArray(a13);
        System.out.println("Gnome Sort Successfull");
        System.out.println();

        // Binary Insertion Sort (Time Complexity = O(n^2), Space Complexity = O(1))
        int a14[] = arr.clone();
        binaryInsertionSort(a14);
        System.out.print("Binary Insertion Sort: ");
        printArray(a14);
        System.out.println("Binary Insertion Sort Successfull");
        System.out.println();
    }
}
