package com.example.tictactoe;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {
    private char occupiedBy = ' ';
    private TicTacToeController controller;
    private Tile[][] tiles;

    private Rectangle border = new Rectangle(100, 100);
    private Text text = new Text();

    private GameManager gameManager;

    public Tile(TicTacToeController controller, Tile[][] tiles, GameManager gameManager) {
        this.controller = controller;
        this.tiles = tiles;
        this.gameManager = gameManager;
        border.setFill(null);
        border.setStroke(Color.BLACK);
        text.setFont(Font.font(40));
        getChildren().addAll(border, text);
        setOnMouseClicked(e -> handleClick());
    }

    public boolean isOccupied() {
        return occupiedBy != ' ';
    }

    public char getOccupiedBy() {
        return occupiedBy;
    }

    public void setOccupiedBy(char player) {
        occupiedBy = player;
        text.setText(Character.toString(player));
    }

    private void handleClick() {
        if (!isOccupied()) {
            controller.handlePlayerTurn(this);
        }
    }

    public void resetTile() {
        occupiedBy = ' ';
        text.setText("");
        border.setStroke(Color.BLACK);
    }

    public void setAsWinningTile() {
        border.setStrokeWidth(1);
        border.setStroke(Color.RED);
    }

    public void highlightWinningTiles(int... indices) {
        for (int i = 0; i < indices.length; i += 2) {
            tiles[indices[i]][indices[i + 1]].setAsWinningTile();
        }
    }
}
