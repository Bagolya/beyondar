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

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.beyondar.android.world.WorldGoogleMapModule;
import com.beyondar.android.world.World;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;

public class GoogleMapActivity extends FragmentActivity implements OnMarkerClickListener {

	private GoogleMap map;
	private WorldGoogleMapModule mGoogleMapModule;
	private World mWorld;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_google);

		map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

		// We create the world...
		mWorld = new World(this);

		// As we want to use GoogleMaps, we are going to create the module and
		// attach it to the World
		mGoogleMapModule = new WorldGoogleMapModule();
		// Then we need to set the map in to the GoogleMapModule
		mGoogleMapModule.setGoogleMap(map);
		// Now that we have the module created let's add it in to our world
		mWorld.addModule(mGoogleMapModule);

		// Now we fill the world
		WorldHelper.generateObjects(mWorld);

		map.setOnMarkerClickListener(this);

		map.moveCamera(CameraUpdateFactory.newLatLngZoom(mGoogleMapModule.getLatLng(), 15));
		map.animateCamera(CameraUpdateFactory.zoomTo(19), 2000, null);
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		// TODO Auto-generated method stub
		return false;
	}
}
