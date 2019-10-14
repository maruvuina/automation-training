package by.bsu.trianglecheck.action;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TriangleMathTest {
    private final TriangleMath triangleMath = new TriangleMath();

    @Test
    public void sideShorterThanSumOfOtherTwo() {
        double a = 15.0;
        double b = 14.0;
        double c = 11.0;
        boolean actual = triangleMath.isTriangle(a, b, c);
        assertTrue(actual);
    }

    @Test(groups = { "sideEqualToSumOfOtherTwo" })
    public void firstSideEqualToSumOfOtherTwo() {
        double a = 8.0;
        double b = 3.0;
        double c = 5.0;
        boolean actual = triangleMath.isTriangle(a, b, c);
        assertEquals(false, actual);
    }

    @Test(groups = { "sideEqualToSumOfOtherTwo" })
    public void secondSideEqualToSumOfOtherTwo() {
        double a = 3.0;
        double b = 8.0;
        double c = 5.0;
        boolean actual = triangleMath.isTriangle(a, b, c);
        assertEquals(false, actual);
    }

    @Test(groups = { "sideEqualToSumOfOtherTwo" })
    public void thirdSideEqualToSumOfOtherTwo() {
        double a = 3.0;
        double b = 5.0;
        double c = 8.0;
        boolean actual = triangleMath.isTriangle(a, b, c);
        assertEquals(false, actual);
    }

    @Test
    public void oneZeroSide() {
        double a = 0.0;
        double b = 1.3;
        double c = 9.8;
        boolean actual = !triangleMath.isTriangle(a, b, c);
        assertTrue(actual);
    }

    @Test
    public void allSidesAreZero() {
        double a = 0.0;
        double b = 0.0;
        double c = 0.0;
        boolean actual = triangleMath.isTriangle(a, b, c);
        assertEquals(false, actual);
    }

    @Test
    public void negativeSide() {
        double a = -90.4;
        double b = 13.7;
        double c = 6.8;
        boolean actual = triangleMath.isTriangle(a, b, c);
        assertEquals(false, actual);
    }

    @Test
    public void triangleEquilateral() {
        double a = 9.9;
        double b = 9.9;
        double c = 9.9;
        boolean actual = triangleMath.isTriangle(a, b, c);
        assertTrue(actual);
    }

    @Test
    public void triangleScalene() {
        double a = 7.0;
        double b = 10.0;
        double c = 5.0;
        boolean actual = triangleMath.isTriangle(a, b, c);
        assertTrue(actual);
    }

    @Test
    public void triangleIsoceles() {
        double a = 5.7;
        double b = 5.7;
        double c = 4.1;
        boolean actual = triangleMath.isTriangle(a, b, c);
        assertTrue(actual);
    }
}