package com.zipcodeconway;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConwayGameOfLifeTest {
    int[][] startMatrix;

    @Before
    public void init() {
        startMatrix = new int[][] {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}};
    }
    @Test
    public void testIsAliveDeadCellBecomesAliveWhen3LiveNeighbors() {
        int[][] matrix = new int[][] {
                {0, 0 ,0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}};

        ConwayGameOfLife sim = new ConwayGameOfLife(5, startMatrix);
        Integer expected = 1;

        Integer actual = sim.isAlive(2, 1, startMatrix);
        Integer actual1 = sim.isAlive(2, 3, startMatrix);
        Integer actual2 = sim.isAlive(3, 4, matrix);

        assertEquals(expected, actual);
        assertEquals(expected, actual1);
        assertEquals(expected, actual2);
    }

    @Test
    public void testIsAliveDeadCellStayDeadWhen2LiveNeighbors() {
        ConwayGameOfLife sim = new ConwayGameOfLife(5, startMatrix);
        Integer expected = 0;

        Integer actual = sim.isAlive(1, 3, startMatrix);

        assertEquals(expected, actual);
    }

    @Test
    public void testIsAliveCellStaysAlive2Or3Neighbors() {
        int[][] matrix = new int[][] {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}};
        ConwayGameOfLife sim = new ConwayGameOfLife(5, matrix);
        Integer expected = 1;

        Integer actual = sim.isAlive(3, 2, matrix);

        assertEquals(expected, actual);

    }
    @Test
    public void runTest1() {
        int[][] start = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}};
        int[][] expected = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}};
        ConwayGameOfLife sim = new ConwayGameOfLife(5, start);
        //System.out.println(sim);
        int[][] results = sim.simulate(9);
        //System.out.println(sim);
        assertTrue(java.util.Arrays.deepEquals(results, expected));
    }

    @Test
    public void runTest2() {
        int[][] start = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};
        int[][] expected = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}};
        ConwayGameOfLife sim = new ConwayGameOfLife(5, start);
        int[][] results = sim.simulate(10);
        assertTrue(java.util.Arrays.deepEquals(results, expected));
    }
}