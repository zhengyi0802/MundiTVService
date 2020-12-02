/*
 * Copyright (C) 2013 4th Line GmbH, Switzerland
 *
 * The contents of this file are subject to the terms of either the GNU
 * Lesser General Public License Version 2 or later ("LGPL") or the
 * Common Development and Distribution License Version 1 or later
 * ("CDDL") (collectively, the "License"). You may not use this file
 * except in compliance with the License. See LICENSE.txt for more
 * information.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 */

package org.fourthline.cling.support.renderingcontrol.callback;

import android.util.Log;

import org.fourthline.cling.controlpoint.ActionCallback;
import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;
import org.fourthline.cling.support.model.Channel;

import java.util.logging.Logger;

/**
 *
 * @author Christian Bauer
 */
public abstract class SetMute extends ActionCallback {

    private final static String TAG = SetMute.class.getSimpleName();

    public SetMute(Service service, boolean desiredMute) {
        this(new UnsignedIntegerFourBytes(0), service, desiredMute);
        Log.d(TAG, "SetMute()");
    }

    public SetMute(UnsignedIntegerFourBytes instanceId, Service service, boolean desiredMute) {
        super(new ActionInvocation(service.getAction("SetMute")));
        Log.d(TAG, "SetMute()");
        getActionInvocation().setInput("InstanceID", instanceId);
        getActionInvocation().setInput("Channel", Channel.Master.toString());
        getActionInvocation().setInput("DesiredMute", desiredMute);
    }

    @Override
    public void success(ActionInvocation invocation) {
        Log.d(TAG, "success()");

    }
}