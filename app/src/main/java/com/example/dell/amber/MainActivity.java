package com.example.dell.amber;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hanuor.amber.Amber;
import com.hanuor.amber.hub.OnTaskCompletion;
import com.hanuor.amber.hub.PairHelper;

import java.util.ArrayList;

;
;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        Amber.with(MainActivity.this).load("For months, the four scientific instruments at the heart of the James Webb Space Telescope have been sealed in what looks like a huge pressure cooker. It's a test chamber that simulates the grueling operating conditions they will face after Webb is launched into orbit in 2018. But in fact, \\\"pressure cooker\\\" is an apt metaphor for the whole project. The infrared Webb observatory is the biggest, most complex, and most expensive science mission that NASA has ever attempted. Like that of its predecessor, the Hubble Space Telescope, Webb's construction has been plagued by redesigns, schedule slips, and cost overruns that have strained relationships with contractors, international partners, and supporters in the U.S. Congress. Lately the project has largely stuck to its schedule and its $8 billion budget. But plenty could still go wrong, and the stakes are high: Both the future of space-based astronomy and NASA's ability to build complex science missions depend on its success.")
                .call(new OnTaskCompletion() {
                    @Override
                    public void onComplete(ArrayList<PairHelper> response) {
                        for(int i =0; i< response.size(); i++){
                            String bm = response.get(i).getWikiName();
                            String bb = response.get(i).getName();
                            tv.setText(bb+", "+bm);
                        }
                    }
                });
    }
}
