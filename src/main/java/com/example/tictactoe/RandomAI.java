package com.example.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomAI {
    private Tile[][] tiles;
    private GameManager gameManager;

    public RandomAI(Tile[][] tiles, GameManager gameManager) {
        this.tiles = tiles;
        this.gameManager = gameManager;
    }

    public void makeMove() {
        List<Tile> availableTiles = new ArrayList<>();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (!tiles[row][col].isOccupied()) {
                    availableTiles.add(tiles[row][col]);
                }
            }
        }

        if (!availableTiles.isEmpty()) {
            Random rand = new Random();
            Tile randomTile = availableTiles.get(rand.nextInt(availableTiles.size()));
            randomTile.setOccupiedBy('O');

            gameManager.checkForWinner(randomTile);
        }
    }
}
