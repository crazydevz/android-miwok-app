package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class PhrasesActivity extends AppCompatActivity {

//    private MediaPlayer mediaPlayer;
//    private AudioManager audioManager;
//    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener(){
//
//        @Override
//        public void onAudioFocusChange(int focusChange) {
//            if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
//                releaseMediaPlayer();
//            }
//            else if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
//                mediaPlayer.pause();
//                mediaPlayer.seekTo(0);
//            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
//                mediaPlayer.start();
//            }
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new PhrasesFragment()).commit();

//        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//
//        final ArrayList<Word> phrasesArrayList = new ArrayList<Word>();  //ArrayList
//
//        phrasesArrayList.add(new Word("Where are you going?", "minto wuksus", 0, R.raw.phrase_where_are_you_going));
//        phrasesArrayList.add(new Word("What is your name?", "tinnә oyaase'nә", 0, R.raw.phrase_what_is_your_name));
//        phrasesArrayList.add(new Word("My name is..." ,"oyaaset...", 0, R.raw.phrase_my_name_is));
//        phrasesArrayList.add(new Word("How are you feeling?" ,"michәksәs?", 0, R.raw.phrase_how_are_you_feeling));
//        phrasesArrayList.add(new Word("I’m feeling good." ,"kuchi achit", 0, R.raw.phrase_im_feeling_good));
//        phrasesArrayList.add(new Word("Are you coming?" ,"әәnәs'aa?", 0, R.raw.phrase_are_you_coming));
//        phrasesArrayList.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", 0, R.raw.phrase_yes_im_coming));
//        phrasesArrayList.add(new Word("I’m coming.", "әәnәm", 0, R.raw.phrase_im_coming));
//        phrasesArrayList.add(new Word("Let’s go.","yoowutis", 0, R.raw.phrase_lets_go));
//        phrasesArrayList.add(new Word("Come here.","әnni'nem", 0, R.raw.phrase_come_here));
//
//        WordAdapter itemsAdapter = new WordAdapter(this,phrasesArrayList, R.color.category_phrases);  //ArrayAdapter
//
//        ListView listView = (ListView) findViewById(R.id.list);
//        listView.setAdapter(itemsAdapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                // Get the {@link Word} object at the given position the user clicked on
//                Word word = phrasesArrayList.get(i);
//                /**
//                 * Clearing memory before the user presses a view to play the audio translation
//                 */
//                releaseMediaPlayer();
//
////                 Request audio focus for playback
//                int result = audioManager.requestAudioFocus(audioFocusChangeListener,
//                        // Use the music stream.
//                        AudioManager.STREAM_MUSIC,
//                        // Request permanent focus.
//                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
//
//                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
//                    //Audio focus acheived
//
//                    /**
//                     * Cretaing a new MediaPlayer obj of the audio file to be played
//                     */
//                    mediaPlayer = MediaPlayer.create(view.getContext(), word.getAudioResource());
//                    mediaPlayer.start();
//
//                    /**
//                     * Setting an onCompletionLitener to clear memory space when the audio file has finished playing
//                     */
//                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                        @Override
//                        public void onCompletion(MediaPlayer mediaPlayer) {
//                            mediaPlayer.release();
//                        }
//                    });
//                }
//            }
//        });
    }

//    private void releaseMediaPlayer(){
//        if(mediaPlayer != null)
//            mediaPlayer.release();
//        else
//            mediaPlayer = null;
//        audioManager.abandonAudioFocus(audioFocusChangeListener);
//    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        releaseMediaPlayer();
//    }
}
