package eu.bde.sc7pilot.lookupservice.service;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.vividsolutions.jts.geom.Geometry;
import eu.bde.sc7pilot.lookupservice.FuzzySearch;
import eu.bde.sc7pilot.lookupservice.Location;
import eu.bde.sc7pilot.lookupservice.webconfig.ResponseMessage;

@Path("/")
public class LookupService {

    private final static String filePath = "/gadm28.csv";
    private FuzzySearch fs;
    
    public LookupService() {
        try {
            fs = new FuzzySearch(filePath);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            respMessage.setMessage(e.getMessage());
            respMessage.setCode(400);
            throw new WebApplicationException(
                    Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(respMessage).build());
        }
    }
    
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

        List<Geometry> geometries = new ArrayList<Geometry>();
        for (int i = 0; i < locationNames.size(); i++) {
            Location result;
            try {
                result = fs.processQuery(locationNames.get(i));
            } catch (Exception e) {
                respMessage.setMessage(e.getMessage());
                respMessage.setCode(400);
                throw new WebApplicationException(
                        Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(respMessage).build());
            }
            
            if (result != null) {
                Geometry geometry = result.getGeometry();
                System.out.println(geometry.toText());
                geometries.add(geometry);
            } else {
                geometries.add(i, null);
            }

        }
        return Response.status(200).entity(geometries).build();
    }
}
