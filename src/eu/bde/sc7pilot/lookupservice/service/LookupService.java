package eu.bde.sc7pilot.lookupservice.service;

import java.awt.geom.Point2D;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.WKTWriter;

import eu.bde.sc7pilot.lookupservice.FuzzySearch;
import eu.bde.sc7pilot.lookupservice.Location;
import eu.bde.sc7pilot.lookupservice.webconfig.ResponseMessage;

public class LookupService {

	@POST
	@Path("/geocode")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getCoordinates(List<String> locationNames) {
		ResponseMessage respMessage = new ResponseMessage();
		if (locationNames == null || locationNames.isEmpty()) {
			respMessage.setMessage("a non empty list of location names is required into the request body.");
			respMessage.setCode(400);
			throw new WebApplicationException(
					Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(respMessage).build());
		}
		String filePath = "/home/efi/SNAP/files/gadm28v2.txt";
		FuzzySearch fs = null;
		try {
			fs = new FuzzySearch(filePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Geometry> geometries = new ArrayList<Geometry>();
		for (int i = 0; i < locationNames.size(); i++) {
			Location result = fs.processQuery(locationNames.get(i));
			//System.out.println(result.getGeometry());
			if (result != null) {

				String geom = result.getGeometry().replaceAll("[^0-9.]", " ");
				Scanner sc = new Scanner(geom);
				Coordinate[] newPolygonCoords = new Coordinate[5];
				Point2D leftPoint = new Point2D.Double(sc.nextDouble(), sc.nextDouble());
				Point2D rightPoint = new Point2D.Double(sc.nextDouble(), sc.nextDouble());
				sc.close();

				newPolygonCoords[0] = new Coordinate(leftPoint.getX(), rightPoint.getY());
				newPolygonCoords[1] = new Coordinate(leftPoint.getX(), leftPoint.getY());
				newPolygonCoords[2] = new Coordinate(rightPoint.getX(), leftPoint.getY());
				newPolygonCoords[3] = new Coordinate(rightPoint.getX(), rightPoint.getY());
				newPolygonCoords[4] = new Coordinate(leftPoint.getX(), rightPoint.getY());

				GeometryFactory geometryFactory = new GeometryFactory();
				Polygon geometry = geometryFactory.createPolygon(newPolygonCoords);
				WKTWriter writer = new WKTWriter();
				System.out.println(writer.write(geometry));
				geometries.add(geometry);
			} else {
				geometries.add(i, null);
			}

		}
		return Response.status(200).entity(geometries).build();
	}

}
