package vn.techmaster.hoangphan.tourist_app.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Flickphoto {
    public String id;
    public String owner;
    public String secret;
    public String server;
    public String farm;

    public String title;
    public String ispublic;
    public String isfriend;
    public String isfamily;

    public Flickphoto() {
    }

    public Flickphoto(String id, String owner, String secret, String server,
                      String farm, String title, String ispublic, String isfriend,
                      String isfamily) {
        this.id = id;
        this.owner = owner;
        this.secret = secret;
        this.server = server;
        this.farm = farm;
        this.title = title;
        this.ispublic = ispublic;
        this.isfriend = isfriend;
        this.isfamily = isfamily;
    }

    public void fromJSON(JSONObject flickJSON){
        //{"id":"15564212473","owner":"97808811@N03","secret":"915592b1e3","server":"8614","farm":9,
        // "title":"mmexport1420203106797","ispublic":1,"isfriend":0,"isfamily":0}

        try {
            this.id = flickJSON.optString("id");
            this.owner = flickJSON.optString("owner");
            this.secret = flickJSON.optString("secret");
            this.server = flickJSON.optString("server");
            this.farm = flickJSON.optString("farm");

            this.title = flickJSON.optString("title");
            this.ispublic = flickJSON.optString("ispublic");
            this.isfriend = flickJSON.optString("isfriend");
            this.isfamily = flickJSON.optString("isfamily");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPhotoUrl(Boolean big){
        String opt = "n";
        if (big)
            opt ="c";
        String photoUri ="https://farm"+this.farm + ".staticflickr.com/"+this.server+"/"+ this.id+"_"+
                this.secret +"_" + opt +".jpg";
        return photoUri;
    }

}
