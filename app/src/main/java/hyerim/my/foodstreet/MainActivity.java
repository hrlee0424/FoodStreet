package hyerim.my.foodstreet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public GridView gridView;
    public TextView gridview_item_text;
    public ImageView gridview_item_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //***************** 메인화면 Main_gridview *****************************
        gridview_item_image = findViewById(R.id.gridview_item_image);
        gridview_item_text = findViewById(R.id.gridview_item_text);

        int image[] = {
                R.drawable.bento,
                R.drawable.rice_bowl,
                R.drawable.friedchicken,
                R.drawable.pizza_100,
                R.drawable.hamburger_96,
                R.drawable.noodles,
                R.drawable.dimsum,
                R.drawable.cafe
        };

        String text[] = {
                "돈가스.일식",
                "한식",
                "치킨",
                "피자",
                "패스트푸드",
                "중국집",
                "아시안",
                "카페"
        };

        gridView = findViewById(R.id.main_gridview);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this,image,text);
        gridView.setAdapter(gridViewAdapter);

        //***************** 메인화면 Main_gridview 끝*****************************

    }
}
