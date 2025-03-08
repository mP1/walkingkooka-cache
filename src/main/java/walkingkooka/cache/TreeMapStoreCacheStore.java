/*
 * Copyright 2025 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package walkingkooka.cache;

import walkingkooka.store.Store;
import walkingkooka.store.Stores;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

final class TreeMapStoreCacheStore implements CacheStore {

    static TreeMapStoreCacheStore empty() {
        return new TreeMapStoreCacheStore();
    }

    private TreeMapStoreCacheStore() {
        this.store = Stores.treeMap(
            Comparator.naturalOrder(),
            TreeMapStoreCacheStore::idSetter
        );
    }

    private static CacheValue idSetter(final CacheKey id,
                                       final CacheValue value) {
        return value.setKey(id);
    }

    @Override 
    public Optional<CacheValue> load(final CacheKey cacheKey) {
        return this.store.load(cacheKey);
    }

    @Override
    public CacheValue save(final CacheValue cacheValue) {
        return this.store.save(cacheValue);
    }

    @Override
    public Runnable addSaveWatcher(final Consumer<CacheValue> watcher) {
        return this.store.addSaveWatcher(watcher);
    }

    @Override
    public void delete(final CacheKey cacheKey) {
        this.store.delete(cacheKey);
    }

    @Override
    public Runnable addDeleteWatcher(final Consumer<CacheKey> watcher) {
        return this.store.addDeleteWatcher(watcher);
    }

    @Override
    public int count() {
        return this.store.count();
    }

    @Override
    public Set<CacheKey> ids(final int offset,
                             final int count) {
        return this.store.ids(
            offset,
            count
        );
    }

    @Override
    public List<CacheValue> values(final int offset,
                                   final int count) {
        return this.store.values(
            offset,
            count
        );
    }

    @Override
    public List<CacheValue> between(final CacheKey from,
                                    final CacheKey to) {
        return this.store.between(
            from,
            to
        );
    }

    private final Store<CacheKey, CacheValue> store;

    @Override
    public String toString() {
        return this.store.toString();
    }
}
