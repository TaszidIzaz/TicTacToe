package com.example.tictactoe;

public class GameManager {
    private Tile[][] tiles;
    private TicTacToeController controller;

    public GameManager(Tile[][] tiles, TicTacToeController controller) {
        this.tiles = tiles;
        this.controller = controller;
    }

    public void checkForWinner(Tile tile) {
        char currentPlayer = tile.getOccupiedBy();

        for (int row = 0; row < 3; row++) {
            if (tiles[row][0].getOccupiedBy() == currentPlayer &&
                    tiles[row][1].getOccupiedBy() == currentPlayer &&
                    tiles[row][2].getOccupiedBy() == currentPlayer) {
                controller.handleWin(currentPlayer);
                tile.highlightWinningTiles(row, 0, row, 1, row, 2);
                return;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (tiles[0][col].getOccupiedBy() == currentPlayer &&
                    tiles[1][col].getOccupiedBy() == currentPlayer &&
                    tiles[2][col].getOccupiedBy() == currentPlayer) {
                controller.handleWin(currentPlayer);
                tile.highlightWinningTiles(0, col, 1, col, 2, col);
                return;
            }
        }

        if (tiles[0][0].getOccupiedBy() == currentPlayer &&
                tiles[1][1].getOccupiedBy() == currentPlayer &&
                tiles[2][2].getOccupiedBy() == currentPlayer) {
            controller.handleWin(currentPlayer);
            tile.highlightWinningTiles(0, 0, 1, 1, 2, 2);
            return;
        }

        if (tiles[0][2].getOccupiedBy() == currentPlayer &&
                tiles[1][1].getOccupiedBy() == currentPlayer &&
                tiles[2][0].getOccupiedBy() == currentPlayer) {
            controller.handleWin(currentPlayer);
            tile.highlightWinningTiles(0, 2, 1, 1, 2, 0);
        }

        if (controller.isBoardFull()) {
            controller.handleDraw();
        }
    }
}
