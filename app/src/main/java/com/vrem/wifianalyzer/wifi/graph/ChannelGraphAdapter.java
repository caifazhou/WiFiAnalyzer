/*
 *    Copyright (C) 2015 - 2016 VREM Software Development <VREMSoftwareDevelopment@gmail.com>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.vrem.wifianalyzer.wifi.graph;

import android.support.annotation.NonNull;

import com.vrem.wifianalyzer.MainContext;
import com.vrem.wifianalyzer.wifi.model.WiFiData;
import com.vrem.wifianalyzer.wifi.scanner.UpdateNotifier;

class ChannelGraphAdapter implements UpdateNotifier {
    private final MainContext mainContext = MainContext.INSTANCE;
    private final ChannelGraphView[] channelGraphViews;

    ChannelGraphAdapter(@NonNull ChannelGraphView... channelGraphViews) {
        this.channelGraphViews = channelGraphViews;
        this.mainContext.getScanner().addUpdateNotifier(this);
    }

    @Override
    public void update(@NonNull WiFiData wiFiData) {
        for (ChannelGraphView channelGraphView : channelGraphViews) {
            channelGraphView.update(wiFiData);
        }
    }

}