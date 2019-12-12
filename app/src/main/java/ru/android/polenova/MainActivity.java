package ru.android.polenova;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Drawable> images = new ArrayList<>();
    private List<ContactItem> contacts = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.list);
        generateRandomContactItem();
        BaseAdapter adapter = new ContactAdapter(this, contacts);
        listView.setAdapter(adapter);

        Button addButton = findViewById(R.id.buttonAdd);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AddNewText();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void AddNewText() throws IOException {
        EditText textNumber = findViewById(R.id.editTextNumber);
        EditText textMessage = findViewById(R.id.editTextMessage);
        String stringNumber = textNumber.getText().toString();
        String stringMessage = textMessage.getText().toString();
        contacts.add(new ContactItem(stringNumber, stringMessage, null));
        ArrayList<String> textAddList = new ArrayList<>();
        textAddList.add(stringNumber);
        textAddList.add(stringMessage);
        FileUtils.updateTextFile(this, textAddList);
    }


    private ArrayList ReadText() {
        ArrayList<String> textList = new ArrayList<>();
        try {
            File file = FileUtils.getTextFile(this, false);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String str = reader.readLine();
            while (str != null) {
                textList.add(str);
                str = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textList;
    }

    private List<ContactItem> generateRandomContactItem() {
        final ArrayList<String> text = ReadText();
        for (String string : text) {
            String[] string1 = string.split(";");
            contacts.add(new ContactItem(string1[0], string1[1], null));
        }
        return contacts;
    }
}

