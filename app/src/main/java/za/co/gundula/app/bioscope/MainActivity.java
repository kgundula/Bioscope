package za.co.gundula.app.bioscope;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import za.co.gundula.app.bioscope.model.BioScope;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener, BioScopeDetailsFragment.OnFragmentInteractionListener {

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.view_bioscope_details_container) != null) {

            mTwoPane = true;
            if (savedInstanceState == null) {

                BioScopeDetailsFragment fragment = new BioScopeDetailsFragment();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.view_bioscope_details_container, new BioScopeDetailsFragment())
                        .commit();
            }

        } else {
            mTwoPane = false;
        }
        //MainFragment mainFragment = ((MainFragment)getSupportFragmentManager().findFragmentById(R.id.movie_fragment));


    }

    public boolean isTablet() {
        return mTwoPane;
    }

    public void replaceFragment(BioScope bioScope) {
        Bundle arguments = new Bundle();
        arguments.putParcelable(BioScopeDetailsActivity.bioscope_object, bioScope);
        arguments.putBoolean(BioScopeDetailsActivity.two_pane, mTwoPane);

        BioScopeDetailsFragment fragment = new BioScopeDetailsFragment();
        fragment.setArguments(arguments);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.view_bioscope_details_container, fragment)
                .commit();

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void setSubTitle(String subTitle) {
        if (subTitle != null)
            getSupportActionBar().setSubtitle(subTitle);
    }
}
