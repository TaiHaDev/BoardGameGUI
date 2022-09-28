package comp1140.ass2;

import comp1140.ass2.game.Matrices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MatrixOperationsTests {

    @Test
    public void matrix_multiplication_applies_identity_correctly() {
        int[][] identity = new int[][] {
                new int[] {1, 0, 0},
                new int[] {0, 1, 0},
                new int[] {0, 0, 1}
        };
        int[][] matrix = new int[][] {
                new int[] {1, 5, 5},
                new int[] {-2, 1, 7},
                new int[] {-21, 5, 1}
        };
        Assertions.assertArrayEquals(Matrices.multiply(identity, matrix), matrix);
        Assertions.assertArrayEquals(Matrices.multiply(matrix, identity), matrix);
    }

    @Test
    public void different_sized_matrices_can_be_multiplied() {
        int[][] matrix = new int[][] {
                new int[] {2, 0, 0},
                new int[] {0, 1, 0},
                new int[] {0, 0, 1}
        };
        int[][] vector = new int[][] {
                new int[] {1},
                new int[] {-2},
                new int[] {-21}
        };
        int[][] expected = new int[][] {
                new int[] {2},
                new int[] {-2},
                new int[] {-21}
        };
        Assertions.assertArrayEquals(Matrices.multiply(matrix, vector), expected);
    }

    @Test
    public void matrix_to_the_power_of_zero_is_the_identity_matrix() {
        int[][] matrix = new int[][] {
                new int[] {2, 0, 0},
                new int[] {0, 1, -1},
                new int[] {3, 0, 1}
        };
        int[][] expected = new int[][] {
                new int[] {1, 0, 0},
                new int[] {0, 1, 0},
                new int[] {0, 0, 1}
        };
        Assertions.assertArrayEquals(Matrices.pow(matrix, 0), expected);
    }

    @Test
    public void matrix_to_the_power_of_one_is_itself() {
        int[][] matrix = new int[][] {
                new int[] {2, 0, 0},
                new int[] {0, 1, -1},
                new int[] {3, 0, 1}
        };
        Assertions.assertArrayEquals(Matrices.pow(matrix, 1), matrix);
    }

    @Test
    public void matrix_powers_are_correct() {
        int[][] matrix = new int[][] {
                new int[] {2, 0, 0},
                new int[] {0, 1, -1},
                new int[] {3, 0, 1}
        };
        int[][] expected = new int[][] {
                new int[] {8, 0, 0},
                new int[] {-12, 1, -3},
                new int[] {21, 0, 1}
        };
        Assertions.assertArrayEquals(Matrices.pow(matrix, 3), expected);
    }

}
