package com.example.my;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.my.quanly.flagquantri;
import com.example.my.taikhoan.flagtaikhoan;


public class viewgate extends FragmentStatePagerAdapter {
    public viewgate(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new flagquantri();
            case 1:
                return new flagtaikhoan();
            default:
                return new flagquantri();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
