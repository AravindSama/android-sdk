/*
 * Copyright 2016, Optimizely
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.optimizely.ab.android.event_handler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.optimizely.ab.android.shared.ServiceScheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Reschedules event flushing after package updates and reboots
 *
 * After the app is updated or the phone is rebooted the event flushing
 * jobs scheduled by {@link ServiceScheduler} are cancelled.
 *
 * This code is called by the Android Framework.  The Intent Filters are registered
 * AndroidManifest.xml.
 */
public class EventRescheduler extends BroadcastReceiver {

    Logger logger = LoggerFactory.getLogger(EventRescheduler.class);

    @Override
    public void onReceive(Context context, Intent intent) {
        if ((context != null && intent != null) && (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED) ||
                intent.getAction().equals(Intent.ACTION_MY_PACKAGE_REPLACED))) {
            intent = new Intent(context, EventIntentService.class);
            context.startService(intent);
            logger.info("Rescheduling event flushing if necessary");
        } else {
            logger.warn("Received invalid broadcast to event rescheduler");
        }
    }
}
