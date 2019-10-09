package tools.mahmoudmabrok.tawasol.feature.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class MainViewModel extends AndroidViewModel {

    MutableLiveData<String> value = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void evaluate(String text) {
        // call API and return res
        switch (text) {
            case "1":
                value.setValue("1");
                break;
            case "2":
                value.setValue("2");
                break;
        }
    }
}
