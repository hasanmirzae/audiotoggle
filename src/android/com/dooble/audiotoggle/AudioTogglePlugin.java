package com.dooble.audiotoggle;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.media.AudioManager;

public class AudioTogglePlugin extends CordovaPlugin {
	public static final String ACTION_SET_AUDIO_MODE = "setAudioMode";
	public static final String ACTION_GET_AUDIO_MODE = "getAudioMode";
	public static final String ACTION_SET_AUDIO_MODE_ID = "setAudioModeById";

	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		if (action.equals(ACTION_GET_AUDIO_MODE)){
			callbackContext.success(String.valueOf(getAudioMode()));
			return true;
		}else if (action.equals(ACTION_SET_AUDIO_MODE)){
			setAudioMode(args.getString(0));
			return true;
		}else if(action.equals(ACTION_SET_AUDIO_MODE_ID)){
			setAudioModeById(Integer.valueOf(args.getString(0)));
			return true;
		}else{
			callbackContext.error("Invalid audio mode");
			return false;
		}
	}

	public boolean setAudioMode(String mode) {
	    Context context = webView.getContext();
	    AudioManager audioManager =
	    	(AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

	    if (mode.equals("earpiece")) {
	    	audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
	    	audioManager.setSpeakerphoneOn(false);
	        return true;
	    } else if (mode.equals("speaker")) {
	    	audioManager.setMode(AudioManager.STREAM_MUSIC);
	    	audioManager.setSpeakerphoneOn(true);
	        return true;
	    } else if (mode.equals("ringtone")) {
	    	audioManager.setMode(AudioManager.MODE_RINGTONE);
	    	audioManager.setSpeakerphoneOn(false);
	        return true;
	    } else if (mode.equals("normal")) {
	    	audioManager.setMode(AudioManager.MODE_NORMAL);
	    	audioManager.setSpeakerphoneOn(false);
	        return true;
	    }

	    return false;
	}

	public int getAudioMode(){
		Context context = webView.getContext();
		AudioManager audioManager =
			(AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		return audioManager.getMode();
	}

	public void setAudioModeById(int mode){
		Context context = webView.getContext();
		AudioManager audioManager =
			(AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		audioManager.setMode(mode);
	}

}
