package manager;

import model.Coordinate;
import model.Player;
import model.Ship;
import model.ShipType;

import java.util.Scanner;

public class PlayerManager {
    private final Player player;

    public PlayerManager(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void placeShips(Scanner scanner) {
        System.out.println("\u001B[32mPlacing ships for " + player.getName() + "\u001B[0m");
        player.getOwnBoard().display();
        for (ShipType type : ShipType.values()) {
            boolean placed = false;
            while (!placed) {
                System.out.println("\u001B[33mEnter the starting coordinate (e.g., A1) and direction (H/V) for " + type + "\u001B[0m");
                String input = scanner.nextLine();
                String[] parts = input.split(" ");
                if (parts.length == 2) {
                    Coordinate start = Coordinate.fromString(parts[0]);
                    boolean isVertical = parts[1].equalsIgnoreCase("V");
                    placed = player.getOwnBoard().placeShip(new Ship(type), start, isVertical);
                    if (!placed) {
                        System.out.println("\u001B[31mInvalid placement. Try again.\u001B[0m");
                    }
                } else {
                    System.out.println("\u001B[31mInvalid input. Try again.\u001B[0m");
                }
            }
            System.out.println();
            player.getOwnBoard().display();
        }
    }

    public boolean hasLost() {
        return player.getOwnBoard().allShipsSunk();
    }

    public void takeTurn(Scanner scanner, PlayerManager opponentManager) {
        boolean hit = false;
        String choice;
        do {
            System.out.println();
            System.out.println("\u001B[32m" + player.getName() + ", it's your turn.\u001B[0m");
            System.out.println("\u001B[36mCurrent status: " + player.getOpponentBoard().getFiredCount() + " cells fired at opponent, " + opponentManager.player.getOwnBoard().getSunkCount() + " opponent's ships sunk, " + player.getOwnBoard().getRemainingShips() + " own ships remaining.");
            System.out.println("\u001B[34m------------------------------\u001B[0m");
            System.out.println("\u001B[34m1. View your board\u001B[0m");
            System.out.println("\u001B[34m2. Enter coordinates to attack\u001B[0m");
            System.out.println("\u001B[34m3. End turn\u001B[0m");
            System.out.println("\u001B[34m------------------------------\u001B[0m");
            System.out.print("\u001B[33mChoose an option: \u001B[0m");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    player.getOwnBoard().display();
                    break;
                case "2":
                    System.out.println();
                    player.getOpponentBoard().display();
                    System.out.println("\u001B[33mEnter the coordinate to fire at (e.g., A1):\u001B[0m");
                    String input = scanner.nextLine();
                    Coordinate target = Coordinate.fromString(input);
                    hit = opponentManager.player.getOwnBoard().fireAt(target);
                    if (hit) {
                        System.out.println("\u001B[31mHit!\u001B[0m");
                    } else {
                        System.out.println("\u001B[34mMiss!\u001B[0m");
                    }
                    player.getOpponentBoard().update(target, hit);
                    player.getOpponentBoard().display();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("\u001B[31mInvalid choice. Try again.\u001B[0m");
                    hit = false;
            }
        } while ((hit || !choice.equals("2")) && !opponentManager.hasLost());
    }
}