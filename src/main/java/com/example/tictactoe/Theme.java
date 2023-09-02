package com.example.tictactoe;

import javafx.scene.layout.GridPane;

public interface Theme {
    void applyTheme(GridPane gridPane);
    String getBoardColor();
}