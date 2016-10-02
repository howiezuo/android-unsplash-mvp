package howiezuo.github.io.unsplash.model;

public class Photo {

    String id;
    int width;
    int height;
    String color;
    int likes;
    boolean liked_by_user;

    User user;

    Links links;
    Urls urls;


    public String getColor() {
        return color;
    }

    public Urls getUrls() {
        return urls;
    }
}
