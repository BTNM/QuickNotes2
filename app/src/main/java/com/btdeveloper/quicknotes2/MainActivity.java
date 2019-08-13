package com.btdeveloper.quicknotes2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;

    private SectionPageAdapter sectionPageAdaper;
    private ViewPager mViewPager;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sectionPageAdaper = new SectionPageAdapter(getSupportFragmentManager() );

        initializeTabLayout();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Quick Notes");



        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {

            }
        });


    }


    private void initializeTabLayout() {
        //ViewPagers animates screen slides automatically, then add the different tabs/fragment to the viewpager
        mViewPager = (ViewPager) findViewById(R.id.viewPagerContainer);

        initializeViewPager(mViewPager);

        //connect the viewPager to the tabLayout in the xml
        TabLayout tabLayout = (TabLayout) findViewById(R.id.allTabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void initializeViewPager(ViewPager viewPager) {
        // SectionPageAdapter extends FragmentPageAdapter to add all fragments into a fragment list
        sectionPageAdaper = new SectionPageAdapter (getSupportFragmentManager() );
        sectionPageAdaper.addFragment(new tabFragment(), "Quick Notes");
        sectionPageAdaper.addFragment(new tabFragment(), "tab2");

        viewPager.setAdapter(sectionPageAdaper);
    }

}
