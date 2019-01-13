/*
 *  Copyright 2017 Google Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example.mudassirkhan.crowdzr.api.eventmessages.login;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.example.mudassirkhan.crowdzr.api.eventmessages.EventMessage;
import com.example.mudassirkhan.crowdzr.api.eventmessages.SingleLiveEvent;


/**
 * A SingleLiveEvent used for prompt messages. Like a {@link SingleLiveEvent} but also prevents
 * null messages and uses a custom observer.
 * <p>
 * Note that only one observer is going to be notified of changes.
 */
public class ValidationLiveEvent extends SingleLiveEvent<EventMessage> {

    public void observe(LifecycleOwner owner, final ValidationObserver observer) {
        super.observe(owner, new Observer<EventMessage>() {
            @Override
            public void onChanged(@Nullable EventMessage t) {
                if (t == null) {
                    return;
                }
                if(t.isValidUser()) {
                    observer.onInvalidUser(t.getMessage());
                }
                else if(t.isValidPassword()){
                    observer.onInvalidPassword(t.getMessage());
                }
            }
        });
    }

    public interface ValidationObserver<String> {
        void onInvalidUser(String message);
        void onInvalidPassword(String message);
    }
}
