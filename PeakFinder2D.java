package com.company;

import java.util.Random;

public class PeakFinder2D {

    int[][] a;

    int nrow = 5, ncol = 5;//Heryerde kullanılabilmesi için global olarak tanımladım

    public PeakFinder2D(){//Constructorda hataları düzelttim ve atanacak sayılara 0 ile 99 arasında olması için sınırları belirledim
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
                //Burada matristeki [0,0] noktasındaki sayıdan başlayarak onun etrafındaki sayıları kontrol edip eğe büyük bir sayı var sa ona gider ve peak bulunana kadar işlemi tekrarlar. Matris alanının dışındaki bir şeyle karşılaşmaması için alanıda kontrol ediyor.
                //TODO
                if (col != ncol-1 && a[row][col+1] >= a[row][col]) {
                    col = col+1;
                    peak = a[row][col];
                }
                else if (row != nrow-1 && a[row+1][col] >= a[row][col]) {
                    row = row+1;
                    peak = a[row][col];
                }
                else if (col != 0 && a[row][col-1] > a[row][col]) {
                    col = col-1;
                    peak = a[row][col];
                }
                else if (row != 0 && a[row-1][col] > a[row][col]) {
                    row = row-1;
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
    public int findMax(int[] b[]){//Kullanmadım
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
        //Burada değişiklik yapmadım
        int imax = 0;
        for(int i = 0; i < nrow; i++ ){
            if(a[i][col]>a[imax][col]){
                imax = i;
            }
        }
        return imax;
    }
/*
    public int findMaxOnRow(int row){//Kullanmadım
        int imax = 0;
        for(int i = 0; i < ncol; i++ ){
            if(a[row][i]>a[row][imax]){
                imax = i;
            }
        }
        return imax;
    }
*/

    public int findMaxOnColWithRange(int col, int startrow, int endrow){
        //Bu metodu ben yazdım. Amacı testDivideAndConq2 için çağırılan çok parametreli olan divideAndConquer1 için belirtilen ve değiştirilebilen sütunda maksimumu bulma
        int imax = startrow;
        for(int i = startrow; i < endrow; i++ ){
            if(a[i][col]>a[imax][col]){
                imax = i;
            }
        }
        return imax;
    }

    public int findMaxOnRowWithRange(int row, int startcol, int endcol){
        //Bu metodu ben yazdım. Amacı testDivideAndConq2 için çağırılan çok parametreli olan divideAndConquer1 için belirtilen ve değiştirilebilen satırda maksimumu bulma
        int imax = startcol;
        for(int i = startcol; i < endcol; i++ ){
            if(a[row][i]>a[row][imax]){
                imax = i;
            }
        }
        return imax;
    }

    public int divideAndConquer1(int startcol, int endcol){
        //Burada sadece hata düzeltme ve sınırları kontrol etme yaptım. Burada satırlar üzerinden işlem yapılmakta.
        //TODO correct and complete the code
        int midcol = (startcol + endcol)/2; //1 tane
        int maxInCol = findMaxOnCol(midcol); // number of rows: m
        ///base case TODO: boundary conditions
        if((startcol == endcol) || ((midcol != ncol-1 && a[maxInCol][midcol] >= a[maxInCol][midcol+1]) && (midcol != 0 && a[maxInCol][midcol] >= a[maxInCol][midcol-1]) )) {//2 tane
            return a[maxInCol][midcol];
        }
        else if(midcol != ncol-1 && a[maxInCol][midcol] <= a[maxInCol][midcol+1]) {
            return divideAndConquer1(midcol+1, endcol);
        }
        else if(midcol != 0 && a[maxInCol][midcol] <= a[maxInCol][midcol-1]) {
            return divideAndConquer1(startcol, midcol-1);
        }

        return 0;
    }

    /** derste anlatilan divide and conquer  yontemini kullanarak O(n+m)
     *  zamanda peak bulan algoritmanin implemantasyonunu yaziniz*/
    public int divideAndConquer1(int startrow, int startcol, int endrow, int endcol){
        //TODO

        if ((endrow - startrow) >= (endcol - startcol)) {//Burada büyüklüğü hesaplamak için çıkarma yapıyoruz çünkü toplama yapılması durumunda satır sayısı küçülse bile satırların numaralarının toplamının büyük olma ihtimali var ama çıkarma yaparak aradaki farkı hesaplarsak satır sayılarına ulaşabiliriz

            int midrow = (startrow + endrow)/2;
            int maxInRow = findMaxOnRowWithRange(midrow, startcol, endcol);

            if(a[midrow][maxInRow] > a[midrow+1][maxInRow] && a[midrow][maxInRow] > a[midrow-1][maxInRow]  ) {
                return a[midrow][maxInRow];
            }
            else if(a[midrow][maxInRow] <= a[midrow+1][maxInRow] ) {
                return divideAndConquer1(midrow, startcol, endrow,endcol);
            }
            else if(a[midrow][maxInRow] <= a[midrow-1][maxInRow] ) {
                return divideAndConquer1( startrow,startcol, midrow, endcol);
            }
        }
        else if ((endrow - startrow) < (endcol - startcol)) {

            int midcol = (startcol + endcol)/2;
            int maxInCol = findMaxOnColWithRange(midcol, startrow, endrow);

            if(a[maxInCol][midcol] > a[maxInCol][midcol+1] && a[maxInCol][midcol] > a[maxInCol][midcol-1]  ) {
                return a[maxInCol][midcol];
            }
            else if(a[maxInCol][midcol] <= a[maxInCol][midcol+1] ) {
                return divideAndConquer1(startrow, midcol, endrow, endcol);
            }
            else if(a[maxInCol][midcol] <= a[maxInCol][midcol-1] ) {
                return divideAndConquer1(startrow, startcol, endrow, midcol);
            }
        }

        return 0;
    }

    /** prints elements of a */
    void printArray(){
        //TODO
        for(int row = 0; row < nrow; row++){
            for(int col = 0; col < ncol; col++){
                System.out.print(a[row][col] + "\t");
            }
            System.out.println();
            //Tüm elemalraı gezerek yazdırıyor ve hepsinin düzenli bie şekilde durmasını sağlar
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

        System.out.println("Divide and Conquer1 Peak: " + test.divideAndConquer1(0, test.ncol-1) + "\n");
    }

    static void testDivideAndConq2(){
        //TODO
        PeakFinder2D test = new PeakFinder2D();

        test.printArray();

        System.out.println("Divide and Conquer2 Peak: " + test.divideAndConquer1(0, 0, test.nrow, test.ncol) + "\n");
    }

    static void testAll() {
        PeakFinder2D test = new PeakFinder2D();

        test.printArray();

        System.out.println("Greedy Peak: " + test.greedyAlg() + "\n");
        System.out.println("Divide and Conquer1 Peak: " + test.divideAndConquer1(0, test.ncol-1) + "\n");
        System.out.println("Divide and Conquer2 Peak: " + test.divideAndConquer1(0, 0, test.nrow, test.ncol) + "\n");
    }
}