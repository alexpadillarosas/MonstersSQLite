package com.blueradix.android.monsterssqlite;

import android.content.Intent;
import android.os.Bundle;

import com.blueradix.android.monsterssqlite.activities.AddMonsterScrollingActivity;
import com.blueradix.android.monsterssqlite.entities.Monster;
import com.blueradix.android.monsterssqlite.services.DataService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import static com.blueradix.android.monsterssqlite.entities.Constants.ADD_MONSTER_ACTIVITY_CODE;

public class MainActivity extends AppCompatActivity {

    private DataService monsterDataService;
    private List<Monster> monsters;
    private View rootView;
    private EditText monsterIDEditText;
    private Monster monster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewMonster();
            }
        });
        rootView = findViewById(android.R.id.content).getRootView();
        monsterIDEditText = findViewById(R.id.monsterIDEditText);

        Button clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear(v);
            }
        });

        Button viewAllButton = findViewById(R.id.viewAllButton);
        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAll(v);
            }
        });

        Button updateButton = findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(v);
            }
        });

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(v);
            }
        });


        //Load Data from the database
        monsterDataService = new DataService();
        monsterDataService.init(this);
    }

    private void delete(View v) {
        String id = monsterIDEditText.getText().toString();
        if(isMonsterIdEmpty(v, id))
            return;

        monster = new Monster();
        monster.setId(Long.valueOf(id));
        boolean result = monsterDataService.delete(monster);
        if (result)
            Snackbar.make(v, "Monster id " + id + " was deleted ", Snackbar.LENGTH_SHORT).show();
        else
            Snackbar.make(v, "Error, Monster id " + id + " was not deleted ", Snackbar.LENGTH_SHORT).show();

    }

    private void update(View v) {
        String id = monsterIDEditText.getText().toString();
        if(isMonsterIdEmpty(v, id))
            return;



    }

    private void viewAll(View v) {
        List<Monster> monsters = monsterDataService.getMonsters();
        String text = "";

        if (monsters.size() > 0) {
            for( Monster monster : monsters){
                text = text.concat(monster.toString());
            }
            showMessage("Data", text);
        } else {
            showMessage("Records", "Nothing found");
        }
    }

    private void clear(View v) {
        monsterIDEditText.getText().clear();

    }

    private boolean isMonsterIdEmpty(View view, String id) {
        //clean trailing and leading empty spaces and then check if the size is not 0
        if(id.trim().isEmpty()){
            Snackbar.make(view, "You must input the Monster's Id", Snackbar.LENGTH_SHORT).show();
            monsterIDEditText.requestFocus();
            return true;
        }
        return false;
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.show();
    }

    private void updateMonster(){



    }

    private void addNewMonster() {
        Intent goToAddCreateMonster = new Intent(this, AddMonsterScrollingActivity.class);
        startActivityForResult(goToAddCreateMonster, ADD_MONSTER_ACTIVITY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_MONSTER_ACTIVITY_CODE){
            if(resultCode == RESULT_OK){
                addMonster(data);
            }
        }
    }

    private void addMonster(Intent data) {
        String message;
        Monster monster = (Monster) data.getSerializableExtra(Monster.MONSTER_KEY);
        //insert your monster into the DB
        Long result = monsterDataService.add(monster);
        //result holds the autogenerated id in the table
        if(result != -1){
            message = "Your monster was created";
        }else{
            message = "We couldn't create your monster, try again";
        }
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
