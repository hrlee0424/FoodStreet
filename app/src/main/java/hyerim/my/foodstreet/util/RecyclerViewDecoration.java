package hyerim.my.foodstreet.util;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
/*
* 리사이클러뷰 데코레이션
* 리사이클러뷰 여백 지정 클래스
* */
public class RecyclerViewDecoration extends RecyclerView.ItemDecoration {
    private final int divHeight;

    public RecyclerViewDecoration(int divHeight) {
        this.divHeight = divHeight;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1)
            outRect.bottom = divHeight;
    }


}
