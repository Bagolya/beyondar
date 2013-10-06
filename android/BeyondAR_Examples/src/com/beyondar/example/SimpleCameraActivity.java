/*
 * Copyright (C) 2013 BeyondAR
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beyondar.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;

import com.beyondar.android.view.BeyondarGLSurfaceView;
import com.beyondar.android.view.BeyondarGLSurfaceView.OnARTouchListener;
import com.beyondar.android.view.BeyondarView;
import com.beyondar.android.world.World;

public class SimpleCameraActivity extends Activity implements OnARTouchListener {

	private BeyondarView mBeyondarView;
	private World mWorld;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Hide the window title.
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.simple_camera);
		mBeyondarView = (BeyondarView) findViewById(R.id.beyondarView);

		// We create the world...
		mWorld = new World(this);
		// ... fill it ...
		WorldHelper.generateObjects(mWorld);
		// .. and send it to the view
		mBeyondarView.setWorld(mWorld);
		
		
		mBeyondarView.setOnARTouchListener(this);
		
		// We can see the Frames per seconds
		mBeyondarView.showFPS(true);

	}

	@Override
	protected void onResume() {
		super.onResume();
		// Every time that the activity is resumed we need to notify the BeyondarView
		mBeyondarView.resume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// Every time that the activity is paused we need to notify the BeyondarView
		mBeyondarView.pause();
	}

	@Override
	public void onTouchARView(MotionEvent event, BeyondarGLSurfaceView beyondarView) {
		
	}

}
