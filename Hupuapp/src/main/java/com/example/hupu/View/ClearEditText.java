package com.example.hupu.View;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;

import com.example.hupu.R;

public class ClearEditText extends AppCompatEditText implements TextWatcher,View.OnFocusChangeListener {

    private Drawable mClearDrawable;
    private boolean hasFoucs;
    public ClearEditText(Context context) {
        this(context,null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        // 获取EditTextt图,假如没有就使用默认的图片,获取图片的顺序是左上右下（0,1,2,3）
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null){
            mClearDrawable = getResources().getDrawable(R.mipmap.delete);
        }
        mClearDrawable.setBounds(0,0,mClearDrawable.getIntrinsicWidth(),mClearDrawable.getIntrinsicHeight());
        //设置隐藏图标
        setClearIconVisible(false);
        //设置焦点改变监听
        setOnFocusChangeListener((OnFocusChangeListener) this);
        //设置输入框里面内容发生改变监听
        addTextChangedListener(this);
    }

    private void setClearIconVisible(boolean visible){
        Drawable clearRight = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0],getCompoundDrawables()[1],clearRight,getCompoundDrawables()[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction() == MotionEvent.ACTION_UP){
            if (getCompoundDrawables()[2] != null){
                int x = (int) event.getX();
                int y = (int) event.getY();
                Rect rect = getCompoundDrawables()[2].getBounds();
                int height = rect.height();
                int distance = (getHeight() - height/2);
                boolean isInnerWidth = x > (getWidth() - getTotalPaddingRight()) && x <(getWidth() - getPaddingRight());
                boolean isInnerHeight = y > distance && y <(distance + height);
                if (isInnerWidth && isInnerHeight){
                    this.setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onFocusChange(View v,boolean hasFoucs){
        this.hasFoucs = hasFoucs;
        if (hasFoucs){
            setClearIconVisible(getText().length() > 0);
        }else {
            setClearIconVisible(false);
        }
    }

    /*
    * Textwatcher :对可显示文本控件和可编辑文本控件中的文字进行监听和修改
    * CharSequence s：文本改变之前的内容
    * int start：文本开始改变时的起点位置，从0开始计算
    * int count：要被改变的文本字数，即将要被替代的选中文本字数
    *  int after：改变后添加的文本字数，即替代选中文本后的文本字数
    * */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    /*
    * CharSequence s：文本改变之后的内容
    * int start：文本开始改变时的起点位置，从0开始计算
    * int before：要被改变的文本字数，即已经被替代的选中文本字数
    * int count：改变后添加的文本字数，即替代选中文本后的文本字数*/
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (hasFoucs){
            setClearIconVisible(s.length() > 0);
        }
    }

    /*
    * Editable s：改变后的最终文本
    * */
    @Override
    public void afterTextChanged(Editable s) {

    }
}
