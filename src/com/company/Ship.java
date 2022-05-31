package com.company;

public class Ship {

    static int size = 5;
    private int health;
    int[][] allShips;

    public Ship(int[][] allShips) {
        this.allShips = allShips;
    }

    public void setAllShips(int[][] allShips) {
        this.allShips = allShips;
    }

    public int[][] getAllShips() {
        return allShips;
    }

    public void takeDamage(int coor1, int coor2) {


    }


    public void aliveOrNot() {
        for (int[] ships : allShips) {
            if (ships == allShips[-1]) {

            }
        }
    }

}
