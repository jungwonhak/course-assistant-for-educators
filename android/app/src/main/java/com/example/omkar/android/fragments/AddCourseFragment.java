package com.example.omkar.android.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.omkar.android.CoursesActivity;
import com.example.omkar.android.R;
import com.example.omkar.android.models.Course;

/**
 * Created by omkar on 12-Mar-18.
 */

public class AddCourseFragment extends Fragment {

    // view of fragment
    View view;
    private EditText mCourseCodeEditText;
    private EditText mStudentCountEditText;
    private EditText mEmailCrEditText;
    private EditText mEmailTaEditText;
    private Course mCourse;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // configure Toolbar in Courses Activity
        setHasOptionsMenu(true);
        ((CoursesActivity)getActivity()).setDrawerLocked(true);
        ((CoursesActivity)getActivity()).initToolbar("Course Details", R.drawable.ic_arrow_back);
        ((CoursesActivity)getActivity()).setViewHidden(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_add_course, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    /**
     * Create and save Course object
     */
    private void saveCourse() {
        // get value from input fields
        mCourseCodeEditText = view.findViewById(R.id.courseCode);
        mStudentCountEditText = view.findViewById(R.id.studentCount);
        mEmailCrEditText = view.findViewById(R.id.emailCr);
        mEmailTaEditText = view.findViewById(R.id.emailTa);

        // TODO: Add validation code for inputs

        // create Course object
        mCourse = new Course(mCourseCodeEditText.getText().toString(), Integer.parseInt(mStudentCountEditText.getText().toString()), mEmailCrEditText.getText().toString(), mEmailTaEditText.getText().toString());
        // save object in Courses Activity
        ((CoursesActivity)getActivity()).insertNewCourse(mCourse);
    }


    /**
     * Inflate menu items into views
     * @param menu menu xml
     * @param inflater inflater obj
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    /**
     * Toolbar Item selection Handler
     * @param item in toolbar
     * @return true: to hold and exit, false: to fall through
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
            case R.id.save:
                // save course
                saveCourse();
                // go back to Courses Activity
                getActivity().onBackPressed();

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        // Reset Courses Activity Toolbar
        ((CoursesActivity)getActivity()).setDrawerLocked(false);
        ((CoursesActivity)getActivity()).initToolbar("Courses", R.drawable.ic_menu);
        ((CoursesActivity)getActivity()).setFabHidden(false);
        ((CoursesActivity)getActivity()).setViewHidden(false);
    }
}




