/////////////////////////////////////////////////////////////////////////
//                                                                     //
//                                                                     //
//       class name: ViewPagerAdapter.java                             //
//                                                                     //
/////////////////////////////////////////////////////////////////////////
//                                                                     //
//      Revision log                                                   //
//     --------------                                                  //
//                                                                     //
//      Created by                              Madhura Bhat           //
//      Modified for tab function               Madhura                //
//      Modified for code review                Priyanka               //
/////////////////////////////////////////////////////////////////////////

package com.sharity.itu.sharity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


/**
 * Created by Madhura on 8/18/2016.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    String userName;
    Bundle bundle = new Bundle();

    public ViewPagerAdapter(FragmentManager fm, String  name) {
        super(fm);
        this.userName = name;
    }

    //Calling new tab for every tab change/tab swipe
    @Override
    public Fragment getItem(int position) {

        bundle.putString("Name", userName);

         switch (position) {
            case 0:
                Tab1Fragment tab1 = new Tab1Fragment();
                tab1.setArguments(bundle);
                return tab1;
            case 1:
                Tab2Fragment tab2 = new Tab2Fragment();
                tab2.setArguments(bundle);
                return tab2;
            case 2:
                Tab3Fragment tab3 = new Tab3Fragment();
                tab3.setArguments(bundle);
                return tab3;
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

}