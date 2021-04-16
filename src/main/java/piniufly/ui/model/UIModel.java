package piniufly.ui.model;

import java.util.List;

public class UIModel {

    private List<Entry> entryList;

    public UIModel(List<Entry> entryList){
        this.entryList = entryList;
    }

    public List<Entry> getEntryList() {
    return entryList;
    }
}
