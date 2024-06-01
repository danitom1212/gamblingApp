package com.example.gamblingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gamblingapp.imageViewScrolling.IEventEnd;
import com.example.gamblingapp.imageViewScrolling.imageViewScrolling;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements IEventEnd {
    // Write a message to the database
    // Write a message to the database
    private DatabaseReference mDatabase;
    ImageView btn_up,btn_down;
imageViewScrolling image ,image2,image3;
TextView txt_score;
int count_done=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // Write a message to the database

        btn_down=(ImageView) findViewById(R.id.btn_down);
        btn_up=(ImageView) findViewById(R.id.btn_up);
        image= (imageViewScrolling) findViewById(R.id.image);
        image2= (imageViewScrolling) findViewById(R.id.image2);
        image3= (imageViewScrolling) findViewById(R.id.image3);
        txt_score=(TextView) findViewById(R.id.txt_score);

        image.setEventEnd(MainActivity.this);

        image2.setEventEnd(MainActivity.this);
        image3.setEventEnd(MainActivity.this);


        btn_up.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");



if (Common.SCORE>=50){
btn_up.setVisibility(View.GONE);
btn_down.setVisibility(View.VISIBLE);
    image.setValueRendom(new Random().nextInt(6),new Random().nextInt((15-5)+1)+5);
    image2.setValueRendom(new Random().nextInt(6),new Random().nextInt((15-5)+1)+5);
    image3.setValueRendom(new Random().nextInt(6),new Random().nextInt((15-5)+1)+5);
Common.SCORE-=50;
txt_score.setText(String.valueOf(Common.SCORE));

}else {
    Toast.makeText(MainActivity.this,"you heve not enough bling bling you know man!!",Toast.LENGTH_SHORT).show();

}
    }

});
    }

    @Override
    public void eventEnd(int result, int count) {
if (count_done<2)
    count_done++;
else {
    btn_down.setVisibility(View.GONE);
    btn_up.setVisibility(View.VISIBLE);
    count_done=0;
    if (image.getValue()==image2.getValue()&& image2.getValue()==image3.getValue()){
        Toast.makeText(this,"you win big prize",Toast.LENGTH_SHORT).show();
        Common.SCORE+=300;
        txt_score.setText(String.valueOf(Common.SCORE));
        
    } else if (image.getValue()==image2.getValue()||
               image2.getValue()==image3.getValue()||
                image.getValue()==image3.getValue()){
        Toast.makeText(this,"you win small prize",Toast.LENGTH_SHORT).show();
        Common.SCORE+=100;
        txt_score.setText(String.valueOf(Common.SCORE));
    }
    else {
        Toast.makeText(this, "you loserrrrr ya efes", Toast.LENGTH_SHORT).show();

    }
}

    }
}