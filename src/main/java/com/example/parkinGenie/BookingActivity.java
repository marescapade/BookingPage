package com.example.parkinGenie;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.fragment.app.DialogFragment;

        import android.app.DatePickerDialog;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.parkinGenie.Entities.DatePickerFragment;

        import java.text.DateFormat;
        import java.util.Calendar;

public class BookingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    DatabaseHelper myDb;
    EditText editName, editCarRegistration, editEmail, editPhone, editDateOfBooking;
    Button btnAddData,btnAddDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        myDb = new DatabaseHelper(this);

        // Datepicker
        Button btnDate = (Button) findViewById(R.id.btnDate);
        btnDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v2){
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }

        });
    }

    public void createCarPark(View view) {

        editName = (EditText) findViewById(R.id.editText_name);
        editCarRegistration = (EditText) findViewById(R.id.editText_carRegistration);
        editEmail = (EditText) findViewById(R.id.editText_email);
        editPhone = (EditText) findViewById(R.id.editText_phone);
        btnAddDate = (Button) findViewById(R.id.btnDate);
        btnAddData = (Button) findViewById(R.id.button_add);
        AddData();

    }

    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editName.getText().toString(),
                                editCarRegistration.getText().toString(), editEmail.getText().toString(),
                                editPhone.getText().toString(), btnAddDate.getText().toString());

                        if (isInserted = true) {
                            Toast.makeText(BookingActivity.this, "Confirmation sent to email", Toast.LENGTH_LONG).show();
                            setContentView(R.layout.activity_booking);
                        }

                    }
                }
        );
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance().format(c.getTime());

        TextView textView = (TextView) findViewById(R.id.btnDate);
        textView.setText(currentDateString);

    }
}
