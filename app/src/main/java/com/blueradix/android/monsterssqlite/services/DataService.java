package com.blueradix.android.monsterssqlite.services;

import android.content.Context;

import com.blueradix.android.monsterssqlite.database.MonsterDatabaseHelper;
import com.blueradix.android.monsterssqlite.entities.Monster;

import java.util.List;

public class DataService {

    private MonsterDatabaseHelper sqlite;

    public void connect(){

    }

    public void disconnect(){

    }

    public void init(Context context){
        sqlite = sqlite.getInstance(context);
    }

    public Long add(Monster monster){
        return sqlite.insert(monster.getName(), monster.getDescription(), monster.getScariness());
    }

    public boolean delete(Monster monster){
        return sqlite.delete(monster.getId());
    }

    public boolean update(Monster monster){
        return sqlite.update(monster.getId(), monster.getName(), monster.getDescription(), monster.getScariness());
    }

    public List<Monster> getMonsters(){
        List<Monster> monsters = sqlite.getMonsters();
        return monsters;
    }

}