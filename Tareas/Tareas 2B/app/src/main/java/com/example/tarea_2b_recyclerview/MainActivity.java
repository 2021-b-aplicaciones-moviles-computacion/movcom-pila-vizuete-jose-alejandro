package com.example.tarea_2b_recyclerview;

import android.view.ActionMode;
import android.widget.ListAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tarea_2b_recyclerview.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding bi;
    List<Inbox> list;
    ListAdapter adapter;
    ActionMode actionMode;
    ActionCallback actionCallback;
}
