package app.beedle.pocketreview.model;

import java.util.List;

/**
 * Created by Beedle on 19/11/2560.
 */

public class NoteEntityDetail {
    public NoteEntityDetail() {

    }

    private List<String> detailList;
    private List<Float> priceList;
    private List<String> detailAll;

    public List<String> getDetailAll() {
        return detailAll;
    }

    public void setDetailAll(List<String> detailAll) {
        this.detailAll = detailAll;
    }

    public List<String> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<String> detailList) {
        this.detailList = detailList;
    }

    public List<Float> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Float> priceList) {
        this.priceList = priceList;
    }

    public void setList(String detailList, Float priceList) {
        detailAll.add(detailList + " " + priceList);
    }
}
