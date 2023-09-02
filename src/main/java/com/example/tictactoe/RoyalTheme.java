package com.example.tictactoe;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class RoyalTheme implements Theme {
    @Override
    public void applyTheme(GridPane gridPane) {
        gridPane.getStyleClass().add("royal-theme");
    }

    @Override
    public String getBoardColor() {
        return toRGBCode(Color.ROYALBLUE);
    }

    private String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}