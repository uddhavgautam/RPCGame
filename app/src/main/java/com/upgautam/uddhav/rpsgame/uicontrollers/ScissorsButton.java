package com.upgautam.uddhav.rpsgame.uicontrollers;

import android.content.Context;
import android.util.AttributeSet;

/**
 * A {@link Hand} which stands for a scissors hand.
 */
public class ScissorsButton extends HandButton {

    public ScissorsButton(Context context) {
        super(context);
    }

    public ScissorsButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    String getHandText() {
        return "Scissors";
    }

    @Override
    public Hand getHand() {
        return Hand.SCISSORS;
    }
}
