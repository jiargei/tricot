package com.messfuchs.geo.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class DataReader extends DataStreamer implements Readable {

    public String inputFile;
    public DataReader (String inputFile) {
        this.inputFile = inputFile;
    }

    public abstract TachyResponse parseResponseLine(String line);

    public abstract Coordinate parseCoordinateLine(String line);

    public abstract Site parseSiteLine(String line);

    public abstract Station parseStationLine(String line);

    public void readData() {
        File file = new File(this.inputFile);
        Site site = null;
        Station station = null;

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                // System.out.println("Parsing '" + line + "'");

                // Define Site

                if (site == null) {
                    site = new Site();
                    this.siteSet.add(site);
                }

                Site tempSite = parseSiteLine(line);
                if (tempSite != null) {
                    site = tempSite;
                    this.siteSet.add(site);
                    continue;
                }

                // Define Station

                if (station == null) {
                    station = new Station();
                }

                Station tempStation = parseStationLine(line);
                if (tempStation != null) {
                    station = tempStation;
                    continue;
                }

                // Define TachyMeasurement & Coordinate

                TachyResponse tachyResponse = parseResponseLine(line);
                if (tachyResponse != null) {
                    TachyMeasurement tachyMeasurement = new TachyMeasurement(station.name, tachyResponse.target, tachyResponse);
                    station.addTachyMeasurement(tachyMeasurement);
                    continue;
                }

                Coordinate coordinate = parseCoordinateLine(line);
                if (coordinate != null) {
                    site.addCoordinate(coordinate);
                    continue;
                }
            }
            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}