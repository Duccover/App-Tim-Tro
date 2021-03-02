package com.example.my;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView nnavigationView;
    private ViewPager vieww;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thu2);
xuly();
        nnavigationView =(BottomNavigationView) findViewById(R.id.bottem);
        vieww =(ViewPager) findViewById(R.id.viewpagee);
      nnavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem item) {
              switch (item.getItemId())
              {
                  case R.id.trang:
                     vieww.setCurrentItem(0);
                      break;
                  case R.id.tai:
                     vieww.setCurrentItem(1);

              }



              return true;
          }
      });

    }
    private void xuly()
    {
   viewgate cc= new viewgate(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
     vieww.setAdapter(cc);
     vieww.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
         @Override
         public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

         }

         @Override
         public void onPageSelected(int position) {
             switch (position)
             {
                 case 0:
                     nnavigationView.getMenu().findItem(R.id.trang).setChecked(true);
                     break;
                 case 1:
                     nnavigationView.getMenu().findItem(R.id.tai).setChecked(true);
                     break;
             }

         }

         @Override
         public void onPageScrollStateChanged(int state) {

         }
     });
    }



}
