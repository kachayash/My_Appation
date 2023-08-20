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

            "Operating System: iPhones run on Apple's proprietary operating system called iOS (iPhone Operating System). As of my last update, the latest version was iOS 15.\n" +
            "\n" +
            "Design and Display: iPhones have evolved in terms of design and display technology over the years. They typically feature high-resolution Retina displays with various sizes and resolutions, offering a clear and vibrant visual experience.",
            //Realme

            "Realme 8 Series: This series included phones like the Realme 8 and Realme 8 Pro. They featured AMOLED displays, powerful processors, quad-camera setups, and fast charging capabilities.\n" +
                    "\n" +
                    "Realme Narzo Series: The Narzo series, such as the Realme Narzo 30 Pro, offered budget-friendly gaming-focused smartphones with MediaTek processors and high refresh rate displays.\n" +
                    "\n" +
                    "Realme X Series: The X series, including phones like Realme X7 and X7 Pro, often emphasized stylish designs, AMOLED displays, and good camera performance.\n" +
                    "\n" +
                    "Realme C Series: The C series, like the Realme C11 and C15, targeted the entry-level market with basic features and affordable prices.\n" +
                    "\n" +
                    "Realme GT Series: This series aimed to provide flagship-level performance at competitive prices. The Realme GT and GT Neo were notable models.\n" +
                    "\n" +
                    "Realme UI: Realme's custom Android skin, Realme UI, offered various customization options, features, and optimizations on top of Android.\n" +
                    "\n" +
                    "5G Connectivity: Many of Realme's recent phones incorporated 5G connectivity, aiming to provide future-proof technology.",

            //oppo
            "Oppo Find Series: The Find series typically focused on innovative features and premium designs. The Oppo Find X2 and Find X3 were known for their high-quality displays, powerful processors, and advanced camera systems.\n" +
                    "\n" +
                    "Oppo Reno Series: The Reno series offered a balance of design, performance, and camera capabilities. Models like the Oppo Reno 4 and Reno 5 emphasized camera features and mid-range performance.\n" +
                    "\n" +
                    "Oppo A Series: The A series targeted budget-conscious consumers and offered a variety of options with decent performance and features for their price. Models like the Oppo A52 and A72 were part of this series.\n" +
                    "\n" +
                    "Oppo F Series: The F series was known for its camera capabilities and stylish designs. The Oppo F19 Pro and F19 Pro+ were examples of phones in this series.\n" +
                    "\n" +
                    "Oppo Aesthetic Designs: Oppo often focused on unique and appealing designs, using materials like glass and metal, and exploring colors and finishes.\n" +
                    "\n" +
                    "ColorOS: Oppo's custom Android skin, ColorOS, offered various features, optimizations, and customization options on top of Android.\n" +
                    "\n" +
                    "Camera Innovations: Oppo was known for pushing camera technology forward, including features like motorized pop-up cameras for full-screen displays and advanced camera algorithms.\n" +
                    "\n" +
                    "5G Connectivity: Many Oppo phones incorporated 5G connectivity to provide faster internet speeds and improved network capabilities.",

            //mi
            "Mi Series: Xiaomi's Mi series featured flagship and premium smartphones. The Mi 11 and Mi 11 Pro were notable models, offering high-end specifications, powerful processors, and advanced camera systems.\n" +
                    "\n" +
                    "Redmi Series: The Redmi series offered a balance between performance and affordability. The Redmi Note 10 and Redmi Note 10 Pro, for example, were popular mid-range options known for their value for money.\n" +
                    "\n" +
                    "Poco Series: Poco, a sub-brand of Xiaomi, focused on providing high-performance smartphones at competitive prices. The Poco X3 and Poco F3 were well-received for their strong specifications.\n" +
                    "\n" +
                    "MiUI: Xiaomi's custom Android skin, MIUI, offered a user-friendly interface with various features, customizations, and optimizations on top of Android.\n" +
                    "\n" +
                    "Camera Innovations: Xiaomi often introduced camera innovations, including higher megapixel counts, versatile camera setups, and software enhancements for photography.\n" +
                    "\n" +
                    "5G Connectivity: Many Xiaomi phones incorporated 5G connectivity to provide faster internet speeds and improved network capabilities.\n" +
                    "\n" +
                    "Affordability and Value: Xiaomi was known for its competitive pricing strategy, aiming to provide high-quality smartphones at lower prices compared to competitors.\n" +
                    "\n" +
                    "Diverse Product Range: In addition to smartphones, Xiaomi also offered products like smart TVs, fitness bands, smart home devices, and more.",

            //redmi
            "Redmi Note Series: The Redmi Note series is one of the most popular and well-known lines from Redmi. These phones typically offer mid-range specifications at competitive prices. Models like the Redmi Note 10 and Redmi Note 10 Pro were notable for their large displays, capable processors, and versatile camera systems.\n" +
                    "\n" +
                    "Redmi K Series: The Redmi K series often focuses on providing higher-end features at a relatively affordable price. The Redmi K40 and Redmi K40 Pro were known for offering powerful processors, high refresh rate displays, and strong camera capabilities.\n" +
                    "\n" +
                    "Redmi A Series: The Redmi A series targeted the budget segment, providing basic features and affordability. These phones often appeal to users who prioritize cost-effective options. An example is the Redmi 9A.\n" +
                    "\n" +
                    "Redmi Design and Build: Redmi phones often feature modern designs with glass and plastic combinations, as well as a range of color options.\n" +
                    "\n" +
                    "MIUI: Redmi phones run Xiaomi's custom Android skin, MIUI, which offers various customization options, features, and optimizations on top of Android.\n" +
                    "\n" +
                    "Battery Life: Redmi phones are known for offering good battery life, catering to users who prioritize long-lasting usage.\n" +
                    "\n" +
                    "Value for Money: Redmi's primary focus has been to provide affordable smartphones with competitive specifications and features.\n" +
                    "\n" +
                    "5G Connectivity: Many Redmi phones integrated 5G connectivity to offer future-proof technology and improved network capabilities.",

            //vivo

            "Vivo V Series: The Vivo V series often focuses on camera technology and design. Models like the Vivo V21 and V21e were known for their selfie-centric features and slim designs.\n" +
                    "\n" +
                    "Vivo X Series: The Vivo X series typically offers a combination of innovative features, premium design, and capable cameras. The Vivo X60 and X60 Pro, for example, emphasized camera partnerships with renowned imaging companies.\n" +
                    "\n" +
                    "Vivo S Series: The Vivo S series aims to provide stylish designs and balanced performance. Phones like the Vivo S9 and S9e were known for their slim profiles and camera capabilities.\n" +
                    "\n" +
                    "Vivo Y Series: The Vivo Y series is often targeted at the budget and mid-range segments, offering a mix of features at affordable prices. The Vivo Y20 and Y30 were part of this series.\n" +
                    "\n" +
                    "Camera Innovations: Vivo often introduces camera innovations, such as higher megapixel counts, advanced sensors, and software enhancements for photography.\n" +
                    "\n" +
                    "Design and Build: Vivo places an emphasis on design, often using premium materials like glass and metal, and exploring unique color options.\n" +
                    "\n" +
                    "FunTouch OS: Vivo's custom Android skin, FunTouch OS (now known as OriginOS), offers various features, customizations, and optimizations on top of Android.\n" +
                    "\n" +
                    "5G Connectivity: Many Vivo phones incorporate 5G connectivity to provide faster internet speeds and improved network capabilities.\n" +
                    "\n" +
                    "In-Display Fingerprint Sensor: Vivo was one of the pioneers in introducing in-display fingerprint sensor technology, which became a common feature in many of its phones.",

            //samsung
            "Samsung Galaxy S Series: The Galaxy S series is Samsung's flagship line of smartphones. Models like the Galaxy S21, S21+, and S21 Ultra offered high-end specifications, cutting-edge features, and advanced camera systems.\n" +
                    "\n" +
                    "Samsung Galaxy Note Series: The Galaxy Note series was known for its large displays and S Pen stylus functionality. Note models, like the Galaxy Note 20 and Note 20 Ultra, were popular among productivity-oriented users.\n" +
                    "\n" +
                    "Samsung Galaxy A Series: The Galaxy A series targeted various price points and offered a balance between features and affordability. Phones like the Galaxy A52 and A72 were notable for their mid-range performance and camera capabilities.\n" +
                    "\n" +
                    "Samsung Galaxy M Series: The Galaxy M series focused on providing budget-friendly options with competitive specifications. Models like the Galaxy M31 and M51 were popular choices in this category.\n" +
                    "\n" +
                    "Samsung One UI: Samsung's custom Android skin, One UI, offers a user-friendly interface with various features, customizations, and optimizations on top of Android.\n" +
                    "\n" +
                    "Camera Technology: Samsung often incorporates advanced camera technology, including high megapixel counts, versatile camera setups, and software enhancements for photography.\n" +
                    "\n" +
                    "Display Innovations: Samsung is known for its AMOLED display technology, and many of its phones offer high-quality and vibrant displays.\n" +
                    "\n" +
                    "5G Connectivity: Samsung integrated 5G connectivity into many of its phones to provide faster internet speeds and improved network capabilities.\n" +
                    "\n" +
                    "Samsung Knox: Samsung Knox is a security platform that provides advanced security features to protect user data and privacy.",

            //one plus
            "OnePlus 9 Series: The OnePlus 9 series, including the OnePlus 9 and OnePlus 9 Pro, offered flagship-level specifications and partnerships with Hasselblad for camera technology. These phones focused on providing a premium experience.\n" +
                    "\n" +
                    "OnePlus Nord Series: The OnePlus Nord series targeted the mid-range market, offering a balance between performance and affordability. Models like the OnePlus Nord and Nord CE provided good value for money.\n" +
                    "\n" +
                    "OnePlus 8 Series: The OnePlus 8 series featured phones like the OnePlus 8 and OnePlus 8 Pro, known for their smooth performance, high-refresh-rate displays, and advanced camera capabilities.\n" +
                    "\n" +
                    "OxygenOS: OnePlus's custom Android skin, OxygenOS (now merged with Oppo's ColorOS), offered a clean and customizable interface with various features and optimizations on top of Android.\n" +
                    "\n" +
                    "Performance: OnePlus phones were often praised for their fast and smooth performance, with powerful processors and ample RAM.\n" +
                    "\n" +
                    "Design and Build: OnePlus paid attention to design, often using premium materials like glass and metal to create sleek and attractive smartphones.\n" +
                    "\n" +
                    "Community Involvement: OnePlus maintained a strong relationship with its user community, often incorporating user feedback into software updates and features.\n" +
                    "\n" +
                    "5G Connectivity: Many OnePlus phones integrated 5G connectivity to provide faster internet speeds and improved network capabilities.\n" +
                    "\n" +
                    "Alert Slider: OnePlus devices featured a physical \"Alert Slider\" that allowed users to quickly switch between different notification profiles."
    };


    String[] idArray={"1","2","3","4","5","6","7","8","9","10"};
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