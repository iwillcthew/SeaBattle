package model;

public class Ship {
    private ShipType type;
    private int size;
    private int hits;

    public Ship(ShipType type) {
        this.type = type;
        this.size = type.getSize();
        this.hits = 0;
    }

    public int getSize() {
        return size;
    }

    public char getSymbol() {
        return type.getSymbol();
    }

    public void hit() {
        hits++;
    }

    public boolean isSunk() {
        return hits >= size;
    }

    public int getHits() {
        return hits;
    }
}