package com.example.tictactoe;

import java.util.List;
import java.util.ArrayList;

public class DefensiveAI {
    private Tile[][] tiles;
    private GameManager gameManager;
    private final int[][][] WINNING_STATES = {
            {{0,0}, {0,1}, {0,2}},
            {{1,0}, {1,1}, {1,2}},
            {{2,0}, {2,1}, {2,2}},
            {{0,0}, {1,0}, {2,0}},
            {{0,1}, {1,1}, {2,1}},
            {{0,2}, {1,2}, {2,2}},
            {{0,0}, {1,1}, {2,2}},
            {{0,2}, {1,1}, {2,0}}
    };

    public DefensiveAI(Tile[][] tiles, GameManager gameManager) {
        this.tiles = tiles;
        this.gameManager = gameManager;
    }

    public void makeMove() {
        Tile defensiveMoveTile = findDefensiveMove('X');

        if (defensiveMoveTile != null) {
            defensiveMoveTile.setOccupiedBy('O');
            gameManager.checkForWinner(defensiveMoveTile);
        } else {
            RandomAI randomAI = new RandomAI(tiles, gameManager);
            randomAI.makeMove();
        }
    }

    private Tile findDefensiveMove(char player) {
        for (int[][] state : WINNING_STATES) {
            List<Tile> tilesInState = new ArrayList<>();
            for (int[] coords : state) {
                tilesInState.add(tiles[coords[0]][coords[1]]);
            }
            if (shouldBlock(tilesInState, player)) {
                for (Tile tile : tilesInState) {
                    if (!tile.isOccupied()) {
                        return tile;
                    }
                }
            }
        }
        return null;
    }

    private boolean shouldBlock(List<Tile> tiles, char player) {
        int playerCount = 0;
        for (Tile tile : tiles) {
            if (tile.getOccupiedBy() == player) {
                playerCount++;
            }
        }
        return playerCount == 2 && tiles.stream().anyMatch(t -> !t.isOccupied());
    }
}