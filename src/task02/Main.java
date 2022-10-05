package task02;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    private static Scanner in = new Scanner(System.in);
    private static int[][] field;

    public static void main(String[] args) {
        readData();

        List<HashSet<int[]>> areas = new ArrayList<>();

        findAreaPoints(areas);

        List<int[][]> areasPoints = executeAreasPoints(areas);

        System.out.println(getCountOfBestArea(areasPoints));
    }

    private static void readData() {
        String[] initData = in.nextLine().split(" ");

        int fieldWidth = Integer.parseInt(initData[0]);
        int fieldHeight = Integer.parseInt(initData[1]);

        field = new int[fieldHeight][fieldWidth];

        int index = 0;
        int steps = fieldHeight;

        while (steps > 0) {
            String[] data = in.nextLine().split(" ");

            for (int i = 0; i < data.length; i++) {
                field[index][i] = Integer.parseInt(data[i]);
            }

            index++;
            steps--;
        }
    }

    private static List<int[][]> executeAreasPoints(List<HashSet<int[]>> areas) {
        List<int[][]> areasPoints = new ArrayList<>();

        for (HashSet<int[]> area : areas) {
            int minX = -1;
            int minY = -1;
            int maxX = -1;
            int maxY = -1;

            for (int[] areaPoint : area) {
                if (minX == -1) {
                    minX = areaPoint[0];
                } else if (minX > areaPoint[0]) {
                    minX = areaPoint[0];
                }

                if (minY == -1) {
                    minY = areaPoint[1];
                } else if (minY > areaPoint[1]) {
                    minY = areaPoint[1];
                }

                if (maxX == -1) {
                    maxX = areaPoint[0];
                } else if (maxX < areaPoint[0]) {
                    maxX = areaPoint[0];
                }

                if (maxY == -1) {
                    maxY = areaPoint[1];
                } else if (maxY < areaPoint[1]) {
                    maxY = areaPoint[1];
                }
            }

            areasPoints.add(new int[][] {{minX, minY}, {maxX, maxY}});
        }

        return areasPoints;
    }

    private static void findAreaPoints(List<HashSet<int[]>> areas) {
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if (field[y][x] == 1 && notFoundInAreas(areas, x, y)) {
                    HashSet<int[]> area = new HashSet<>();
                    area.add(new int[] {x, y});
                    findAllPointsOfArea(area, x, y);
                    areas.add(area);
                }
            }
        }
    }

    private static boolean notFoundInAreas(List<HashSet<int[]>> areas, int x, int y) {
        for (HashSet<int[]> area : areas) {
            for (int[] areaPoint : area) {
                if (areaPoint[0] == x && areaPoint[1] == y) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean notFoundInArea(HashSet<int[]> area, int x, int y) {
        for (int[] areaPoint : area) {
            if (areaPoint[0] == x && areaPoint[1] == y) {
                return false;
            }
        }

        return true;
    }

    private static void findAllPointsOfArea(HashSet<int[]> area, int x, int y) {
        if (x + 1 < field[y].length && field[y][x + 1] == 1 && notFoundInArea(area, x + 1, y)) {
            area.add(new int[] {x + 1, y});
            findAllPointsOfArea(area, x + 1, y);
        }
        if (y + 1 < field.length && field[y + 1][x] == 1 && notFoundInArea(area, x, y + 1)) {
            area.add(new int[] {x, y + 1});
            findAllPointsOfArea(area, x, y + 1);
        }
        if (x - 1 > -1 && field[y][x - 1] == 1 && notFoundInArea(area, x - 1, y)) {
            area.add(new int[] {x - 1, y});
            findAllPointsOfArea(area, x - 1, y);
        }
        if (y - 1 > -1 && field[y - 1][x] == 1 && notFoundInArea(area, x, y - 1)) {
            area.add(new int[] {x, y - 1});
            findAllPointsOfArea(area, x, y - 1);
        }
        if (x + 1 < field[y].length && y + 1 < field.length && field[y + 1][x + 1] == 1 && notFoundInArea(area, x + 1, y + 1)) {
            area.add(new int[] {x + 1, y + 1});
            findAllPointsOfArea(area, x + 1, y + 1);
        }
        if (x - 1 > -1 && y - 1 > -1 && field[y - 1][x - 1] == 1 && notFoundInArea(area, x - 1, y - 1)) {
            area.add(new int[] {x - 1, y - 1});
            findAllPointsOfArea(area, x - 1, y - 1);
        }
        if (x - 1 > -1 && y + 1 < field.length && field[y + 1][x - 1] == 1 && notFoundInArea(area, x - 1, y + 1)) {
            area.add(new int[] {x - 1, y + 1});
            findAllPointsOfArea(area, x - 1, y + 1);
        }
        if (x + 1 < field[y].length && y - 1 > -1 && field[y - 1][x + 1] == 1 && notFoundInArea(area, x + 1, y - 1)) {
            area.add(new int[] {x + 1, y - 1});
            findAllPointsOfArea(area, x + 1, y - 1);
        }
    }

    private static int getCountOfBestArea(List<int[][]> areaPointsList) {
        int countPointsOfArea = 0;
        double resultOfArea = 0;

        for (int[][] areaPoints : areaPointsList) {
            int wholeAreaPoints = 0;
            int goodAreaPoints = 0;
            double result = 0;

            for (int y = 0; y < field.length; y++) {
                for (int x = 0; x < field[y].length; x++) {
                    if (y >= areaPoints[0][1] && y <= areaPoints[1][1] && x >= areaPoints[0][0] && x <= areaPoints[1][0]) {
                        wholeAreaPoints++;
                        if (field[y][x] == 1) {
                            goodAreaPoints++;
                        }
                    }
                }
            }

            result = (double) goodAreaPoints / (double) wholeAreaPoints;

            if (goodAreaPoints < 2) {
                continue;
            }

            if ((result > resultOfArea) || (result == resultOfArea && wholeAreaPoints > countPointsOfArea)) {
                resultOfArea = result;
                countPointsOfArea = wholeAreaPoints;
            }
        }

        return countPointsOfArea;
    }
}
