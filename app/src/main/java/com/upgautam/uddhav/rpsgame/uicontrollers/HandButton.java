package com.upgautam.uddhav.rpsgame.uicontrollers;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * A {@link AppCompatButton} to play a hand. For each hand, a user can play 10 times at most.
 * This also controls a playable count left.
 */
public abstract class HandButton extends AppCompatButton {

    /**
     * Playable count for this hand.
     */
    private int rest;

    public HandButton(Context context) {
        super(context);
        rest = 10;
        setText(getHandText());
    }

    public HandButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        rest = 10;
        CharSequence mText = getHandText() + "\nrest: " + rest;
        setText(mText);
    }

    /**
     * Updates button text by means of {@link #getHandText()} and the rest count.
     */
    public void updateText() {
        CharSequence mText = getHandText() + "\nrest: " + rest;
        setText(mText);
    }

    public int getRest() {
        return rest;
    }

    public void play() {
        --rest;
    }

    /**
     * Returns a hand name corresponding to this button.
     */
    abstract String getHandText();

    public abstract Hand getHand();
}
