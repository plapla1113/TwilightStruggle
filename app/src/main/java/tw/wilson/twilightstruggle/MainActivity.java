package tw.wilson.twilightstruggle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button mBtnStartNewGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnStartNewGame = (Button) findViewById(R.id.btn_start_new_game);
        mBtnStartNewGame.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_start_new_game:
                Intent startStatActivityIntent = new Intent();
                startStatActivityIntent.setClass(MainActivity.this, StatActivity.class);
                startActivity(startStatActivityIntent);
                break;
            default:
                break;
        }
    }
}
