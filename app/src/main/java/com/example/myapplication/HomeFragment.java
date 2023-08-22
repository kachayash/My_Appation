package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class HomeFragment extends Fragment {

    RecyclerView recyclerView;

    String[] phoneArray = {"Iphone" , "Realme" , "Oppo" ,"Mi" , "Redmi" , "Vivo" , "Samsung" ,"One plus"};
    int[] phone_imageArray={R.drawable.apple ,R.drawable.realme,R.drawable.oppo,R.drawable.mi,R.drawable.redmi,R.drawable.vivo,R.drawable.samsung,R.drawable.oneplus, };

    String[] priceArray={"8000","7000","6000","5000","4000","3000","2000","1000"};

    String [] phone_desc={
            //iphone
            "Apple",
            //Realme
            "Realme",
            //oppo
            "Oppo",
            //mi
            "Mi",
            //redmi
            "Redmi",
            //samsung
            "Samsung",
            //one plus
            "Oneplus"
    };


    String[] idArray={"1","2","3","4","5","6","7","8"};
    RecyclerView cat;
    String catnameArray[] = {"Phone" , "Grocery","Fashion","Electronics" ,"Accessories"};
    int catImgArray[]={R.drawable.phone,R.drawable.grocery,R.drawable.shop,R.drawable.smarthome,R.drawable.phonecase};


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView=view.findViewById(R.id.home_recycle);
        cat=view.findViewById(R.id.home_recycle_category);
        //list
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //grad
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        cat.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        cat.setItemAnimator(new DefaultItemAnimator());

        PhoneAdapter adapter=new PhoneAdapter(getActivity(),phoneArray,phone_imageArray , priceArray,phone_desc,idArray );
        recyclerView.setAdapter(adapter);

        CategoryAdapter catadpater = new CategoryAdapter (getActivity(),catImgArray,catnameArray);
        cat.setAdapter(catadpater);


        return  view;


    }
}