package com.example.coolweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.coolweather.db.AddStarCity;
import com.example.coolweather.db.Province;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class AddStarCityActivity extends AppCompatActivity {

    private ListView listView;

    private ArrayAdapter<String> adapter;

    private List<String> dataList = new ArrayList<>();

    private List<AddStarCity> addStarCityList;

    private String weatherId;

    private Button button;

    private AddStarCity selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_star_city);
        Toast toast = Toast.makeText(AddStarCityActivity.this,"长按取消关注",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
        listView = (ListView) findViewById(R.id.list_view);
        button = (Button) findViewById(R.id.back_button);
        adapter = new ArrayAdapter<>(MyApplication.getContext(), android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        addStarCityList = DataSupport.findAll(AddStarCity.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddStarCityActivity.this.finish();
            }
        });
       if (addStarCityList.size() > 0) {
            dataList.clear();
            for (AddStarCity city : addStarCityList) {
                dataList.add(city.getCity());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    weatherId = addStarCityList.get(position).getWeatherId();
                    addStarCityList.clear();
                    Intent intent = new Intent(AddStarCityActivity.this, WeatherActivity.class);
                    intent.putExtra("weather_id", weatherId);
                    startActivity(intent);
                }
            });
        }

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DataSupport.deleteAll(AddStarCity.class,"city = ?",addStarCityList.get(position).getCity());
                dataList.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        }
    }

