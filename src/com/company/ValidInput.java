package com.company;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidInput {
    String name;
    int shipCounter = 0;
    char[][] fieldShips = new char[10][10];
    char[][] fieldShots = new char[10][10];
    private static int SHIP_COUNTER;

    PlayingField rankingShipsAndShots = new PlayingField(this.fieldShips, this.fieldShots, this.shipCounter);


    public int getShipCounter() {
        return shipCounter;
    }

    public ValidInput() {
    }

    public ValidInput(PlayingField rankingShipsAndShots, String name) {
        this.name = name;
        this.rankingShipsAndShots = rankingShipsAndShots;
    }

    public void showFieldShips() {
        System.out.println("This field ships is " + this.name);
        rankingShipsAndShots.showField(rankingShipsAndShots.getRankingShips());
    }

    public void showFieldShots() {
        System.out.println("This shot field is " + this.name);
        rankingShipsAndShots.showField(rankingShipsAndShots.getShotField());
    }

    public void create() {
        SHIP_COUNTER = 0;
        rankingShipsAndShots.createField();
    }

    Scanner sc = new Scanner(System.in);

    private enum Ship {
        AIRCRAFT_CARRIER("Aircraft Carrier", 5),
        BATTLESHIP("Battleship", 4),
        SUBMARINE("Submarine", 3),
        CRUISER("Cruiser", 3),
        DESTROYER("Destroyer", 2);

        private final String name;
        private final int cells;

        Ship(String name, int cells) {
            this.name = name;
            this.cells = cells;
        }
    }

    public void addShip() {
        rankingShipsAndShots.showField(rankingShipsAndShots.getRankingShips());
        while (SHIP_COUNTER < Ship.values().length) {
            Ship listShip = Ship.values()[SHIP_COUNTER];
            Pattern pattern = Pattern.compile("^([A-J][1-9]|[A-J]1[0])\\s([A-J][1-9]|[A-J]1[0])$");
            System.out.println("Enter the coordinates of the " + listShip.name + " (" + listShip.cells + " cells):");
            String input = sc.nextLine();
            Matcher matcher = pattern.matcher(input);

            if (matcher.find()) {
                String[] coordinates = input.split(" ");
                int x1 = (int) coordinates[0].charAt(0) - 'A';
                int y1 = Integer.parseInt(coordinates[0].substring(1)) - 1;
                int x2 = (int) coordinates[1].charAt(0) - 'A';
                int y2 = Integer.parseInt(coordinates[1].substring(1)) - 1;
                checkInput(x1, x2, y1, y2, listShip.cells, listShip.name);
                SHIP_COUNTER++;

            } else {
                System.out.println("Error! Wrong ship location! Try again:");
                addShip();
            }
        }
    }

    private void checkInput(int x1, int x2, int y1, int y2, int cells, String name) {


        while (true) {
            if (x1 >= 0 && x1 <= 10 && y1 >= 0 && y1 <= 10 && x2 >= 0 && x2 <= 10 && y2 >= 0 && y2 <= 10) {
                if (x1 - x2 == cells - 1 || x2 - x1 == cells - 1 || y1 - y2 == cells - 1 || y2 - y1 == cells - 1) {

                    int shipFrontX = Math.max(x1, x2), shipFrontY = Math.max(y1, y2),
                            shipRearX = Math.min(x1, x2), shipRearY = Math.min(y1, y2);

                    for (int x = Math.max(shipRearX, 1) - 1; x <= Math.min(shipFrontX, 8) + 1; x++) {
                        for (int y = Math.max(shipRearY, 1) - 1; y <= Math.min(shipFrontY, 8) + 1; y++) {
                            if (rankingShipsAndShots.getRankingShips()[x][y] == '0') {
                                System.out.println("Error! Wrong length of the " + name + "!Try again:");
                                addShip();
                            } else {
                                break;
                            }
                        }
                    }
                    rankingShipsAndShots.addingShip(shipRearX, shipRearY, shipFrontX, shipFrontY, rankingShipsAndShots.getRankingShips());

                    rankingShipsAndShots.showField(rankingShipsAndShots.getRankingShips());


                } else {
                    System.out.println("Error! Wrong length of the " + name + "! Try again:");
                    addShip();
                }
                break;

            } else {
                System.out.println("Error! Wrong ship location! Try again:");
                addShip();
            }

        }
    }

    public void fire(char[][] fieldShips, char[][] fieldShots, int shipCounter) {
        if (shipCounter < 17) {
            System.out.println("Strike your opponent's ship");

            Pattern pattern = Pattern.compile("^([A-J][1-9]|[A-J]1[0])$");
            String input1 = sc.nextLine();
            Matcher matcher = pattern.matcher(input1);
            if (matcher.find()) {
                String[] coordinates = input1.split("^[A-J]$");
                int coor1 = (int) input1.charAt(0) - 'A';
                int coor2 = Integer.parseInt(coordinates[0].substring(1)) - 1;
                System.out.println(coor1 + " " + coor2);
                checkInput(coor1, coor2, fieldShips, fieldShots, shipCounter);

            } else {
                System.out.println("Incorrect ship coordinates, please try again!");
                fire(fieldShips, fieldShots, shipCounter);
            }
        } else {
            System.out.println("Game Over");
        }
    }

    private void checkInput(int coor1, int coor2, char[][] fieldShips, char[][] fieldShots, int shipCounter) {
        if (fieldShips[coor1][coor2] == '0' && this.shipCounter < 17) {
            this.shipCounter++;

            rankingShipsAndShots.hit(coor1, coor2, fieldShips);
            rankingShipsAndShots.hit(coor1, coor2, fieldShots);
            if ((fieldShips[Math.max(coor1, 1) - 1][coor2] == '~' || fieldShips[Math.max(coor1, 1) - 1][coor2] == 'x') &&
                    (fieldShips[coor1][Math.max(coor2, 1) - 1] == 'x' || fieldShips[coor1][Math.max(coor2, 1) - 1] == '~') &&
                    (fieldShips[Math.min(coor1, 8) + 1][coor2] == '~' || fieldShips[Math.min(coor1, 8) + 1][coor2] == 'x') &&
                    (fieldShips[coor1][Math.min(coor2, 8) + 1] == '~' || fieldShips[coor1][Math.min(coor2, 8) + 1] == 'x')
            ) {
                System.out.println("You destroyed ship");
            }
            System.out.println("You hit an enemy ship, shoot some more");
            if (this.shipCounter == 17) {
                System.out.println("Game Over");
                System.out.println();
                System.out.println("Ships end!!!)");

            } else {
                fire(fieldShips, fieldShots, shipCounter);
            }
        } else if (this.shipCounter < 17 && (fieldShips[coor1][coor2] == 'x' && fieldShots[coor1][coor2] == 'x') || (fieldShots[coor1][coor2] == '!')) {
            System.out.println("You have already fired at this coordinate, please try again!");
            fire(fieldShips, fieldShots, shipCounter);


        } else if (fieldShips[coor1][coor2] == '~' && this.shipCounter < 17) {
            rankingShipsAndShots.missed(coor1, coor2, fieldShips);
            rankingShipsAndShots.missed(coor1, coor2, fieldShots);
            System.out.println("You didn't hit any enemy ships, another player moves");
            System.out.println();


        }

    }

}











