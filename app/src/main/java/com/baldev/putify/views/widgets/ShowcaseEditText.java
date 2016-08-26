package com.baldev.putify.views.widgets;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.widget.EditText;

import com.github.amlcurran.showcaseview.targets.Target;

public class ShowcaseEditText extends EditText implements Target {

	public ShowcaseEditText(Context context) {
		super(context);
	}

	public ShowcaseEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ShowcaseEditText(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	public Point getPoint() {
		int[] location = new int[2];
		this.getLocationInWindow(location);
		int x = location[0] + this.getWidth() / 4;
		int y = location[1] + this.getHeight() / 2;
		return new Point(x, y);
	}
}
