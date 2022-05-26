package com.company;

public class PlayingField {
    char[][] rankingShips;
    char[][] shotField;
    int amountShips;


    public PlayingField(char[][] rankingShips, char[][] shotField, int amountShips) {
        this.rankingShips = rankingShips;
        this.shotField = shotField;
        this.amountShips = amountShips;
    }
    public char[][] getShotField() {
        return shotField;
    }

    public char[][] getRankingShips() {
        return rankingShips;
    }

    public void createField() {
        for (int i = 0; i < rankingShips.length; i++) {
            for (int j = 0; j < rankingShips.length; j++) {
                rankingShips[i][j] = '~';
                shotField[i][j] = '~';
            }
        }
    }

    protected char[][] addingShip(int shipRearX, int shipRearY, int shipFrontX, int shipFrontY, char[][] battlefield) {
        for (int i = shipRearX; i <= shipFrontX; i++) {
            for (int j = shipRearY; j <= shipFrontY; j++) {
                battlefield[i][j] = '0';
            }
        }
        return battlefield;
    }

    protected char[][] showField(char[][] battlefield) {
        int number = 1;
        System.out.print("  ");
        for (int i = 0; i < 10; i++) {
            System.out.print(number + " ");
            number++;
        }
        System.out.println();
        char alf = 'A';
        for (int i = 0; i < battlefield.length; i++) {
            System.out.print(alf + " ");
            alf++;
            for (int j = 0; j < battlefield.length; j++) {
                System.out.print(battlefield[i][j] + " ");
            }
            System.out.println();
        }
        return battlefield;
    }

    public char[][] hit(int coor1, int coor2, char[][] battlefield) {
        battlefield[coor1][coor2] = 'x';
        return battlefield;
    }

    public char[][] missed(int coor1, int coor2, char[][] battlefield) {
        battlefield[coor1][coor2] = '!';
        return battlefield;
    }

}

