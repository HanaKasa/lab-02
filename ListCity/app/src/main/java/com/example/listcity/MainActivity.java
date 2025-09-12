package com.example.listcity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity{

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    int city_pos = -1; //unreachable position to start

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        String[] cities = {"Edmonton", "Montreal", "Toronto", "Vancouver", "Winnipeg", "Calgary", "Quebec City", "NewYork City", "Seattle", "Dallas", "Los Angeles"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        //tracks user selection of city through UI (adapter view, specific view clicked, index, id)
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            city_pos = position; //storing city index
        });

        //store id of UI objects to give them their behaviour
        Button addbutton = findViewById(R.id.addbutton);
        Button confirmbutton = findViewById(R.id.confirmbutton);
        Button removebutton = findViewById(R.id.removebutton);
        EditText enter = findViewById(R.id.enter);


        removebutton.setOnClickListener(v -> {
            //removes user clicked item from array
            if (city_pos >= 0 && city_pos < dataList.size()) {
                dataList.remove(city_pos); //remove city
                cityAdapter.notifyDataSetChanged();
                city_pos = -1; // reset position for reselection of city
            }
        });

        addbutton.setOnClickListener(v -> {
            //reveals edittext and button identity to add city
            confirmbutton.setVisibility(View.VISIBLE);
            enter.setVisibility(View.VISIBLE);
        });

        confirmbutton.setOnClickListener(v -> {
            //pushes user input into array
            String city = enter.getText().toString().trim(); //read input and trim whitespaces
            if (!city.isEmpty()) {
                dataList.add(city); //add city
                cityAdapter.notifyDataSetChanged();
                enter.setText(""); // erase line after
                enter.setVisibility(View.GONE);  // keep hidden to maintain add city visibility action
                confirmbutton.setVisibility(View.GONE);
            }
        });
    }
}

