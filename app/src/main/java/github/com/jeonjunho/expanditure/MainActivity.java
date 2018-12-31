package github.com.jeonjunho.expanditure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import github.com.jeonjunho.expanditure.db.DB;

public class MainActivity extends AppCompatActivity {

    private final static String BLANK = "";

    private final static Integer ZERO = 0;

    private final static String HOUSE = "Housing";

    private final static String TELECOM = "Telecom";

    private final static String TRANS = "Trans";

    private final static String FOOD = "Food";

    private final static String SHOPPING = "Shopping";

    private DB db;

    private TextView inputDate;

    private TextView inputItem;

    private TextView inputPrice;

    private TextView showResult;

    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DB(getApplicationContext(), "EXPENDITURE.db", null, 1);

        inputDate = findViewById(R.id.inputDate);
        inputItem = findViewById(R.id.inputItem);
        inputPrice = findViewById(R.id.inputPrice);
        showResult = findViewById(R.id.showResult);
        scrollView = findViewById(R.id.scrollView);

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String yyyyMMdd = sdf.format(date);

        inputDate.setText(yyyyMMdd);
        inputItem.requestFocus();

        showResult.setText(db.showToday(getDate()));
    }

    public void btnHouseClick(View v){
        db.insert(getDate(), getItem(), getPrice(), HOUSE);
        afterBtnClick();
    }

    public void btnTelecomClick(View v){
        db.insert(getDate(), getItem(), getPrice(), TELECOM);
        afterBtnClick();
    }

    public void btnTransClick(View v){
        db.insert(getDate(), getItem(), getPrice(), TRANS);
        afterBtnClick();
    }

    public void btnFoodClick(View v){
        db.insert(getDate(), getItem(), getPrice(), FOOD);
        afterBtnClick();
    }

    public void btnShoppingClick(View v){
        db.insert(getDate(), getItem(), getPrice(), SHOPPING);
        afterBtnClick();
    }

    private String getDate(){
        if(inputDate.getText() != null && !BLANK.equals(inputDate.getText().toString())){
            return inputDate.getText().toString();
        } else {
            return BLANK;
        }
    }

    private String getItem(){
        if(inputItem.getText() != null && !BLANK.equals(inputItem.getText().toString())){
            return inputItem.getText().toString();
        } else {
            return BLANK;
        }
    }

    private Integer getPrice(){
        if(inputPrice.getText() != null && !BLANK.equals(inputPrice.getText().toString())){
            return Integer.parseInt(inputPrice.getText().toString());
        } else {
            return ZERO;
        }
    }

    private void afterBtnClick(){
        showResult.setText(db.showToday(getDate()));
        inputItem.setText(BLANK);
        inputPrice.setText(BLANK);
        inputItem.requestFocus();
        scrollView.fullScroll(View.FOCUS_DOWN);
    }

}