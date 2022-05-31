package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checking {
    boolean checkCoordinateShip(String input, char[][] battlefield, int cells) {
        Pattern pattern = Pattern.compile("^([A-J][1-9]|[A-J]1[0])\\s([A-J][1-9]|[A-J]1[0])$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String[] coordinates = input.split(" ");
            int x1 = (int) coordinates[0].charAt(0) - 'A';
            int y1 = Integer.parseInt(coordinates[0].substring(1)) - 1;
            int x2 = (int) coordinates[1].charAt(0) - 'A';
            int y2 = Integer.parseInt(coordinates[1].substring(1)) - 1;
            while (true) {
                if (x1 >= 0 && x1 <= 10 && y1 >= 0 && y1 <= 10 && x2 >= 0 && x2 <= 10 && y2 >= 0 && y2 <= 10) {
                    if (x1 - x2 == cells - 1 || x2 - x1 == cells - 1 || y1 - y2 == cells - 1 || y2 - y1 == cells - 1) {

                        int shipFrontX = Math.max(x1, x2), shipFrontY = Math.max(y1, y2),
                                shipRearX = Math.min(x1, x2), shipRearY = Math.min(y1, y2);

                        for (int x = Math.max(shipRearX, 1) - 1; x <= Math.min(shipFrontX, 8) + 1; x++) {
                            for (int y = Math.max(shipRearY, 1) - 1; y <= Math.min(shipFrontY, 8) + 1; y++) {
                                if (battlefield[x][y] == '0') {
                                    System.out.println("Error! Wrong length! Try again:");

                                } else {
                                    break;
                                }
                            }
                        }
                        return true;

                    } else {
                        System.out.println("Error! Wrong length of the! Try again:");
                        return false;
                    }

                } else {
                    System.out.println("Error! Wrong ship location! Try again:");
                    return false;
                }

            }
        } else {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        }
    }

    public boolean checkCoordinateShot(String input) {
        Pattern pattern = Pattern.compile("^([A-J][1-9]|[A-J]1[0])$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String[] coordinates = input.split("^[A-J]$");
            int coor1 = (int) input.charAt(0) - 'A';
            int coor2 = Integer.parseInt(coordinates[0].substring(1)) - 1;
            return true;

        } else {
            System.out.println("Incorrect ship coordinates, please try again!");
            return false;

        }
    }
}
