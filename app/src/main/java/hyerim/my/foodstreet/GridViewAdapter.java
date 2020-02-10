package hyerim.my.foodstreet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewAdapter extends BaseAdapter {
    private LayoutInflater inflater ;

    Context context;
    int image[];
    String text[];

    public GridViewAdapter(Context context, int[] image, String[] text){
        this.context = context;
        this.text = text;
        this.image = image;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public Object getItem(int position) {
        return image[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.main_gridview_item, null);

        ImageView imageView = view.findViewById(R.id.gridview_item_image);
        TextView textView = view.findViewById(R.id.gridview_item_text);

        imageView.setImageResource(image[position]);
        textView.setText(text[position]);

        return view;
    }
}
