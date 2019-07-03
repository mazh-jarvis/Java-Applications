package ca.jrvs.apps.twitter.dao.helper;

public class URIBuilder {

    private StringBuilder URIString;
    private boolean multiParam,
        setEndpoint;

    public URIBuilder(){
        this.URIString = new StringBuilder();
    }

    public URIBuilder base(String baseURI) {
        if(this.URIString.length() != 0) {
            System.err.println("#WAR: Base URI was already set");
            return this;
        }
        this.URIString.append(baseURI);
        return this;
    }

    public URIBuilder endpoint(String endpoint) {
        if (setEndpoint) {
            System.err.println("#WAR: Endpoint was already set");
            return this;
        }
        this.URIString.append("/" + endpoint + ".json?");
        setEndpoint = true;
        return this;
    }

    public URIBuilder param(String key, String value) {
        if(this.multiParam)
            this.URIString.append("&");
        this.URIString.append(key + "=" + value);
        this.multiParam = true;
        return this;
    }

    @Override
    public String toString() {
        return URIString.toString();
    }
}