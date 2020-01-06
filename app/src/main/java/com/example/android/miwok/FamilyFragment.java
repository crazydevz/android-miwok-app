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
public class FamilyFragment extends Fragment {


    public FamilyFragment() {
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

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> familyArrayList = new ArrayList<Word>();  //ArrayList

        familyArrayList.add(new Word("father", "әpә", R.drawable.family_father, R.raw.family_father));
        familyArrayList.add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        familyArrayList.add(new Word("son" ,"angsi", R.drawable.family_son, R.raw.family_son));
        familyArrayList.add(new Word("daughter" ,"tune", R.drawable.family_daughter, R.raw.family_daughter));
        familyArrayList.add(new Word("older brother" ,"taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        familyArrayList.add(new Word("younger brother" ,"chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        familyArrayList.add(new Word("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        familyArrayList.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        familyArrayList.add(new Word("grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        familyArrayList.add(new Word("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), familyArrayList, R.color.category_family);  //ArrayAdapter

        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the {@link Word} object at the given position the user clicked on
                Word word = familyArrayList.get(i);
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
