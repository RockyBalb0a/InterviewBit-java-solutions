/**************************************************************
Description:-

A robot is located at the top-left corner of an A x B grid.

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked ‘Finish’ in the diagram below).

How many possible unique paths are there?

Note: A and B will be such that the resulting answer fits in a 32 bit signed integer.

Example :

Input : A = 2, B = 2
Output : 2

2 possible routes : (0, 0) -> (0, 1) -> (1, 1) 
              OR  : (0, 0) -> (1, 0) -> (1, 1)
**************************************************************/*

public class Solution {
    public int uniquePaths(int A, int B) {
				//finding (A-1 + B-1)! by cancelling out the factorial of max value of A-1 & B-1
        long num =1;
        for(int i = Math.max(A-1,B-1)+1; i<=(A+B-2); i++ ) 
            num*=i;
				//finding factorial of min value of (A-1) & (B-1)
        long den =1;
        for(int i = 1; i<=Math.min(A-1,B-1); i++)
            den*=i;
        return (int)(num/den);
    }
}
