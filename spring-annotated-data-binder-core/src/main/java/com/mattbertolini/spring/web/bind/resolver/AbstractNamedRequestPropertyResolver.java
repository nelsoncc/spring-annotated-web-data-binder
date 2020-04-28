/*
 * Copyright 2019-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mattbertolini.spring.web.bind.resolver;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.lang.NonNull;

public abstract class AbstractNamedRequestPropertyResolver<T, R> implements RequestPropertyResolverBase<T, R> {
    @NonNull
    protected abstract String getName(@NonNull TypeDescriptor typeDescriptor);

    @Override
    public final R resolve(@NonNull TypeDescriptor typeDescriptor, @NonNull T request) {
        String name = getName(typeDescriptor);
        return resolveWithName(typeDescriptor, name, request);
    }

    protected abstract R resolveWithName(@NonNull TypeDescriptor typeDescriptor, String name, @NonNull T request);
}
