/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.bde.sc7pilot.lookupservice;

import java.awt.geom.Point2D;
import java.net.URL;
import java.util.Scanner;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.WKTWriter;

import eu.bde.sc7pilot.lookupservice.service.LookupService;

/**
 *
 * @author efi
 */
public class Main {

    public static void main(String[] args) {
    	String filePath =ClassLoader.getSystemResource("gadm28.csv").getPath();
    	//URL filePath = Main.class.getResource();
    	//toString();
    	System.out.println(filePath);
      //  String filePath = "/home/efi/SNAP/files/gadm28v2.txt";
//        FuzzySearch fs = null;
//        try {
//            fs = new FuzzySearch(filePath);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        String locationName = "Paris";
//        Location result = null;
//		try {
//			result = fs.processQuery(locationName);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        System.out.println("scanner started");
//        String geom = result.getGeometry().replaceAll("[^0-9.]", " ");
//        System.out.println(geom);
//        Scanner sc = new Scanner(geom);
//        Coordinate[] newPolygonCoords = new Coordinate[5];
//        Point2D leftPoint = new Point2D.Double(sc.nextDouble(), sc.nextDouble());
//        Point2D rightPoint = new Point2D.Double(sc.nextDouble(), sc.nextDouble());
//
//        newPolygonCoords[0] = new Coordinate(rightPoint.getX(), leftPoint.getY());
//        newPolygonCoords[1] = new Coordinate(rightPoint.getX(), rightPoint.getY());
//        newPolygonCoords[2] = new Coordinate(leftPoint.getX(), rightPoint.getY());
//        newPolygonCoords[3] = new Coordinate(leftPoint.getX(), leftPoint.getY());
//        newPolygonCoords[4] = new Coordinate(rightPoint.getX(), leftPoint.getY());
//
//        GeometryFactory geometryFactory = new GeometryFactory();
//        Polygon geometry = geometryFactory.createPolygon(newPolygonCoords);
//        WKTWriter writer = new WKTWriter();
//        System.out.println(writer.write(geometry));
//        // close the scanner
//        sc.close();
////        try {
////			wktReader.read(result.getGeometry());
////		} catch (ParseException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//        System.out.println(result.getGeometry());
    }
}
