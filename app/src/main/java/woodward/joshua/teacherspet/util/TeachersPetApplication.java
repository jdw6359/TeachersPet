package woodward.joshua.teacherspet.util;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Joshua on 12/29/2014.
 */
public class TeachersPetApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Parse.initialize(this,"kge28LwBgZKZVWjHMnXJ5ehhT4YUzmTjL4fJ4fwY","5WMSRXtN2f44lcCVDenvnrk5ZmS4TdjyTOhVDBiB");
    }

}
