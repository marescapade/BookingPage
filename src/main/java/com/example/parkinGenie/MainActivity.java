package com.example.parkinGenie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.parkinGenie.Entities.CarPark;
import com.example.parkinGenie.Entities.DatePickerFragment;
import com.example.parkinGenie.Entities.Tariff;
import com.example.parkinGenie.utils.Utils;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int cpId;
    Spinner cpList;
    TextView txtAddress, txtPhone, txtWebsite, txtTariff, txtOpeningTimes, txtAvailability;
    ArrayList<String> cpNames;
    ArrayList<CarPark> carparkList;
    Button btnBook;
    private Button button;
    ImageView imgAddress, imgPhone, imgWebsite, imgOpening, imgTariff;

    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        cpList = findViewById(R.id.cpSpinner);
        txtAddress = findViewById(R.id.txtAddress);
        txtPhone = findViewById(R.id.txtPhone);
        txtWebsite = findViewById(R.id.txtWebsite);
        txtTariff = findViewById(R.id.txtTariff);
        txtOpeningTimes = findViewById(R.id.txtOpeningTimes);
        txtAvailability = findViewById(R.id.txtAvailability);
        btnBook = findViewById(R.id.btn_book);
        imgAddress = findViewById(R.id.img_address);
        imgPhone = findViewById(R.id.img_phone);
        imgWebsite = findViewById(R.id.img_website);
        imgOpening = findViewById(R.id.img_opening);
        imgTariff = findViewById(R.id.img_tariff);
        btnBook = findViewById(R.id.btn_book);
        btnBook.setOnClickListener(this);

        //Access to database
        DatabaseOpenHelper conn = new DatabaseOpenHelper(this, "ParkinGenie.db", null, 1);
        db = conn.getReadableDatabase();

        consultCpList();
        obtainList();
        consultTariff();
        consultOpeningTimes();


        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, cpNames);
        cpList.setAdapter(adapter);

        //method to select an option from spinner
        cpList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    btnBook.setVisibility(View.VISIBLE);
                    imgAddress.setVisibility(View.VISIBLE);
                    imgPhone.setVisibility(View.VISIBLE);
                    imgWebsite.setVisibility(View.VISIBLE);
                    imgOpening.setVisibility(View.VISIBLE);
                    imgTariff.setVisibility(View.VISIBLE);

                    txtAvailability.setText(carparkList.get(position - 1).getFree_spaces() + " spaces available");
                    txtAddress.setText(carparkList.get(position - 1).getAddress());
                    txtWebsite.setText(carparkList.get(position - 1).getWebsite());
                    txtPhone.setText(carparkList.get(position - 1).getPhone());
                    cpId = carparkList.get(position - 1).getCarParkId();
                    txtTariff.setText(consultTariff());
                    txtOpeningTimes.setText(consultOpeningTimes());

                } else {
                    txtAvailability.setText("");
                    txtAddress.setText("");
                    txtWebsite.setText("");
                    txtPhone.setText("");
                    txtTariff.setText("");
                    txtOpeningTimes.setText("");
                    btnBook.setVisibility(View.INVISIBLE);
                    imgAddress.setVisibility(View.INVISIBLE);
                    imgPhone.setVisibility(View.INVISIBLE);
                    imgWebsite.setVisibility(View.INVISIBLE);
                    imgOpening.setVisibility(View.INVISIBLE);
                    imgTariff.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    //method to get array of objects from table
    private void consultCpList() {

        CarPark carpark;
        carparkList = new ArrayList<CarPark>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_CARPARK, null);

        while (cursor.moveToNext()) {
            carpark = new CarPark();
            carpark.setCarParkId(cursor.getInt(0));
            carpark.setName(cursor.getString(1));
            carpark.setWebsite(cursor.getString(2));
            carpark.setAddress(cursor.getString(3));
            carpark.setPhone(cursor.getString(4));
            carpark.setGps(cursor.getString(5));
            carpark.setTot_spaces(cursor.getInt(6));
            carpark.setFree_spaces(cursor.getInt(7));
            carpark.setHeight_restrictions(cursor.getString(8));
            carpark.setPayment_methods(cursor.getString(9));
            carparkList.add(carpark);
        }
    }

    //method to populate spinner
    private void obtainList() {
        cpNames = new ArrayList<String>();
        cpNames.add("(Select Car Park)");

        for (int i = 0; i < carparkList.size(); i++) {
            cpNames.add(carparkList.get(i).getName());
        }
    }


    private String consultTariff() {

        //select tariff info from table tariff where carparkid = id orderby userId
        Cursor tcursor = db.rawQuery("SELECT " + Utils.TARIFF_INFO + " FROM " + Utils.TABLE_TARIFF
                + " WHERE " + Utils.CARPARKID + "= " + cpId + " ORDER BY " + Utils.ORDERID, new String[]{});
        StringBuffer buffer = new StringBuffer();

        while (tcursor.moveToNext()) {
            String tariff = tcursor.getString(0);
            buffer.append(tariff + "\n");
        }
        return buffer.toString();
    }


    private String consultOpeningTimes() {

        //select tariff info from table tariff where carparkid = id orderby userId
        Cursor opCursor = db.rawQuery("SELECT " + Utils.DAY_OF_WEEK + ", " + Utils.OPENING_INFO + " FROM "
                + Utils.TABLE_OPENING_TIMES + " WHERE " + Utils.CARPARKID + "= " + cpId + " ORDER BY " + Utils.ORDERID, new String[]{});
        StringBuffer buffer = new StringBuffer();

        while (opCursor.moveToNext()) {
            String dayOfWeek = opCursor.getString(0);
            buffer.append(dayOfWeek + "  ");
            String openingInfo = opCursor.getString(1);
            buffer.append(openingInfo + "\n");
        }
        return buffer.toString();
    }


    // Booking Button
    @Override
    public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, BookingActivity.class));
    }

}
