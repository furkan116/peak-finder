package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	//Ufak bir arayüz ekledim bu sayede tüm metodları kontrol edebilirsiniz

        System.out.println("1-Greedy");
        System.out.println("2-DivideAndConq1");
        System.out.println("3-DivideAndConq2");
        System.out.println("Hangi yontem ile matrisinizi hesaplamak istersiniz: ");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1: PeakFinder2D.testGreedyAlg();break;
            case 2: PeakFinder2D.testDivideAndConq1();break;
            case 3: PeakFinder2D.testDivideAndConq2();break;
        }
    }
}
