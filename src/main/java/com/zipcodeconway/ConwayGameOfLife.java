package com.zipcodeconway;

public class ConwayGameOfLife {
    int[][] board;
    public ConwayGameOfLife(Integer dimension) {
        board = new int[dimension][dimension];
     }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        board = startmatrix;
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(50);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        return new int[1][1];
    }

    public int[][] simulate(Integer maxGenerations) {
        return new int[1][1];
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {
        //for (int i = 0; i < )
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
            for (int j = (Math.max(col - 1, 0)); j < Math.min(col + 2, columnBound); j++) {
                // bitwise AND to return 0 or 1 if cell alive
                aliveCount += world[i][j] & 1;
            }
        }
        return aliveCount;
    }
}
