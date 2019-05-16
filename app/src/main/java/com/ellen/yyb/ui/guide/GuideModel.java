package com.ellen.yyb.ui.guide;

import com.ellen.yyb.R;
import com.ellen.yyb.helper.key_value.MMKVHelper;

public class GuideModel implements GuideAgree.GuideAgreeModel {

    private MMKVHelper mmkvHelper;
    private final static String USER_FIRST_LANUCHER = "user_first_lanucher";

    public GuideModel(){
        mmkvHelper = new MMKVHelper(this.getClass().getName());
    }

    @Override
    public boolean isFirstLanucher() {
        return (boolean) mmkvHelper.getValue(USER_FIRST_LANUCHER,false);
    }

    @Override
    public void saveFirstLanucher(boolean isFirstLanucher) {
         mmkvHelper.save(USER_FIRST_LANUCHER,isFirstLanucher);
    }

    @Override
    public int[] getGuideImageArray() {
        int[] imageResource = {
                R.mipmap.i1,
                R.mipmap.i2,
                R.mipmap.i3,
                R.mipmap.i4
        };
        return imageResource;
    }

}
