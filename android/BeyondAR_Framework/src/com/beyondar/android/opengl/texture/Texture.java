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
package com.beyondar.android.opengl.texture;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Texture implements Serializable {

	public static float TEMPLATE_TEXTURE[] = {
			// Mapping coordinates for the vertices
			0.0f, 1.0f, // top left (V2)
			0.0f, 0.0f, // bottom left (V1)
			1.0f, 1.0f, // top right (V4)
			1.0f, 0.0f // bottom right (V3)
	};

	// buffer holding the texture coordinates
	private FloatBuffer mTextureBuffer;

	private static final long serialVersionUID = -3680867097568273278L;

	private int mWidth, mHeight;
	private float mWidthRate, mHeightRate;
	private int mTexture;
	private boolean mIsLoaded;
	private double mTimeStamp;
	private int mCounterLoaded;
	private float[] mTextureMap = new float[TEMPLATE_TEXTURE.length];

	public Texture(int textureReference) {
		mTexture = textureReference;
		mIsLoaded = true;
		mCounterLoaded = 0;
		System.arraycopy(TEMPLATE_TEXTURE, 0, mTextureMap, 0, mTextureMap.length);
	}

	public Texture setImageSize(int width, int height) {
		mWidth = width;
		mHeight = height;
		calculateImageSizeRate();
		return this;
	}

	private void calculateImageSizeRate() {
		if (mWidth < mHeight) {
			mWidthRate = ((float) mWidth / (float) mHeight);
			mHeightRate = 1;
		} else {
			mHeightRate = ((float) mHeight / (float) mWidth);
			mWidthRate = 1;
		}

//		for (int i = 0; i < mTextureMap.length; i++) {
//			if (i % 2 == 0) {
//				mTextureMap[i] = mTextureMap[i] * mWidthRate;
//			} else {
//				mTextureMap[i] = mTextureMap[i] * mHeightRate;
//			}
//		}

		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(mTextureMap.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		mTextureBuffer = byteBuffer.asFloatBuffer();
		mTextureBuffer.put(mTextureMap);
		mTextureBuffer.position(0);

	}

	public FloatBuffer getTextureBuffer() {
		return mTextureBuffer;
	}

	public float[] getTextureMap() {
		return mTextureMap;
	}

	public float getWithRate() {
		return mWidthRate;
	}

	public float getHeightRate() {
		return mHeightRate;
	}

	public int getImageWidth() {
		return mWidth;
	}

	public int getImageHeight() {
		return mHeight;
	}

	public Texture() {
		mIsLoaded = false;
	}

	public int getTexturePointer() {
		return mTexture;
	}

	public Texture setTexturePointer(int texture) {
		mTexture = texture;
		mIsLoaded = true;
		mCounterLoaded = 0;
		return this;
	}

	public Texture setLoaded(boolean isLoaded) {
		mIsLoaded = isLoaded;
		return this;
	}

	public boolean isLoaded() {
		return mIsLoaded;
	}

	public Texture setTimeStamp(double time) {
		mTimeStamp = time;
		return this;
	}

	public double getTimeStamp() {
		return mTimeStamp;
	}

	public Texture setLoadTryCounter(int counter) {
		mCounterLoaded = counter;
		return this;
	}

	public int getLoadTryCounter() {
		return mCounterLoaded;
	}

	public Texture clone() {
		Texture clone = new Texture();
		return clone.setLoaded(isLoaded()).setTexturePointer(getTexturePointer())
				.setTimeStamp(getTimeStamp()).setLoadTryCounter(getLoadTryCounter())
				.setImageSize(mWidth, mHeight);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("loaded: ");
		sb.append(isLoaded());
		sb.append("    pointer: ");
		sb.append(getTexturePointer());
		sb.append("    timestamp: ");
		sb.append(getTimeStamp());
		return sb.toString();
	}

}
