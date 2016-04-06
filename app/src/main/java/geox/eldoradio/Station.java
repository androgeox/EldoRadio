package geox.eldoradio;

/**
 * Created by Georgiy on 11.03.2016.
 */
public class Station {
    private String name;
    //private String description;
    public String dataStream;
    private int imageResourceId;

    public static final Station[] stations = {
            new Station("Eldoradio", "http://emgspb.hostingradio.ru/eldoradio64.mp3", R.drawable.eldo),
            new Station("KEKS FM", "http://emgspb.hostingradio.ru/keksfmspb64.mp3", R.drawable.keks),
            new Station("Europa+ SPb", "http://emgspb.hostingradio.ru/europaplusspb64.mp3", R.drawable.europa)
    };

    public Station(String name, String dataStream,int imageResourceId){
        this.name = name;
        this.dataStream = dataStream;
        this.imageResourceId = imageResourceId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataStream() {
        return dataStream;
    }

    public void setDataStream(String dataStream) {
        this.dataStream = dataStream;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
