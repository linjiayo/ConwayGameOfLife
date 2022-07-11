package com.zipcodeconway;

import java.util.Arrays;
import java.util.Random;

public class ConwayGameOfLife {
    private int dimension;
    private int[][] matrix;
    private SimpleWindow displayWindow;

    public ConwayGameOfLife(Integer dimension) {
        this.dimension = dimension;
        displayWindow = new SimpleWindow(dimension);
        matrix = createRandomStart(dimension);
     }

    public ConwayGameOfLife(Integer dimension, int[][] startMatrix) {
        matrix = startMatrix;
        this.dimension = dimension;
        displayWindow = new SimpleWindow(dimension);
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(50);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        Random rand = new Random();
        int[][] board = new int[dimension][dimension];

        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                board[row][col] = rand.nextInt(2);
            }
        }
        return board;
    }

    public int[][] simulate(Integer maxGenerations) {
        if (this.matrix == null) {
            this.matrix = createRandomStart(this.dimension);
        }
        int currentGen = 0;
        int[][] newGen = new int[dimension][dimension];

        while (currentGen <= maxGenerations) {
            this.displayWindow.display(this.matrix, currentGen);
            for (int row = 0; row < dimension; row++) {
                for (int col = 0; col < dimension; col++) {
                    newGen[row][col] = isAlive(row, col, this.matrix);
                }
            }
            copyAndZeroOut(newGen, this.matrix);
            currentGen++;
            this.displayWindow.sleep(125);
        }
        return this.matrix;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {
        for (int row = 0; row < Math.min(next.length, current.length); row++) {
            for (int col = 0; col < Math.min(next.length, current.length); col++) {
                current[row][col] = next[row][col];
                next[row][col] = 0;
            }
        }
    }

    // Calculate if an individual cell should be alive in the next generation.
    // Based on the game logic:
	/*
		Any live cell with fewer than two live neighbours dies, as if by needs caused by underpopulation.
		Any live cell with more than three live neighbours dies, as if by overcrowding.
		Any live cell with two or three live neighbours lives, unchanged, to the next generation.
		Any dead cell with exactly three live neighbours cells will come to life.
	*/
    protected int isAlive(int row, int col, int[][] world) {
        int aliveCount = 0;
        int rowBound = world.length;
        int columnBound = world[0].length;

        // iterate over each adjacent row and column, ensuring bounds
        for (int i = Math.max(row - 1 , 0); i < Math.min(row + 2, rowBound); i++) {
            for (int j = Math.max(col - 1, 0); j < Math.min(col + 2, columnBound); j++) {
                // bitwise AND to return 0 or 1 if cell alive, increment aliveCount with value.
                aliveCount += world[i][j] & 1;
            }
        }
        aliveCount -= world[row][col] & 1;
        if (world[row][col] == 0 && aliveCount == 3) {
            return 1;
        }
        return (world[row][col] == 1 && !(aliveCount > 3 || aliveCount < 2))
                ? 1
                : 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(dimension * (dimension + dimension));
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                res.append(matrix[row][col] + " ");
            }
            res.append("\n");
        }
        return res.toString();
    }
}
