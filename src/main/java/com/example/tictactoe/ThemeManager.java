package com.example.tictactoe;

import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;

public class ThemeManager {

    private GridPane gridPane;
    private Theme selectedTheme;

    public ThemeManager(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public void changeThemeBasedOnSelection(RadioButton selectedRadioButton) {
        String themeName = selectedRadioButton.getText();
        selectedTheme = getThemeByName(themeName);

        if (selectedTheme != null) {
            selectedTheme.applyTheme(gridPane);
            applyBoardTheme();
        }
    }

    private void applyBoardTheme() {
        String boardColor = selectedTheme.getBoardColor();
        gridPane.setStyle("-fx-background-color: " + boardColor);
    }

    private Theme getThemeByName(String themeName) {
        switch (themeName) {
            case "Light Theme":
                return new LightTheme();
            case "Dark Theme":
                return new DarkTheme();
            case "Royal Theme":
                return new RoyalTheme();
            case "Red Theme":
                return new RedTheme();
            default:
                return null;
        }
    }
}
