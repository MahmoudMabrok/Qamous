package tools.mahmoudmabrok.tawasol.feature.home;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tools.mahmoudmabrok.tawasol.R;
import tools.mahmoudmabrok.tawasol.feature.Utils;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.imRes)
    ImageView mImRes;
    @BindView(R.id.edWordTotranslate)
    EditText mEdWordTotranslate;
    @BindView(R.id.btnEvaluate)
    ImageButton mBtnEvaluate;
    @BindView(R.id.root)
    LinearLayout mRoot;

    MainViewModel mainViewModel;
    @BindView(R.id.spScoreboard)
    SpinKitView mSpScoreboard;
    @BindView(R.id.tvInfo)
    TextView mTvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @OnClick(R.id.btnEvaluate)
    public void onViewClicked() {
        mainViewModel.evaluate(mEdWordTotranslate.getText().toString());
        mainViewModel.value.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mImRes.setVisibility(View.VISIBLE);
                switch (s) {
                    case "1":
                        mImRes.setImageResource(R.drawable.m1);
                        break;
                    case "2":
                        mImRes.setImageResource(R.drawable.m2);
                        break;
                    case "3":
                        mImRes.setImageResource(R.drawable.m3);
                        break;

                    default:
                        mImRes.setVisibility(View.GONE);

                }
                Utils.hideInputKeyboard(MainActivity.this);
            }
        });
    }


}
