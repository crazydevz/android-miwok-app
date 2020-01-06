package com.example.android.miwok;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MR.Laptop Point on 6/29/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int colorResourceId;
    private static MediaPlayer mediaPlayer;
    private AudioManager mAudioManager;

    public WordAdapter(Activity context, ArrayList<Word> arrayListWord, int colorResourceId) {
        super(context, 0, arrayListWord);
        this.colorResourceId = colorResourceId;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

//        return super.getView(position, convertView, parent);

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate( R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        final Word currentTranslation = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_translation
        TextView defaultTranslationTextView = (TextView) listItemView.findViewById(R.id.miwok_translation);
        // Get the version name from the current currentTranslation object and
        // set this text on the default Translation TextView
        defaultTranslationTextView.setText(currentTranslation.getmMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID default_translation
        TextView miwokTranslationTextView = (TextView) listItemView.findViewById(R.id.default_translation);
        // Get the default translation from the currentTranslation object and
        // set this text on the miwok Translation TextView
        miwokTranslationTextView.setText(currentTranslation.getmDefaultTranslation());

        // Find the ImageView in the list_item.xml layout with the ID image
        ImageView image = (ImageView) listItemView.findViewById(R.id.image);
        // Get the resource id from the currentTranslation object and
        // set this image on the image
        if(currentTranslation.getImageResourceId() != 0) {
            image.setImageResource(currentTranslation.getImageResourceId());
        }
        else
            image.setVisibility(View.GONE);

        LinearLayout textContainer = (LinearLayout) listItemView.findViewById(R.id.text_holder);
        int color = ContextCompat.getColor(getContext(), colorResourceId);
        textContainer.setBackgroundColor(color);

//        LinearLayout activity = (LinearLayout) textContainer.findViewById(R.id.text_holder);
//        activity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                /**
//                 * Clearing memory before the user presses a view to play the audio translation
//                 */
//                releaseMediaPlayer();
//
////                 Request audio focus for playback
//                int result = mAudioManager.requestAudioFocus(afChangeListener,
//                        // Use the music stream.
//                        AudioManager.STREAM_MUSIC,
//                        // Request permanent focus.
//                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
//
//                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
//                    // Start playback
//                }
//
//                /**
//                 * Cretaing a new MediaPlayer obj of the audio file to be played
//                 */
//                mediaPlayer = MediaPlayer.create(view.getContext(), currentTranslation.getAudioResource());
//                mediaPlayer.start();
//
//                /**
//                 * Setting an onCompletionLitener to clear memory space when the audio file has finished playing
//                 */
//                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mediaPlayer) {
//                        mediaPlayer.release();
//                    }
//                });
//            }
//        });

        return listItemView;
    }

//    public static MediaPlayer getMediaPlayer(){
//        if(mediaPlayer != null)
//            return mediaPlayer;
//        else
//            return null;
//    }

//    private void releaseMediaPlayer(){
//        if(mediaPlayer != null)
//            mediaPlayer.release();
//        else
//            mediaPlayer = null;
//    }
}
