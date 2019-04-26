import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.Conversion;
import org.apache.commons.lang3.math.NumberUtils;

import static org.apache.commons.lang3.math.NumberUtils.isParsable;

/**
 * simple program to read a .CSV file in Java.
 *
 * @author https://github.com/everettmthunzi
 *
 */
class CSVParser{

    public static void main(String[] args){
        List<Rating> ratings = readCSV("/Users/everett/Desktop/csv-parser/src/data.csv");

        // displaying ratings read from the .csv file
        for (Rating r: ratings){
            System.out.println(r);
        }
    }


    private static List<Rating> readCSV(String fileName) {

        // initializing readCSV variables
        String line = null;
        BufferedReader reader = null;

        Rating rating = null;
        List<Rating> ratings = new ArrayList<>();

        try {
            // creating a BufferedReader instance
            reader = new BufferedReader(new FileReader(fileName));

            //reading each line of the .CSV file
            while ((line = reader.readLine()) != null) {

                // splitting "line" based on the delimiting char (;)
                String[] attributes = line.split(";");

                // creating a rating
                rating = createRating(attributes);

                // ... then appending it to a rating list
                ratings.add(rating);

                // todo: check if all lines have been read !!!!!
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ratings;
    }


    private static Rating createRating(String[] metadata) {
        int styleID = parseValue(metadata[0]);
        String review = metadata[1];
        int stars = parseValue(metadata[2]);

        // creating and returning a rating with these attributes
        return new Rating(styleID, review, stars);
    }

    private static int parseValue(String string){
        int value = 0;
        if (isParsable(string))
            value = Integer.parseInt(string);
        return value;
    }


}

class Rating {
    private int styleID;
    private String review;
    private int stars;

    public Rating(int styleID, String review, int stars) {
        this.styleID = styleID;
        this.review = review;
        this.stars = stars;
    }

    public int getStyleID() {
        return styleID;
    }

    public void setStyleID(int styleID) {
        this.styleID = styleID;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Rating [styleID=" + styleID + ", review=" + review + ", rating=" + stars
                + "]";
    }
}
