package piniufly.util;

import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.FileReader;
import java.io.IOException;

public class Param {

    public final static String AUDIO_FILES_DIR = "audiofiles";

    public final static String APP_NAME = "JCabin Sounds";

    public static String APP_VERSION;

    static {
        try {
            APP_VERSION = new MavenXpp3Reader().read(new FileReader("pom.xml")).getVersion();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    public static final int MAX_ENTRIES = 24;

    public static final int HEIGHT_WHEN_MAX_ENTRIES = 600;

}
