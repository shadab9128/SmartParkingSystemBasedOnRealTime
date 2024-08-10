package com.example.smartparkingsystem.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.smartparkingsystem.R;
import com.example.smartparkingsystem.adapter.ModuleAdapter;
import com.example.smartparkingsystem.module.Module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment implements BaseSliderView.OnSliderClickListener,ViewPagerEx.OnPageChangeListener{
    private RecyclerView moduleRecyclerView;
    private List<Module> moduleList;
    private SliderLayout sliderLayout;
    private HashMap<String, Integer> Hash_file_maps;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        initview(view);
        mysliderimage();
        mymodule();
        moduleRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        //give modules list to the custom moduleadapter class
        ModuleAdapter moduleAdapter = new ModuleAdapter(moduleList);
        //call listner on module adapter
        moduleAdapter.setOnMyModuleClickListener(new ModuleAdapter.MyModuleClickListener() {
            @Override
            public void onModuleClick(View view, int position) {
                NavController navController = Navigation.findNavController(view);
                //get Module object from module list
                Module module = moduleList.get(position);
                if (module.getName().equalsIgnoreCase(getResources().getString(R.string.menu_Bookparking)))
                {
                    //go to book parking page
                    navController.navigate(R.id.action_nav_home_to_nav_bookparking);
                    Toast.makeText(getActivity(), "Booking", Toast.LENGTH_SHORT).show();
                }
                else if (module.getName().equalsIgnoreCase(getResources().getString(R.string.menu_Profile)))
                {
                    //go to profile page
                    navController.navigate(R.id.action_nav_home_to_profileFragment);
                    Toast.makeText(getActivity(), "Profile", Toast.LENGTH_SHORT).show();
                }
                else if (module.getName().equalsIgnoreCase(getResources().getString(R.string.menu_Parkinghistory)))
                {
                    //go to parking history page
                    navController.navigate(R.id.action_nav_home_to_parkinghistoryFragment);
                    Toast.makeText(getActivity(), "Parking", Toast.LENGTH_SHORT).show();
                }
                else if (module.getName().equalsIgnoreCase(getResources().getString(R.string.menu_Addparking)))
                {
                    //go to add parking page
                    navController.navigate(R.id.action_nav_home_to_nav_addparking);
                    Toast.makeText(getActivity(), "Add Parking", Toast.LENGTH_SHORT).show();
                }
            }
        });
        moduleRecyclerView.setAdapter(moduleAdapter);

    }

    private void mysliderimage() {
        Hash_file_maps = new HashMap();
        Hash_file_maps.put("Always check your area around.", R.drawable.park1);
        Hash_file_maps.put("There must not be litter on the ground.", R.drawable.park2);
        Hash_file_maps.put("Keep Parking clean to make it disease free.", R.drawable.park3);
        Hash_file_maps.put("Come, join and pledge together to park.", R.drawable.park4);
        Hash_file_maps.put("always follow the lane.", R.drawable.park5);

        for (String name : Hash_file_maps.keySet())
        {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView
                    .description(name)
             .image(Hash_file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);
            sliderLayout.addSlider(textSliderView);

        }
        }

        private void mymodule() {
        Module m1=new Module(getResources().getString(R.string.menu_Bookparking),R.drawable.booking);
        Module m2=new Module(getResources().getString(R.string.menu_Profile),R.drawable.profile);
        Module m3=new Module(getResources().getString(R.string.menu_Addparking),R.drawable.add_parking);
        Module m4=new Module(getResources().getString(R.string.menu_Parkinghistory),R.drawable.parking_history);

        moduleList=new ArrayList<>();
        moduleList.add(m1);
        moduleList.add(m2);
        moduleList.add(m3);
        moduleList.add(m4);
    }

    private void initview(View view) {
        moduleRecyclerView = view.findViewById(R.id.mymodulerecycler);
        sliderLayout = view.findViewById(R.id.slider);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}