package com.charlesdrews.usabilitytestinglab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements ListFragment.OnZodiacSignSelectedListener {

    String website;

    private boolean mScreenIsLageEnoughForTwoPanes = false;
    private DetailFragment mDetailFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//

        // this class - MainActivity - implements the OnZodiacSignSelectedListener interface
        // defined in ListFragment - so "this" can be set as the listener for the list fragment
        ListFragment listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.list_fragment);
        listFragment.setListener(this);

        //TODO determine which layout file is being used (hint: is there an element in the large-screen
        //TODO  layout that's not in the regular layout?) and if the large screen layout is being used,
        //TODO  then load the detail fragment in MainActivity rather than using DetailActivity
        if (findViewById(R.id.detail_fragment_container) !=null) {
            mDetailFragment = DetailFragment.newInstance(new Bundle());
            getSupportFragmentManager().beginTransaction().add(R.id.detail_fragment_container, mDetailFragment).commit();

        }
    }


    @Override
    public void onZodiacSignSelected(String zodiacSignSelected) {
        //http://stackoverflow.com/questions/5015094/how-to-determine-device-screen-size-category-small-normal-large-xlarge-usin
        if (findViewById(R.id.detail_fragment_container) !=null) {

            mDetailFragment.updateWebView(zodiacSignSelected);


//
//
//
//
//
//            //TODO - if the detail fragment is loaded into MainActivity, update it rather than launching
//            //TODO      the DetailActivity
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.SIGN_KEY, zodiacSignSelected);
            startActivity(intent);
//        }


        }
    }
}
