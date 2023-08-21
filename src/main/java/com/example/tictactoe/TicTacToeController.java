package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

public class TicTacToeController {

    private char currentPlayer = 'X';

    @FXML
    private GridPane gridPane;

    private Tile[][] tiles = new Tile[3][3];
    private ThemeManager themeManager;

    private RandomAI randomAI;
    private boolean randomAIMode = false;

    private GameManager gameManager;

    public boolean isRandomAIMode() {
        return randomAIMode;
    }


    @FXML
    private void initialize() {

        gameManager = new GameManager(tiles, this);
        themeManager = new ThemeManager(gridPane);
        randomAI = new RandomAI(tiles, gameManager);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                tiles[row][col] = new Tile(this, tiles, gameManager);
                gridPane.add(tiles[row][col], col, row);
            }
        }
    }

    @FXML
    public void handlePlayerTurn(Tile tile) {
        if (!tile.isOccupied()) {
            tile.setOccupiedBy(currentPlayer);
            switchPlayer();

            if (isRandomAIMode() && currentPlayer == 'O') {
                randomAI.makeMove();
                switchPlayer();
            }
            gameManager.checkForWinner(tile);
        }
    }

    @FXML
    private void handleThemeChange(ActionEvent event) {
        RadioButton selectedRadioButton = (RadioButton) event.getSource();
        themeManager.changeThemeBasedOnSelection(selectedRadioButton);
    }


    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';

    }

    public void resetBoard() {
        currentPlayer = 'X';

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                tiles[row][col].resetTile();
            }
        }
    }

    public void handleRandomAI(ActionEvent actionEvent) {

        resetBoard();

        ToggleButton btn = (ToggleButton) actionEvent.getSource();
        randomAIMode = btn.isSelected();
        if (randomAIMode) {
            if (currentPlayer == 'O') {
                randomAI.makeMove();
                switchPlayer();
            }
        }

    }


    public void handleDefensiveAI(ActionEvent actionEvent) {

    }

    public void restart(ActionEvent actionEvent) {
        resetBoard();
    }

    public void handleWin(char player) {
        String winner = player + " wins!";

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(winner);
        alert.setContentText("Congratulations!");

        alert.showAndWait();
    }

    public boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (!tiles[row][col].isOccupied()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void handleDraw() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("It's a Draw!");
        alert.setContentText(" It's a tie!");

        alert.showAndWait();
    }

}
