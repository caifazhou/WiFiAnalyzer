/*
 *    Copyright (C) 2010 - 2015 VREM Software Development <VREMSoftwareDevelopment@gmail.com>
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
package com.vrem.wifianalyzer;

import java.util.ArrayList;
import java.util.List;

enum Security {
    // weak security first - keep this order
    NONE(R.drawable.unlock),
    WEP(R.drawable.brokenlock),
    WPS(R.drawable.brokenlock),
    WPA(R.drawable.lock),
    WPA2(R.drawable.lock);

    private final int imageResource;

    Security(int imageResource) {
        this.imageResource = imageResource;
    }

    int getImageResource() {
        return imageResource;
    }
    private static List<Security> findSecurities(String capabilities) {
        List<Security> results = new ArrayList<>();
        if (capabilities != null) {
            String[] values = capabilities.toUpperCase().replace("][", "-").replace("]", "").replace("[", "").split("-");
            for (String value: values) {
                try {
                    results.add(Security.valueOf(value));
                } catch (Exception e) {
                    // skip capabilities that are not security
                }
            }
        }
        return results;
    }

    static Security findOne(String capabilities) {
        List<Security> securities = findSecurities(capabilities);
        for (Security security: Security.values()) {
            if (securities.contains(security)) {
                return security;
            }
        }
        return Security.NONE;
    }

    static String findAll(String capabilities) {
        StringBuilder result = new StringBuilder();
        for (Security current: findSecurities(capabilities)) {
            result.append(current.name());
            result.append(" ");
        }
        return result.toString();
    }
}