package Lecture2;

import java.util.Arrays;
import java.util.Scanner;

public class M {
    private static int[][] matrix;
    private static int sum;

    public static void main(String[] args) {
        fillMatrix();
        sum = getRowSum(0);

        for (int[] aMatrix : matrix) {
            System.out.println(Arrays.toString(aMatrix));
        }

        System.out.println(isMagicSquare());
    }

    private static void fillMatrix() {
        System.out.println("Введите размер матрицы");
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        matrix = new int[n][n];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print("[" + i + "][" + j + "]:");
                matrix[i][j] = scanner.nextInt();
            }
        }
    }

    private static boolean isMagicSquare() {
        int diagSum = 0;
        int invertDiagSum = 0;

        for (int i = 0; i < matrix.length; i++) {
            int j = i; // идем по диагонали

            if (getColSum(j) != sum || getRowSum(i) != sum)
                return false;

            diagSum += matrix[i][j];
            invertDiagSum += matrix[i][matrix.length - 1 - j];
        }

        return diagSum == sum && invertDiagSum == sum;
    }

    private static int getRowSum(int rowInd) {
        int tmpSum = 0;
        for (int j = 0; j < matrix.length; j++) {
            tmpSum += matrix[rowInd][j];
        }

        return tmpSum;
    }

    private static int getColSum(int colInd) {
        int tmpSum = 0;
        for (int i = 0; i < matrix.length; i++) {
            tmpSum += matrix[i][colInd];
        }

        return tmpSum;
    }
}
