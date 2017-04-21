package cache;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Created by dverbivskyi on 2017-04-21.
 */
class Category {

    private List<String> items;

    List<String> getItems() {
        return items;
    }

    void setItems(List<String> items) {
        this.items = ImmutableList.copyOf(items);
    }
}
