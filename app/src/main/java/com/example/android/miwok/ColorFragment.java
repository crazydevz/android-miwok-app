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
public class ColorFragment extends Fragment {


    public ColorFragment() {
        // Required empty public constructor
    }

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener(){

        @Override
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list, container, false);

        audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> colorArrayList = new ArrayList<Word>();  //ArrayList

        colorArrayList.add(new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        colorArrayList.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        colorArrayList.add(new Word("brown" ,"ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        colorArrayList.add(new Word("gray" ,"ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        colorArrayList.add(new Word("black" ,"kululli", R.drawable.color_black, R.raw.color_black));
        colorArrayList.add(new Word("white" ,"kelelli", R.drawable.color_white, R.raw.color_white));
        colorArrayList.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colorArrayList.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        WordAdapter itemsAdapter = new WordAdapter(getActivity(),colorArrayList, R.color.category_colors);  //ArrayAdapter

        ListView listView = (ListView) rootView.findViewById(R.id.list);  //ListView
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the {@link Word} object at the given position the user clicked on
                Word word = colorArrayList.get(i);
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

    private void releaseMediaPlayer(){
        if(mediaPlayer != null)
            mediaPlayer.release();
        else
            mediaPlayer = null;
        audioManager.abandonAudioFocus(audioFocusChangeListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
