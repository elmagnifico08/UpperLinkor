package com.company;

public class Launch {
    public Launch() {
        gameLogic();
    }

    public void gameLogic() {
        ValidInput player1 = new ValidInput(new PlayingField(new char[10][10], new char[10][10], 0), "player1");
        ValidInput player2 = new ValidInput(new PlayingField(new char[10][10], new char[10][10], 0), "player2");
        ValidInput countShipPlayer1 = new ValidInput();
        ValidInput countShipPlayer2 = new ValidInput();
        player1.create();
        player1.addShip();
        player2.create();
        player2.addShip();
        boolean rez = true;
        while (rez) {
            if (countShipPlayer1.getShipCounter() == 17) {
                System.out.println("Winner player1");
                break;
            } else if (countShipPlayer2.getShipCounter() == 17) {
                System.out.println("Winner player2");
                break;
            } else {
                if (countShipPlayer2.getShipCounter() != 17) {
                    countShipPlayer1.fire(player2.rankingShipsAndShots.getRankingShips(), player1.rankingShipsAndShots.getShotField(), countShipPlayer2.getShipCounter());
                }if (countShipPlayer1.getShipCounter() != 17) {
                    countShipPlayer2.fire(player1.rankingShipsAndShots.getRankingShips(), player2.rankingShipsAndShots.getShotField(), countShipPlayer1.getShipCounter());
                }
            }
        }
            player1.showFieldShips();
            player1.showFieldShots();
            player2.showFieldShips();
            player2.showFieldShots();




    }
}