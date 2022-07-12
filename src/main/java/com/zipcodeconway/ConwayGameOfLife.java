package com.zipcodeconway;

import java.util.Arrays;
import java.util.Random;

public class ConwayGameOfLife {
    private int dimension;
    private short[][] matrix;
    private SimpleWindow displayWindow;

    public ConwayGameOfLife(Integer dimension) {
        this.dimension = dimension;
        displayWindow = new SimpleWindow(dimension);
        matrix = createRandomStart(dimension);
     }

    public ConwayGameOfLife(Integer dimension, int[][] startMatrix) {
        matrix = padArray(startMatrix);
        this.dimension = dimension;
        displayWindow = new SimpleWindow(dimension);
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ConwayGameOfLife sim = new ConwayGameOfLife(900);
        int[][] endingWorld = sim.simulate(100);
        long endTime = System.currentTimeMillis();
        System.out.printf("Execution time is %dms\n", endTime - startTime);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private short[][] createRandomStart(Integer dimension) {
        Random rand = new Random();
        short[][] board = new short[dimension + 2][dimension + 2];

        for (int row = 1; row < dimension - 1; row++) {
            for (int col = 1; col < dimension - 1; col++) {
                board[row][col] = (short) rand.nextInt(2);
            }
        }
        return board;
    }

    public int[][] simulate(Integer maxGenerations) {
        if (this.matrix == null) {
            this.matrix = createRandomStart(this.dimension);
        }
        int currentGen = 0;
        short[][] newGen = new short[dimension + 2][dimension + 2];

        while (currentGen <= maxGenerations) {
            this.displayWindow.display(this.matrix, currentGen);
            for (int row = 1; row < dimension; row++) {
                for (int col = 1; col < dimension; col++) {
                    newGen[row][col] = isAlive(row, col, this.matrix);
                }
            }
            //System.out.println(this);
            copyAndZeroOut(newGen, this.matrix);
            currentGen++;
            //this.displayWindow.sleep(125);
        }
        return unPadArray(this.matrix);
    }

    public short[][] padArray(int[][] current) {
        short[][] newArr = new short[current.length + 2][current.length + 2];
        for (int row = 0; row < current.length; row++) {
            for (int col = 0; col < current.length; col++) {
                newArr[row + 1][col + 1] = (short) current[row][col];
            }
        }
        return newArr;
    }

    public int[][] unPadArray(short[][] current) {
        int[][] newArr = new int[dimension][dimension];
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                newArr[row][col] = current[row + 1][col + 1];
            }
        }
        return newArr;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(short [][] next, short[][] current) {
        for (int row = 1; row < next.length - 1; row++) {
            for (int col = 1; col < next.length - 1; col++) {
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
    protected int isAliveForTesting(int row, int col, int[][] world) {
        short[][] newShortArr = new short[world.length][world.length];
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world.length; j++) {
                newShortArr[i][j] = (short) world[i][j];
            }
        }
        return isAlive(row, col, newShortArr);
    }


    protected short isAlive(int row, int col, short[][] world) {
        int count = 0;
        count += world[row-1][col-1];
        count += world[row-1][col];
        count += world[row-1][col+1];
        count += world[row][col-1];
        count += world[row][col+1];
        count += world[row+1][col-1];
        count += world[row+1][col];
        count += world[row+1][col+1];

        return (short) (((world[row][col] == 1 && !(count > 3 || count < 2))
                        || (world[row][col] == 0 && count == 3))
                            ? 1
                            : 0);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(dimension * (dimension + dimension));
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                res.append(matrix[row][col] + " ");
            }
            res.append("\n");
        }
        return res.toString();
    }
}
