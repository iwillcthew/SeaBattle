package model;

public class Coordinate {
    private final int xAxis;
    private final int yAxis;

    public Coordinate(int xAxis, int yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public int getXAxis() {
        return xAxis;
    }

    public int getYAxis() {
        return yAxis;
    }

    public static Coordinate fromString(String input) {
        char letter = input.charAt(0);
        int number = Integer.parseInt(input.substring(1));
        return new Coordinate(letter - 'A', number - 1);
    }
}