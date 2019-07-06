package pojo;

import com.pinyougou.pojo.TbGoods;
import com.pinyougou.pojo.TbGoodsDesc;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.pojo.TbTypeTemplate;

import java.io.Serializable;
import java.util.List;

public class Goods implements Serializable {
    private TbGoods goods;
    private TbGoodsDesc goodsDesc;
    private List<TbItem> itemList;
    private TbTypeTemplate typeTemplates;

    public Goods() {
    }

    public Goods(TbGoods goods, TbGoodsDesc goodsDesc, List<TbItem> itemList, TbTypeTemplate typeTemplates) {
        this.goods = goods;
        this.goodsDesc = goodsDesc;
        this.itemList = itemList;
        this.typeTemplates = typeTemplates;
    }

    public TbGoods getGoods() {
        return goods;
    }

    public void setGoods(TbGoods goods) {
        this.goods = goods;
    }

    public TbGoodsDesc getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(TbGoodsDesc goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public List<TbItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<TbItem> itemList) {
        this.itemList = itemList;
    }

    public TbTypeTemplate getTypeTemplates() {
        return typeTemplates;
    }

    public void setTypeTemplates(TbTypeTemplate typeTemplates) {
        this.typeTemplates = typeTemplates;
    }
}
