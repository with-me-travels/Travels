package com.wjx.travelwithm_master.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wjx.travelwithm_master.R;
import com.wjx.travelwithm_master.utils.SystemUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xts on 2016/3/9.
 * 用法:
 *          初始化的时候
 *          mIndicator.initSize(80,32,6);
            mIndicator.setNumbers(3);
        和ViewPager关联的时候
        setSelectedState(int position)

 */
public class PreviewIndicator extends LinearLayout {

    //指示器个数
    private int INDICATOR_COUNT = 3;
    private List<ImageView> mImageList = new ArrayList<>();
    //选中时对应指示器点的宽度
    private int chooseSize = 80;//80
    //未选中时指示器点的宽度
    private int nomalSize = 32;//32
    //指示器高度
    private int height = 10;//10

    public PreviewIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PreviewIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    //设置指示器点的个数
    public void setNumbers(int number){
        INDICATOR_COUNT = number;
        initView();
    }

    //初始化所有的指示器点
    private void initView() {
        //initSize();
        removeAllViews();
        mImageList.clear();
        for (int i = 0; i < INDICATOR_COUNT; i++) {
            ImageView imageView = new ImageView(getContext());
            if (i == 0) {
                setSelectedState(imageView);
            } else {
                setNomalState(imageView);
            }
            addView(imageView);
            mImageList.add(imageView);
        }

    }

    /**
     * 初始化指示器点的宽高参数,单位dp
     * @param chosedSize 选中指示器点的宽度
     * @param nomal 未选中时指示器点的宽度
     * @param hei 指示器点的高度
     */
    public void initSize(int chosedSize,int nomal,int hei) {
        chooseSize = SystemUtil.dp2px(getContext(),chosedSize);
        nomalSize = SystemUtil.dp2px(getContext(),nomal);
        height = SystemUtil.dp2px(getContext(),hei);
    }

    /**
     * 设置选中指示器点的参数
     * @param imageView
     */
    private void setNomalState(ImageView imageView) {
        imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.splash_pager_bg_unselect));
        LayoutParams params = new LayoutParams(nomalSize, height);
        params.setMargins(height, 0, 0, 0);
        imageView.setLayoutParams(params);
    }

    /**
     * 设置未选中指示器点的宽度
     * @param imageView
     */
    private void setSelectedState(ImageView imageView) {
        imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.splash_pager_bg_select));
        LayoutParams params = new LayoutParams(chooseSize, height);
        params.setMargins(height, 0, 0, 0);
        imageView.setLayoutParams(params);
    }

    /**
     * 设置哪个指示器的点为选中的点
     * @param position 该选中的点
     */
    public void setSelected(int position) {
        for (int i = 0; i < mImageList.size(); i++) {
            ImageView imageView = mImageList.get(i);
            int curPosition = position % mImageList.size();
            if (i == curPosition) {
               setSelectedState(imageView);
            } else {
                setNomalState(imageView);
            }
        }
    }

}
