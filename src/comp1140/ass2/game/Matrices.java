package comp1140.ass2.game;
// Author Matthew
public class Matrices {

    public static int[][] multiply(int[][] left, int[][] right) {
        int[][] result = new int[left.length][right[0].length];
        for (int i = 0; i < left.length; i++) {
            for (int j = 0; j < right[i].length; j++) {
                int sum = 0;
                for (int k = 0; k < right.length; k++) {
                    sum += left[i][k] * right[k][j];
                }
                result[i][j] = sum;
            }
        }
        return result;
    }

    public static int[][] pow(int[][] matrix, int n) {
        if (n == 0) {
            int[][] identity = new int[matrix.length][matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                identity[i][i] = 1;
            }
            return identity;
        }
        int[][] product = copyOfMatrix(matrix);
        for (int i = 0; i < n - 1; i++) {
            product = multiply(product, matrix);
        }
        return product;
    }

    private static int[][] copyOfMatrix(int[][] in) {
        int[][] out = new int[in.length][in[0].length];
        for (int i = 0; i < in.length; i++) {
            System.arraycopy(in[i], 0, out[i], 0, in[i].length);
        }
        return out;
    }

}
