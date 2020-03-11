package hyerim.my.foodstreet.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import hyerim.my.foodstreet.vpfragment.ViewPagerFragAsian;
import hyerim.my.foodstreet.vpfragment.ViewPagerFragCafe;
import hyerim.my.foodstreet.vpfragment.ViewPagerFragChicken;
import hyerim.my.foodstreet.vpfragment.ViewPagerFragChinese;
import hyerim.my.foodstreet.vpfragment.ViewPagerFragFast;
import hyerim.my.foodstreet.vpfragment.ViewPagerFragJapan;
import hyerim.my.foodstreet.vpfragment.ViewPagerFragKorean;
import hyerim.my.foodstreet.vpfragment.ViewPagerFragPizza;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private int pagecount;

    public ViewPagerAdapter(FragmentManager fm, int pageCount){
        super(fm);
        this.pagecount = pageCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                ViewPagerFragKorean viewPagerFragKorean = new ViewPagerFragKorean();
                return viewPagerFragKorean;
            case 1:
                ViewPagerFragJapan viewPagerFragJapan = new ViewPagerFragJapan();
                return viewPagerFragJapan;
            case 2:
                ViewPagerFragChicken viewPagerFragChicken = new ViewPagerFragChicken();
                return viewPagerFragChicken;
            case 3:
                ViewPagerFragPizza viewPagerFragPizza = new ViewPagerFragPizza();
                return viewPagerFragPizza;
            case 4:
                ViewPagerFragFast viewPagerFragFast = new ViewPagerFragFast();
                return viewPagerFragFast;
            case 5:
                ViewPagerFragChinese viewPagerFragChinese = new ViewPagerFragChinese();
                return viewPagerFragChinese;
            case 6:
                ViewPagerFragCafe viewPagerFragCafe = new ViewPagerFragCafe();
                return viewPagerFragCafe;
//            case 7:
//                ViewPagerFragCafe viewPagerFragCafe = new ViewPagerFragCafe();
//                return viewPagerFragCafe;
                default:
                    return null;
        }
    }



    @Override
    public int getCount() {
        return pagecount;
    }
}
