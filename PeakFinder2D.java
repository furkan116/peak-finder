package com.company;

import java.util.Random;

public class PeakFinder2D {

    int[][] a;

    int nrow = 5, ncol = 5;

    public PeakFinder2D(){
        a = new int[nrow][ncol];
        Random r = new Random();
        for (int i = 0; i < nrow; i++) {
            for (int j = 0; j < ncol; j++) {
                a[i][j] = r.nextInt(100);
            }
        }
    }

    public int greedyAlg(){
        int peak = a[0][0];
        int row = 0;
        while(row < nrow){
            int col = 0;
            while (col < ncol){
                //TODO
                if (a[row][col+1] >= a[row][col] && col != 4) {
                    row = row;
                    col = col+1;
                    peak = a[row][col];
                }
                else if (a[row+1][col] >= a[row][col] && row != 4) {
                    row = row+1;
                    col = col;
                    peak = a[row][col];
                }
                else {
                    return peak;
                }
            }
        }
        return peak;
    }
/*
    public int findMax(int[] b[]){
        int imax = 0;
        for(int i = 0; i < a.length; i++ ){
            if(a[i] > a[imax]){
                imax = i;
            }
        }
        return imax;
    }
*/
    public int findMaxOnCol(int col){
        int imax = 0;
        for(int i = 0; i < nrow; i++ ){
            if(a[i][col]>a[imax][col]){
                imax = i;
            }
        }
        return imax;
    }
/*
    public int findMaxOnRow(int row){
        int imax = 0;
        for(int i = 0; i < ncol; i++ ){
            if(a[row][i]>a[row][imax]){
                imax = i;
            }
        }
        return imax;
    }
*/
    public int divideAndConquer1(int startcol, int endcol){
        //TODO correct and complete the code
        int midcol = (startcol + endcol)/2; //1 tane
        int maxInRow = findMaxOnCol(midcol); // number of rows: m
        ///base case TODO: boundary conditions
        if(a[maxInRow][midcol] >= a[maxInRow][midcol+1] && a[maxInRow][midcol] >= a[maxInRow][midcol-1]  ) {//2 tane
            return a[maxInRow][midcol];
        }
        if(a[maxInRow][midcol] <= a[maxInRow][midcol+1] ) {
            return divideAndConquer1(midcol, endcol);
        }
        if(a[maxInRow][midcol] <= a[maxInRow][midcol-1] ) {
            return divideAndConquer1(startcol, midcol);
        }

        return 0;
    }

    /** derste anlatilan divide and conquer  yontemini kullanarak O(n+m)
     *  zamanda peak bulan algoritmanin implemantasyonunu yaziniz*/
/*    public int divideAndConquer1(int startrow, int startcol, int endrow, int endcol){
        //TODO
    }
*/
    /** prints elements of a */
    void printArray(){
        //TODO
        for(int row = 0; row < nrow; row++){
            for(int col = 0; col < ncol; col++){
                System.out.print(a[row][col] + "\t");
            }
            System.out.println();
        }
    }

    static void testGreedyAlg(){
        //TODO
        PeakFinder2D test = new PeakFinder2D();

        test.printArray();

        System.out.println("Greedy Peak: " + test.greedyAlg() + "\n");
    }

    static void testDivideAndConq1(){
        //TODO
        PeakFinder2D test = new PeakFinder2D();

        test.printArray();

        System.out.println("Divide and Conquer1 Peak: " + test.divideAndConquer1(0, 4) + "\n");
    }

    static void testDivideAndConq2(){
        //TODO
    }

}
