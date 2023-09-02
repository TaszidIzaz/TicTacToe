package com.example.tictactoe;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class RedTheme implements Theme {
    @Override
    public void applyTheme(GridPane gridPane) {
        gridPane.getStyleClass().add("red-theme");
    }

    @Override
    public String getBoardColor() {
        return toRGBCode(Color.FIREBRICK);
    }

    private String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}