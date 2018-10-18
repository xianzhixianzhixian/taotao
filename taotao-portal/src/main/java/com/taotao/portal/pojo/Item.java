package com.taotao.portal.pojo;

/**
 * solr查询数据的实体类
 * @author: xianzhixianzhixian on 2018/10/15
 */
public class Item {

    private String id; /* 商品id */
    private String title; /* 商品标题 */
    private String sell_point; /* 商品卖点 */
    private Long price; /* 商品价格 */
    private String image; /* 商品图片链接 */
    private String category_name; /* 商品分类名称 */
    private String item_des; /* 商品描述 */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSell_point() {
        return sell_point;
    }

    public void setSell_point(String sell_point) {
        this.sell_point = sell_point;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getItem_des() {
        return item_des;
    }

    public void setItem_des(String item_des) {
        this.item_des = item_des;
    }

    public String[] getImages() {
        if (image!=null && image.trim().length()!=0){
            String[] images = image.split(",");
            return images;
        }
        return null;
    }

}
