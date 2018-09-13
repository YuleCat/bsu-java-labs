package lab8;

import lab8.gameObjects.tiles.WrongArrayException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new BattleCityApp();
        } catch (WrongArrayException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WrongLevelFileException e) {
            e.printStackTrace();
        }
    }
}
