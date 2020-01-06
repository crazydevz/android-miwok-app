/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file

        setContentView(R.layout.activity_main);
        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        CategoryAdapter adapter = new CategoryAdapter(this, getSupportFragmentManager());

        //set the adapter of the viewPager to be this adapter
        viewPager.setAdapter(adapter);

        //Find the tab layout that shows the tabs
        TabLayout tab = (TabLayout) findViewById(R.id.tabs);
        tab.setupWithViewPager(viewPager);

//        TextView numbersView = (TextView) findViewById(R.id.numbers);  //TextView for the Numbers TextView
//
//        //Methods to open the NumbersActivity when the Numbers TextView is clicked
//        numbersView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(view.getContext(), NumbersActivity.class);
//                startActivity(i);
//            }
//        });
//
//        TextView familyMembersView = (TextView) findViewById(R.id.family);  //TextView for the FamilyMembers TextView
//
//        //Methods to open the FamilyMembersActivity when the FamilyMembers TextView is clicked
//        familyMembersView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(view.getContext(), FamilyMembersActivity.class);
//                startActivity(i);
//            }
//        });
//
//        TextView colorsView = (TextView) findViewById(R.id.colors);  //TextView for the Colors TextView
//
//        //Methods to open the FamilyMembersActivity when the Colors TextView is clicked
//        colorsView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(view.getContext(), ColorActivity.class);
//                startActivity(i);
//            }
//        });
//
//        TextView phrasesView = (TextView) findViewById(R.id.phrases);  //TextView for the Phrases TextView
//
//        //Methods to open the PhrasesActivity when the Phrases TextView is clicked
//        phrasesView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(view.getContext(), PhrasesActivity.class);
//                startActivity(i);
//            }
//        });
    }
}
