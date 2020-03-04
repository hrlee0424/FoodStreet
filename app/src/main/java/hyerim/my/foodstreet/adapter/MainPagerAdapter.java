package hyerim.my.foodstreet.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import hyerim.my.foodstreet.R;

public class MainPagerAdapter extends PagerAdapter {
    private Context context;
    private int[] images = {R.drawable.korean, R.drawable.pizza, R.drawable.coffee, R.drawable.stake}; //인디게이터에 사용할 이미지

    public MainPagerAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = null;

        if (context != null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.main_viewpageritem, container, false);

            ImageView imageView = view.findViewById(R.id.mainpager_image);
            imageView.setImageResource(images[position]);
        }
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (View)object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.invalidate();
    }
}
