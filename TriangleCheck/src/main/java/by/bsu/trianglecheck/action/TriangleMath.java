package by.bsu.trianglecheck.action;

public class TriangleMath {
    public boolean isTriangle(double a, double b, double c){
        return check(a, b, c) && check(a, c, b) && check(b, c, a);
    }

    private boolean check(double a, double b, double c){
        return (a + b) > c;
    }
}
