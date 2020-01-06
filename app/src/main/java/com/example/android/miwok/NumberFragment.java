package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;


/**
 * A simple {@link Fragment} subclass.
 */
public class NumberFragment extends Fragment {

    private AudioManager audioManager;
    private MediaPlayer mediaPlayer;

    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                    else if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mediaPlayer.start();
                    }
                }
            };

    public NumberFragment() {
        // Required empty public constructor
    }

    private void releaseMediaPlayer(){
        if(mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = null;
        audioManager.abandonAudioFocus(audioFocusChangeListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView  =  inflater.inflate(R.layout.word_list, container, false);

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> numbersArrayList = new ArrayList<Word>();  //ArrayList

        numbersArrayList.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        numbersArrayList.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        numbersArrayList.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        numbersArrayList.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        numbersArrayList.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        numbersArrayList.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        numbersArrayList.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        numbersArrayList.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        numbersArrayList.add(new Word("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        numbersArrayList.add(new Word("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), numbersArrayList, R.color.category_numbers);  //ArrayAdapter

        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /**
                 * Clearing memory before the user presses a view to play the audio translation
                 */
                releaseMediaPlayer();

//                 Request audio focus for playback
                int result = audioManager.requestAudioFocus(audioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Audio focus acheived

                    // Get the {@link Word} object at the given position the user clicked on
                    final Word word = numbersArrayList.get(i);
                    /**
                     * Cretaing a new MediaPlayer obj of the audio file to be played
                     */
                    mediaPlayer = MediaPlayer.create(view.getContext(), word.getAudioResource());
                    mediaPlayer.start();

                    /**
                     * Setting an onCompletionLitener to clear memory space when the audio file has finished playing
                     */
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            releaseMediaPlayer();
                        }
                    });
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
