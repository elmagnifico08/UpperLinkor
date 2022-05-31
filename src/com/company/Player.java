package com.company;

import java.util.ArrayList;
import java.util.Scanner;

import static com.company.Ship.size;

public class Player {
    Ship ship = new Ship(new int[10][10]);
    Checking checking = new Checking();
    static Scanner sc = new Scanner(System.in);
    String name;
    char[][] rankingShips;
    char[][] shotField;

    public String getName() {
        return name;
    }

    public char[][] getRankingShips() {
        return rankingShips;
    }

    public char[][] getShotField() {
        return shotField;
    }

    int count = 0;


    public Player(String name, char[][] rankingShips, char[][] shotField) {
        this.name = name;
        this.rankingShips = rankingShips;
        this.shotField = shotField;
        for (int i = 0; i < rankingShips.length; i++) {
            for (int j = 0; j < rankingShips.length; j++) {
                rankingShips[i][j] = '~';
                shotField[i][j] = '~';
            }
        }
    }

    public void showField(char[][] battledield) {
        int number = 1;
        System.out.print("  ");
        for (int i = 0; i < 10; i++) {
            System.out.print(number + " ");
            number++;
        }
        System.out.println();
        char alf = 'A';
        for (int i = 0; i < battledield.length; i++) {
            System.out.print(alf + " ");
            alf++;
            for (int j = 0; j < battledield.length; j++) {
                System.out.print(battledield[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void addShip() {

        while (count < size) {
            Ships listShip = Ships.values()[count];
            System.out.println("Enter the coordinates of the " + listShip.name + " (" + listShip.cells + " cells):");
            String input = sc.nextLine();
            if (!checking.checkCoordinateShip(input, this.rankingShips, listShip.cells)) {
                addShip();
            } else {
                String[] coordinates = input.split(" ");
                int x1 = (int) coordinates[0].charAt(0) - 'A';
                int y1 = Integer.parseInt(coordinates[0].substring(1)) - 1;
                int x2 = (int) coordinates[1].charAt(0) - 'A';
                int y2 = Integer.parseInt(coordinates[1].substring(1)) - 1;

                int shipFrontX = Math.max(x1, x2), shipFrontY = Math.max(y1, y2),
                        shipRearX = Math.min(x1, x2), shipRearY = Math.min(y1, y2);
                for (int i = shipRearX; i <= shipFrontX; i++) {
                    for (int j = shipRearY; j <= shipFrontY; j++) {
                        this.rankingShips[i][j] = '0';
                        for (int k = 0; k <= listShip.cells; k++) {

                        }
                    }
                }
                showField(this.rankingShips);
                count++;
            }
        }
    }

    public void fire(Player player) {
        System.out.println(this.name + " strike your opponent's ship");
        String input = sc.nextLine();
        if (!checking.checkCoordinateShot(input)) {
            fire(player);
        } else {
            String[] coordinates = input.split("^[A-J]$");
            int coor1 = (int) input.charAt(0) - 'A';
            int coor2 = Integer.parseInt(coordinates[0].substring(1)) - 1;
            if (player.getRankingShips()[coor1][coor2] == '0') {
                player.getRankingShips()[coor1][coor2] = 'x';
                ship.takeDamage(coor1, coor2);


            }

        }

    }
}


