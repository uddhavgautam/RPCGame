package com.upgautam.uddhav.rpsgame.uicontrollers;

import android.content.Context;
import android.util.AttributeSet;

/**
 * A {@link Hand} which stands for a paper hand.
 */
public class PaperButton extends HandButton {

    public PaperButton(Context context) {
        super(context);
    }

    public PaperButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    String getHandText() {
        return "Paper";
    }

    @Override
    public Hand getHand() {
        return Hand.PAPER;
    }
}
