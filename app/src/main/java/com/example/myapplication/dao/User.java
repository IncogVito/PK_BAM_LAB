package com.example.myapplication.dao;


import androidx.room.*;

@Entity
public class User {

        @PrimaryKey(autoGenerate = true)
        private int uid;
        @ColumnInfo(name = "user_name")
        private String userName;
        @ColumnInfo(name = "number")
        private int number;

}

