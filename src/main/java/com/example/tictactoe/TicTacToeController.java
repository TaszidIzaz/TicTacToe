package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class TicTacToeController {

    private char currentPlayer = 'X';

    @FXML
    private GridPane gridPane;

    @FXML
    private Pane overlayPane;


    private Tile[][] tiles = new Tile[3][3];
    private ThemeManager themeManager;

    private RandomAI randomAI;
    private boolean randomAIMode = false;

    private DefensiveAI defensiveAI;

    private boolean defensiveAIMode = false;

    private GameManager gameManager;

    public boolean isRandomAIMode() {
        return randomAIMode;
    }

    public boolean isDefensiveAIMode() { return defensiveAIMode; }

    private Line winLine;



    @FXML
    private void initialize() {

        gameManager = new GameManager(tiles, this);
        themeManager = new ThemeManager(gridPane);
        defensiveAI = new DefensiveAI(tiles, gameManager);
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
            } else if (isDefensiveAIMode() && currentPlayer == 'O') {
                defensiveAI.makeMove();
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

        if (winLine != null) {
            overlayPane.getChildren().remove(winLine);
            winLine = null;
        }

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
        resetBoard();

        ToggleButton btn = (ToggleButton) actionEvent.getSource();
        defensiveAIMode = btn.isSelected();
        if (defensiveAIMode) {
            if (currentPlayer == 'O') {
                defensiveAI.makeMove();
                switchPlayer();
            }
        }

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

    public void highlightWinningTiles(int... indices) {
        if (indices.length != 6) {
            return;
        }

        double tileSize = 115.0;
        double startX = indices[1] * tileSize + tileSize / 2;
        double startY = indices[0] * tileSize + tileSize / 2;
        double endX = indices[5] * tileSize + tileSize / 2;
        double endY = indices[4] * tileSize + tileSize / 2;

        if (winLine != null) {
            overlayPane.getChildren().remove(winLine);
        }

        winLine = new Line(startX, startY, endX, endY);
        winLine.setStroke(Color.BLACK);
        winLine.setStrokeWidth(2);
        winLine.setMouseTransparent(true);

        overlayPane.getChildren().add(winLine);

        for (int i = 0; i < indices.length; i += 2) {
            tiles[indices[i]][indices[i + 1]].setAsWinningTile();
        }
    }

}
