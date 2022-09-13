/*Problem Description

Given a character matrix of size N x M in the form of a string array A of size N where A[i] denotes ith row.

Each character in the matrix consists any one of the following three characters {'r', 'g', 'b'} where 'r' denotes red color similarly 'g' denotes green color and 'b' denotes blue color.

You have to find the area of the largest triangle that has one side parallel to y-axis i.e vertical and the color of all three vertices are different.

NOTE:

If the area comes out to be a real number than return the ceil of that number.


Problem Constraints
2 <= N, M <= 103

A[i][j] = 'r' or A[i][j] = 'g' or A[i][j] = 'b'



Input Format
First and only argument is an string array A of size N denoting the 2D character matrix.



Output Format
Return a single integer denoting the area of the largest triangle that has one side parallel to y-axis i.e vertical and the color of all three vertices are different.

If the area comes out to be a real number than return the ceil of that number.



Example Input
Input 1:

 A = ["rrrrr", "rrrrg", "rrrrr", "bbbbb"]
Input 2:

 A = ["rrr", "rrr", "rrr", "rrr"]


Example Output
Output 1:

 10
Output 2:

 0


Example Explanation
Explanation 1:

 The maximum area of triangle is 10.
 Triangle coordinates are (0,0) containing r, (1,4) containing g, (3,0) containing b.
 
Explanation 2:

 All cells have same color so no triangle possible so we will return 0
 * 
 */

public class Solution {
    public int solve(ArrayList<String> A) {
        char[][] arr = new char[A.size()][A.get(0).length()];
        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < A.get(0).length(); j++) {
                arr[i][j] = A.get(i).charAt(j);
            }
        }
        ArrayList<HashMap<Character, Integer>> base = new ArrayList<>();
        for (int i = 0; i < arr[0].length; i++) {
            base.add(new HashMap<>());
        }
        for (int j = 0; j < arr[0].length; j++) {
            for (int i = arr.length - 1; i >= 0; i--) {
                if (base.get(j).containsKey(arr[i][j]) == false) {
                    base.get(j).put(arr[i][j], i);
                }
            }
        }
        HashMap<Character, Integer> left = new HashMap<>();
        HashMap<Character, Integer> right = new HashMap<>();
        for (int j = 0; j < arr[0].length; j++) {
            for (int i = arr.length - 1; i >= 0; i--) {
                if (left.containsKey(arr[i][j]) == false) {
                    left.put(arr[i][j], j);
                }
            }
        }
        for (int j = arr[0].length - 1; j >= 0; j--) {
            for (int i = arr.length - 1; i >= 0; i--) {
                if (right.containsKey(arr[i][j]) == false) {
                    right.put(arr[i][j], j);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 'r') {
                    if (base.get(j).containsKey('b')) {
                        int baselen = Math.abs(base.get(j).get('b') - i) + 1;
                        if (left.containsKey('g')) {
                            int height = Math.abs(j - left.get('g')) + 1;
                            ans = Math.max(ans, findarea(baselen, height));
                        }
                        if (right.containsKey('g')) {
                            int height = Math.abs(j - right.get('g')) + 1;
                            ans = Math.max(ans, findarea(baselen, height));
                        }
                    }
                    if (base.get(j).containsKey('g')) {
                        int baselen = Math.abs(base.get(j).get('g') - i) + 1;
                        if (left.containsKey('b')) {
                            int height = Math.abs(j - left.get('b')) + 1;
                            ans = Math.max(ans, findarea(baselen, height));
                            ;
                        }
                        if (right.containsKey('b')) {
                            int height = Math.abs(j - right.get('b')) + 1;
                            ans = Math.max(ans, findarea(baselen, height));
                        }
                    }
                } else if (arr[i][j] == 'b') {
                    if (base.get(j).containsKey('g')) {
                        int baselen = Math.abs(base.get(j).get('g') - i) + 1;
                        if (left.containsKey('r')) {
                            int height = Math.abs(j - left.get('r')) + 1;
                            ans = Math.max(ans, findarea(baselen, height));
                        }
                        if (right.containsKey('r')) {
                            int height = Math.abs(j - right.get('r')) + 1;
                            ans = Math.max(ans, findarea(baselen, height));
                        }
                    }
                    if (base.get(j).containsKey('r')) {
                        int baselen = Math.abs(base.get(j).get('r') - i) + 1;
                        if (left.containsKey('g')) {
                            int height = Math.abs(j - left.get('g')) + 1;
                            ans = Math.max(ans, findarea(baselen, height));
                        }
                        if (right.containsKey('g')) {
                            int height = Math.abs(j - right.get('g')) + 1;
                            ans = Math.max(ans, findarea(baselen, height));
                        }
                    }
                } else if (arr[i][j] == 'g') {
                    if (base.get(j).containsKey('b')) {
                        int baselen = Math.abs(i - base.get(j).get('b')) + 1;
                        if (left.containsKey('r')) {
                            int height = Math.abs(j - left.get('r')) + 1;
                            ans = Math.max(ans, findarea(baselen, height));
                        }
                        if (right.containsKey('r')) {
                            int height = Math.abs(j - right.get('r')) + 1;
                            ans = Math.max(ans, findarea(baselen, height));
                        }
                    }
                    if (base.get(j).containsKey('r')) {
                        int baselen = Math.abs(i - base.get(j).get('r')) + 1;
                        if (left.containsKey('b')) {
                            int height = Math.abs(j - left.get('b')) + 1;
                            ans = Math.max(ans, findarea(baselen, height));
                        }
                        if (right.containsKey('b')) {
                            int height = Math.abs(j - right.get('b')) + 1;
                            ans = Math.max(ans, findarea(baselen, height));
                        }
                    }
                }
            }
        }
        return ans;

    }

    public int findarea(int baselen, int height) {
        int area = baselen * height;
        if (area % 2 == 0) {
            return area = area / 2;
        } else {
            return area = (area / 2) + 1;
        }
    }
}