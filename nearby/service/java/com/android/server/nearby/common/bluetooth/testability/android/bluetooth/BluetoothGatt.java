/*
 * Copyright 2021 The Android Open Source Project
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

package com.android.server.nearby.common.bluetooth.testability.android.bluetooth;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.os.Build;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

/** Mockable wrapper of {@link android.bluetooth.BluetoothGatt}. */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class BluetoothGatt {

    /** See {@link android.bluetooth.BluetoothGatt#STATE_CONNECTED}. */
    public static final int STATE_CONNECTED = android.bluetooth.BluetoothGatt.STATE_CONNECTED;

    /** See {@link android.bluetooth.BluetoothGatt#STATE_DISCONNECTED}. */
    public static final int STATE_DISCONNECTED = android.bluetooth.BluetoothGatt.STATE_DISCONNECTED;

    /** See {@link android.bluetooth.BluetoothGatt#GATT_REQUEST_NOT_SUPPORTED}. */
    public static final int GATT_REQUEST_NOT_SUPPORTED =
            android.bluetooth.BluetoothGatt.GATT_REQUEST_NOT_SUPPORTED;

    /** See {@link android.bluetooth.BluetoothGatt#GATT_SUCCESS}. */
    public static final int GATT_SUCCESS = android.bluetooth.BluetoothGatt.GATT_SUCCESS;

    /** See {@link android.bluetooth.BluetoothGatt#GATT_FAILURE}. */
    public static final int GATT_FAILURE = android.bluetooth.BluetoothGatt.GATT_FAILURE;

    /** See {@link android.bluetooth.BluetoothGatt#CONNECTION_PRIORITY_BALANCED}. */
    public static final int CONNECTION_PRIORITY_BALANCED =
            android.bluetooth.BluetoothGatt.CONNECTION_PRIORITY_BALANCED;

    /** See {@link android.bluetooth.BluetoothGatt#CONNECTION_PRIORITY_HIGH}. */
    public static final int CONNECTION_PRIORITY_HIGH =
            android.bluetooth.BluetoothGatt.CONNECTION_PRIORITY_HIGH;

    /** See {@link android.bluetooth.BluetoothGatt#CONNECTION_PRIORITY_LOW_POWER}. */
    public static final int CONNECTION_PRIORITY_LOW_POWER =
            android.bluetooth.BluetoothGatt.CONNECTION_PRIORITY_LOW_POWER;

    private final android.bluetooth.BluetoothGatt mWrappedBluetoothGatt;

    private BluetoothGatt(android.bluetooth.BluetoothGatt bluetoothGatt) {
        mWrappedBluetoothGatt = bluetoothGatt;
    }

    /** See {@link android.bluetooth.BluetoothGatt#getDevice()}. */
    public BluetoothDevice getDevice() {
        return BluetoothDevice.wrap(mWrappedBluetoothGatt.getDevice());
    }

    /** See {@link android.bluetooth.BluetoothGatt#getServices()}. */
    public List<BluetoothGattService> getServices() {
        return mWrappedBluetoothGatt.getServices();
    }

    /** See {@link android.bluetooth.BluetoothGatt#getService(UUID)}. */
    @Nullable(/* null if service is not found */)
    public BluetoothGattService getService(UUID uuid) {
        return mWrappedBluetoothGatt.getService(uuid);
    }

    /** See {@link android.bluetooth.BluetoothGatt#discoverServices()}. */
    public boolean discoverServices() {
        return mWrappedBluetoothGatt.discoverServices();
    }

    /**
     * Hidden method. Clears the internal cache and forces a refresh of the services from the remote
     * device.
     */
    // TODO(b/201300471): remove refresh call using reflection.
    public boolean refresh() {
        try {
            Method refreshMethod = android.bluetooth.BluetoothGatt.class.getMethod("refresh");
            return (Boolean) refreshMethod.invoke(mWrappedBluetoothGatt);
        } catch (NoSuchMethodException
            | IllegalAccessException
            | IllegalArgumentException
            | InvocationTargetException e) {
            return false;
        }
    }

    /**
     * See {@link android.bluetooth.BluetoothGatt#readCharacteristic(BluetoothGattCharacteristic)}.
     */
    public boolean readCharacteristic(BluetoothGattCharacteristic characteristic) {
        return mWrappedBluetoothGatt.readCharacteristic(characteristic);
    }

    /**
     * See {@link android.bluetooth.BluetoothGatt#writeCharacteristic(BluetoothGattCharacteristic)}.
     */
    public boolean writeCharacteristic(BluetoothGattCharacteristic characteristic) {
        return mWrappedBluetoothGatt.writeCharacteristic(characteristic);
    }

    /** See {@link android.bluetooth.BluetoothGatt#readDescriptor(BluetoothGattDescriptor)}. */
    public boolean readDescriptor(BluetoothGattDescriptor descriptor) {
        return mWrappedBluetoothGatt.readDescriptor(descriptor);
    }

    /** See {@link android.bluetooth.BluetoothGatt#writeDescriptor(BluetoothGattDescriptor)}. */
    public boolean writeDescriptor(BluetoothGattDescriptor descriptor) {
        return mWrappedBluetoothGatt.writeDescriptor(descriptor);
    }

    /** See {@link android.bluetooth.BluetoothGatt#readRemoteRssi()}. */
    public boolean readRemoteRssi() {
        return mWrappedBluetoothGatt.readRemoteRssi();
    }

    /** See {@link android.bluetooth.BluetoothGatt#requestConnectionPriority(int)}. */
    public boolean requestConnectionPriority(int connectionPriority) {
        return mWrappedBluetoothGatt.requestConnectionPriority(connectionPriority);
    }

    /** See {@link android.bluetooth.BluetoothGatt#requestMtu(int)}. */
    public boolean requestMtu(int mtu) {
        return mWrappedBluetoothGatt.requestMtu(mtu);
    }

    /** See {@link android.bluetooth.BluetoothGatt#setCharacteristicNotification}. */
    public boolean setCharacteristicNotification(
            BluetoothGattCharacteristic characteristic, boolean enable) {
        return mWrappedBluetoothGatt.setCharacteristicNotification(characteristic, enable);
    }

    /** See {@link android.bluetooth.BluetoothGatt#disconnect()}. */
    public void disconnect() {
        mWrappedBluetoothGatt.disconnect();
    }

    /** See {@link android.bluetooth.BluetoothGatt#close()}. */
    public void close() {
        mWrappedBluetoothGatt.close();
    }

    /** See {@link android.bluetooth.BluetoothGatt#hashCode()}. */
    @Override
    public int hashCode() {
        return mWrappedBluetoothGatt.hashCode();
    }

    /** See {@link android.bluetooth.BluetoothGatt#equals(Object)}. */
    @Override
    public boolean equals(@Nullable Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BluetoothGatt)) {
            return false;
        }
        return mWrappedBluetoothGatt.equals(((BluetoothGatt) o).unwrap());
    }

    /** Unwraps a Bluetooth Gatt instance. */
    public android.bluetooth.BluetoothGatt unwrap() {
        return mWrappedBluetoothGatt;
    }

    /** Wraps a Bluetooth Gatt instance. */
    public static BluetoothGatt wrap(android.bluetooth.BluetoothGatt gatt) {
        return new BluetoothGatt(gatt);
    }
}
