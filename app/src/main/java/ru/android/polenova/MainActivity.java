package ru.android.polenova;

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

    private List<ContactItem> contacts = new ArrayList<>();
    BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateRandomContactItem();
        adapter = new ContactAdapter(this, contacts);
        ListView listView = findViewById(R.id.list);
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
        FileUtils.appendTextFile(this, textAddList);
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
        ArrayList<String> textRead = ReadText();
        final ArrayList<String> textSplit = new ArrayList<>();
        for (String string : textRead) {
            String[] string1 = string.split(";");
            for (int j = 0; j < string1.length; j++) {
                textSplit.add(string1[j]);
            }
            for (int i = 0, length = textSplit.size(); i < length; i += 2) {
                contacts.add(new ContactItem(textSplit.get(i), textSplit.get(i + 1), null));
            }
        }
        return contacts;
    }
}




