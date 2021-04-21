package piniufly.ui.model;

import java.util.List;
import java.util.Map;

public class UIModel {

    private Map<String, List<Entry>> entryList;

    public UIModel(Map<String, List<Entry>> entryList) {
        this.entryList = entryList;
    }


    public void setEntryList(Map<String, List<Entry>> entryList) {
        this.entryList = entryList;
    }

    public Map<String, List<Entry>> getEntryList() {
        return entryList;
    }
}
