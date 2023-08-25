package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class deshbord extends AppCompatActivity {


    MeowBottomNavigation mbottom;

    int HOME_MENU=1,PROFILE_MENU=2,CART=3,WISH=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deshbord);


        mbottom=findViewById(R.id.dashbord_bottom_navigation);

        mbottom.add(new MeowBottomNavigation.Model(HOME_MENU,R.drawable.baseline_home_24));
        mbottom.add(new MeowBottomNavigation.Model(PROFILE_MENU,R.drawable.ic_user));
        mbottom.add(new MeowBottomNavigation.Model(CART,R.drawable.shoppingcartfull));
        mbottom.add(new MeowBottomNavigation.Model(WISH,R.drawable.favoritefull));




        mbottom.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                if(item.getId()==HOME_MENU){
                    //fragment calll
                    FragmentManager manager =getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.relative_dashbord,new HomeFragment()).commit();
                    //end
                    mbottom.show(HOME_MENU,true);
                } else if (item.getId()==PROFILE_MENU) {
                    FragmentManager manager =getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.relative_dashbord,new update_activity()).commit();
                    mbottom.show(PROFILE_MENU,true);

                }else if (item.getId()==CART){
                    CartFragment.Totalp=0;
                    FragmentManager manager =getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.relative_dashbord,new CartFragment()).commit();
                    mbottom.show(CART,true);
                }else if (item.getId()==WISH){
                    FragmentManager manager =getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.relative_dashbord,new WishFragment()).commit();
                    mbottom.show(WISH,true);
                }
            }
        });

        mbottom.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                // your codes
            }
        });

        mbottom.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                // your codes
            }
        });
        FragmentManager manager =getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.relative_dashbord,new HomeFragment()).commit();

        mbottom.show(HOME_MENU,true);




    }
}