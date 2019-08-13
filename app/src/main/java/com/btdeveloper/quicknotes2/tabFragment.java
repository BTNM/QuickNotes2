package com.btdeveloper.quicknotes2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class tabFragment extends Fragment {
    private RecyclerView mRecycleView; // recycleview connects to the recycleview element in the xml
//    private RecyclerView.Adapter mAdapter; // bridge that connects datalist to recycleview
    private NoteAdapter mAdapter; // Change to our note adapter to access custom method
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Note> noteList = new ArrayList<>();

    private Button btnTest;
    private Boolean isViewAsList = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment,container, false);

//        populateNoteList();
        setupRecycleView(view);
        //enable access to toolbar from fragment
        setHasOptionsMenu(true);



//        btnTest = (Button) view.findViewById(R.id.btnTest);
//        btnTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), "Testing button click 1", Toast.LENGTH_SHORT).show();
//
//            }
//        });

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("from fragment");
//                addTab();
//                removeCurrentTab();
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // do stuff that differs from activity's menu here
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add:
                //implemented in activity's menu
                return false;
            case R.id.search:

                break;
            case R.id.SwitchView:
                //do the fragment menu item stuff here
//                Toast.makeText(getContext(),"from fragment", Toast.LENGTH_SHORT).show();
//                isViewAsList = !isViewAsList;
////                supportInvalidateOptionsMenu();
//                mRecycleView.setLayoutManager(isViewAsList ? new LinearLayoutManager(getContext()) : new GridLayoutManager(getContext(), 2) );
//                mRecycleView.setAdapter(mAdapter);
                checkChangeLayout();
                break;
            case R.id.delete:
                return false;
            case R.id.setting:
                return false;
            case R.id.logout:
                return false;
        }

//        return false;
        return super.onOptionsItemSelected(item);
    }


    private void setupRecycleView(View view) {
        mRecycleView = view.findViewById(R.id.recyclerView);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        mRecycleView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

//        mAdapter = new NoteAdapter(noteList, isViewAsList);

        mLayoutManager = new LinearLayoutManager(getContext());
//        checkChangeLayout();

//        mLayoutManager = new LinearLayoutManager(getContext() ) {
//            @Override
//            public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
////                return super.checkLayoutParams(lp);
//                lp.height = getHeight() /4;
//                return true;
//            }
//        };

        mAdapter = new NoteAdapter();

        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setAdapter(mAdapter);

//        mRecycleView.setAdapter(mAdapter);
//        mAdapter.setOnItemClickListener (new NoteAdapter().OnItemClickListener() {
//            @Override
//            public void onDeleteClick(int posistion) {
//                //delete item here
//                noteList.remove(posistion);
//                mAdapter.notifyItemRemoved(posistion);
//                mAdapter.notifyItemRangeChanged(posistion, noteList.size() );
//
//
//            }
//        });

//        ItemMovesCallback callback = new ItemMovesCallback(mAdapter);
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
//        itemTouchHelper.attachToRecyclerView(mRecycleView);

    }

    public void checkChangeLayout () {
        isViewAsList = !isViewAsList;

        if (isViewAsList) {
            mLayoutManager = new LinearLayoutManager(getContext() ) {
                @Override
                public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
//                return super.checkLayoutParams(lp);
                    lp.height = getHeight() / 4;

//                    ((ItemMovesCallback) callback).setSwipeFlags(ItemTouchHelper.START | ItemTouchHelper.END);
                    return true;
                }
            };

        } else {
            mLayoutManager = new GridLayoutManager(getContext(), 2) {
                @Override
                public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
                    lp.height = getHeight() / 4;
//                    ((ItemMovesCallback) callback).setSwipeFlags(0);
                    return true;
                }
            };

        }
        mRecycleView.setLayoutManager(mLayoutManager);
    }


    private List<Note> populateNoteList() {
        // pseudo code to get note, replace your code to get real note here
        noteList = new ArrayList<>();
        noteList.add(new Note("Test Title1", R.drawable.ic_work,"Work", "Test description for the note","120494") );
        noteList.add(new Note("Test Title2", R.drawable.ic_note_blue,"Work", "Test description for the note. Meeting at bar in Bergen blablalbaf dfdfd dfdfdf df df ed","120494") );
        noteList.add(new Note("Test Title3", R.drawable.ic_work,"Work", "Test description, Work note ssssssssssssssssssssssssssssssssssssssssssssss sssssssssssssssssssssssssssssssssssss sssssss","120494") );
        noteList.add(new Note("Test Title4", R.drawable.ic_note_color,"Work", "Test description","120494") );
        noteList.add(new Note("Test Title5", R.drawable.ic_work,"Work", "Test description for the note. Test description for the note. Test description for the note. Test description for the note. Test description for the note. Test description for the note. Test description for the note. Test description for the note. Test description for the note.","120494") );

        return noteList;


    }



}
