package sg.edu.rp.c346.id20047102.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spnAddDel;
    EditText etTask;
    Button btAdd;
    Button btDelete;
    Button btClear;
    ListView lvTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.editTextTask);
        btAdd = findViewById(R.id.buttonAdd);
        btDelete = findViewById(R.id.buttonRemove);
        btClear = findViewById(R.id.buttonClear);
        lvTask = findViewById(R.id.listViewTask);
        spnAddDel = findViewById(R.id.spinnerAddDel);

        ArrayList<String> taskList;
        taskList = new ArrayList<String>();

        ArrayAdapter<String> aaTask = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, taskList);
        lvTask.setAdapter(aaTask);

        spnAddDel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etTask.setHint("Type in a new task here");
                        btDelete.setEnabled(false);
                        btAdd.setEnabled(true);
                        etTask.getText().clear();
                        break;
                    case 1:
                        etTask.setHint("Type in the index of the task to be removed");
                        btDelete.setEnabled(true);
                        btAdd.setEnabled(false);
                        etTask.getText().clear();
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task = etTask.getText().toString();
                taskList.add(task);
                aaTask.notifyDataSetChanged();
                etTask.getText().clear();
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etTask.getText().toString().trim().length() == 0) {
                    Toast.makeText(MainActivity.this, "The field is empty", Toast.LENGTH_SHORT).show();
                } else {
                    int pos = Integer.parseInt(etTask.getText().toString());

                    if (taskList.size() > 0) {
                        for (int i = 0; i < taskList.size(); i++) {
                            if (pos == i) {
                                taskList.remove(i);
                                aaTask.notifyDataSetChanged();
                                etTask.getText().clear();
                            } else if (pos > taskList.size() - 1) {
                                Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });


        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskList.clear();
                aaTask.notifyDataSetChanged();
                etTask.getText().clear();
            }
        });


    }
}