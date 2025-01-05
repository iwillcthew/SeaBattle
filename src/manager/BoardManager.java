package manager;

import model.Coordinate;
import model.Ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardManager {
    private final char[][] grid;
    private final int size = 10;
    private final List<Ship> ships;

    public BoardManager() {
        grid = new char[size][size];
        ships = new ArrayList<>();
        for (char[] row : grid) {
            Arrays.fill(row, '~');
        }
    }

    public void display() {
        System.out.print("  ");
        for (int i = 1; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < size; j++) {
                char cell = grid[i][j];
                switch (cell) {
                    case '~':
                        System.out.print("\u001B[34m~\u001B[0m ");
                        break;
                    case 'O':
                        System.out.print("\u001B[36mO\u001B[0m ");
                        break;
                    case 'X':
                        System.out.print("\u001B[31mX\u001B[0m ");
                        break;
                    default:
                        System.out.print("\u001B[33m" + cell + "\u001B[0m ");
                        break;
                }
            }
            System.out.println();
        }
    }

    public boolean placeShip(Ship ship, Coordinate start, boolean isVertical) {
        int xAxis = start.getXAxis();
        int yAxis = start.getYAxis();
        int size = ship.getSize();

        if (isVertical) {
            if (xAxis + size > this.size) return false;
            for (int i = 0; i < size; i++) {
                if (grid[xAxis + i][yAxis] != '~') return false;
            }
            for (int i = 0; i < size; i++) {
                grid[xAxis + i][yAxis] = ship.getSymbol();
            }
        } else {
            if (yAxis + size > this.size) return false;
            for (int i = 0; i < size; i++) {
                if (grid[xAxis][yAxis + i] != '~') return false;
            }
            for (int i = 0; i < size; i++) {
                grid[xAxis][yAxis + i] = ship.getSymbol();
            }
        }
        ships.add(ship);
        return true;
    }

    public boolean fireAt(Coordinate target) {
        int xAxis = target.getXAxis();
        int yAxis = target.getYAxis();
        if (xAxis < 0 || xAxis >= size || yAxis < 0 || yAxis >= size) {
            System.out.println("Coordinates out of bounds. Try again.");
            return false;
        }
        if (grid[xAxis][yAxis] == '~') {
            grid[xAxis][yAxis] = 'O';
            return false;
        } else if (grid[xAxis][yAxis] != 'O' && grid[xAxis][yAxis] != 'X') {
            for (Ship ship : ships) {
                if (Character.valueOf(ship.getSymbol()).equals(grid[xAxis][yAxis])) {
                    ship.hit();
                    break;
                }
            }
            grid[xAxis][yAxis] = 'X';
            return true;
        } else {
            System.out.println("Already fired at this location. Try again.");
            return false;
        }
    }

    public void update(Coordinate target, boolean hit) {
        int xAxis = target.getXAxis();
        int yAxis = target.getYAxis();
        grid[xAxis][yAxis] = hit ? 'X' : 'O';
    }

    public boolean allShipsSunk() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    public int getFiredCount() {
        int count = 0;
        for (char[] row : grid) {
            for (char cell : row) {
                if (cell == 'O' || cell == 'X') {
                    count++;
                }
            }
        }
        return count;
    }

    public int getSunkCount() {
        int count = 0;
        for (Ship ship : ships) {
            if (ship.isSunk()) {
                count++;
            }
        }
        return count;
    }

    public int getRemainingShips() {
        int count = 0;
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                count++;
            }
        }
        return count;
    }

    public char[][] getGrid() {
        return grid;
    }
}