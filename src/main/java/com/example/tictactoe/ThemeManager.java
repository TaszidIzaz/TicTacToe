package com.example.tictactoe;

import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ThemeManager {

    private GridPane gridPane;

    public ThemeManager(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public void changeThemeBasedOnSelection(RadioButton selectedRadioButton) {
        String theme = selectedRadioButton.getText();

        gridPane.getStyleClass().removeAll("light-theme", "dark-theme", "royal-theme", "red-theme");

        if (theme.equals("Light Theme")) {
            gridPane.getStyleClass().add("light-theme");
        } else if (theme.equals("Dark Theme")) {
            gridPane.getStyleClass().add("dark-theme");
        } else if (theme.equals("Royal Theme")) {
            gridPane.getStyleClass().add("royal-theme");
        } else if (theme.equals("Red Theme")) {
            gridPane.getStyleClass().add("red-theme");
        }

        changeBoardTheme(theme);
    }

    private void changeBoardTheme(String theme) {
        Color boardColor = Color.WHITE;

        if (theme.equals("Dark Theme")) {
            boardColor = Color.DARKGRAY;
        } else if (theme.equals("Royal Theme")) {
            boardColor = Color.ROYALBLUE;
        } else if (theme.equals("Red Theme")) {
            boardColor = Color.FIREBRICK;
        }

        gridPane.setStyle("-fx-background-color: " + toRGBCode(boardColor));
    }

    private String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}