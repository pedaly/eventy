package pl.nonamesevent.utilities;

import java.util.HashMap;

public class GeoLocationInBoundingCircle {
	private static final double EARTH_RADIUS = 6371;
	// get currentLocation = [lat,lon];
	// get the distance d = 1km and covert into radians
	// r = d/R
	// calculate LatMin LatMax
	// latMin = lat - r;
	// latMax = lat + r;
	// calculate LongMin LongMax
	// lonMin = lon - dLon
	// lonMax = lon + dLon
	// where dLon =
	// calculate precise value of distance between 2 points
	// dist = arccos(sin(lat1) · sin(lat2) + cos(lat1) · cos(lat2) · cos(lon1 -
	// lon2)) · R
	
	public static HashMap<String, Double> getBoundingCords(double latInDegrees , double lonInDegrees, int searchDistane){
		HashMap<String, Double> boundingCords = new HashMap<String, Double>();
		Double radius = searchDistane/EARTH_RADIUS;
		Double deltaLon = calculateDeltaLon(latInDegrees, radius);
		double lat = Math.toRadians(latInDegrees);
		double lon = Math.toRadians(lonInDegrees);
		boundingCords.put("radius", Math.toDegrees(radius));
		boundingCords.put("latMin", Math.toDegrees(calculateLatMin(lat, radius)));
		boundingCords.put("latMax", Math.toDegrees(calculateLatMax(lat, radius)));
		boundingCords.put("lonMin", Math.toDegrees(calculateLonMin(lon, deltaLon)));
		boundingCords.put("lonMax", Math.toDegrees(calculateLonMax(lon, deltaLon)));
		
		return boundingCords;
	}
	public static String createSQLQueryBasedOnCurrentLocation(
			double latInDegrees, double lonInDegrees, double searchDistance,
			String[] columns, String tableName, String latColumnName,
			String lonColumnName) {
		double lat = Math.toRadians(latInDegrees);
		double lon = Math.toRadians(lonInDegrees);
		double radius = searchDistance / EARTH_RADIUS;
		double latMin = calculateLatMin(lat, radius);
		double latMax = calculateLatMax(lat, radius);
		double deltaLon = calculateDeltaLon(lat, radius);
		double lonMin = calculateLonMin(lon, deltaLon);
		double lonMax = calculateLonMax(lon, deltaLon);
		StringBuilder query = new StringBuilder();
		query.append("Select e from Event e WHERE");
		System.out.println("radois " + radius);		
		System.out.println("LAT : " + lat + " LON " + lon);
		System.out.println("LAT : " + latMin + " " + latMax);
		System.out.println("LON : " + lonMin + " " + lonMax);
		System.out.println("dLON : " + deltaLon);
		String exampleQuery= "Select e from Event e WHERE (lat > " + Math.toDegrees(latMin) + " AND lat < " + Math.toDegrees(latMax) + ")";
		return exampleQuery;

		
		// adding columns to list
		/*
		 * if(columns != null){ for(int i = 0; i < columns.length; i++){ // add
		 * comma if it's not start of statement SELECT 1, 2, 3 from... if(i !=
		 * 0){ query.append(", "); } query.append(columns[i]); } }else {
		 * query.append(" t "); } query.append("from "); query.append(tableName
		 * + " t "); query.append("WHERE (");
		 */
		// SELECT * FROM Places WHERE
		// (Lat >= 1.2393 AND Lat <= 1.5532) AND (Lon >= -1.8184 AND Lon <=
		// 0.4221)
		// HAVING
		// acos(sin(1.3963) * sin(Lat) + cos(1.3963) * cos(Lat) * cos(Lon -
		// (-0.6981))) <= 0.1570;
	}

	private static double calculateLatMin(double lat, double r) {
		return lat - r;
	}

	private static double calculateLatMax(double lat, double r) {
		return lat + r;
	}

	private static double calculateLonMin(double lon, double deltaLon) {
		return lon - deltaLon;
	}

	private static double calculateLonMax(double lon, double deltaLon) {
		return lon + deltaLon;
	}

	private static double calculateDeltaLon(double lat, double r) {
		return Math.asin(Math.sin(r) / Math.cos(lat));
	}
}
