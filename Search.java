import java.util.*;

public class Search {

    // ===================== Helper Methods =====================

    // Used by Exponential Search / Binary Indexed Search
    public static void binarySearch(int arr[], int low, int high, int target) {
        int mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                System.out.println("Element found at index " + mid);
                return;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println("Element Not Found");
    }
 

    // Recursive Binary Search (Time Complexity = O(log n), Space Complexity = O(log n) call stack)
    public static int recursiveBinarySearch(int arr[], int low, int high, int target) {
        if (low > high) return -1;
        int mid = low + (high - low) / 2;
        if (arr[mid] == target) return mid;
        else if (arr[mid] < target) return recursiveBinarySearch(arr, mid + 1, high, target);
        else return recursiveBinarySearch(arr, low, mid - 1, target);
    }

    // Sentinel Linear Search: place the target at the very end so the loop never needs a bounds check.
    public static int sentinelLinearSearch(int arr[], int target) {
        int n = arr.length;
        if (n == 0) return -1;
        int last = arr[n - 1];
        arr[n - 1] = target; // sentinel
        int i = 0; 
        while (arr[i] != target) {
            i++; 
        } 
        arr[n - 1] = last; // restore original array
        if (i < n - 1 || last == target) {
            return i;
        }
        return -1;
    } 

    // Breadth-First Search on a graph (Time Complexity = O(V+E), Space Complexity = O(V))
    public static boolean bfsSearch(Map<Integer, List<Integer>> graph, int start, int target) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        visited.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == target) return true;
            for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return false;
    } 
 
    // Depth-First Search on a graph (Time Complexity = O(V+E), Space Complexity = O(V))
    public static boolean dfsSearch(Map<Integer, List<Integer>> graph, int start, int target, Set<Integer> visited) {
        if (start == target) return true;
        visited.add(start);
        for (int neighbor : graph.getOrDefault(start, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                if (dfsSearch(graph, neighbor, target, visited)) return true;
            }
        }
        return false;
    }
 
    // Naive String Search (Time Complexity = O(n*m), Space Complexity = O(1))
    public static int naiveStringSearch(String text, String pattern) {
        int n = text.length(), m = pattern.length();
        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) break;
            }
            if (j == m) return i;
        }
        return -1;
    }     

    // Knuth-Morris-Pratt (KMP) String Search (Time Complexity = O(n+m), Space Complexity = O(m))
    private static int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0, i = 1;
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else if (len != 0) {
                len = lps[len - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }
        return lps;
    }

    public static int kmpSearch(String text, String pattern) {
        int n = text.length(), m = pattern.length();
        if (m == 0) return 0;
        int[] lps = computeLPSArray(pattern);
        int i = 0, j = 0;
        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == m) return i - j;
            } else if (j != 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }
        return -1;
    }

    // Rabin-Karp String Search (Time Complexity = O(n+m) average, O(n*m) worst case, Space Complexity = O(1))
    public static int rabinKarpSearch(String text, String pattern) {
        int n = text.length(), m = pattern.length();
        if (m == 0 || m > n) return -1;
        int prime = 101;
        int textHash = 0, patternHash = 0, h = 1;
        for (int i = 0; i < m - 1; i++) h = (h * 256) % prime;
        for (int i = 0; i < m; i++) {
            patternHash = (256 * patternHash + pattern.charAt(i)) % prime;
            textHash = (256 * textHash + text.charAt(i)) % prime;
        }
        for (int i = 0; i <= n - m; i++) {
            if (patternHash == textHash) {
                int j;
                for (j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) break;
                }
                if (j == m) return i;
            }
            if (i < n - m) {
                textHash = (256 * (textHash - text.charAt(i) * h) + text.charAt(i + m)) % prime;
                if (textHash < 0) {
                    textHash += prime;
                }
            } 
        }
        return -1;
    }

    // ===================== Main =====================

                        
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Sorted array - required for Binary/Fibonacci/Jump/Interpolation/Exponential/Ternary/Indexed search
        int arr[] = {3, 4, 5, 6, 7, 8, 9, 12, 16, 18};
        System.out.println("Enter key element: ");
        int target = sc.nextInt();
        boolean aim = false;
 
        // Linear Search (Time Complexity = O(n), Space Complexity = O(1))
        for (int i = 0; i <= arr.length - 1; i++) {
            if (arr[i] == target) {
                System.out.println("Element found at index " + i);
                aim = true;
                break;
            }
        }
        if (!aim) {
            System.out.println("Element Not Found");
        }
        System.out.println("Linear Search Successfull");

        // Binary Search (Time Complexity = O(log(n)), Space Complexity = O(1))
        int low = 0, high = arr.length - 1, mid = 0;
        boolean binFound = false;
        while (low <= high) {
            mid = low + (high - low) / 2; // fixed precedence bug
            if (arr[mid] == target) {
                System.out.println("Element found at index " + mid);
                binFound = true;
                break;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if (!binFound) {
            System.out.println("Element Not Found");
        }
        System.out.println("Binary Search Successfull");

        // Recursive Binary Search
        int recResult = recursiveBinarySearch(arr, 0, arr.length - 1, target);
        System.out.println(recResult != -1 ? "Element found at index " + recResult : "Element Not Found");
        System.out.println("Recursive Binary Search Successfull");

        // Fibonacci Search (Time Complexity = O(log(n)), Space Complexity = O(1))
        int fibMMm2 = 0; // (m-2)'th
        int fibMm1 = 1;  // (m-1)'th
        int fibM = fibMMm2 + fibMm1; // m'th
        while (fibM < arr.length) {
            fibMMm2 = fibMm1;
            fibMm1 = fibM;
            fibM = fibMMm2 + fibMm1;
        }
        int offset = -1;
        boolean fibFound = false;
        while (fibM > 1) {
            int i = Math.min(offset + fibMMm2, arr.length - 1);
            if (arr[i] < target) {
                fibM = fibMm1;
                fibMm1 = fibMMm2;
                fibMMm2 = fibM - fibMm1;
                offset = i;
            } else if (arr[i] > target) {
                fibM = fibMMm2;
                fibMm1 = fibMm1 - fibMMm2;
                fibMMm2 = fibM - fibMm1;
            } else {
                System.out.println("Element found at index " + i);
                fibFound = true;
                break;
            }
        }
        if (!fibFound && fibMm1 == 1 && offset + 1 < arr.length && arr[offset + 1] == target) {
            System.out.println("Element found at index " + (offset + 1));
            fibFound = true;
        }
        if (!fibFound) {
            System.out.println("Element Not Found");
        }
        System.out.println("Fibonacci Search Successfull");

        //Jump Search (Time Complexity = O(sqrt(n)), Space Complexity = O(1))
        int jump = (int) Math.sqrt(arr.length);
        int prev = 0;
        boolean jumpFound = false;
        while (arr[Math.min(jump, arr.length) - 1] < target) {
            prev = jump;
            jump += (int) Math.sqrt(arr.length);
            if (prev >= arr.length) {
                System.out.println("Element Not Found");
                jumpFound = true; // skip further checks
                break;
            }
        }
        if (!jumpFound) {
            while (prev < Math.min(jump, arr.length) && arr[prev] < target) {
                prev++;
            }
            if (prev < arr.length && arr[prev] == target) {
                System.out.println("Element found at index " + prev);
            } else {
                System.out.println("Element Not Found");
            }
        }
        System.out.println("Jump Search Successfull");

        //Sentinel Linear Search
        int sentResult = sentinelLinearSearch(arr.clone(), target);
        System.out.println(sentResult != -1 ? "Element found at index " + sentResult : "Element Not Found");
        System.out.println("Sentinel Linear Search Successfull");

        //Hash Search (Time Complexity = O(1) average, Space Complexity = O(n))
        // Using HashSet - a set can only tell you if the value exists, not its index
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        if (set.contains(target)) {
            System.out.println("Element found (HashSet confirms presence, use HashMap for index)");
        } else {
            System.out.println("Element Not Found");
        }
        System.out.println("Hash Search Successfull");

        // Using HashMap - this is what actually lets you look up index in O(1)
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        if (map.containsKey(target)) {
            System.out.println("Element found at index " + map.get(target));
        } else {
            System.out.println("Element Not Found");
        }
        System.out.println("Hash Search Successfull");

        //Interpolation Search (Time Complexity = O(log(log(n))), Space Complexity = O(1))
        int iLow = 0, iHigh = arr.length - 1;
        boolean interpFound = false;
        while (iLow <= iHigh && target >= arr[iLow] && target <= arr[iHigh] && arr[iHigh] != arr[iLow]) {
            int pos = iLow + (iHigh - iLow) * (target - arr[iLow]) / (arr[iHigh] - arr[iLow]);
            if (arr[pos] == target) {
                System.out.println("Element found at index " + pos);
                interpFound = true;
                break;
            }
            if (arr[pos] < target) {
                iLow = pos + 1;
            } else {
                iHigh = pos - 1;
            }
        }
        if (!interpFound) {
            System.out.println("Element Not Found (or Interpolation Search skipped)");
        }
        System.out.println("Interpolation Search Successfull");

        //Exponential Search (Time Complexity = O(log(n)), Space Complexity = O(1))
        int expI = 1;
        while (expI < arr.length && arr[expI] <= target) {
            expI = expI * 2;
        }
        binarySearch(arr, expI / 2, Math.min(expI, arr.length - 1), target);
        System.out.println("Exponential Search Successfull");

        //Ternary Search (Time Complexity = O(log(n)), Space Complexity = O(1))
        int l = 0, r = arr.length - 1;
        boolean ternaryFound = false;
        while (l <= r) {
            int mid1 = l + (r - l) / 3;
            int mid2 = r - (r - l) / 3;
            if (arr[mid1] == target) {
                System.out.println("Element found at index " + mid1);
                ternaryFound = true;
                break;
            }
            if (arr[mid2] == target) {
                System.out.println("Element found at index " + mid2);
                ternaryFound = true;
                break;
            }
            if (target < arr[mid1]) {
                r = mid1 - 1;
            } else if (target > arr[mid2]) {
                l = mid2 + 1;
            } else {
                l = mid1 + 1;
                r = mid2 - 1;
            }
        }
        if (!ternaryFound) {
            System.out.println("Element Not Found");
        }
        System.out.println("Ternary Search Successfull");

        //Sequential Indexed Search (Time Complexity = O(sqrt(n)), Space Complexity = O(1))
        int n = arr.length;
        int block = (int) Math.sqrt(n);
        int start = 0;
        int end = block - 1;
        while (end < n && arr[end] < target) {
            start = end + 1;
            end = end + block;
        }
        while (start < n && arr[start] < target) {
            start++;
        }
        if (start < n && arr[start] == target) {
            System.out.println("Element found at index " + start);
        } else {
            System.out.println("Element Not Found");
        }
        System.out.println("Sequential Indexed Search Successfull");

        //Binary Indexed Search (Time Complexity = O(log(n)), Space Complexity = O(1))
        int index = 1;
        while (index < arr.length && arr[index] <= target) {
            index = index * 2;
        }
        binarySearch(arr, index / 2, Math.min(index, arr.length - 1), target);
        System.out.println("Binary Indexed Search Successfull");

        // ===================== Graph Search Algorithms =====================
        // Build a small sample graph (adjacency list) to demonstrate BFS/DFS.
        // These search a GRAPH for a target node, not the int array above.
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(2, 3));
        graph.put(2, Arrays.asList(1, 4));
        graph.put(3, Arrays.asList(1, 4));
        graph.put(4, Arrays.asList(2, 3, 5));
        graph.put(5, Arrays.asList(4));
        int graphStart = 1;
        int graphTarget = 5;

        //Breadth-First Search
        boolean bfsFound = bfsSearch(graph, graphStart, graphTarget);
        System.out.println(bfsFound ? "Node " + graphTarget + " found via BFS" : "Node Not Found via BFS");
        System.out.println("Breadth-First Search Successfull");

        //Depth-First Search
        boolean dfsFound = dfsSearch(graph, graphStart, graphTarget, new HashSet<>());
        System.out.println(dfsFound ? "Node " + graphTarget + " found via DFS" : "Node Not Found via DFS");
        System.out.println("Depth-First Search Successfull");

        // ===================== String Search Algorithms =====================
        // These search for a PATTERN inside a TEXT, a different search problem entirely.
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";

        //Naive String Search
        int naiveIdx = naiveStringSearch(text, pattern);
        System.out.println(naiveIdx != -1 ? "Pattern found at index " + naiveIdx : "Pattern Not Found");
        System.out.println("Naive String Search Successfull");

        //Knuth-Morris-Pratt (KMP) String Search
        int kmpIdx = kmpSearch(text, pattern);
        System.out.println(kmpIdx != -1 ? "Pattern found at index " + kmpIdx : "Pattern Not Found");
        System.out.println("KMP String Search Successfull");

        //Rabin-Karp String Search
        int rkIdx = rabinKarpSearch(text, pattern);
        System.out.println(rkIdx != -1 ? "Pattern found at index " + rkIdx : "Pattern Not Found");
        System.out.println("Rabin-Karp String Search Successfull");

        sc.close();
    }
}