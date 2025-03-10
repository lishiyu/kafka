/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.kafka.streams.processor.internals;

import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.PunctuationType;
import org.apache.kafka.streams.processor.Punctuator;
import org.apache.kafka.streams.processor.StateStoreContext;
import org.apache.kafka.streams.processor.To;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Duration;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class StoreToProcessorContextAdapterTest {
    @Mock
    private StateStoreContext delegate;
    private ProcessorContext context;
    @Mock
    private Punctuator punctuator;

    @Before
    public void setUp() {
        context = StoreToProcessorContextAdapter.adapt(delegate);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowOnCurrentSystemTime() {
        context.currentSystemTimeMs();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowOnCurrentStreamTime() {
        context.currentStreamTimeMs();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowOnGetStateStore() {
        context.getStateStore("store");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowOnScheduleWithDuration() {
        context.schedule(Duration.ZERO, PunctuationType.WALL_CLOCK_TIME, punctuator);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowOnForward() {
        context.forward("key", "value");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowOnForwardWithTo() {
        context.forward("key", "value", To.all());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowOnCommit() {
        context.commit();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowOnTopic() {
        context.topic();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowOnPartition() {
        context.partition();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowOnOffset() {
        context.offset();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowOnHeaders() {
        context.headers();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowOnTimestamp() {
        context.timestamp();
    }
}
