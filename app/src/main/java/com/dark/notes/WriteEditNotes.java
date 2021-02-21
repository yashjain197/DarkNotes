package com.dark.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class WriteEditNotes extends AppCompatActivity {
    public static final String EXTRA_ID
            ="com.yashjain.notes.EXTRA_ID";

    public static final String EXTRA_TITLE
            ="com.yashjain.notes.EXTRA_TITLE";

    public static final String EXTRA_DESCRIPTION
            ="com.yashjain.notes.EXTRA_DESCRIPTION";

    public static final String EXTRA_PRIORITY
            ="com.yashjain.notes.EXTRA_PRIORITY";


    private EditText editTextTitle;

    private EditText editTextDescription;
    private NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_notes);
        editTextTitle = findViewById(R.id.title);
        editTextDescription = findViewById(R.id.note);
        numberPickerPriority = findViewById(R.id.number_picker_priority);
        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent=getIntent();
        if(intent.hasExtra(EXTRA_ID)){
            getSupportActionBar().setTitle("Edit Note");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));
        }else{
        getSupportActionBar().setTitle("Add Note");
        }



    }

    private void saveNote(){
        String title=editTextTitle.getText().toString();
        String description=editTextDescription.getText().toString();
        int priority=numberPickerPriority.getValue();

        if(title.trim().isEmpty()|| description.trim().isEmpty()){
            Toast.makeText(this,"Please enter title and description",Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data= new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);
        data.putExtra(EXTRA_PRIORITY,priority);
        int id=getIntent().getIntExtra(EXTRA_ID,-1);
        if(id!=-1){
            data.putExtra(EXTRA_ID,id);
        }

        setResult(RESULT_OK,data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}