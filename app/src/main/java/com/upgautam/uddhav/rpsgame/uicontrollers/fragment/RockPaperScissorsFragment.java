package com.upgautam.uddhav.rpsgame.uicontrollers.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upgautam.uddhav.rpsgame.R;
import com.upgautam.uddhav.rpsgame.uicontrollers.Hand;
import com.upgautam.uddhav.rpsgame.uicontrollers.HandButton;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A placeholder to keep hand buttons and a text view for results.
 */
public class RockPaperScissorsFragment extends Fragment {

    HandButton rockButton;
    HandButton scissorsButton;
    HandButton paperButton;
    Hand opponentHand = Hand.ROCK;

    // avoid creating several instances, should be singleton OkHttpClient
    OkHttpClient client = new OkHttpClient();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.rockpaperscissors_fragment, container, false);
        rockButton = fragmentView.findViewById(R.id.rock_button);
        scissorsButton = fragmentView.findViewById(R.id.scissors_button);
        paperButton = fragmentView.findViewById(R.id.paper_button);

        rockButton.setOnClickListener(new PrsButtonClickListener());
        scissorsButton.setOnClickListener(new PrsButtonClickListener());
        paperButton.setOnClickListener(new PrsButtonClickListener());

        return fragmentView;
    }

    public void getOpponentHand() {

        //default method is GET
        Request request = new Request.Builder()
                .url("http://example.com/randhand")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    //connection failed
                    //provide random response {0, 1, 2} yourself using random function
                    Random r = new Random();
                    int Low = 0;
                    int High = 3;
                    int value = r.nextInt(High - Low) + Low; //0 inclusive to 3 exclusive
                    System.out.println("Uddhav: " + value);
                    opponentHand = Hand.fromInt(value);

                } else {

                    // do something wih the result
                    BufferedReader reader = new BufferedReader(new InputStreamReader(
                            new BufferedInputStream(response.body().byteStream())));
                    String str = reader.readLine();

                    Log.i(Thread.currentThread().getName(), str);

                    while (!TextUtils.isEmpty(str)) {
                        opponentHand = Hand.fromInt(Integer.valueOf(str.trim()));
                        str = reader.readLine();
                    }

                }
            }
        });
    }

//    public void getOpponentHand() {
//        try {
//            Log.i(Thread.currentThread().getName(), "Network Conn!");
//
//            URL url = new URL("http://example.com/randhand");
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //changed to HttpURLConnection
//
//            //added part
//            connection.setRequestMethod("GET");
//            connection.setDoOutput(true);
//            connection.setConnectTimeout(5000);
//            connection.setReadTimeout(5000);
//            connection.connect();
//
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(
//                    new BufferedInputStream(connection.getInputStream())));
//            String str = reader.readLine();
//
//            Log.i(Thread.currentThread().getName(), str);
//
//            while (!TextUtils.isEmpty(str)) {
//                opponentHand = Hand.fromInt(Integer.valueOf(str.trim()));
//                str = reader.readLine();
//            }
//        } catch (IOException e) {
//            ErrorReporter.report(e);
//        }
//    }

    class PrsButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            HandButton button = (HandButton) v;
            if (button.getRest() != 0) {
                button.play();
                button.updateText();
                getOpponentHand();
                int result = button.getHand().compareTo(opponentHand);

                TextView resultTextView = getActivity().findViewById(R.id.text_view);
                CharSequence mText = "";
                if (result < 0) {

                    mText = resultTextView.getText() + "LOSE"
                            + "(You: " + button.getHand().toString() + ", Opponent: "
                            + opponentHand.toString() + ")\n";

                    resultTextView.setText(mText);

                } else if (result > 0) {

                    mText = resultTextView.getText() + "WIN"
                            + "(You: " + button.getHand().toString() + ", Opponent: "
                            + opponentHand.toString() + ")\n";

                    resultTextView.setText(mText);

                } else {

                    mText = resultTextView.getText() + "DRAW"
                            + "(You: " + button.getHand().toString() + ", Opponent: "
                            + opponentHand.toString() + ")\n";

                    resultTextView.setText(mText);

                }
            }
        }
    }
}
