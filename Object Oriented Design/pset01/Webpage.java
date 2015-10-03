/**
 *
 */

public class Webpage implements IPublication{
    private final String title, url, retrieved;

    /**
     *
     * @param title
     * @param url
     * @param retrieved
     */
    public Webpage(String title, String url, String retrieved) {
        this.title = title;
        this.url = url;
        this.retrieved = retrieved;
    }

    /**
     *
     * @return
     */
    @Override
    public String citeApa() {
        return title + "." + " Retrieved " + retrieved + "," + " from " + url + ".";
    }

    /**
     *
     * @return
     */
    @Override
    public String citeMla() {
        return title + "." + " Web. " + retrieved + " <" + url + ">" + ".";
    }
}