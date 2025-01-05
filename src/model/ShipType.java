package model;

public enum ShipType {
    PATROL_BOAT(2, 'P'),
    DESTROYER_BOAT(4, 'D'),
    SUBMARINE(3, 'S'),
    BATTLE_SHIP(5, 'B');

    private final int size;
    private final char symbol;

    ShipType(int size, char symbol) {
        this.size = size;
        this.symbol = symbol;
    }

    public int getSize() {
        return size;
    }

    public char getSymbol() {
        return symbol;
    }
}