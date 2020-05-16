package com.nure;

public class CountNumbers {
    private static int method(int n){
        return 1+(n-1)/6;
    }
    private static int func(int n){
        return 1+(n-1)%6;
    }

    public static void main(String[] args) {
        new Solution().cycle(4./3, 13./3);
    }
}
