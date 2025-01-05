package model;

import manager.BoardManager;

public class Player {
    private final String name;
    private final BoardManager ownBoard;
    private final BoardManager opponentBoard;

    public Player(String name) {
        this.name = name;
        ownBoard = new BoardManager();
        opponentBoard = new BoardManager();
    }

    public String getName() {
        return name;
    }

    public BoardManager getOwnBoard() {
        return ownBoard;
    }

    public BoardManager getOpponentBoard() {
        return opponentBoard;
    }
}